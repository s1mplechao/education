package com.mz.stu;


import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.mz.stu",exclude = SecurityAutoConfiguration.class)
@MapperScan("com.mz.stu.mapper")
public class StuEduApp {
    public static void main(String[] args) {
        SpringApplication.run(StuEduApp.class,args);

    }
}
