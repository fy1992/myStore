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
 * 货流 订货信息单
 * Created by fy on 2017/1/22.
 */
@Table(name = "t_goods_traffic")
@Entity
public class GoodsTraffic {
    @Id
    @GeneratedValue
    private int id;
    //下单时间
    @Column(name = "order_time")
    private Date orderTime;
    //期望发货时间
    @Column(name = "wish_time'")
    private Date wishTime;
    //订货状态 -1 已作废 0 待审核 1 配货中 2 已完成
    private int status;
    //备注
    private String description;
    //所属店面
    @Column(name = "store_id")
    private int storeId;
    //所包含的订货信息
    @OneToMany(mappedBy = "goodsTraffic", cascade = CascadeType.ALL)
    private Set<OrderGoodsInfo> goodsInfoSet = new HashSet<>();

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

    public Date getWishTime() {
        return wishTime;
    }

    public void setWishTime(Date wishTime) {
        this.wishTime = wishTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public Set<OrderGoodsInfo> getGoodsInfoSet() {
        return goodsInfoSet;
    }

    public void setGoodsInfoSet(Set<OrderGoodsInfo> goodsInfoSet) {
        this.goodsInfoSet = goodsInfoSet;
    }
}
