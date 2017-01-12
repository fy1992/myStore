package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue
    private int id;
    //统计时间
    @Column(name = "count_date")
    private Date countDate;
    //单数
    @Column(name = "sale_sum")
    private int saleSum;
    //所属分店Id
    @Column(name = "store_id")
    private int storeId;



}
