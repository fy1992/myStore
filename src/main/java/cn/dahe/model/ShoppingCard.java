package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 购物卡
 * Created by fy on 2017/1/12.
 */
@Table(name = "t_shopping_card")
@Entity
public class ShoppingCard {
    @Id
    @GeneratedValue
    private int id;
    //购物卡名称
    private String name;
    //是否有使用期限 0 没有 1 有
    private int isDue;
    //有效天数
    private int dueDays;
    //购买商品范围
    @Column(name = "categories_ids")
    private String categoriesIds;

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

    public int getIsDue() {
        return isDue;
    }

    public void setIsDue(int isDue) {
        this.isDue = isDue;
    }

    public int getDueDays() {
        return dueDays;
    }

    public void setDueDays(int dueDays) {
        this.dueDays = dueDays;
    }

    public String getCategoriesIds() {
        return categoriesIds;
    }

    public void setCategoriesIds(String categoriesIds) {
        this.categoriesIds = categoriesIds;
    }
}
