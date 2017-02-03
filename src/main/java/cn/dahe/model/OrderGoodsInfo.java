package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 订货信息
 * Created by fy on 2017/1/28.
 */
@Table(name = "t_order_goods_info")
@Entity
public class OrderGoodsInfo {
    @Id
    @GeneratedValue
    private int id;
    //备注
    private String description;
    //商品id
    @Column(name = "goods_id")
    private int goodsId;
    //商品名称
    @Column(name = "goods_name")
    private String goodsName;
    //请求量（货流量）
    @Column(name = "order_num")
    private int orderNum;
    //商品条码
    @Column(name = "goods_no")
    private String goodsNo;
    //配货价格
    private int price;
    //单位
    @Column(name = "main_unit")
    private String mainUnit;
    //价格小计
    @Column(name = "price_sum")
    private int priceSum;
    //配货量（实收量）
    @Column(name = "distribute_num")
    private int distributeNum;
    //商品种类
    @Column(name = "categories_id")
    private int categoriesId;
    //所属的订货单
    @ManyToOne
    @JoinColumn(name = "goods_traffic_id")
    private GoodsTraffic goodsTraffic;
    //所属订单管理
    @ManyToOne
    @JoinColumn(name = "traffic_manage")
    private TrafficManage trafficManage;
    //供货商id
    @Column(name = "supplier_id")
    private int supplierId;
    //供货商名称
    @Column(name = "supplier_name")
    private String supplierName;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMainUnit() {
        return mainUnit;
    }

    public void setMainUnit(String mainUnit) {
        this.mainUnit = mainUnit;
    }

    public int getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(int priceSum) {
        this.priceSum = priceSum;
    }

    public int getDistributeNum() {
        return distributeNum;
    }

    public void setDistributeNum(int distributeNum) {
        this.distributeNum = distributeNum;
    }

    public GoodsTraffic getGoodsTraffic() {
        return goodsTraffic;
    }

    public void setGoodsTraffic(GoodsTraffic goodsTraffic) {
        this.goodsTraffic = goodsTraffic;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
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

    public TrafficManage getTrafficManage() {
        return trafficManage;
    }

    public void setTrafficManage(TrafficManage trafficManage) {
        this.trafficManage = trafficManage;
    }
}
