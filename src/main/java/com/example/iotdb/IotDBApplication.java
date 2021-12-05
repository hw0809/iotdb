package com.example.iotdb;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { MybatisAutoConfiguration.class/*, DataSourceAutoConfiguration.class*/})
@ComponentScan(basePackages = "com.example.iotdb.*")
public class IotDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotDBApplication.class, args);
    }

}
