package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 促销活动
 * Created by fy on 2017/1/11.
 */
@Table(name = "t_sale_pro")
@Entity
public class SalePro {
    @Id
    @GeneratedValue
    private int id;
    //促销类型  0 1 2 3 4 5
    private int type;
}
