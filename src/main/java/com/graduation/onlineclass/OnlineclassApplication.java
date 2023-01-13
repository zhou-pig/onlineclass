package com.graduation.onlineclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2//开启swagger2
public class OnlineclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineclassApplication.class, args);
    }

}
