package cn.dahe.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 后台登录用户
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_user")
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    //手机号
    private String phone;
    //用户名
    @NotEmpty(message = "用户名不能为空")
    private String username;
    //登录名
    @Column(name = "login_name")
    @NotEmpty(message = "登录名不能为空")
    private String loginName;
    //密码
    @NotEmpty(message = "密码不能为空")
    private String password;
    //所属店面Id
    @Column(name = "store_id")
    private int storeId;
    //所属店面名称
    @Column(name = "store_name")
    private String storeName;
    //状态 0 停用  1 启用
    private int status;
    //创建时间
    @Column(name = "create_date")
    private Date createDate;
    //上次登录时间
    @Column(name="last_login_date")
    private Date lastLoginDate;
    //用户对应的权限
    @ManyToMany
    @JoinTable(name = "t_user_permission",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<Permission> permissionList;
    //用户对应的角色
    @ManyToMany
    @JoinTable(name = "t_user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roleList;

    @Transient
    public Set<String> getRolesName(){
        List<Role> roles = getRoleList();
        Set<String> set = new HashSet<String>();
        for (Role role : roles) {
            set.add(role.getRoleName());
        }
        return set;
    }

    @Transient
    public Set<String> getPermissionName(){
        List<Permission> permissions = getPermissionList();
        Set<String> set = new HashSet<String>();
        for (Permission permission : permissions) {
            set.add(permission.getName());
        }
        return set;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
