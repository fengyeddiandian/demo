package com.example.demoweb;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = "com.example")
@MapperScan(basePackages = "com.example")
@EnableDiscoveryClient
@Slf4j
public class DemoWebApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoWebApplication.class, args);
    log.info("启动成功");
  }
}
