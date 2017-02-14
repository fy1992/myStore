package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 口味
 * Created by fy on 2017/1/18.
 */
@Table(name = "t_taste")
@Entity
public class Taste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //口味名称
    private String name;
    //价格
    private int price;
    //所属店面
    @Column(name = "store_id")
    private int storeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
