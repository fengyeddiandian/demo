package com.example.demo.common.csvutil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @ClassName TestCSVServiceImpl
 * @Author yu.zhang
 * @Description
 * @Date 2022/8/15 15:58
 **/
@Service("TestCSVServiceImpl")
@Slf4j
public class TestCSVServiceImpl implements TestCSVService{

    @Autowired
    private DataSource dataSource;

    private final BigDecimal DCCP_THR = new BigDecimal("0.2");

    @Value("${dccpThr:0.2}")
    private String dccpThr;

    @Override
    public void insert(String path) {
        List<String> fileNameList = getFileNameList(path);

        fileNameList.forEach(fileName->{
            List<String> title = new ArrayList<>();
            List<List<String>> list = new ArrayList<>();
            AnalyserCSV.getCsvData(fileName,list,title);
            String tableName = fileName.split("\\.")[1];
            if (title.size()==0|| StringUtils.isBlank(tableName)){
                return;
            }
            String insertsql=" insert into  " +tableName.toLowerCase()+"_copy" + " ( "+ title.stream().map(String::toLowerCase).collect(Collectors.joining(","))+" ) " + "value (  "+ title.stream().map(a -> "?").collect(Collectors.joining(","))+" )";
            Connection conn = null;
            PreparedStatement stmt = null;
            int toleCount = 0;
            int currCount = 0;
            try {
                conn = dataSource.getConnection();
                if (conn == null) {
                    log.error(" can not open db connection !");
                    return;
                }
                conn.setAutoCommit(false);
                for (int i = 0; i < list.size(); i++) {
                    List<String> list1 = list.get(i);
                    if (currCount == 0) {
                        stmt = conn.prepareStatement(insertsql);
                    }
                    for (int j = 0; j < title.size(); j++) {
                        stmt.setString(i,list1.get(j) );
                    }
                    stmt.addBatch();
                    currCount = currCount + 1;
                    if (currCount == 10000) {
                        toleCount = toleCount + currCount;
                        log.info(" sql insert tableName: " + tableName + " size： " + toleCount);
                        stmt.executeBatch();
                        stmt.close();
                        currCount = 0;
                    }
                    if (!stmt.isClosed()) {
                        log.info(" sql insert tableName: " + tableName + " size： " + (toleCount + currCount));
                        stmt.executeBatch();
                        stmt.close();
                    }
                    conn.commit();

                    boolean isChangeTableName=isChangeTableName((toleCount + currCount),tableName,dccpThr);
                    if (isChangeTableName){
                        throw new Exception("由于数据量差异过大，执行抑制！");
                    }else {
                        //更换表名称
                        changeTableName(tableName, tableName+"_copy");
                    }
                }



            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    log.error(e.getMessage(), e);
                }
            }


            System.out.println(insertsql);

        });






    }

    public boolean isChangeTableName(int newNum,String tableName,String dccp_Thr) {
        BigDecimal dccpThr;
        boolean b=false;
        if (StringUtils.isBlank(dccp_Thr)){
            dccpThr=DCCP_THR;
        }else {
            dccpThr=new BigDecimal(dccp_Thr);
        }
        String sql = " select count(1)  from  ? ";
        int oldNum=0;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            if (conn == null) {
                log.error(" can not open db connection !");
                return false;
            }
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,tableName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                oldNum = rs.getInt(1);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
        int cha=newNum - oldNum;
        if (cha<0&&oldNum!=0){
            int abs = Math.abs(newNum - oldNum);
            b= new BigDecimal(abs).divide(new BigDecimal(oldNum),6, RoundingMode.HALF_UP).compareTo(dccpThr)>=0;
        }
        if (b){
            log.info("tableName ："+tableName+"数据量差异过大 触发抑制 原数据量为 ： "+oldNum+"，同步后数据量为 : "+newNum);
        }
        return b ;
    }

    /**
     * 将2个表名称互相转换
     *
     * @param tableName     正式表名称
     * @param tableTempName 临时表名称
     */
    public void changeTableName(String tableName, String tableTempName) {
        Connection conn = null;
        PreparedStatement stmt = null;
        long startTime = System.currentTimeMillis();
        try {
            conn = dataSource.getConnection();
            if (conn == null) {
                log.error(" can not open db connection !");
                return;
            }
            conn.setAutoCommit(false);
            //设置事务的隔离级别。
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            String tableTempName2 = createTempTableName(tableName);
            StringBuilder sqlBuf = getStringBuilder(tableName, tableTempName2);
            stmt = conn.prepareStatement(sqlBuf.toString());
            stmt.executeUpdate();
            log.info("更换表名，sql : " + sqlBuf);
            StringBuilder sqlBuf1 = getStringBuilder(tableTempName, tableName);
            stmt = conn.prepareStatement(sqlBuf1.toString());
            stmt.executeUpdate();
            log.info("更换表名，sql : " + sqlBuf1);
            StringBuilder sqlBuf2 = getStringBuilder(tableTempName2, tableTempName);
            stmt = conn.prepareStatement(sqlBuf2.toString());
            stmt.executeUpdate();
            log.info("更换表名，sql : " + sqlBuf2);
            conn.commit();
            long endTime = System.currentTimeMillis();
            log.info(tableName + "更换表名完成,用时：" +getTimeString((endTime - startTime)));
        } catch (Exception e) {
            log.error("tableName：" + tableName + " 更换表名失败！ " + e.getMessage(), e);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public String createTempTableName(String tableName) {
        String replace = UUID.randomUUID().toString().replace("-", "").substring(0,5);
        return tableName + "_" + replace;
    }
    private StringBuilder getStringBuilder(String fromTableName, String toTableName) {
        StringBuilder sqlBuf = new StringBuilder();
        sqlBuf.append("  alter table  ");
        sqlBuf.append(fromTableName);
        sqlBuf.append("  rename to  ");
        sqlBuf.append(toTableName);
        return sqlBuf;
    }

    private String getTableStructureSql(String dateType) {
        String sql="";
            switch (dateType){
                case "oracle" :
                    sql = " SELECT COLUMN_NAME columnName ,DATA_TYPE dataType FROM user_tab_columns where table_name = upper(?) ";
                    break;
                case "pgsql" :
                case "mysql" :
                    sql =  " SELECT COLUMN_NAME as columnName , data_type as dataType  FROM information_schema.COLUMNS WHERE TABLE_NAME = ? ";
                    break;
            }
        return sql;
    }

    public  List<String> getFileNameList(String path) {
        List<String> list= new ArrayList<>();
        File f = new File(path);
        if (!f.exists()) {
            return list;
        }
        File[] fa = f.listFiles();
        if (fa!=null){
            for (File fs : fa) {
                if (fs.isDirectory()) {
                    getFileNameList(fs.getPath());
                } else {
                    list.add(fs.getPath());
                }
            }
        }
        return list;
    }


    public static void main(String[] args) {
        TestCSVServiceImpl testCSVService = new TestCSVServiceImpl();
        testCSVService.insert("E:\\工作资料\\需求\\电信\\OTMS接入\\新建文件夹\\zqotn");
    }

    public  String getTimeString(long time) {
        if (time<=1000){
            return time+"毫秒";
        }else if (time <60*1000){
            return (time/1000)+"秒";
        }else if (time <(60*60*1000)){
            return (time/(1000*60))+"分钟";
        }else {
            return (time/(1000*60*60))+"小时";
        }
    }
}
