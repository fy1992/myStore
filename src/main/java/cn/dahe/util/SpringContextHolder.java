package cn.dahe.util;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware,
		DisposableBean {
	
	private static final Logger logger = LoggerFactory
			.getLogger(SpringContextHolder.class);

	private static ApplicationContext applicationContext = null;
	
	public static ApplicationContext getApplicationContext(){
		assertContextInject();
		return applicationContext;
	}
	
	/**
	 * 检查 applicationContext不为空
	 */
	private static void assertContextInject() {
		Validate.validState(applicationContext != null,
				"applicationContext属性未注入，请在applicationContext.xml中定义SpirngContextHolder");
	}
	
	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null.
	 */
	private static void clearHolder() {
		if (logger.isDebugEnabled()) {
			logger.debug("清除SpringContextHolder中的ApplicationContext:"
					+ applicationContext);
		}
		applicationContext = null;
	}

	@Override
	public void destroy() throws Exception {
		SpringContextHolder.clearHolder();
	}
    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		if (SpringContextHolder.applicationContext != null) {
			logger.info("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
					+ SpringContextHolder.applicationContext);
		}
		SpringContextHolder.applicationContext = arg0;
	}
	
	/**
	 * 获取根目录
	 * @return
	 */
	public static String getRootPath(){
		String path ="";
		try {
			path = getApplicationContext().getResource("").getFile().getAbsolutePath();
		} catch (IOException e) {
			logger.warn("获取根目录失败！");
			e.printStackTrace();
		}
		return path;
	}
	
	/**
	 * 获取指定的目录
	 * @param ml
	 * @return
	 */
	public static String getMuluPath(String ml){
		String mulu = "/"+ml;
		String path ="";
		
		try {
			path = getApplicationContext().getResource(mulu).getFile().getAbsolutePath();
		} catch (IOException e) {
			logger.warn("获取目录失败！");
			e.printStackTrace();
		}
		return path;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		assertContextInject();
		return (T) applicationContext.getBean(name);
	}

}
