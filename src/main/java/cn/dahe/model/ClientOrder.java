package cn.dahe.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 冯源 on 2017/3/15.
 */
@Table(name = "t_client_order")
@Entity
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //下单时间
    @Column(name = "order_time")
    private Date orderTime;
    //所属门店
    @Column(name = "store_id")
    private int storeId;
    //订单类型 0 网单 1 店单
    private int type;
    //订单状态 0 等待处理  1 配送途中  2 完成
    private int status;
    //电话
    private String phone;
    //姓名
    @Column(name = "order_name")
    private String orderName;
    //备注
    private String description;
    //支付方式 0 会员支付 1 货到付款
    @Column(name = "pay_type")
    private int payType;
    //地址
    @Column(name = "order_addr")
    private String orderAddr;
    //总价
    @Column(name = "total_price")
    private int totalPrice;
    //总数量
    @Column(name = "order_nums")
    private int orderNums;
    //订单所属会员
    @Column(name = "vip_no")
    private int vipNo;
    @Column(name = "vip_name")
    private String vipName;
    //订单号
    @Column(name = "client_order_no")
    private String clientOrderNo;

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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrderNums() {
        return orderNums;
    }

    public void setOrderNums(int orderNums) {
        this.orderNums = orderNums;
    }

    public int getVipNo() {
        return vipNo;
    }

    public void setVipNo(int vipNo) {
        this.vipNo = vipNo;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getClientOrderNo() {
        return clientOrderNo;
    }

    public void setClientOrderNo(String clientOrderNo) {
        this.clientOrderNo = clientOrderNo;
    }
}
