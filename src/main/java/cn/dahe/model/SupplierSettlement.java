package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 供货商结算
 * Created by fy on 2017/1/26.
 */
@Table(name = "t_supplier_settlement")
@Entity
public class SupplierSettlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "supplier_id")
    private int supplierId;
    //供货商
    @Column(name = "supplier_name")
    private String supplierName;
    @Column(name = "store_id")
    private int storeId;
    //门店
    @Column(name = "store_name")
    private String storeName;
    //货流单号
    @Column(name = "traffic_no")
    private String trafficNo;
    //下单时间
    @Column(name = "order_date")
    private Date orderDate;
    //货单类型 0 退货单 1 进货单
    @Column(name = "traffic_type")
    private int trafficType;
    //货流量
    @Column(name = "traffic_num")
    private int trafficNum;
    //状态
    private int status;
    //总价
    @Column(name = "total_price")
    private int totalPrice;
    //备注
    private String description;
    //预付款
    private int imprest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public String getTrafficNo() {
        return trafficNo;
    }

    public void setTrafficNo(String trafficNo) {
        this.trafficNo = trafficNo;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(int trafficType) {
        this.trafficType = trafficType;
    }

    public int getTrafficNum() {
        return trafficNum;
    }

    public void setTrafficNum(int trafficNum) {
        this.trafficNum = trafficNum;
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
}
