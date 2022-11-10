package com.example.demo.common.csvutil;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName TestCSVController
 * @Author yu.zhang
 * @Description
 * @Date 2022/8/15 15:55
 **/
@RestController
@RequestMapping("/csv")
public class TestCSVController {

    @Resource(name = "TestCSVServiceImpl")
    public TestCSVService testCSVService;


    @RequestMapping("/insert")
    public void insert(String path){


        testCSVService.insert(path);

    }


}
