package cn.dahe.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 * Created by fy on 2016/12/30.
 */
@Table(name = "t_role")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //角色名称
    @Column(name = "role_name")
    private String roleName;
    //角色key
    @Column(name = "role_key")
    private String roleKey;
    //角色描述
    private String description;

    //角色对应的权限
    @ManyToMany
    @JoinTable(name = "t_role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name="permission_id")})
    private Set<Permission> permissionSet = new HashSet<>();

    //角色对应的登录用户
    @ManyToMany
    @JoinTable(name = "t_user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> userSet = new HashSet<>();

    //角色对应的导购员
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<Cashier> cashierSet = new HashSet<>();

    //所属分店
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "store_name")
    private int storeName;
    //状态  0  停用 1 启用
    private int status;
    //是否同步相应收银员的权限 0 不同步 1 同步
    @Column(name = "is_asyne")
    private int isAsync;
    @Transient
    public Set<String> getPermissionsName(){
        Set<Permission> permissions = getPermissionSet();
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<Cashier> getCashierSet() {
        return cashierSet;
    }

    public void setCashierSet(Set<Cashier> cashierSet) {
        this.cashierSet = cashierSet;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getStoreName() {
        return storeName;
    }

    public void setStoreName(int storeName) {
        this.storeName = storeName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsAsync() {
        return isAsync;
    }

    public void setIsAsync(int isAsync) {
        this.isAsync = isAsync;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }
}
