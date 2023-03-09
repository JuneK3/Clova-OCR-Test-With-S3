package com.example.essay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EssayApplication {

    /*
     * 환경변수로 설정하는 것도 가능
     * 출처: https://antdev.tistory.com/93
     */
    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    public static void main(String[] args) {
        SpringApplication.run(EssayApplication.class, args);
    }

}