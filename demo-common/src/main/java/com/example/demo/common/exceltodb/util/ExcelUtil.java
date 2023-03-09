package com.example.demo.common.exceltodb.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.generator.domain.TSncAggregate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public static List<Object> getDataFromExcel(boolean xls, InputStream inputStream,Map<String,String> titleMap ) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Workbook workbook = null;
        List<Object> list = new ArrayList<>();
        try {
            if (xls) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                workbook = new XSSFWorkbook(inputStream);
            }
            if (titleMap==null){
                titleMap= new HashMap<>();
            }
            // 得到一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 得到表头
            Row rowHead = sheet.getRow(0);
            // 判断表头是否正确
            if (rowHead.getPhysicalNumberOfCells() < 1) {
                throw new Exception("表头错误");
            }
            List<String> titleList = new ArrayList<>();
            for (int j = 0; j < rowHead.getLastCellNum(); j++) {
                titleList.add(rowHead.getCell(j).getStringCellValue());
            }
            // 获取数据
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                // 获取第i行
                Row row = sheet.getRow(i);

                int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                if (titleList.size()!=physicalNumberOfCells){
                    continue;
                }
                Map<String,String> map = new LinkedHashMap<>();
                for (int j = 0; j < titleList.size(); j++) {
                    String title = titleList.get(j);
                    String stringCellValue = row.getCell(j).getStringCellValue();
                    map.put(Objects.toString(titleMap.get(title),title),stringCellValue);
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public static void main(String[] args) {
        Path path1 = Paths.get("F:\\SNC端口修改.xlsx");


        Map<String,String>  map  =  new HashMap<>();
        map.put("地市","city");
        map.put("电路代号","circuitName");
        map.put("A端MAC端口名称","aMacPortName");
        map.put("A端MAC端口备注信息","aMacPortUserlabel");
        map.put("A端vctrunk端口名称","aVctrunkPortName");
        map.put("A端vctrunk端口备注信息","aVctrunkPortUserlabel");
        map.put("SNC名称","userlabel");
        map.put("SNC A端端口名称","sncAportName");
        map.put("SNC A端端口备注信息","sncAportUserlabel");
        map.put("SNC A端端口时隙名称","sncAtimeName");
        map.put("SNC Z端端口名称","sncZportName");
        map.put("SNC Z端端口备注信息","sncZportUserlabel");
        map.put("SNC Z端端口时隙名称","sncZtimeName");
        map.put("z端vctrunk端口名称","zMacPortName");
        map.put("z端vctrunk端口备注信息","zMacPortUserlabel");
        map.put("z端MAC端口名称","zVctrunkPortName");
        map.put("z端MAC端口备注信息","zVctrunkPortUserlabel");

        try {
            List<Object> dataFromExcel = getDataFromExcel(false, Files.newInputStream(path1), map);
            List<TSncAggregate> tSncAggregates = JSON.parseArray(JSON.toJSONString(dataFromExcel), TSncAggregate.class);
            System.out.println(JSON.toJSONString(tSncAggregates));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}