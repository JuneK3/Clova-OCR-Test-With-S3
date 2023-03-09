package com.example.essay.config;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.essay.util.MultipartUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
@RequiredArgsConstructor
public class AmazonS3ResourceStorage {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // aws-auto-configure에 의해 AmazonS3Client가 자동 생성되어 주입됨.
    private final AmazonS3Client amazonS3Client;

    /*
     * local 저장소에 파일을 저장한 후 s3에 업로드하는 방식
     * file의 inputStream을 이용해 바로 s3에 업로드하는 것도 가능함
     */
    public void store(String fullPath, MultipartFile multipartFile) {
        File file = new File(MultipartUtil.getLocalHomeDirectory(), fullPath);
        try {
            multipartFile.transferTo(file); // local 저장소에 일단 저장
            amazonS3Client.putObject(new PutObjectRequest(bucket, fullPath, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            throw new RuntimeException("이미지 업로드 실패");
        } finally {
            if (file.exists()) {
                file.delete(); // local 저장소에 있는 파일 삭제
            }
        }
    }
}