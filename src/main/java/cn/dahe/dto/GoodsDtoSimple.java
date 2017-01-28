package cn.dahe.dto;

import cn.dahe.model.Goods;

/**
 * 客户端显示的商品信息
 * Created by fy on 2017/1/28.
 */
public class GoodsDtoSimple {
    private int id;
    private String name;
    //库存
    private long goodsNum;
    //商品图片
    private String goodsImg;
    //商品价格（销售价）
    private int price;
    //主单位
    private String mainUnit;

    public String getMainUnit() {
        return mainUnit;
    }

    public void setMainUnit(String mainUnit) {
        this.mainUnit = mainUnit;
    }

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

    public long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public GoodsDtoSimple() {
    }

    public GoodsDtoSimple(Goods goods) {
        this.id = goods.getId();
        this.name = goods.getName();
        this.goodsNum = goods.getStock().getGoodNum();
        this.goodsImg = goods.getImgUrl();
        this.price = goods.getPrice();
        this.mainUnit = goods.getMainUnit().getName();
    }
}
