package com.example.essay.controller;

import com.example.essay.domain.FileDetail;
import com.example.essay.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/upload", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UploadController {
    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<FileDetail> post(@RequestPart("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(fileUploadService.save(multipartFile));
    }
}