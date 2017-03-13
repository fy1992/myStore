package cn.dahe.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存工具类
 * @author fy
 */
public class CacheUtils {
	
	private static CacheManager cacheManager = SpringContextHolder.getBean("cacheManager");
	
	public static CacheManager getCacheManager(){
		return cacheManager;
	}
	//商品缓存
	private static final String GOODS_CACHE = "goodsCache";

	//客户端用户登录缓存
	private static final String CASHIER_USER = "cashierUser";

	/**
	 * @param key
	 * @param value
	 * */
	public static void putGoods(String key, Object value){
		put(GOODS_CACHE, key, value);
	}

	/**
	 * 得到缓存
	 * @param key
	 * */
	public static Object getGoods(String key){
		return get(GOODS_CACHE, key);
	}

	/**
	 * 移除缓存
	 * @param key
	 * */
	public static void removeGoods(String key){
		remove(GOODS_CACHE, key);
	}

    /**
     * @param key
     * @param value
     * */
    public static void putCashierUser(String key, Object value){
        put(CASHIER_USER, key, value);
    }

    /**
     * 得到缓存
     * @param key
     * */
    public static Object getCashierUser(String key){
        return get(CASHIER_USER, key);
    }

    /**
     * 移除缓存
     * @param key
     * */
    public static void removeCashierUser(String key){
        remove(CASHIER_USER, key);
    }

	/**
	 * 获取一个cache，没有则创建
	 * @param cacheName
	 */
	private static Cache getCache(String cacheName){
		Cache cache = cacheManager.getCache(cacheName);
		if(cache == null){
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

	//通用
	public static Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element == null ? null : element.getObjectValue();
	}

	public static void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}

	public static void removeAll(String cacheName){
		getCache(cacheName).removeAll();
	}

}
