package com.graduation.onlineclass.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/*
   该配置类用于项目开启后自动调用浏览器并且调用对应的命令
 */
@Configuration
public class StartConfig implements CommandLineRunner {

    @Value("${loginurl}")
    private String loginUrl;
    @Value("${edgepath}")
    private String edgepath;

    @Override
    public void run(String... args) throws Exception {

        Runtime runtime = Runtime.getRuntime();
        String cmd = edgepath + " " + loginUrl;
        runtime.exec(cmd);

    }
}
