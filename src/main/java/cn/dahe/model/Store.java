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
    //营业时间
    @Column(name = "work_time")
    private String workTime;
    //店面简介
    private String description;


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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
