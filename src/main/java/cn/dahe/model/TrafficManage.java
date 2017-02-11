package cn.dahe.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 货流管理
 * Created by fy on 2017/1/26.
 */
@Table(name = "t_traffic_manage")
@Entity
public class TrafficManage {
    @Id
    @GeneratedValue
    private int id;
    //下单时间
    @Column(name = "order_date")
    private Date orderDate;
    //货流单号
    @Column(name = "traffic_no")
    private String trafficNo;
    //货单类型 0 退货单 1 进货单 2 调货单
    @Column(name = "traffic_type")
    private int trafficType;
    //所属店面 (进货门店)
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "store_name")
    private String storeName;
    //状态 0 待确认进货 1 已完成进货
    private int status;
    //总价
    @Column(name = "total_price")
    private int totalPrice;
    //出货门店id
    @Column(name = "out_store_id")
    private int outStoreId;
    //出货门店名称
    @Column(name = "out_store_name")
    private String outStoreName;
    //备注
    private String description;
    //预付款
    private int imprest;
    //货流量
    @Column(name = "goods_num")
    private int goodsNum;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getTrafficNo() {
        return trafficNo;
    }

    public void setTrafficNo(String trafficNo) {
        this.trafficNo = trafficNo;
    }

    public int getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(int trafficType) {
        this.trafficType = trafficType;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOutStoreId() {
        return outStoreId;
    }

    public void setOutStoreId(int outStoreId) {
        this.outStoreId = outStoreId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getOutStoreName() {
        return outStoreName;
    }

    public void setOutStoreName(String outStoreName) {
        this.outStoreName = outStoreName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImprest() {
        return imprest;
    }

    public void setImprest(int imprest) {
        this.imprest = imprest;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }
}
