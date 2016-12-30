package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户角色关系表
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_user_role")
@Entity
public class UserRole {
    @Id
    @GeneratedValue
    private int id;
    //用户Id
    @Column(name = "user_id")
    private int userId;
    //角色Id
    @Column(name = "role_id")
    private int roleId;
}
