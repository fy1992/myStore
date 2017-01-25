package cn.dahe.util;

import cn.dahe.attack.WafHelper;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil extends StringUtils{
	/**
	 * 过滤sql注入和xss攻击
	 * */
	public static String filterSqlInjectAndXss(String str){
        if(StringUtils.isEmpty(str) || StringUtils.isBlank(str)){
            return "";
        }
        String result = WafHelper.stripXssSql(str);
		if(StringUtils.isEmpty(result)){
            result = "";
		}
		return result;
	}

	/**
	 * 是否是纯数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher m = p.matcher(str);
        return m.find();
	}

	/**
	 * 是否是电话
	 * @param str
	 * @return
	 */
	public static boolean isMobile(String str){
		return str.length() > 6 && str.length() <= 11 && str.matches("^1[34578][0-9]{9}$");
	}

	/**
	 * 是否是纯拼音
	 * @param str
	 * @return
	 */
	public static boolean isPinyin(String str){
        Pattern p = Pattern.compile("(a[io]?|ou?|e[inr]?|ang?|ng|[bmp](a[io]?|[aei]ng?|ei|ie?|ia[no]|o|u)|pou|me|m[io]u|[fw](a|[ae]ng?|ei|o|u)|fou|wai|[dt](a[io]?|an|e|[aeio]ng|ie?|ia[no]|ou|u[ino]?|uan)|dei|diu|[nl](a[io]?|ei?|[eio]ng|i[eu]?|i?ang?|iao|in|ou|u[eo]?|ve?|uan)|nen|lia|lun|[ghk](a[io]?|[ae]ng?|e|ong|ou|u[aino]?|uai|uang?)|[gh]ei|[jqx](i(ao?|ang?|e|ng?|ong|u)?|u[en]?|uan)|([csz]h?|r)([ae]ng?|ao|e|i|ou|u[ino]?|uan)|[csz](ai?|ong)|[csz]h(ai?|uai|uang)|zei|[sz]hua|([cz]h|r)ong|y(ao?|[ai]ng?|e|i|ong|ou|u[en]?|uan))");
        Matcher m = p.matcher(str);
        return m.find();
	}
}
