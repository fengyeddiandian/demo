package com.example.demo.common.excelUtil;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 学生
 *
 * @ClassName Student
 * @Author yu.zhang
 * @Description
 * @Date 2021/7/5 17:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(25)
public class Student {
    @ExcelProperty(value = "年龄",index = 0)//导出字段标题名称，及位置下标
    Integer age;
    @ExcelProperty(value = "名称",index = 1)//导出字段标题名称，及位置下标
    String name;
    @ExcelIgnore // 导出时过滤此字段
    String sex;


    /**
     * 生日
     */
    @ExcelProperty(value = "生日",index = 2)//导出字段标题名称，及位置下标
    @DateTimeFormat(value = "yyyy")
    Date birthday;

}
