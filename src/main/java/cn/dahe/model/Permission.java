package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
