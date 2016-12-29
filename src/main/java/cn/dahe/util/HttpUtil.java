package cn.dahe.util;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

public class HttpUtil {
	
	
	 private static class TrustAnyTrustManager implements X509TrustManager {
		 
	        public void checkClientTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }
	 
	        public void checkServerTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	        }
	 
	        public X509Certificate[] getAcceptedIssuers() {
	            return new X509Certificate[] {};
	        }
	    }
	 
	    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    }
	 
	    /**
	     * post方式请求服务器(https协议)
	     * 
	     * @param url
	     *            请求地址
	     * @param content
	     *            参数
	     * @param charset
	     *            编码
	     * @return
	     * @throws NoSuchAlgorithmException
	     * @throws KeyManagementException
	     * @throws IOException
	     */
	    public static String post(String url, String content, String charset)
	            throws NoSuchAlgorithmException, KeyManagementException,
	            IOException {
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
	                new java.security.SecureRandom());
	 
	        URL console = new URL(url);
	        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
	        conn.setSSLSocketFactory(sc.getSocketFactory());
	        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
	        conn.setDoOutput(true);
	        conn.connect();
	        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	        out.write(content.getBytes(charset));
	        // 刷新、关闭
	        out.flush();
	        out.close();
	        InputStream is = conn.getInputStream();
	        InputStreamReader isReader = new InputStreamReader(is, "UTF-8");
	        BufferedReader br = new BufferedReader(isReader);
	        String line = "";
	        StringBuffer sbr = new StringBuffer();
	        while((line=br.readLine())!=null){
	        	sbr.append(line);
	        }
	        return sbr.toString();
	        /*if (is != null) {
	            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            while ((len = is.read(buffer)) != -1) {
	                outStream.write(buffer, 0, len);
	            }
	            is.close();
	            return outStream.toByteArray();
	        }
	        return null;*/
	    }
	    
	    
	    public static String get(String url,  String charset)
	            throws NoSuchAlgorithmException, KeyManagementException,
	            IOException {
	        SSLContext sc = SSLContext.getInstance("SSL");
	        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
	                new java.security.SecureRandom());
	 
	        URL console = new URL(url);
	        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
	        conn.setSSLSocketFactory(sc.getSocketFactory());
	        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
	        conn.setDoOutput(true);
	        conn.connect();
	        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
	        // 刷新、关闭
	        out.flush();
	        out.close();
	        InputStream is = conn.getInputStream();
	        InputStreamReader isRer = new InputStreamReader(is,charset);
	        BufferedReader buffer = new BufferedReader(isRer);
	        StringBuffer stbuf = new StringBuffer();
	        String con ="";
	        while((con=buffer.readLine())!=null){
	        	stbuf.append(con);
	        }
	        return stbuf.toString();
	        
	    }
}
