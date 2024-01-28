package com.mdms.yiliao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mdms.yiliao.mapper")
public class YiliaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiliaoApplication.class, args);
    }

}
