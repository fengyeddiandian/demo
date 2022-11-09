package com.example.demo.common.csvutil;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

/**
 * @ClassName TestCSV
 * @Author yu.zhang
 * @Description
 * @Date 2022/8/15 9:52
 **/
@Slf4j
public class AnalyserCSV {


    public static void main(String[] args) {
        List<String> fileName = new ArrayList<>();
        fileName.add("E:\\工作资料\\需求\\电信\\OTMS接入\\新建文件夹\\新建文件夹\\0d7c58cfb17dd402.T_TRANS_RACK_VIEW.1_Finish.csv.gz");
        List<String> title = new ArrayList<>();
        Set<String> tableNames = new HashSet<>();
        List<List<String>> list = new ArrayList<>();
//        GetFilesNames.getFileName("E:\\工作资料\\需求\\电信\\OTMS接入\\新建文件夹\\zqotn",fileName);
        getCsvData(fileName.get(0),list,title);
//        System.out.println();
//        fileName.forEach(a->{
//            String tableName=a.split("\\.")[1];
//            tableNames.add(tableName);
//        });
//
//        String collect = tableNames.stream().map(a -> "'" + a.toLowerCase() + "'").collect(Collectors.joining(","));
//        System.out.println(collect);
//        tableNames.forEach(System.out::println);
//        Date date = DateUtils.getDate("2021-12-16 08:03:18.0", DateUtils.DATE_TIME_PATTERN);
//        Timestamp timestamp = getTimestamp("2021-12-16 08:03:18.0");
//        System.out.println(timestamp);
        System.out.println();
    }

    public static Timestamp getTimestamp(String dateString) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(dateString);
            System.out.println(ts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }

    public static void getCsvData(String fileName, List<List<String>> list,List<String> title) {
        String record;
        try {
                Path path = Paths.get(fileName);
                // 设定UTF-8字符集，使用带缓冲区的字符输入流BufferedReader读取文件内容
                BufferedReader file = new BufferedReader(new InputStreamReader(new GZIPInputStream(Files.newInputStream(path)), StandardCharsets.UTF_8));
                int line = 0;
                // 遍历数据行并存储在名为records的ArrayList中，每一行records中存储的对象为一个String数组
                while ((record = file.readLine()) != null) {
                    String[] fields;
                    if (record.contains("##")) {
                        fields = record.split("##");
                    }else {
                        continue;
                    }
                    if (line == 0) {
                        title .addAll(Arrays.stream(fields).collect(Collectors.toList())) ;
                        line++;
                    } else {
                        List<String> collect = Arrays.stream(fields).collect(Collectors.toList());
                        list.add(collect);
                        log.info("collect .size : "+collect.size());
                    }
                }
                // 关闭文件
                file.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
