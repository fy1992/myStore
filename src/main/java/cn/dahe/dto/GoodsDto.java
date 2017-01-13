package cn.dahe.dto;

import cn.dahe.model.Goods;

import java.util.Date;

/**
 * Created by fy on 2017/1/13.
 */
public class GoodsDto {
    //商品名称
    private String name;
    //商品类型
    private String categoriesName;
    //条码
    private String goodsNo;
    //会员价
    private int vipPrice;
    //批发价
    private int tradePrice;
    //商品价格（销售价）
    private int price;
    //商品进价
    private int bid;
    //主单位
    private String mainUnit;
    //拼音码
    private String pinyin;
    //供货商
    private String supplier;
    //生产日期
    private Date productionDate;
    //保质期
    private Date shelfLife;
    //是否开启会员折扣 0 不开启  1 开启
    private int isVipSet;
    //库存
    private String goodsNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public int getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(int vipPrice) {
        this.vipPrice = vipPrice;
    }

    public int getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(int tradePrice) {
        this.tradePrice = tradePrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getMainUnit() {
        return mainUnit;
    }

    public void setMainUnit(String mainUnit) {
        this.mainUnit = mainUnit;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }

    public int getIsVipSet() {
        return isVipSet;
    }

    public void setIsVipSet(int isVipSet) {
        this.isVipSet = isVipSet;
    }

    public String getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum;
    }

    public GoodsDto() {
    }

    public GoodsDto(Goods goods) {
        this.name = goods.getName();
        this.bid = goods.getBid();
        this.pinyin = goods.getPinyin();
        this.isVipSet = goods.getIsVipSet();
        this.goodsNum = Long.toString(goods.getStock().getGoodNum());
        this.shelfLife = goods.getShelfLife();
        this.tradePrice = goods.getTradePrice();
        this.price = goods.getPrice();
        this.categoriesName = goods.getCategories().getName();
        this.mainUnit = goods.getMainUnit().getName();
        this.supplier = goods.getSupplier().getName();
        this.vipPrice = goods.getVipPrice();
        this.productionDate = goods.getProductionDate();
        this.goodsNo = goods.getGoodsNo();
    }
}
