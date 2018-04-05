package com.jianglei.zhaopin.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CoordsConverter {

	public static void main(String[] args) {
		/*
		 * String convert = convert("125.321753,43.897727");
		 * System.out.println(convert);
		 */
		try {
			convertAddtoCoords("长春");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据地址解析经纬度，如果没有解析出来，返回空字符串
	 * 
	 * @param addr
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static String convertAddtoCoords(String addr) {
		String lonlat = "";
		String result;
		try {
			String enAddr = URLEncoder.encode(addr, "UTF-8");
			String url = "http://api.map.baidu.com/geocoder/v2/" + "?address=" + enAddr
					+ "&output=json&ak=huWwdukpW9jeD86w0InyquPR630HxsSO";
			result = getResult(url);
			JSONObject json = JSONObject.fromObject(result);
			Integer status = (Integer) json.get("status");
			if (status == null || status != 0) {
				return lonlat;
			}
			if (status == 0) {
				JSONObject bresult = JSONObject.fromObject(json.get("result"));
				JSONObject loc = JSONObject.fromObject(bresult.get("location"));
				String lon = loc.get("lng") + "";
				String lat = loc.get("lat") + "";
				lonlat = lon + "," + lat;
			}
			System.out.println(lonlat);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return lonlat;
		}
	}

	/**
	 * 单个经度转换 格式： 经度+,+纬度 125.xxxxxx，43.xxxxx 如果转换出问题会返回 空字符串”“
	 * 
	 * @param LonLat
	 */
	public static String convert(String LonLat) {
		String url = "http://api.map.baidu.com/geoconv/v1/?coords=" + LonLat
				+ "&from=3&to=5&ak=huWwdukpW9jeD86w0InyquPR630HxsSO";
		String lonlat = "";
		try {
			String content = getResult(url);
			JSONObject jsonObject = JSONObject.fromObject(content);
			Object result = jsonObject.get("result");
			JSONObject xy = JSONObject.fromObject(JSONArray.fromObject(result).get(0));
			lonlat = xy.getString("x") + "," + xy.getString("y");
			/*
			 * System.out.println(xy.getString("x"));
			 * System.out.println(xy.getString("y"));
			 */
		} catch (IOException e) {
			System.out.println("坐标转换出现问题：" + LonLat);
			e.printStackTrace();
		}
		return lonlat;
	}

	/**
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static String getResult(String url) throws MalformedURLException, IOException {
		URL urL = new URL(url);
		URLConnection Connection = urL.openConnection();
		InputStream inputStream = Connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		String content = "";
		while ((line = reader.readLine()) != null) {
			content = content + line;
		}
		return content;
	}

}
