package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色权限表
 * Created by fy on 2016/12/30.
 */
@Table(name = "t_role_permission")
@Entity
public class RolePermission {
    @Id
    @GeneratedValue
    private int id;
    //角色Id
    @Column(name = "role_id")
    private int roleId;
    //权限Id
    @Column(name = "permission_id")
    private int permissionId;

}
