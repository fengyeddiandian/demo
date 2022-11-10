package com.example.demo.common.TestController;

import org.apache.commons.lang3.ObjectUtils;

import java.text.NumberFormat;
import java.util.ArrayList;

@SuppressWarnings("all")
public class StringUtils extends org.apache.commons.lang3.StringUtils{
	
	
	/**
	 * 对象转换成字符串
	 * @param obj 最好是object override toString()方法
	 * @return
	 */
	public static String asString(Object obj){
		return asString(obj, "");
	}
	
	
	/**
	 * 对象转换成字符串
	 * @param obj 最好是object override toString()方法
	 * @param defaultValue
	 * @return
	 */
	public static String asString(Object obj,String defaultValue){
		if(isBlank(ObjectUtils.toString(obj))) 
			return defaultValue;
		
		return ObjectUtils.toString(obj);
	}
	
	/**
	 * 判断对象是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isBlank(Object obj){
		return isBlank(ObjectUtils.toString(obj));
	}
	
	/**
	 * 判断对象是否不为空
	 * @param obj
	 * @return
	 */
	public static boolean isNotBlank(Object obj){
		return isNotBlank(ObjectUtils.toString(obj));
	}
	
	/**
	 * sql转义
	 * @param str
	 * @return
	 */
	public static String escapeSql(String str) {
        return isNotBlank(str) ? replace(str, "'", "''") : null;
    }
	
	public static String nvl(String str){
		String ret = "";
		if(null != str && !"null".equals(str)){
			ret = str;
		}
		return str;
	}
	
	
	/**
	 *******************************************************************************
	 * 将数组转换成List
	 * @param <T>
	 * @param arr
	 * @return : 
	 *******************************************************************************
	 */
	static public <T> ArrayList<T> toList(T[] arr){
		ArrayList<T> results=new ArrayList<T>();
		if(arr!=null && arr.length>0){
			for (T t : arr) 
				results.add(t);
		}
		return results;
	}
	
	/**
	 **********************************************************************************************
	 * 生成百分比，并截取小数点位数
	 * @param ob
	 * @return : 0.0% or 50.0%
	 **********************************************************************************************
	 */
	public static String bigDecMath3(Object ob,Integer...ii){
		NumberFormat num = NumberFormat.getPercentInstance(); 
		num.setMaximumIntegerDigits(3); 
		int c=2;
		if(ii!=null && ii.length>0)
			c=ii[0];
		num.setMaximumFractionDigits(c); 
		return num.format(ob);
	}
	
	/**
	 **********************************************************************************************
	 * 保留小类点位数，默认2位
	 * @param o 分子
	 * @param p 分母
	 * @return : 0.398
	 **********************************************************************************************
	 */
	public static double bigDecMath2(Object o,Object p,int i){
		Double result=0.0; 
		double doo=Double.parseDouble(String.valueOf(o));
		double poo=Double.parseDouble(String.valueOf(p));
		if(poo==0.0 || poo==0)
			return 0.0;
		
		Double roo=doo/poo;
		result=bigDecMath2(roo,i); 
		return result;
	}
	
	/**
	 **********************************************************************************************
	 * 保留小类点位数，默认2位
	 * @param ob
	 * @return : 0.398
	 **********************************************************************************************
	 */
	public static double bigDecMath2(Object ob,Integer...ii){
		Double result=0.0;
		if(ob==null)
			return 0.0;
		
		NumberFormat num = NumberFormat.getNumberInstance(); 
		num.setMaximumIntegerDigits(3); 
		int c=2;
		if(ii!=null && ii.length>0)
			c=ii[0];
		num.setMaximumFractionDigits(c); 
		result=Double.parseDouble(num.format(ob)); 
		return result;
	}
	
	
	/**
	 *******************************************************************************
	 * 判断是否是NULL或是空字符串 <br/>
	 * "" 		is 	true <br/>
	 * "  " 	is 	true <br/>
	 * null 	is 	true <br/>
	 * "null" 	is 	true 
	 * @param s
	 * @return : return true is "" or null else "12345" return false
	 *******************************************************************************
	 */
	public static boolean isNullOrNothing(String s) {
		String in = s;
		if (hasLength(in)) {
			return true;
		}
		if ("".equals(in.trim()) || "null".equals(in.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 *******************************************************************************
	 * 检查字符长度 <br/>
	 * 返回true : 表示字符为空或者长度小于等于零<br/>
	 * 返回false : 表示字符不为空 并且长度大于零
	 * @param str
	 * @return : true or false
	 *******************************************************************************
	 */
	public static boolean hasLength(String str) {
		return (str==null || str.length()<=0);
	}
	
	
}