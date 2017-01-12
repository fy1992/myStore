package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 权限
 * Created by fy on 2016/12/30.
 */
@Table(name = "t_permission")
@Entity
public class Permission {
    @Id
    @GeneratedValue
    private int id;
    //权限名称
    private String name;
    //权限描述
    private String description;
    //权限对应的角色
    @ManyToMany
    @JoinTable(name = "t_role_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> role;
    //权限对应的用户
    @ManyToMany
    @JoinTable(name = "t_user_permission",
            joinColumns = {@JoinColumn(name = "permission_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<Permission> permissionList;

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

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
