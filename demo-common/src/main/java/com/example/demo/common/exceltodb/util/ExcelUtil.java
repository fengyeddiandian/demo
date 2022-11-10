package com.example.demo.common.exceltodb.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel工具类
 */
public class ExcelUtil {

    /**
     * 从excel中读取数据
     *
     * @param xls         true xls文件，false xlsx文件
     * @param inputStream 文件输入流
     * @return 数据封装到对象
     */
    public static List<Object> getDataFromExcel(boolean xls, InputStream inputStream) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Workbook workbook = null;
        List<Object> userList = new ArrayList<>();
        try {
            if (xls) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                workbook = new XSSFWorkbook(inputStream);
            }

            // 得到一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 得到表头
            Row rowHead = sheet.getRow(0);
            // 判断表头是否正确
            if (rowHead.getPhysicalNumberOfCells() < 1) {
                throw new Exception("表头错误");
            }
            // 获取数据
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                // 获取第i行
                Row row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    String stringCellValue = row.getCell(j).getStringCellValue();





                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

}