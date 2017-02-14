package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 销售统计
 * Created by fy on 2017/1/12.
 */
@Table(name = "t_sale_count")
@Entity
public class SaleCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //统计时间
    @Column(name = "count_date")
    private Date countDate;
    //概况
    private String info;
    //所属分店Id
    @Column(name = "store_id")
    private int storeId;
    //现金支付
    @Column(name = "paid_by_money")
    private int paidByMoney;


}
