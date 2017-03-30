package cn.dahe.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 销售单据明细
 * Created by fy on 2017/3/30.
 */
@Table(name = "sale_info_item")
@Entity
public class SaleInfoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sale_info_id")
    private int saleInfoId;
    @Column(name = "goods_no")
    private int goodsNo;
    @Column(name = "goods_name")
    private int goodsName;
    //商品原价
    @Column(name = "goods_price")
    private double goodsPrice;
    //实收金额
    @Column(name = "real_price")
    private double realPrice;
    //预定数量
    @Column(name = "goods_num")
    private int goodsNum;
    //利润
    private double gain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSaleInfoId() {
        return saleInfoId;
    }

    public void setSaleInfoId(int saleInfoId) {
        this.saleInfoId = saleInfoId;
    }

    public int getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(int goodsNo) {
        this.goodsNo = goodsNo;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public int getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(int goodsName) {
        this.goodsName = goodsName;
    }
}
