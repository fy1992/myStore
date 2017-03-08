package cn.dahe.service.impl;

import cn.dahe.dao.IPermissionDao;
import cn.dahe.dao.IRoleDao;
import cn.dahe.dao.IUserDao;
import cn.dahe.model.Permission;
import cn.dahe.model.Role;
import cn.dahe.model.User;
import cn.dahe.service.IRepairService;
import cn.dahe.util.ResourcesUtils;
import cn.dahe.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RepairServiceImpl implements IRepairService {
	private static Logger logger = LoggerFactory.getLogger(RepairServiceImpl.class);
	@Resource
	private IUserDao userDao;
	@Resource
    private IPermissionDao permissionDao;
	@Resource
    private IRoleDao roleDao;

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
    public void repairRole() {
        Role role = roleDao.findByRoleKey(ResourcesUtils.getRole(), 0);
        if(role == null){
            role = new Role();
            role.setStoreId(0);
            role.setStatus(1);
            role.setRoleName("系统管理员");
            role.setRoleKey("sys:admin");
            List<Permission> permissionList = permissionDao.findAll(0);
            Set<Permission> permissions = new HashSet<>(permissionList);
            role.setPermissions(permissions);
            roleDao.add(role);
        }
    }

    @Override
    public void repairPermission() {
        String permissions = ResourcesUtils.getPermissions();
        String[] permissionArr = permissions.split("#");
        for(int i = 0, len = permissionArr.length; i < len; i++){
            String[] p = permissionArr[i].split(",");
            Permission permission =  permissionDao.findByPerKey(p[1]);
            if(permission == null){
                logger.info("--- 初始化权限 ： " + p[0]);
                permission = new Permission();
                permission.setType(0);
                permission.setName(p[0]);
                permission.setPerKey(p[1]);
                permission.setUrl(p[2]);
                permission.setParentId(Integer.parseInt(p[3]));
                permission.setLevel(1);
                permission.setResourceType(1);
                permissionDao.add(permission);
            }
        }
    }

    @Override
    public void repairMenu() {
        String menuStr = ResourcesUtils.getMenu();
        if(StringUtils.isNotBlank(menuStr)){
            String[] menuArr= menuStr.split("@");
            for(int i = 0, len = menuArr.length; i < len; i++) {
                String[] menu = menuArr[i].split(",");
                Permission permission =  permissionDao.findByPerKey(menu[1]);
                if(permission == null){
                    permission = new Permission();
                    permission.setPerKey(menu[1]);
                    permission.setUrl("#");
                    permission.setType(0);
                    permission.setLevel(0);
                    permission.setName(menu[0]);
                    permission.setIconType(menu[2]);
                    permission.setResourceType(0);
                    permissionDao.add(permission);
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
        user.setRegisterDate(new Date());
        user.setRank(0);
		user.setPassword(SecurityUtil.MD5(ResourcesUtils.getAdminPassword()));
        Role role = roleDao.findByRoleKey(ResourcesUtils.getRole(), 0);
        user.setRole(role);
        userDao.add(user);
    }
}
