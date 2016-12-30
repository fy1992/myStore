package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 供货商
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_supplier")
@Entity
public class Supplier {
    @Id
    @GeneratedValue
    private int id;

}
