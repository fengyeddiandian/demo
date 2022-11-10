package com.example.demo.common.xmlutil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetFilesNames {

    public static void main(String[] args) {

        String path = "D:\\工作资料\\data"; // 路径
        List<String> fileName = new ArrayList<String>();
        getFileName(path, fileName);
        fileName.forEach(System.out::println);
        System.out.println(fileName.size());
    }

    public static void getFileName(String path, List<String> list) {

        File f = new File(path);
        if (!f.exists()) {
            return;
        }
        File fa[] = f.listFiles();
        for (File fs : fa) {
            if (fs.isDirectory()) {
                getFileName(fs.getPath(), list);
            } else {
                list.add(fs.getPath());
            }
        }
    }
}
