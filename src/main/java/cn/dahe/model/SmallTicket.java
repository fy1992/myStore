package cn.dahe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * 厨房小票
 * Created by fy on 2017/1/11.
 */
@Table(name = "t_small_ticket")
@Entity
public class SmallTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //小票名
    private String name;
    //小票类型 0 一品一切 1 一单一切
    private int type;
    //小票对应的商品
    @ManyToMany(targetEntity = Goods.class, fetch = FetchType.EAGER)
    @JoinTable(name = "t_goods_smallTicket",
            joinColumns = {@JoinColumn(name = "ticket_id")},
            inverseJoinColumns = {@JoinColumn(name = "goods_id")})
    @JsonIgnore
    private Set<Goods> goodsSet;
    //所属分店
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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
