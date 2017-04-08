package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 客户端的商品
 * Created by fy on 2017/3/14.
 */
@Entity
@Table(name = "t_client_goods")
public class ClientGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //商品編碼
    @Column(name = "goods_no")
    private String goodsNo;
    //商品名称
    @Column(name = "goods_name")
    private String goodsName;
    //商品所属门店
    @Column(name = "store_id")
    private int storeId;
    //商品分類id
    @Column(name = "categories_id")
    private int categoriesId;
    //商品分類名稱
    @Column(name = "categories_name")
    private String categoriesName;
    //商品圖片
    @Column(name = "img_url")
    private String imgUrl;
    //售價
    private double price;
    //成品商品库存
    @Column(name = "goods_num")
    private int finishedNum;
    //半成品商品库存
    @Column(name = "semifinished_num")
    private int semifinishedNum;
    //商品单位
    @Column(name = "goods_unit")
    private String goodsUnit;

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

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public ClientGoods() {
    }

    public ClientGoods(Goods goods){
        this.goodsNo = goods.getGoodsNo();
        this.categoriesId = goods.getCategoriesId();
        this.categoriesName = goods.getCategoriesName();
        this.price = goods.getPrice();
        this.imgUrl = goods.getImgUrl();
        this.storeId = goods.getStoreId();
        this.semifinishedNum = 0;
        this.finishedNum = 0;
        this.goodsUnit = goods.getMainUnitName();
        this.goodsName = goods.getName();
    }
}
