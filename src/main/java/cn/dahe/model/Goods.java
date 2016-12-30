package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_goods")
@Entity
public class Goods {
    @Id
    @GeneratedValue
    private int id;

}
