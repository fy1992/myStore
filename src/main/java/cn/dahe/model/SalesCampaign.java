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
@Table(name = "t_sales_campaign")
@Entity
public class SalesCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //促销类型  0 打折促销 1 套餐促销 2 满额返现 3 换购促销 4 第二件打折
    private int type;
    //促销名称
    private String name;
    //适用范围
    @Column(name = "app_scope")
    private String appScope;
    //开始时间
    @Column(name = "start_date")
    private Date startDate;
    //结束时间
    @Column(name = "end_date")
    private Date endDate;
    //是否已过期 0 过期  1 不过期
    @Column(columnDefinition = "INT DEFAULT 1")
    private int overdue;
    //所属分店
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "store_name")
    private String storeName;
    //优惠券验证 0 不需要 1 需要
    @Column(columnDefinition = "INT DEFAULT 0")
    private int coupon;
    //说明
    private String description;
    //消费金额
    private double monetary;
    //返现金额
    @Column(name = "re_amount")
    private double reAmount;

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

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public int getOverdue() {
        return overdue;
    }

    public void setOverdue(int overdue) {
        this.overdue = overdue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMonetary() {
        return monetary;
    }

    public void setMonetary(double monetary) {
        this.monetary = monetary;
    }

    public double getReAmount() {
        return reAmount;
    }

    public void setReAmount(double reAmount) {
        this.reAmount = reAmount;
    }

    public String getAppScope() {
        return appScope;
    }

    public void setAppScope(String appScope) {
        this.appScope = appScope;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
