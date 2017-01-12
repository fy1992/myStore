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
	
	public static String getAdminLoginName(){
		return bundel.getString("admin_loginName");
	}

	public static String getAdminPassword(){
		return bundel.getString("admin_password");
	}

}
