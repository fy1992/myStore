package cn.dahe.util;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * @author Administrator
 *
 */
public class ResourcesUtils {
	
	private static final ResourceBundle bundel = ResourceBundle.getBundle("config");
	
	private void ResourcesUtils(){
		
	}
	
	/**
	 * 获取配置文件中的敏感词，
	 * @return
	 */
	public static final String getMinganKey(){
		return bundel.getString("keys");
	}
	/**
	 * 获取排除不让登陆的省份
	 * @return
	 */
	public static final String getIncludeProvinceKeys(){
		return  bundel.getString("includeProvinceKeys");
	}
	
	/**
	 * 判断手机号归属地的排除区域
	 * @return
	 */
	public static final String getBlackphonekeys(){
		return bundel.getString("blackphonekeys");
	}

	/**
	 * oracle url
	 * @return
	 */
	public static final String oracleUrl(){
		return bundel.getString("oracle.url");
	}

	/**
	 * oracle username
	 * @return
	 */
	public static final String oracleUsername(){
		return bundel.getString("oracle.username");
	}

	/**
	 * oracle password
	 * @return
	 */
	public static final String oraclePassword(){
		return bundel.getString("oracle.password");
	}

}
