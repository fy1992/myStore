package cn.dahe.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.HttpURLConnection;
import java.net.URL;

public class ImgUtils {
	private static Log log = LogFactory.getLog(ImgUtils.class);
	/**
	 * 转换图片名
	 * */
	public static String changeImgName(String str){
		if(StringUtils.isEmpty(str) || str.equals("")){
			return "";
		}
		if(str.contains(".")){
			String firstStr = str.substring(0, str.lastIndexOf("."));
			String lastStr = str.substring(str.lastIndexOf("."));
			str = SecurityUtil.getHash(firstStr + System.currentTimeMillis()) + lastStr;
		}
		return str.toLowerCase();
	}

}
