package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 进货
 * Created by fy on 2017/1/26.
 */
@Table(name = "t_purchase")
@Entity
public class Purchase {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;
    @Column(name = "store_id")
    private int storeId;
    //小计
    @Column(name = "price_sum")
    private int priceSum;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(int priceSum) {
        this.priceSum = priceSum;
    }
}
