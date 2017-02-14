package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 导购员
 * Created by fy on 2017/1/12.
 */
@Table(name = "t_sales")
@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //导购员编号
    @Column(name = "sales_no")
    private String salesNo;
    //导购员姓名
    @Column(name = "sales_name")
    private String salesName;
    //手机
    private String phone;
    //状态  0 禁用 1 启用
    private int status;
    //提成
    private int percentage;
    //是否开启精准营销  0 不开启 1  开启
    @Column(name = "is_pre_mark")
    private int isPreMark;
    //精准营销有效期
    @Column(name = "pre_mark_time")
    private Date preMarkTime;
    //所属分店
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "store_name")
    private int storeIName;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalesNo() {
        return salesNo;
    }

    public void setSalesNo(String salesNo) {
        this.salesNo = salesNo;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getIsPreMark() {
        return isPreMark;
    }

    public void setIsPreMark(int isPreMark) {
        this.isPreMark = isPreMark;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getStoreIName() {
        return storeIName;
    }

    public void setStoreIName(int storeIName) {
        this.storeIName = storeIName;
    }

    public Date getPreMarkTime() {
        return preMarkTime;
    }

    public void setPreMarkTime(Date preMarkTime) {
        this.preMarkTime = preMarkTime;
    }
}
