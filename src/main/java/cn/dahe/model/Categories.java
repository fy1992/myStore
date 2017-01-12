package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @Column(name = "categories_name")
    private String categoriesName;
    //商品父类
    @ManyToOne
    @JoinColumn(name = "pid")
    private Categories parent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public Categories getParent() {
        return parent;
    }

    public void setParent(Categories parent) {
        this.parent = parent;
    }
}
