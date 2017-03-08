package cn.dahe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

/**
 * 收银员
 * Created by fy on 2017/1/12.
 */
@Table(name = "t_cashier")
@Entity
public class Cashier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //姓名
    private String name;
    //密码
    private String password;
    //编号
    @Column(name = "cashier_no")
    private String cashierNo;
    //手机号
    private String mobile;
    //邮箱
    private String email;
    //地址
    private String addr;
    //微信 openId
    @Column(name = "open_id")
    private String openId;
    //收银员角色
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
    //状态 0 停用 1 启用
    private int status;
    //收银员对应的权限
    @ManyToMany(targetEntity = Permission.class, fetch = FetchType.EAGER)
    @JoinTable(name = "t_cashier_permission",
            joinColumns = {@JoinColumn(name = "cashier_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    @JsonIgnore
    private Set<Permission> permissions;

    //获取权限名称
    @Transient
    public Set<String> getPermissionName(){
        Set<Permission> permissions = getPermissions();
        Set<String> set = new HashSet<String>();
        for (Permission permission : permissions) {
            set.add(permission.getName());
        }
        return set;
    }

    //所属分店
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "store_name")
    private String storeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCashierNo() {
        return cashierNo;
    }

    public void setCashierNo(String cashierNo) {
        this.cashierNo = cashierNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
