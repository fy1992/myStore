package cn.dahe.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtil {
	
	private static final Logger logger = LoggerFactory
			.getLogger(DateUtil.class);
	
	
	public static String formate(Date date){
		return format(date,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 日期转为字符串
	 * @param date  日期
	 * @param pattern 规则
	 * @return
	 */
	public static String format(Date date,String pattern){
		if(date==null){
			return "null";
		}
		if(pattern==null||pattern.equals("")||pattern.equals("null")){
			pattern="yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(pattern).format(date);
	}
	/**
	 * 字符串转为日期
	 * @param date  字符串
	 * @param pattern 规则
	 * @return
	 */
	public static Date format(String date,String pattern){
		if(date==null||date.equals("")||date.equals("null")){
			return new Date();
		}
		if(pattern==null||pattern.equals("")||pattern.equals("null")){
			pattern="yyyy-MM-dd HH:mm:ss";
		}
		Date d = null;
		try {
			d = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
			logger.info("string to date is error!!!");
		}
		return d;
	}
	public static Date format(String date){
		return format(date,null);
	}
	
	/**
	 * 查看距离今天的天数
	 * @param date
	 * @return
	 */
	public static long pastDate(Date date){
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}
	/**
	 * 日期的开始时间
	 * @param date
	 * @return
	 */
    public static Date getDateStart(Date date){
    	if(date==null){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			date = sdf.parse(format(date, "yyyy-MM-dd")+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return date;
    }
    /**
     * 日期的结束时间
     * @param date
     * @return
     */
    public static Date getDateEnd(Date date){
    	if(date==null){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			date = sdf.parse(format(date, "yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return date;
    }
	
	public static void main(String[] args) {
        System.out.println(LocalDate.now());
    }
}
