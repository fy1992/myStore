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
import javax.persistence.Table;
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
    //所属父资源
    @Column(name = "pid")
    private int parentId;
    //资源类型  0  栏目  1 菜单
    @Column(name = "resource_type")
    private int resourceType;
    //图标类型
    @Column(name = "icon_type")
    private String iconType;
    //权限key 授权 （多个用逗号分隔）
    @Column(name = "per_key")
    private String perKey;
    //权限描述
    private String description;
    //权限url
    private String url;
    //资源等级  1 一级（只有超管可见） 2 门店可见
    private int level;
    //权限对应的角色
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "t_role_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @JsonIgnore
    private Set<Role> roles;
    //权限对应的用户
    @ManyToMany(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "t_user_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @JsonIgnore
    private Set<User> users;
    //权限对应的收银员
    @ManyToMany(targetEntity = Cashier.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "t_cashier_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "cashier_id")})
    @JsonIgnore
    private Set<Cashier> cashiers;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
