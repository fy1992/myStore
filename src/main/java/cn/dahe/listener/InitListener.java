package cn.dahe.listener;

import cn.dahe.service.IRepairService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by fy on 2017/1/11.
 */
public class InitListener implements ServletContextListener,
        ServletContextAttributeListener, HttpSessionListener {
    private static final Log logger = LogFactory.getLog(InitListener.class);

    private static ApplicationContext ctx = null;

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent arg0) {
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent arg0) {
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent arg0) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        logger.info("--enter the listener--");
        ctx = WebApplicationContextUtils.getWebApplicationContext(evt.getServletContext());
        IRepairService repairService = (IRepairService) ctx.getBean("repairServiceImpl");
        repairService.repairUser();
    }
}
