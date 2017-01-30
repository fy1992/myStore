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
	 * 超级管理员登录名
	 * @return
	 */
	public static String getAdminLoginName(){
		return bundel.getString("admin_loginName");
	}

	/**
	 * 超级管理员密码
	 * @return
	 */
	public static String getAdminPassword(){
		return bundel.getString("admin_password");
	}

	/**
	 * 读取文件路径
	 * @return
	 */
	public static String getFilePath(){
		return bundel.getString("filePath");
	}

}
