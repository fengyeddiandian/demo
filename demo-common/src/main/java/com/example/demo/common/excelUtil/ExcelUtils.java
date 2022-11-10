

package com.example.demo.common.excelUtil;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public class ExcelUtils {
    /**
     *
     * @param response
     * @param fileName
     * @param sheetName
     * @param list
     * @param pojoClass
     * @throws IOException
     */
    public static void exportExcel(HttpServletResponse response, String fileName, String sheetName, List<?> list,
                                   Class<?> pojoClass) throws IOException {
        if(StringUtils.isBlank(fileName)){
            //当前日期
            fileName = DateUtils.getDate(new Date());
        }

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" +  fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), pojoClass).sheet(sheetName).doWrite(list);
    }
    public static void exportExcelByOutputStream(OutputStream out, String fileName, String sheetName, List<?> list,
                                        Class<?> pojoClass) throws IOException {
        if(StringUtils.isBlank(fileName)){
            //当前日期
            fileName = DateUtils.getDate(new Date());
        }

        fileName = URLEncoder.encode(fileName, "UTF-8");
        EasyExcel.write(out, pojoClass).sheet(sheetName).doWrite(list);
    }
    public static void exportExcelToTarget(HttpServletResponse response, String fileName, String sheetName, List<?> sourceList,
                                           Class<?> targetClass) throws Exception {
        List targetList = new ArrayList<>(sourceList.size());
        for(Object source : sourceList){
            Object target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }

        exportExcel(response, fileName, sheetName, targetList, targetClass);
    }

    /**
     * 导出多个sheet页工具类
     * @param response
     * @param fileName
     * @param targetClassMap
     * @param map
     * @throws Exception
     */
    public static void exportExcelSheet(HttpServletResponse response, String fileName  , Map<String,Object> targetClassMap , Map<String , Object> map) throws Exception{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

        int id = 0;
        for (Map.Entry<String , Object> entry : map.entrySet()) {

            WriteSheet writeSheet = EasyExcel.writerSheet(id, entry.getKey()).head((Class)targetClassMap.get(entry.getKey())).build();
            excelWriter.write((List) entry.getValue(), writeSheet);
            id++;
            //   System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
        }
        //千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }


}
