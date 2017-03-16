package cn.dahe.model;

import javax.persistence.*;

/**
 * Created by 冯源 on 2017/3/16.
 */
@Table(name = "t_goods_raw")
@Entity
public class GoodsRaw {
    @Id
    @GeneratedValue
    private int id;
    //所属门店
    @Column(name = "storeId")
    private int storeId;
    @Column(name = "raw_num")
    private int rawNum;
    //原材料名称
    private String name;
    //销售价
    private double price;
    //进价
    private double bid;


}
