package cn.edu.xupt.help.goods;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cn.edu.xupt.help.framework.common"})//common
@ComponentScan(basePackages = {"cn.edu.xupt.help.goods"})//本类
@MapperScan({"cn.edu.xupt.help.goods.mapper"})//mapper扫描
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }
}
