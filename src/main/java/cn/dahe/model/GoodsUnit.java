package cn.dahe.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * 商品单位
 * Created by fy on 2017/1/13.
 */
@Table(name = "t_goods_unit")
@Entity
public class GoodsUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //单位名称
    private String name;
    //所属店面
    @Column(name = "store_id")
    private int storeId;
    @OneToMany(mappedBy = "mainUnit", cascade = CascadeType.ALL)
    private Set<Goods> goodsSet = new HashSet<>();

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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public Set<Goods> getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(Set<Goods> goodsSet) {
        this.goodsSet = goodsSet;
    }
}
