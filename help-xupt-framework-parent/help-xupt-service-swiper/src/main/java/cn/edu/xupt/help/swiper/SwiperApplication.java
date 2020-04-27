package cn.edu.xupt.help.swiper;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.edu.xupt.help.api"})//api
@ComponentScan(basePackages = {"cn.edu.xupt.help.framework"})//common
@ComponentScan(basePackages = {"cn.edu.xupt.help.swiper"})//本类
@MapperScan({"cn.edu.xupt.help.swiper.mapper"})//mapper
public class SwiperApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwiperApplication.class, args);
    }
}
