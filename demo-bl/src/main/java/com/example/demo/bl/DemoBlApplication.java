package com.example.demo.bl;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author Lenovo
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"com.example","com.zznode"})
@MapperScan(basePackages ={"com.example","com.zznode"})
@Slf4j
@EnableDiscoveryClient
public class DemoBlApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoBlApplication.class, args);
        log.info("启动完成！");
    }
}
