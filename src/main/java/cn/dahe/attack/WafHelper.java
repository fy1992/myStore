package cn.dahe.attack;

import org.apache.commons.lang3.StringUtils;

public class WafHelper {
	
	/**
	 * 过滤xss
	 * @param value
	 * @return
	 */
	public static String stripXss(String value){
		if(StringUtils.isBlank(value)){
			return null;
		}
		return new XSS().strip(value);
	}
	
	public static String stripSql(String value){
		if(StringUtils.isBlank(value)){
			return null;
		}
		return new SqlInject().strip(value);
	}
	
	public static String stripXssSql(String value){
		if(StringUtils.isBlank(value)){
			return null;
		}
		return stripXss(stripSql(value));
	}

}
