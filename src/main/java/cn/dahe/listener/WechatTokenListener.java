package cn.dahe.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.dahe.util.WechatConstant;
import weixin.popular.support.TokenManager;

public class WechatTokenListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// WEB容器 初始化时调用
		TokenManager.init(WechatConstant.appid, WechatConstant.secret);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// WEB容器 关闭时调用
		TokenManager.destroyed();
	}
}
