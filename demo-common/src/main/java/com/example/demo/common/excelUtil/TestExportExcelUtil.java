package com.example.demo.common.excelUtil;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 测试文件导出
 * @author liuyazhuang
 *
 */
public class TestExportExcelUtil {

    public static void main(String[] args) throws Exception{
        ExportExcelUtil<Student> util = new ExportExcelUtil<>();
        // 准备数据
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Student(111,"张三","男",new Date()));
            list.add(new Student(111,"李四","男",new Date()));
            list.add(new Student(111,"王五","女",new Date()));
        }
        List<String> columnNames =new ArrayList<>();
        columnNames.add("ID");
        columnNames.add("姓名");
        columnNames.add("性别");
//        util.exportExcel("用户导出", columnNames, list, new FileOutputStream("D:/test.xls"), ExportExcelUtil.EXCEL_FILE_2003);
        util.exportExcel("用户导出",  list, new FileOutputStream("D:/test.xls"), ExportExcelUtil.EXCEL_FILE_2003);
        ExcelUtils.exportExcelByOutputStream(new FileOutputStream("D:/test-easy.xls"), "中台指标数据", "指标信息",list, Student.class);
    }
}