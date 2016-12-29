package cn.dahe.attack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SQL注入攻击
 * @author Administrator
 *
 */
public class SqlInject implements Istrip {
	
	private static final Logger logger = LoggerFactory.getLogger(SqlInject.class);

	@Override
	public String strip(String value) {
		logger.info("sql注入虑前的值:"+value);
		value = value.toLowerCase();// 统一转为小写
		String badStr = "'|and|exec|execute|count|drop|*|chr|mid|master|truncate|"
				+ "char|declare|sitename|net user|xp_cmdshell|or|like'insert|create|"
				+ "table|from|grant|use|group_concat|column_name|order|by|table_schema|"
				+ "information_schema.columns|union|where|select|delete|update"
				+ ";|-|--|+|like|//|/|%|#";// 过滤掉的sql关键字，可以手动添加
		String[] badStrs = badStr.split("\\|");
        if(!value.matches("\\d{4}(\\-|\\/|\\.)\\d{1,2}\\1\\d{1,2}") && !value.matches("(-)?[1-9][0-9]*")) {
            for (int i = 0; i < badStrs.length; i++) {
                value = value.replace(badStrs[i], "");
            }
        }
        value = value.replaceAll("('.+--)|(--)|(\\|)|(%7C)", "");
		logger.info("sql 注入滤后的值:" + value);
		return value;
	}

}
