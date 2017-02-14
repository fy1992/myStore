package cn.dahe.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 后台登录用户
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //手机号
    private String phone;
    //用户名
    private String username;
    //登录名（账号）
    @Column(name = "login_name")
    private String loginName;
    //密码
    private String password;
    //邮箱
    private String email;
    //注册时间
    @Column(name = "register_date")
    private Date registerDate;
    //店铺Id  默认值 0
    @Column(name ="store_id", columnDefinition = "INT DEFAULT 0")
    private int storeId = 0;
    //店铺名称
    @Column(name ="store_name")
    private String storeName;
    //联系地址
    private String addr;
    //状态  0 停用 1 启用
    private int status;
    //用户对应的角色
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    //用户对应的权限
    @ManyToMany
    @JoinTable(name = "t_user_permission",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<Permission> permissionSet = new HashSet<>();

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
