package cn.edu.xupt.help.comment;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.edu.xupt.help.framework.common"})//common
@ComponentScan(basePackages = {"cn.edu.xupt.help.comment"})//本类
@MapperScan({"cn.edu.xupt.help.comment.mapper"})//mapper扫描
public class CommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommentApplication.class, args);
    }
}
