package cn.dahe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * 商品标签
 * Created by fy on 2017/1/11.
 */
@Entity
@Table(name = "t_goods_tags")
public class GoodsTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //标签名
    private String name;
    //商品
    @ManyToMany
    @JoinTable(name = "t_goods_goodsTags",
            joinColumns = {@JoinColumn(name = "tags_id")},
            inverseJoinColumns = {@JoinColumn(name = "goods_id")})
    @JsonIgnore
    private Set<Goods> goodsSet = new HashSet<>();
    //所属店面Id
    @Column(name = "store_id")
    private int storeId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Goods> getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(Set<Goods> goodsSet) {
        this.goodsSet = goodsSet;
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

    @Override
    public String toString() {
        return "GoodsTags{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goodsSet=" + goodsSet +
                ", storeId=" + storeId +
                '}';
    }
}
