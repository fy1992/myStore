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
import java.util.List;
import java.util.Set;

/**
 * 权限
 */
@Table(name = "t_permission")
@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //权限名称
    private String name;
    @Column(name = "pid")
    private int parentId;
    //权限key 授权 （多个用逗号分隔）
    @Column(name = "per_key")
    private String perKey;
    //权限描述
    private String description;
    //权限url
    private String url;
    //权限对应的角色
    @ManyToMany
    @JoinTable(name = "t_role_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roleSet;
    //权限对应的用户
    @ManyToMany
    @JoinTable(name = "t_user_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> userSet;
    //权限对应的收银员
    @ManyToMany
    @JoinTable(name = "t_cashier_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "cashier_id")})
    private Set<Cashier> cashierSet;

    //所属分店
    @Column(name = "store_id")
    private int storeId;
    //权限类型  0 web 端权限 1 client 端权限
    private int type;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getPerKey() {
        return perKey;
    }

    public void setPerKey(String perKey) {
        this.perKey = perKey;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", perKey='" + perKey + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", roleSet=" + roleSet +
                ", userSet=" + userSet +
                ", cashierSet=" + cashierSet +
                ", storeId=" + storeId +
                ", type=" + type +
                '}';
    }
}
