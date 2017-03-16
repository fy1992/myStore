package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 原材料
 * Created by fy on 2017/3/16.
 */
@Table(name = "t_recipe")
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //所属商品编码
    @Column(name = "goods_no")
    private String goodsNo;
    //数量
    @Column(name = "recipe_num")
    private int recipeNum;
    //单位Id
    @Column(name = "goods_unit_id")
    private int goodsUnitId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public int getRecipeNum() {
        return recipeNum;
    }

    public void setRecipeNum(int recipeNum) {
        this.recipeNum = recipeNum;
    }

    public int getGoodsUnitId() {
        return goodsUnitId;
    }

    public void setGoodsUnitId(int goodsUnitId) {
        this.goodsUnitId = goodsUnitId;
    }
}
