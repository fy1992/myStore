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
    @ManyToMany(targetEntity = Permission.class, fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<Permission> permissions = new HashSet<>();
    //角色对应的用户
    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "role")
    @JsonIgnore
    private Set<User> users = new HashSet<>();
    //角色对应的收银员
    @OneToMany(targetEntity = Cashier.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "role")
    @JsonIgnore
    private Set<Cashier> cashiers = new HashSet<>();
    //所属分店
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "store_name")
    private String storeName;
    //状态  0  停用 1 启用
    private int status;
    //是否同步相应收银员的权限 0 不同步 1 同步
    @Column(name = "is_asyne")
    private int isAsync;

    @Transient
    public Set<String> getPermissionsName(){
        Set<Permission> permissions = getPermissions();
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

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(Set<Cashier> cashiers) {
        this.cashiers = cashiers;
    }
}
