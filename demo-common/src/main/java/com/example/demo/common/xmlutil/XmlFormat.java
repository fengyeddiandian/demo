package com.example.demo.common.xmlutil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *  XMl格式化工具
 */
public class XmlFormat {
    public static String format(FileInputStream in,String path) throws Exception {
        SAXReader reader = new SAXReader();
//        // System.out.println(reader);
//        // 注释：创建一个串的字符输入流
//        StringReader in = new StringReader(str);
        Document doc = reader.read(in);
        // System.out.println(doc.getRootElement());
        // 注释：创建输出格式
        OutputFormat formater = OutputFormat.createPrettyPrint();
        //formater=OutputFormat.createCompactFormat();
        // 注释：设置xml的输出编码
        formater.setEncoding("utf-8");
        // 注释：创建输出(目标)
        StringWriter out = new StringWriter();
        // 注释：创建输出流
        XMLWriter writer = new XMLWriter(out, formater);
        // 注释：输出格式化的串到目标中，执行后。格式化后的串保存在out中。
        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
        outputFormat.setEncoding("UTF-8");
        outputFormat.setSuppressDeclaration(true); //是否生产xml头
        outputFormat.setIndent(true); // 设置是否缩进
        outputFormat.setIndent("    "); // 以四个空格方式实现缩进
        outputFormat.setNewlines(true); // 设置是否换行
        path=path+File.separator +"格式化-"+ UUID.randomUUID()+".xml";
        FileOutputStream fos = new FileOutputStream(path);
        writer.setOutputStream(fos);
        writer.write(doc);
        writer.close();
        System.out.println("写入完毕！");
        // 注释：返回我们格式化后的结果
        return "1";
    }

    public static void main(String[] args) throws Exception {

        List<String> fileName = new ArrayList<>();
        String path ="D:\\documents\\tencent files\\华为\\新建文件夹";
        GetFilesNames.getFileName(path,fileName);
        if (fileName.size()>0){
            String s = fileName.get(0);
            File f = new File(s);
            FileInputStream in = new FileInputStream(f);
            format(in,path);
        }

    }
}