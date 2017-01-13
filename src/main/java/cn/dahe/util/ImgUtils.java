package cn.dahe.util;

import com.lowagie.text.Image;
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
	
	/**
	 * 检测图片宽高
	 * */
	public static boolean checkImgSize(String imgUrl){
		try {
			URL url = new URL(imgUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			if(conn.getResponseCode() == 200){
				Image image = Image.getInstance(imgUrl);
				float weight = image.getWidth();
				float height = image.getHeight();
				log.info("weight : " + weight + " | height : " + height + " | result : " + weight / height);
				return weight / height > 2;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(checkImgSize("http://app.henan.gov.cn/img/2016/10/13/50f6f99147fd24c20840198054dbe041.jpg"));
	}
}
