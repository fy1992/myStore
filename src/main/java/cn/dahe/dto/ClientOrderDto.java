package cn.dahe.dto;

import cn.dahe.model.ClientOrder;

import java.util.Date;

/**
 * Created by fy on 2017/3/16.
 */
public class ClientOrderDto {
    //订单细缆  (商品编码  数量)
    private String orderItemInfo;

    private int id;
    //下单时间
    private Date orderTime;
    //订单类型 0 网单 1 店单
    private int type;
    //订单状态 0 等待处理  1 配送途中  2 完成
    private int status = 0;
    //电话
    private String phone;
    //姓名
    private String orderName;
    //备注
    private String description;
    //支付方式 0 会员支付 1 货到付款
    private int payType = 0;
    //地址
    private String orderAddr;
    //总价
    private double totalPrice = 0;
    //总数量
    private int orderNums = 0;
    //订单来源类型 0 自营  1 美团  2 饿了么  3 百度外卖
    private int fromType;

    public String getOrderItemInfo() {
        return orderItemInfo;
    }

    public void setOrderItemInfo(String orderItemInfo) {
        this.orderItemInfo = orderItemInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getFromType() {
        return fromType;
    }

    public void setFromType(int fromType) {
        this.fromType = fromType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getOrderAddr() {
        return orderAddr;
    }

    public void setOrderAddr(String orderAddr) {
        this.orderAddr = orderAddr;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderNums() {
        return orderNums;
    }

    public void setOrderNums(int orderNums) {
        this.orderNums = orderNums;
    }
}
