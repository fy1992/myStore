package cn.dahe.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
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

	//密码加盐加密
	public static String encryptPassword(String username, String password){
	    if(StringUtils.isBlank(username)){
            return "";
        }
        if(StringUtils.isBlank(password)){
            return "";
        }
        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator();
        String salt = secureRandomNumberGenerator.nextBytes().toHex();
        return new Md5Hash(password, username + salt, 2).toBase64();
	}

	public static String getHash(String str) {
		StringBuilder sb = new StringBuilder();
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes());
			for (byte b : md5.digest()) {
				sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
        System.out.println(encryptPassword("大河网", "dahewang123"));
    }
}
