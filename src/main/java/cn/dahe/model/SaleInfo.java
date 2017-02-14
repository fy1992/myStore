package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 销售单据
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_sale_info")
@Entity
public class SaleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //所属分店
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "store_name")
    private String storeName;
    //流水号
    @Column(name = "serialNum")
    private String serialNum;
    //记录日期
    @Column(name = "mark_time")
    private Date markTime;
    //类型
    private int type;
    //收银员
    @Column(name = "cashier_id")
    private int cashierId;
    @Column(name = "cashier_name")
    private int cashierName;
    //商品数量
    @Column(name = "goods_num")
    private int goodsNum;
    //商品原价
    @Column(name = "goods_price")
    private int goodsPrice;
    //实收金额
    @Column(name = "real_price")
    private int realPrice;
    //利润
    private int gain;
    //导购员Id
    @Column(name = "sales_id")
    private int salesId;
    //导购员姓名
    @Column(name = "sales_name")
    private String salesName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public Date getMarkTime() {
        return markTime;
    }

    public void setMarkTime(Date markTime) {
        this.markTime = markTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public int getCashierName() {
        return cashierName;
    }

    public void setCashierName(int cashierName) {
        this.cashierName = cashierName;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(int realPrice) {
        this.realPrice = realPrice;
    }

    public int getGain() {
        return gain;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
