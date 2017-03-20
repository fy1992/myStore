package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品报损
 * Created by fy on 2017/3/20.
 */
@Table(name = "t_bad_goods")
@Entity
public class BadGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //报损时间
    @Column(name = "bad_time")
    private Date badTime;
    //报损金额
    private double price;
    //营业额占比
    private double proportion;
    //报损门店
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "store_name")
    private String storeName;
    //备注
    private String description;
    //条码
    @Column(name = "goods_no")
    private String goodsNo;
    //商品名称
    @Column(name = "goods_name")
    private String goodsName;
    //报损数量
    @Column(name = "bad_num")
    private int badNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBadTime() {
        return badTime;
    }

    public void setBadTime(Date badTime) {
        this.badTime = badTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getBadNum() {
        return badNum;
    }

    public void setBadNum(int badNum) {
        this.badNum = badNum;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }
}
