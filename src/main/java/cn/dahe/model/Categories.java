package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品类别
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_categories")
@Entity
public class Categories {
    @Id
    @GeneratedValue
    private int id;
}
