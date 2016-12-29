package cn.dahe.util;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * 判断来源的IP地址
 * @author Administrator
 *
 */
public class IpUtil {
	
	public static  String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		return ip;
	}
	
	public static boolean isAllow(String ip) throws UnsupportedEncodingException, IOException{
		// ip限定ipip.net
		if(ip.startsWith("10.")) return true;
    	
		String result = "";
		BufferedReader in = null;
		URL url = new URL("http://api.ipip.net/ip/search?token=4d3f8ce6e67854db729080c20753995430b8a461&ip="+ip);
		URLConnection connection = url.openConnection();   
		connection.setRequestProperty("Charset", "utf-8");
		connection.connect();
		Map<String, List<String>> map = connection.getHeaderFields();
		// 遍历所有的响应头字段
		/*for (String key : map.keySet()) {
		   System.out.println(key + "--->" + map.get(key));
		}*/
		// 定义 BufferedReader输入流来读取URL的响应
		in = new BufferedReader(new InputStreamReader(
		   connection.getInputStream(), "utf-8"));
		   String line;
		   while ((line = in.readLine()) != null) {
		        result += line;
		}
		
		in.close();
		
		Object obj= JSONValue.parse(result);
		JSONObject jobj=(JSONObject)obj;
		JSONArray jarr=(JSONArray)jobj.get("data");
		
		//开始判断
		String exceptProvince = ResourceBundle.getBundle("config").getString("exceptProvinceKeys");
		String[] exceptProvinceArr = StringUtils.split(exceptProvince, "&");
		List<String> exceptProvinceList = Arrays.asList(exceptProvinceArr);
		if(!exceptProvinceList.contains(jarr.get(1))){
			return false;
		}else{
			return true;
		}
	        
	}

}
