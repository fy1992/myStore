package cn.dahe.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单编号生成工具类
 * @author Administrator
 *
 */
public class OrderNoUtil {

	public synchronized static String generateWbNo() {
		Date date = new Date();
		try {
			Thread.sleep(10);
		} catch (Exception ignore) {
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSS");
		String str = "00" + sdf.format(date);
		str = str.substring(0, 16);
		return str;
	}

}
