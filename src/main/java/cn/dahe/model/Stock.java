package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    //商品id
    @Column(name = "good_id")
    private int goodId;
    //商品名称
    @Column(name = "good_name")
    private String goodName;
    //商品库存
    @Column(name = "good_num")
    private long goodNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public long getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(long goodNum) {
        this.goodNum = goodNum;
    }
}
