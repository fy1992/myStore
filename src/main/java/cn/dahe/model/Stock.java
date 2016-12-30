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

}
