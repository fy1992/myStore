package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 销售信息
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_sale_info")
@Entity
public class SaleInfo {
    @Id
    @GeneratedValue
    private int id;
}
