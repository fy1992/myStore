package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    //排序
    private int seq;
    //是否在网店显示 0 不显示 1 显示
    @Column(name = "toggle_show",columnDefinition = "INT DEFAULT 1")
    private int toggleShow;
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

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getToggleShow() {
        return toggleShow;
    }

    public void setToggleShow(int toggleShow) {
        this.toggleShow = toggleShow;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", storeId=" + storeId +
                ", seq=" + seq +
                '}';
    }
}
