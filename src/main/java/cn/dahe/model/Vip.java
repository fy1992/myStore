package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会员
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_vip")
@Entity
public class Vip {
    @Id
    @GeneratedValue
    private int id;

}
