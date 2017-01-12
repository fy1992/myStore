package cn.dahe.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * 厨房小票
 * Created by fy on 2017/1/11.
 */
@Table(name = "t_small_ticket")
@Entity
public class SmallTicket {
    @Id
    @GeneratedValue
    private int id;
    //小票名
    private String name;
    //小票类型 0 一品一切 1 一单一切
    private int type;
    //商品
    @ManyToMany
    @JoinTable(name = "t_goods_smallTicket",
            joinColumns = {@JoinColumn(name = "ticket_id")},
            inverseJoinColumns = {@JoinColumn(name = "goods_id")})
    private List<Goods> goodsList;

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

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
