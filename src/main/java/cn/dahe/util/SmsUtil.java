package cn.dahe.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SmsUtil {

	public static void main(String[] args) {
		
	}
	


	public static void sendSms(String phone, String content) {
		try {
			// 发送内容
			//String content = "您的手机号：" + phone + "，注册验证码：" + code
			//		+ "，如不是本人操作请忽略！【大河网】";
			// http://211.147.244.114:9801/SendMessage.jsp?userid=63516&password=a123456&destnumbers=186383977791&msg=test&sendtime=%22%22
			// 创建StringBuffer对象用来操作字符串
			StringBuffer sb = new StringBuffer(
					"http://120.25.135.43:9205/API/SendMessages.jsp?");

			// 向StringBuffer追加用户名
			sb.append("userid=Z1024");

			// 
			sb.append("&password=emcsll");
			
			sb.append("&spnumber=0079");

			// 向StringBuffer追加手机号码
			sb.append("&destnumbers="+phone);
			
			//sb.append("&sendtime="+URLEncoder.encode("\"\""));

			// 向StringBuffer追加消息内容转URL标准码
			sb.append("&msg=" + URLEncoder.encode(content, "utf-8"));

			// 创建url对象
			URL url = new URL(sb.toString());

			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			//connection.setRequestProperty("contentType", "utf8"); 
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));

			// 返回发送结果
			String inputline =null;
			while(( inputline =in.readLine())!=null){
				System.out.println(inputline);
			}

			// 返回结果
			
			in.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
