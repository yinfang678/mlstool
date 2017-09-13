package com.homethy.web.utils;

/**
 * 常量，服务接口
 * 
 * @author wangmeng
 *
 */
public interface Constants {

	// 默认的图片
	static final String DefaultPicture = "http://static.chimeroi.com/listing-images/listing-default.png";
	static final String DefaultValue = "N/A";
	
	static final String NULLValue = "";

	static final String DOMAIN = "chimeroi.com";// 网站域名
	static final String ENCODING = "utf-8"; // 编码方式

	static int SOLDDATE = 3 * 365;// 展示三年内的sold数据
	
	static final int DEFAULTMLS = 0;//默认的mls
	
	static int RETRY_COUNT = 3;//zillow 失败的重试次数
	
}
