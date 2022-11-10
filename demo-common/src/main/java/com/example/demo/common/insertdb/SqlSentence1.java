package com.example.demo.common.insertdb;


import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * @author yu.zhang6696
 */
public class SqlSentence1 {

    /**
     * 生成插入语句
     * insert into table_name (column_name1,column_name2, ...) values (value1,value2, ...)
     *
     * @param tableName 表名
     * @param clazz     与数据库中字段一一对应的类
     * @param t         有数据的实体
     * @param <T>       数据实体类型 如 User
     */
    public <T> String getInsertSql(String tableName, Class<?> clazz, T t) {
        String sql;
        Field[] fields = ReflectUtil.getFieldsDirectly(clazz, false);
        StringBuilder topHalf = new StringBuilder("insert into " + tableName + " (");
        StringBuilder afterAlf = new StringBuilder("values (");
        for (Field field : fields) {
            topHalf.append(field.getName()).append(",");
            if (ReflectUtil.getFieldValue(t, field.getName()) instanceof String) {
                afterAlf.append("'").append(ReflectUtil.getFieldValue(t, field.getName())).append("',");
            } else {
                afterAlf.append(ReflectUtil.getFieldValue(t, field.getName())).append(",");
            }
        }
        topHalf = new StringBuilder(StrUtil.removeSuffix(topHalf.toString(), ","));
        afterAlf = new StringBuilder(StrUtil.removeSuffix(afterAlf.toString(), ","));
        topHalf.append(") ");
        afterAlf.append(") ");
        sql = topHalf + afterAlf.toString();
        return sql;
    }

    public <T> void insertList(List<userDB> baseInfoList, String tableName, Class<T> clazz) {
            List<String> sqlList = new ArrayList<>();
            baseInfoList.forEach(dto -> {
                String insertSql = getInsertSql(tableName, clazz, dto);
                sqlList.add(insertSql);
                System.out.println(insertSql);
            });
    }



}