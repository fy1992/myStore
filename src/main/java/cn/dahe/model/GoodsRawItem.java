package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品-原材料對應表
 * Created by fy on 2017/3/16.
 */
@Table(name = "t_goods_raw_item")
@Entity
public class GoodsRawItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //所属商品id
    @Column(name = "goods_id")
    private int goodsId;
    //数量
    @Column(name = "raw_num")
    private int rawNum;
    //原材料
    @Column(name = "raw_id")
    private int rawId;
    private String rawName;
    //单位Id
    @Column(name = "goods_unit_id")
    private int goodsUnitId;
    @Column(name = "goods_unit_name")
    private String goodsUnitName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getRawId() {
        return rawId;
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public int getRawNum() {
        return rawNum;
    }

    public void setRawNum(int rawNum) {
        this.rawNum = rawNum;
    }

    public int getGoodsUnitId() {
        return goodsUnitId;
    }

    public void setGoodsUnitId(int goodsUnitId) {
        this.goodsUnitId = goodsUnitId;
    }

    public String getGoodsUnitName() {
        return goodsUnitName;
    }

    public void setGoodsUnitName(String goodsUnitName) {
        this.goodsUnitName = goodsUnitName;
    }
}
