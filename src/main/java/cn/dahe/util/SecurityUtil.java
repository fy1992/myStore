package cn.dahe.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
	
	public static String MD5(String password){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			logger.info("MD5加密出错");
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
	}
}
