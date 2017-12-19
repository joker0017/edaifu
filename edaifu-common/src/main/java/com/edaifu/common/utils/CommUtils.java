package com.edaifu.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author duzx
 * @desc 项目中使用到的非平台提供的工具类
 * 
 */
public class CommUtils {

	/*
	 * @Author duzx
	 * 
	 * @desc 计算两个经纬度之间的地球曲线距离。
	 * 
	 * @param lat1 纬度 lng1经度 lat2纬度 lng2经度
	 */
	private static double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double GetDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = Math.abs(radLat1 - radLat2);
		double b = Math.abs(rad(lng1) - rad(lng2));
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof CharSequence)
			return ((CharSequence) obj).length() == 0;

		if (obj instanceof Collection)
			return ((Collection) obj).isEmpty();

		if (obj instanceof Map)
			return ((Map) obj).isEmpty();

		if (obj instanceof Object[]) {
			Object[] object = (Object[]) obj;
			if (object.length == 0) {
				return true;
			}
			boolean empty = true;
			for (int i = 0; i < object.length; i++) {
				if (!isNullOrEmpty(object[i])) {
					empty = false;
					break;
				}
			}
			return empty;
		}
		return false;
	}

	/*
	 * by duzx desc 统一处理list map里面的日期信息，把日期信息转化成string
	 * 
	 * @param source:需要转换日期类型的数据源（list map） var ： 需要转换的字段名称
	 */
	public static void convdate4list(List<Map<String, Object>> source,
			String var) {
		String updatetime = null;
		for (Map<String, Object> temp : source) {
			updatetime = (String) DateUtils.getDate((Date) temp.get(var),
					DateUtils.datetimePattern);
			temp.put(var, updatetime);
		}

	}

	/*
	 * by duzx desc 统一处理 map里面的日期信息，把日期信息转化成string
	 * 
	 * @param source:需要转换日期类型的数据源（ map） var ： 需要转换的字段名称
	 */
	public static void convdate4Map(Map<String, Object> source, String var) {
		String updatetime = null;
		updatetime = (String) DateUtils.getDate((Date) source.get(var),
				DateUtils.datetimePattern);
		source.put(var, updatetime);
	}

	/**
	 * 获取文件大小 将byte类型转换为M
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytes2mb(long bytes) {
		java.math.BigDecimal filesize = new java.math.BigDecimal(bytes);
		java.math.BigDecimal megabyte = new java.math.BigDecimal(1024 * 1024);
		float returnValue = filesize.divide(megabyte, 2,
				java.math.BigDecimal.ROUND_UP).floatValue();
		return returnValue + "";
	}

	// 将空数据移除
	public static Map<String, Object> delkong(Map<String, Object> data) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		for (String key : data.keySet()) {
			if (data.get(key) == null || "".equals(data.get(key))) {

			} else {
				dataMap.put(key, data.get(key));
			}
		}
		return dataMap;
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * @param fileName
	 */
	public static String readFileByLines(File file) {
		BufferedReader reader = null;
		String result = "";
		String tempString = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((tempString = reader.readLine()) != null) {
				result+=tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return result;
	}


}
