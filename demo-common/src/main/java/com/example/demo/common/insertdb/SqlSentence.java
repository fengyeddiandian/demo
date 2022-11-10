package com.example.demo.common.insertdb;


import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tqf
 * @Description 根据对象生成sql语句
 * @Version 1.0
 * @since 2020-09-09 16:58
 */
public class SqlSentence {

    //String insertSql = getInsertSql("user", User.class, user);
    public static void main(String[] args) {

        List<userDB> list = new ArrayList<>();

        for (int i=0 ;i<3;i++){
            Users users = new Users();
            users.setId(i);
            users.setName("tqf");
            users.setSex("男");
            list.add(users);
        }


//        Me me = new Me();
//
//        me.setObjectkey("12312");
//        me.setObjectid("00001");
//        me.setEmsobjectid("1231asd");
//        me.setMeid("UUId:12312");
        //生成插入语句
//        String sql = getInsertSql("me",Users.class,users);
        SqlSentence1 sqlSentence1 = new SqlSentence1();
        sqlSentence1.insertList(list,"me",Users.class);


//        System.out.println(sql);

//        //生成更新语句
//        String sql_update = getUpdateSql("users.users",Users.class,users);
//        System.out.println(sql_update);
//
//        //生成查询语句
//        String sql_select = getSelectSql("users.users",users);
//        System.out.println(sql_select);
//
//        //生成删除语句
//        String sql_delete = getDeleteSql("users.users",users);
//        System.out.println(sql_delete);
    }


    /**
     * 生成插入语句
     * @param tablename 表名
     * @param clazz 与数据库中字段一一对应的类
     * @param t 有数据的实体
     * @param <T> 数据实体类型 如 User
     */
    public static  <T> String getInsertSql(String tablename, Class<T> clazz, T t){
        //insert into table_name (column_name1,column_name2, ...) values (value1,value2, ...)
        String sql = "";
        Field[] fields = ReflectUtil.getFieldsDirectly(clazz, false);
        StringBuilder topHalf = new StringBuilder("insert into "+tablename+" (");
        StringBuilder afterAlf = new StringBuilder("values (");
        for (Field field : fields) {
//            if ("ID".equals(field.getName()) || "id".equals(field.getName())){
//                continue;   //id 自动生成无需手动插入
//            }
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

    /**
     * 生成更新语句
     * 必须含有id
     * 数据实体中 null 与 空字段不参与更新
     * @param tableName 数据库中的表明
     * @param clazz 与数据库中字段一一对应的类
     * @param t 有数据的实体
     * @param <T> 数据实体类型,如 User
     */
    public static  <T> String getUpdateSql(String tableName, Class<T> clazz, T t){
        //UPDATE table_name SET column_name1 = value1, column_name2 = value2, ... where ID=xxx
        //or
        //UPDATE table_name SET column_name1 = value1, column_name2 = value2, ... where id=xxx
        StringBuilder sql = new StringBuilder();
        //保存id名：ID or id
        String id = "";
        Field[] fields = ReflectUtil.getFieldsDirectly(clazz, false);
        sql = new StringBuilder("update " + tableName + " set ");
        for (Field field : fields) {
            StringBuffer tmp = new StringBuffer();
            if ("ID".equals(field.getName()) || "id".equals(field.getName())){
                id = field.getName();
                continue;//更新的时候无需set id=xxx
            }
            if (ReflectUtil.getFieldValue(t, field.getName()) != null && ReflectUtil.getFieldValue(t, field.getName()) != "") {
                tmp.append(field.getName()).append("=");
                if (ReflectUtil.getFieldValue(t, field.getName()) instanceof String) {
                    tmp.append("'").append(ReflectUtil.getFieldValue(t, field.getName())).append("',");
                } else {
                    tmp.append(ReflectUtil.getFieldValue(t, field.getName())).append(",");
                }
                sql.append(tmp);
            }
        }
        sql = new StringBuilder(StrUtil.removeSuffix(sql.toString(), ",") + " where " + id + "='" + ReflectUtil.getFieldValue(t, id) + "'");
        return sql.toString();
    }

    /**
     * 生成删除语句
     * 根据 user 中第一个不为空的字段删除,应该尽量使用 id,提供至少一个非空属性
     * @param tablename 表明
     * @param t 有数据的实体
     * @param <T> 数据实体类型 如 User
     */
    public static  <T> String getDeleteSql(String tablename, T t) throws IllegalArgumentException {
        //delete from table_name where column_name = value
        return getSelectOrDeleteSql(tablename, t, "delete");
    }

    /**
     * 生成查询语句
     * 根据 user 中第一个不为空的字段查询
     * @param tablename 表名
     * @param t 有数据的实体
     * @param <T> 数据实体类型 如 User
     */
    public static  <T> String getSelectSql(String tablename, T t) throws IllegalArgumentException {
        //delete from table_name where column_name = value
        return getSelectOrDeleteSql(tablename, t, "select *");
    }

    /**
     * 根据 operation 生成一个如：operation from table_name where column_name = value 的sql语句
     * @param tablename
     * @param t
     * @param operation "select *"  or "delete"
     * @param <T>
     * @return
     * @throws IllegalArgumentException
     */
    private static  <T> String getSelectOrDeleteSql(String tablename, T t, String operation) throws IllegalArgumentException {
        //operation from table_name where column_name = value
        boolean flag = false;
        String sql = "";
        Field[] fields = ReflectUtil.getFieldsDirectly(t.getClass(), false);
        StringBuffer topHalf = new StringBuffer(operation + " from " + tablename + " where ");
        for (Field field : fields) {
            if ("ID".equals(field.getName()) || "id".equals(field.getName())) {
                if (ReflectUtil.getFieldValue(t, field.getName()) != null && (int)ReflectUtil.getFieldValue(t, field.getName()) != 0) {
                    //id 不为空
                    topHalf.append(field.getName() + " = " + ReflectUtil.getFieldValue(t, field.getName()));
                    flag = true;
                    break;
                }
            }
            else {
                if (ReflectUtil.getFieldValue(t, field.getName()) != null && (String)ReflectUtil.getFieldValue(t, field.getName()) != "") {
                    topHalf.append(field.getName() + " = '" + ReflectUtil.getFieldValue(t, field.getName()) + "'");
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            throw new IllegalArgumentException(t.getClass() +  "NullException.\nThere is no attribute that is not empty.You must provide an object with at least one attribute.");
        }
        sql = topHalf.toString();
        return sql;
    }

}