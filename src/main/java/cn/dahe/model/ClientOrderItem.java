package cn.dahe.model;

import javax.persistence.*;

/**
 * 客户端订单明细
 * Created by fy on 2017/3/16.
 */
@Table(name = "t_client_order_item")
@Entity
public class ClientOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //所属订单
    @Column(name = "client_order_id")
    private int clientOrderId;
    //预定数量
    @Column(name = "order_num")
    private int orderNum;
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

    public int getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(int clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
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
