package com.tjj.zj.tjjwork;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.tjj.zj.tjjwork.mapper")
@ServletComponentScan
public class OrderWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderWorkApplication.class, args);
    }

}
