package com.example.demo.common.classDuiBi;

import com.example.demo.common.datautil.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName TestClassDuiBi
 * @Author yu.zhang
 * @Description
 * @Date 2021/10/15 11:17
 **/
public class TestClassDuiBi {

    public static void main(String[] args) throws ParseException {

//        Me me =new Me();
//        me.setAdditionalinfo("123");
//        me.setAssetnumber("456");
//
//        Me me1 =new Me();
//        me1.setAdditionalinfo("123");
//        me1.setAssetnumber("ME!456");
//        List<String> list = new ArrayList<>();
//        list.add("additionalinfo");
//        list.add("map");
//        boolean b = ClassCompareUtil.compareObject(me, me1);
//        boolean c = ClassCompareUtil.compareObject(me, me1,list);
//    System.out.println(b+"---"+c);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN);

        String time1="2019-08-03 20:32:07";
        String time2="2021-07-31 18:04:18";
        Date parse = simpleDateFormat.parse(time1);
        Date parse2 = simpleDateFormat.parse(time2);
        long l = parse.getTime() - parse2.getTime();
        System.out.println(l);


    }

}
