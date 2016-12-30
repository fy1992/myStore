package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 店面
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_store")
@Entity
public class Store {
    @Id
    @GeneratedValue
    private int id;
    //店面名称
    private String name;
    //店面类型  0 总店 1 分店
    private int type;
    //创建日期
    @Column(name = "create_date")
    private Date createDate;

}
