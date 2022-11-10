package com.example.demo.common.MD5;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @ClassName MD5Test
 * @Author yu.zhang
 * @Description
 * @Date 2021/11/27 14:21
 **/
public class MD5Test {

    public static void main(String[] args) {

        OnuPtpMd5Info OnuPtpMd5Info = new OnuPtpMd5Info("1","","1","1","1",1,"1","1","1",1,"1","1");
        OnuPtpMd5Info OnuPtpMd5Info1 = new OnuPtpMd5Info("1",null,"1","1","1",1,"1","1","1",1,"1","1");

        String jsonString = JSON.toJSONString(OnuPtpMd5Info);
        String md5=MD5Util.getMD5(jsonString);

        String jsonString1 = JSON.toJSONString(OnuPtpMd5Info1, SerializerFeature.WriteNullStringAsEmpty);
        String md51=MD5Util.getMD5(jsonString1);
        System.out.println(jsonString);
        System.out.println(jsonString1);


        System.out.println(md5);
        System.out.println(md51);
        //
    }
}
