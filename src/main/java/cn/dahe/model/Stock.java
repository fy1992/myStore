package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 库存
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_stock")
@Entity
public class Stock {
    @Id
    @GeneratedValue
    private int id;
    //商品
    @OneToOne
    @JoinColumn(name = "goods_id", insertable = true, unique = true)
    private Goods goods;
    //商品库存
    @Column(name = "good_num")
    private long goodNum;
    //所属分店
    @Column(name = "store_id")
    private int storeId;
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

    public long getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(long goodNum) {
        this.goodNum = goodNum;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
