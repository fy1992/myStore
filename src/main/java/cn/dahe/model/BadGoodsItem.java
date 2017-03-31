package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 报损明细
 * Created by fy on 2017/3/31.
 */
@Table(name = "t_bad_goods_item")
@Entity
public class BadGoodsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //报损单id
    @Column(name = "bad_goods_id")
    private int badGoodsId;
    //预定数量
    @Column(name = "goods_num")
    private int goodsNum;
    //商品编码
    @Column(name = "goods_no")
    private String goodsNo;
    @Column(name = "goods_name")
    private String goodsName;
    //价格
    private double price;
    //单位Id
    @Column(name = "goods_unit_id")
    private int goodsUnitId;
    @Column(name = "goods_unit_name")
    private String goodsUnitName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBadGoodsId() {
        return badGoodsId;
    }

    public void setBadGoodsId(int badGoodsId) {
        this.badGoodsId = badGoodsId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getGoodsUnitId() {
        return goodsUnitId;
    }

    public void setGoodsUnitId(int goodsUnitId) {
        this.goodsUnitId = goodsUnitId;
    }

    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName;
    }
}
