package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by fy on 2016/12/29.
 */
@Table(name="t_user")
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
}
