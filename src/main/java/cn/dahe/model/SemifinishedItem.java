package cn.dahe.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 半成品记录
 * Created by 冯源 on 2017/4/8.
 */
@Table(name = "t_semifinished_item")
@Entity
public class SemifinishedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //商品分類id
    @Column(name = "categories_id")
    private int categoriesId;
    //成品商品库存
    @Column(name = "goods_num")
    private int finishedNum;
    //半成品商品库存
    @Column(name = "semifinished_num")
    private int semifinishedNum;
    //商品单位
    @Column(name = "goods_unit")
    private String goodsUnit;
    //制作时间
    @Column(name = "semifinished_time")
    private Date semifinishedTime;
    //半成品制作人
    @Column(name = "cashier_name")
    private String cashierName;
    //成品名称
    @Column(name = "target_goods_name")
    private String targetGoodsName;
    //成品条码
    @Column(name = "target_goods_no")
    private String targetGoodsNo;
    //所属门店
    @Column(name = "store_id")
    private int storeId;
    //半成品商品編碼
    @Column(name = "goods_no")
    private String goodsNo;
    //半成品商品名称
    @Column(name = "goods_name")
    private String goodsName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public int getFinishedNum() {
        return finishedNum;
    }

    public void setFinishedNum(int finishedNum) {
        this.finishedNum = finishedNum;
    }

    public int getSemifinishedNum() {
        return semifinishedNum;
    }

    public void setSemifinishedNum(int semifinishedNum) {
        this.semifinishedNum = semifinishedNum;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Date getSemifinishedTime() {
        return semifinishedTime;
    }

    public void setSemifinishedTime(Date semifinishedTime) {
        this.semifinishedTime = semifinishedTime;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getTargetGoodsName() {
        return targetGoodsName;
    }

    public void setTargetGoodsName(String targetGoodsName) {
        this.targetGoodsName = targetGoodsName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTargetGoodsNo() {
        return targetGoodsNo;
    }

    public void setTargetGoodsNo(String targetGoodsNo) {
        this.targetGoodsNo = targetGoodsNo;
    }
}
