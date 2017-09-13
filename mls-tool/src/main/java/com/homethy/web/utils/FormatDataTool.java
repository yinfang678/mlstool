package com.homethy.web.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class FormatDataTool {

	/**
	 * 提取字符串中的数字
	 * 
	 * @param str
	 * @return
	 */
	public static String getOnlyNumbers(String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			if (ch >= '0' && ch <= '9') {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	/**
	 * 格式化电话号码
	 * 
	 * @param phone
	 * @return
	 */
	public static String formatPhone(String phone) {
		String formatPhone = "";
		if (StringUtils.isEmpty(phone) || phone.contains("Invalid phone number")) {
			formatPhone = "";
		} else {
			phone = getOnlyNumbers(phone);
			if (phone.length() < 10) {
				formatPhone = "";
			} else if (phone.length() == 10) {
				formatPhone = phone;
			} else {
				if (phone.startsWith("1")) {
					formatPhone = phone.substring(1, 11);
				} else {
					formatPhone = phone.substring(0, 10);
				}
			}

		}
		return formatPhone;
	}

	/**
	 * 判断是否是有效的电话号
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isVaildPhone(String phone) {
		phone = formatPhone(phone);
		if (StringUtils.isNotEmpty(phone)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是一个有效的字符串
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isVaildStringValue(String value) {
		if (value != null) {
			value = value.trim();
		}
		if (StringUtils.isEmpty(value) || StringUtils.isEmpty(value.toLowerCase().replace("null", "").trim())
				|| value.equalsIgnoreCase("0") || value.equalsIgnoreCase("none")
				|| value.equalsIgnoreCase(Constants.DefaultValue)) {
			return false;
		}
		return true;
	}

	/**
	 * 格式化字符串
	 * 
	 * @param value
	 * @return
	 */
	public static String formatStringField(String value) {
		if (!isVaildStringValue(value)) {
			value = Constants.DefaultValue;
		}
		return value.trim();
	}

	/**
	 * 是否是有效的zipcode
	 * 
	 * @return
	 */
	public static boolean isVaildZipcode(String zipCode) {
		if (StringUtils.isEmpty(zipCode) || zipCode.trim().length() > 10 || zipCode.trim().length() < 5) {
			return false;
		}
		return true;
	}

	/**
	 * 判断两个字符串是否有效且相同
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean isVaildAndEqual(String value1, String value2) {
		value1 = formatStringField(value1);
		value2 = formatStringField(value2);

		if (!value1.equals(Constants.DefaultValue) && value1.equalsIgnoreCase(value2)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断两个字符串是否有效且不同
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static boolean isVaildAndNotEqual(String value1, String value2) {
		value1 = formatStringField(value1);
		value2 = formatStringField(value2);

		// 1 和 2 都是有效的，但是不相同
		if (!value1.equals(Constants.DefaultValue) && !value2.equals(Constants.DefaultValue)
				&& !value1.equalsIgnoreCase(value2)) {
			return true;
		}
		return false;
	}

	/**
	 * 是否都是有效的，且包含（两个变量都有内容）
	 * 
	 * @param value1
	 * @param list
	 * @return
	 */
	public static boolean isVaildAndContainsIngoreCase(String value1, List<String> list) {
		value1 = formatStringField(value1);
		if (!isVaildStringValue(value1)) {
			return false;
		}
		if (list == null || list.size() <= 0) {
			return false;
		}
		for (String value : list) {
			if (value1.equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 是否都是有效的数据，但是不包含（两个变量都有内容）
	 * 
	 * @param value1
	 * @param list
	 * @return
	 */
	public static boolean isVaildAndNotContainsIngoreCase(String value1, List<String> list) {
		value1 = formatStringField(value1);
		if (!isVaildStringValue(value1)) {
			return false;
		}
		if (list == null || list.size() <= 0) {
			return true;
		}
		for (String value : list) {
			if (value1.equalsIgnoreCase(value)) {
				return false;
			}
			if (!isVaildStringValue(value)) {
				list.remove(value);
			}
		}
		if (list == null || list.size() <= 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是有效的电话号码，且包含
	 * @param phone
	 * @param phones
	 * @return
	 */
	public static boolean isVaildPhoneAndContains(String phone,List<String> phones){
		if(!isVaildPhone(phone)){
			return false;
		}
		if (phones == null || phones.size() <= 0) {
			return false;
		}
		for (String value : phones) {
			value = formatPhone(value);
			if (phone.equalsIgnoreCase(value)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断是否是有效的电话号码，且包含
	 * @param phone
	 * @param phones
	 * @return
	 */
	public static boolean isVaildPhoneAndNotContains(String phone,List<String> phones){
		if(!isVaildPhone(phone)){
			return false;
		}
		if (phones == null || phones.size() <= 0) {
			return true;
		}
		for (String value : phones) {
			if(!isVaildPhone(value)){
				phones.remove(value);
			}
			value = formatPhone(value);
			
			if (phone.equalsIgnoreCase(value)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 判断一个时间是否是有效的时间戳
	 * @param date
	 * @return
	 */
	public static boolean isVaildTimeStamp(Date date){
		if(date == null){
			return false;
		}
		Calendar create = Calendar.getInstance();
		create.setTime(date);
		if (create.get(Calendar.YEAR) < 1970 || create.after(Calendar.getInstance())) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String s1 = "test   ";
		String s2 = "test      ";
		System.out.println(isVaildAndEqual(s1, s2));
	}
}
