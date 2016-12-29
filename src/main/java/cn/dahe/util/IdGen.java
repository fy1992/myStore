package cn.dahe.util;

import java.util.UUID;

public class IdGen {
	
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获取加密的因子
	 * @return
	 */
	public static String getSalt(){
		return UUID.randomUUID().toString().replace("-", "").substring(0,6);
	}

}
