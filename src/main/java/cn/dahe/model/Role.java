package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色
 * Created by fy on 2016/12/30.
 */
@Table(name = "t_role")
@Entity
public class Role {
    @Id
    @GeneratedValue
    private int id;
    //角色名称
    @Column(name = "role_name")
    private String roleName;
    //角色类型  0 店长 1 收银员 2 导购员（服务员）
    private int type;
    //角色描述
    private String description;
    //角色对应的权限
    @ManyToMany
    @JoinTable(name = "t_role_permission",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name="permission_id")})
    private List<Permission> permissionList;
    //角色对应的用户
    @ManyToMany
    @JoinTable(name = "t_user_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> userList;

    @Transient
    public Set<String> getPermissionsName(){
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
