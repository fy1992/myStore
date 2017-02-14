package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 促销活动
 * Created by fy on 2017/1/11.
 */
@Table(name = "t_sale_pro")
@Entity
public class SalePro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //促销类型  0 1 2 3 4 5
    private int type;
    //促销名称
    private String name;
    //开始时间
    @Column(name = "start_date")
    private Date startDate;
    //结束时间
    @Column(name = "end_date")
    private Date endDate;
    //所属分店
    @Column(name = "store_id")
    private int storeId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
