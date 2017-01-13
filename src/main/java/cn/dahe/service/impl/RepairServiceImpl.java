package cn.dahe.service.impl;

import cn.dahe.dao.IUserDao;
import cn.dahe.model.User;
import cn.dahe.service.IRepairService;
import cn.dahe.util.ResourcesUtils;
import cn.dahe.util.SecurityUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RepairServiceImpl implements IRepairService {
	private static Log  logger = LogFactory.getLog(RepairServiceImpl.class);
	@Resource
	private IUserDao userDao;
	
	@Override
	public void repairUser() {
		logger.info("--- enter the repair user -----");
		String loginName = ResourcesUtils.getAdminLoginName();
		User user = userDao.findByLoginName(loginName);
		if(user == null){
            logger.info("--- 超级管理员不存在 ---");
			initUser(loginName);
		}
	}

    @Override
    public void repairPermission() {

    }

    private void initUser(String loginName){
		User user = new User();
		user.setLoginName(loginName);
		user.setStatus(1);
        user.setUsername("系统管理员");
		user.setPassword(SecurityUtil.MD5(ResourcesUtils.getAdminPassword()));
		userDao.add(user);
	}
}
