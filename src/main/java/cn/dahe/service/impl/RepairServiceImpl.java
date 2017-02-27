package cn.dahe.service.impl;

import cn.dahe.dao.ISysMenuDao;
import cn.dahe.dao.IUserDao;
import cn.dahe.model.SysMenu;
import cn.dahe.model.User;
import cn.dahe.service.IRepairService;
import cn.dahe.util.ResourcesUtils;
import cn.dahe.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RepairServiceImpl implements IRepairService {
	private static Logger logger = LoggerFactory.getLogger(RepairServiceImpl.class);
	@Resource
	private IUserDao userDao;
	@Resource
	private ISysMenuDao sysMenuDao;

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

    @Override
    public void repairMenu() {
        String menuStr = ResourcesUtils.getMenu();
        if(StringUtils.isNotBlank(menuStr)){
            String[] menuArr= menuStr.split(",");
            for(int i = 0, len = menuArr.length; i < len; i++) {
                SysMenu sysMenu = sysMenuDao.queryByName(menuArr[i]);
                if(sysMenu == null){
                    sysMenu = new SysMenu();
                    sysMenu.setName(menuArr[i]);
                    sysMenuDao.add(sysMenu);
                }
            }
        }
    }

    private void initUser(String loginName){
		User user = new User();
		user.setLoginName(loginName);
		user.setStatus(1);
        user.setUsername("系统管理员");
        user.setStoreId(0);
        user.setRank(0);
		user.setPassword(SecurityUtil.MD5(ResourcesUtils.getAdminPassword()));
		userDao.add(user);
	}
}
