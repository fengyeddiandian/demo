package com.example.demo.common.log;

/**
 * @ClassName LoggerTest
 * @Author yu.zhang
 * @Description
 * @Date 2021/10/18 17:15
 **/
public class LoggerTest {
    public static void main(String[] args) {
        org.slf4j.Logger logger1 = Logger.createLogger("ems");
       // org.slf4j.Logger logger2 = Logger.createLogger("ems1");
       // org.slf4j.Logger logger3 = Logger.createLogger("ems2");
        for(int i=0;i<50000;i++) {
            logger1.info("Testing testing testing 111--"+i);
            logger1.debug("Testing testing testing 222--"+i);
            logger1.error("Testing testing testing 333--"+i);

        }
    }
}
