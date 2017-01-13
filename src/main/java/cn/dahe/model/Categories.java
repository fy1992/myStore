package cn.dahe.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

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
    //商品类别名称
    private String name;
    //商品父类
    @ManyToOne
    @JoinColumn(name = "pid")
    private Categories parent;
    //所属店面
    @Column(name = "store_id")
    private int storeId;
    @OneToMany(mappedBy = "categories", cascade = CascadeType.ALL)
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

    public Categories getParent() {
        return parent;
    }

    public void setParent(Categories parent) {
        this.parent = parent;
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
