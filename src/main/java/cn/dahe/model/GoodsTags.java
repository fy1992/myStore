package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

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
    @Column(name = "tags_name")
    private String tagsName;
    //商品
    @ManyToMany
    @JoinTable(name = "t_goods_goodsTags",
            joinColumns = {@JoinColumn(name = "tags_id")},
            inverseJoinColumns = {@JoinColumn(name = "goods_id")})
    private List<Goods> goodsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }
}
