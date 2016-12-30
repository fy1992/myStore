package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String name;
    //角色类型  0 店长 1 收银员 2 导购员（服务员）
    private int type;

}
