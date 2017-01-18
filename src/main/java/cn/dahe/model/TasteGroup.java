package cn.dahe.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * 口味组
 * Created by fy on 2017/1/18.
 */
@Table(name = "t_taste_group")
@Entity
public class TasteGroup {
    @Id
    @GeneratedValue
    private int id;
    //口味组名称
    private String name;
    //是否是必选的
    @Column(name = "is_required")
    private int isRequired;
    //口味选择方式
    private int type;
    //口味对应的商品
    @ManyToMany
    @JoinTable(name = "t_goods_taste",
            joinColumns = {@JoinColumn(name = "taste_id")},
            inverseJoinColumns = {@JoinColumn(name = "goods_id")})
    private Set<Goods> goodsSet = new HashSet<>();
    //口味组对应的口味
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Taste> tasteSet = new HashSet<>();

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

    public int getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(int isRequired) {
        this.isRequired = isRequired;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Set<Goods> getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(Set<Goods> goodsSet) {
        this.goodsSet = goodsSet;
    }

    public Set<Taste> getTasteSet() {
        return tasteSet;
    }

    public void setTasteSet(Set<Taste> tasteSet) {
        this.tasteSet = tasteSet;
    }
}
