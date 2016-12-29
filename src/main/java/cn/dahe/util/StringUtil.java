package cn.dahe.util;

import cn.dahe.attack.WafHelper;
import org.apache.commons.lang3.StringUtils;

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
}
