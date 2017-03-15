package cn.dahe.model;

import javax.persistence.*;

/**
 * 优惠券
 * Created by fy on 2017/3/15.
 */
@Table(name = "t_coupon")
@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
