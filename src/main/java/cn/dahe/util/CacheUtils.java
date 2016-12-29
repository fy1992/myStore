package cn.dahe.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存工具类
 * @author Administrator
 *
 */
public class CacheUtils {
	
	private static CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
	
	private static final String SYS_CACHE="sys_cache";
	
	public static CacheManager getCacheManager(){
		return cacheManager;
	}
	
	/**
	 * 获取一个cache，没有则创建
	 * @param cacheName
	 * @return
	 */
	private static Cache getCache(String cacheName){
		Cache cache = cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}
	
	public static void remove(String cacheName,String key){
		getCache(cacheName).remove(key);
	}
	
	public static void put(String cacheName,String key,String value){
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}
	
	public static Object get(String cacheName,String key){
		Element element = getCache(cacheName).get(key);
		return element==null?null:element.getObjectValue();
	}
	/**
	 * 删除缓存
	 * @param key
	 */
	public static void remove(String key){
		remove(SYS_CACHE, key);
	}
	/**
	 * 向缓存写入
	 * @param key
	 * @param value
	 */
	public static void put(String key,String value){
		put(SYS_CACHE, key, value);
	}
	/**
	 * 对外的得到的key的值
	 * @param key 缓存的key
	 * @return
	 */
	public static Object get(String key){
		return get(SYS_CACHE, key);
	}

}
