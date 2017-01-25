package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * 供货商结算记录
 * Created by fy on 2017/1/26.
 */
@Table(name = "t_supplier_settlement")
@Entity
public class SupplierSettleLog {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    //结算时间
    @Column(name = "settle_time")
    private Date settleTime;
    //总价
    @Column(name = "price_total")
    private int priceTotal;
    //实付金额
    @Column(name = "real_price")
    private int realPrice;
    //备注
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getSettleTime() {
        return settleTime;
    }

    public void setSettleTime(Date settleTime) {
        this.settleTime = settleTime;
    }

    public int getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(int priceTotal) {
        this.priceTotal = priceTotal;
    }

    public int getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(int realPrice) {
        this.realPrice = realPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
