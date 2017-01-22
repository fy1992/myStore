package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 货流 订货信息
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
}
