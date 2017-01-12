package cn.dahe.shiro;

import cn.dahe.dao.IUserDao;
import cn.dahe.model.Permission;
import cn.dahe.model.Role;
import cn.dahe.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * shiro 自实现realm
 * Created by fy on 2016/12/30.
 */
@Component("myRealm")
public class MyRealm extends AuthorizingRealm {
    @Resource
    private IUserDao userDao;

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录时输入的用户名
        String loginName = SecurityUtils.getSubject().getPrincipal().toString();
        if(StringUtils.isNotEmpty(loginName)){
            //到数据库查是否有此对象
            User user = userDao.findByLoginName(loginName);
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Role role = user.getRole();
            Set<String> roleSet = new HashSet<>();
            roleSet.add(role.getRoleName());
            //用户的角色集合
            info.setRoles(roleSet);
            //用户的角色对应的所有权限，和用户的自定义权限
            info.addStringPermissions(role.getPermissionsName());
            Set<Permission> permissionSet = user.getPermissionSet();
            Set<String> permissionNameSet = new HashSet<>();
            for(Permission permission : permissionSet){
                permissionNameSet.add(permission.getName());
            }
            info.addStringPermissions(permissionNameSet);
            return info;
        }
        return null;
    }

    /**
     * 登录认证;
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //查出是否有此用户
        User user = userDao.findByLoginName(token.getUsername());
        if(user != null){
            if(user.getStatus() == 0){
                throw new DisabledAccountException();//账号被禁用
            }
            //若存在，将此用户存放到登录认证info中
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
            Session session = SecurityUtils.getSubject().getSession();
            // 当验证都通过后，把用户信息放在session里
            session.setAttribute("loginUser", user);
            session.setAttribute("loginUserId", user.getId());
            return info;
        }
        return null;
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }
    /**
     * 更新用户信息缓存.
     */
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    /**
     * 清除用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    /**
     * 清除用户信息缓存.
     */
    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 清空所有缓存
     */
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }


    /**
     * 清空所有认证缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
