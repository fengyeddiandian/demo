package com.example.demo.common.calculator;

import javafx.scene.input.DataFormat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/** @ClassName TestB @Author yu.zhang @Description @Date 2021/11/2 16:48 */
public class TestB {

    public static void main(String[] args) {

        Object a = 0.10001;
        DecimalFormat demo = new DecimalFormat("0.00");
        String format = demo.format(a);
        System.out.println(format);
    }
}
