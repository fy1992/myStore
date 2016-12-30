package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 后台登录用户
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_user")
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    //手机号
    private String phone;
    //密码
    private String password;
    //用户名
    private String username;
    //所属店面Id
    @Column(name = "store_id")
    private int storeId;
    //所属店面名称
    @Column(name = "store_name")
    private String storeName;
    //状态 0 停用  1 启用
    private int status;
    //创建时间
    @Column(name = "create_date")
    private Date createDate;
}
