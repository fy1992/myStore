package cn.dahe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * 库存
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_stock")
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //商品库存
    @Column(name = "good_num")
    private long goodNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(long goodNum) {
        this.goodNum = goodNum;
    }
}
