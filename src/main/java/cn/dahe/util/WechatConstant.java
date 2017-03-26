package cn.dahe.util;

/**
 * 微信公众号常量类
 * @author ALERT
 *
 */
public class WechatConstant {
	
	public static String token;
	
	public static String appid;
	
	public static String secret;
	
	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		WechatConstant.token = token;
	}

	public static String getAppid() {
		return appid;
	}

	public static void setAppid(String appid) {
		WechatConstant.appid = appid;
	}

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		WechatConstant.secret = secret;
	}

}
