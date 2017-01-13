package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue
    private int id;
    //标签名
    private String name;
    //商品
    @ManyToMany
    @JoinTable(name = "t_goods_goodsTags",
            joinColumns = {@JoinColumn(name = "tags_id")},
            inverseJoinColumns = {@JoinColumn(name = "goods_id")})
    private Set<Goods> goodsSet = new HashSet<>();

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
}
