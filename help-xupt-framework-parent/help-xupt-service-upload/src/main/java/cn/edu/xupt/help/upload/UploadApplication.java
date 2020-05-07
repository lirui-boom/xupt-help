package cn.edu.xupt.help.upload;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.edu.xupt.help.framework.common.config.swagger"})//swagger
@ComponentScan(basePackages = {"cn.edu.xupt.help.framework.common.exception"})//exception
@ComponentScan(basePackages = {"cn.edu.xupt.help.framework.common.model"})//model
@ComponentScan(basePackages = {"cn.edu.xupt.help.upload"})//本类
public class UploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }
}
