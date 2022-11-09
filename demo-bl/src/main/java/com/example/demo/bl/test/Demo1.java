package com.example.demo.bl.test;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.xmlutil.Me;
import com.example.demo.common.xmlutil.Me1;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName Demo1
 * @Author yu.zhang
 * @Description
 * @Date 2021/7/28 20:40
 **/
@Slf4j
public class Demo1 {
    public Demo1() {
        super();
    }

    @SneakyThrows
    public static void main(String[] args) {

        //extracted();
      //
        //  testTime();


//        Class cache = Integer.class.getDeclaredClasses()[0];
//        Field c = cache.getDeclaredField("cache");
//        c.setAccessible(true);
//        Integer[] array = (Integer[]) c.get(cache);
//        // array[129] is 1
//        array[130] = array[129];
//        // Set 2 to be 1
//        array[131] = array[129];
//        // Set 3 to be 1
//        Integer a = 1;
//        Arrays.asList(array).forEach(System.out::println);
//        if(a == (Integer)1 && a == (Integer)2 && a == (Integer)3){
//            System.out.println("Success");
//        }


        List<Integer> list= new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        for (Integer integer :list) {

            if (integer==5){
                continue;
            }
            System.out.println(integer);
        }
        list.forEach(a->{

            if (a==5){
             return;
            }
            System.out.println(a);
        });
//
//        BigDecimal bigDecimal = new BigDecimal(4);
//        BigDecimal t = new BigDecimal(0);
//        for (int i=0 ; i<255 ;i++){
//            bigDecimal= bigDecimal.multiply(new BigDecimal(4));
//        }
//        System.out.println(bigDecimal);
//        System.out.println(new BigDecimal((2 << 512)));


//        BigDecimal bigDecimal1 = new BigDecimal(4);
//        while (t.compareTo(bigDecimal.subtract(new BigDecimal(1)))<0){
//            bigDecimal1= bigDecimal1.multiply(new BigDecimal(4));
//            t.add(new BigDecimal(1));
//        }
//        System.out.println(bigDecimal1);
    }


    public static void  testTime(){

        String time ="2021-01-02 21:10:10";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


        try {
            Date parse = simpleDateFormat.parse(time);
      System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }



    private static void extracted() {
        Me me= new Me();
        me.setAdditionalinfo("123");
        me.setAlarmdomain("123123");
        me.setMaintainvendor("asdsa");
        Me1 me1= new Me1();
        me1.setAdditionalinfo("123");
        me1.setAlarmdomain("123123");
        me1.setMaintainvendor("asdsa");

        String s = JSON.toJSONString(me);
        String s1 = JSON.toJSONString(me1);

        String   s2="{\"additionalinfo\":\"123\",\"alarmdomain\":\"123123\",\"maintainvendor\":\"asdsa\",\"maxoutputpower\":0.0}";
        String s3 =  "{\"additionalinfo\":\"123\",\"maintainvendor\":\"asdsa\",\"alarmdomain\":\"123123\",\"maxoutputpower\":0.0}";

        log.info(s);
        log.info(s1);
        log.info(s2);
        log.info(s3);
        log.info(String.valueOf(s.hashCode()));
        log.info(String.valueOf(s1.hashCode()));
        log.info(String.valueOf(s2.hashCode()));
        log.info(String.valueOf(s3.hashCode()));
        boolean equals = s.equals(s1);

        boolean equals1 = s2.equals(s3);

        log.info(equals+"------"+equals1);
    }
}
