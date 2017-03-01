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
	 * 读取文件路径（图片）
	 * @return
	 */
	public static String getFilePath(){
		return bundel.getString("filePath");
	}

	/**
	 * 文件链接（图片）
	 * @return
	 */
	public static String getFileUrl(){
		return bundel.getString("fileUrl");
	}

	/**
	 * 收银员用户密码
	 * @return
	 */
	public static String getCashierPassword(){
		return bundel.getString("cashier_password");
	}

    /**
     * 获取全部目录
     * @return
     */
	public static String getMenu(){
        String str= null;
        try{
            str = new String(bundel.getString("menu").getBytes("ISO-8859-1"), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
	}

    /**
     * 上传文件的大小限制
     * @return
     */
	public static String getFileSize(){
	    return bundel.getString("fileSize");
    }
}
