package com.graduation.onlineclass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.graduation.onlineclass.mapper")
@EnableSwagger2//开启swagger2
public class OnlineclassApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineclassApplication.class, args);
    }

}
