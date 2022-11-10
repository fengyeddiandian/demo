package com.example.demo.common.cornutil;

import com.example.demo.common.datautil.DateUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.TimerTask;

/**
 * @ClassName TimerTest
 * @Author yu.zhang
 * @Description
 * @Date 2021/7/19 17:03
 **/
@Slf4j
public class TimerTest extends TimerTask {
    @Override
    public void run() {
        test();
    }

    public void test(){
        Date nextExecution = CronUtils.getNextExecution("0/5 * * * * ? *");
        String invalidMessage = CronUtils.getInvalidMessage("0/5 * * * * ? *");
        boolean valid = CronUtils.isValid("0/5 * * * * ? *");
        String format = DateUtils.format(nextExecution);
        log.info(format+invalidMessage+valid);
    }

}
