package cn.dahe.dto;

import cn.dahe.model.Goods;

import java.util.Date;

/**
 * Created by fy on 2017/1/13.
 */
public class GoodsDto {
    private int id;
    //是否启用
    private int status;
    //商品名称
    private String name;
    //商品类型
    private int categoriesId;
    private String categoriesName;
    //条码
    private String goodsNo;
    //是否开启会员折扣
    private int vipSet;
    //会员价
    private int vipPrice = 0;
    //批发价
    private String tradePrice;
    //商品价格（销售价）
    private int price;
    //商品进价
    private int bid;
    //商品图片
    private String goodsImg;
    //主单位
    private int mainUnit;
    private String mainUnitName;
    //拼音码
    private String pinyin;
    //供货商
    private String supplierId;
    private String supplierName;
    //生产日期
    private String productionDate;
    //保质期
    private String shelfLife;
    //库存
    private String stock;
    //库存上限
    private String stockUp;
    //库存下限
    private String stockDown;
    //厨房票打ids
    private String smallTickets;
    //商品标签ids
    private String goodsTagss;
    //备注
    private String description;

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

    public int getMainUnit() {
        return mainUnit;
    }

    public void setMainUnit(int mainUnit) {
        this.mainUnit = mainUnit;
    }

    public String getMainUnitName() {
        return mainUnitName;
    }

    public void setMainUnitName(String mainUnitName) {
        this.mainUnitName = mainUnitName;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public int getVipSet() {
        return vipSet;
    }

    public void setVipSet(int vipSet) {
        this.vipSet = vipSet;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSmallTickets() {
        return smallTickets;
    }

    public void setSmallTickets(String smallTickets) {
        this.smallTickets = smallTickets;
    }

    public String getGoodsTagss() {
        return goodsTagss;
    }

    public void setGoodsTagss(String goodsTagss) {
        this.goodsTagss = goodsTagss;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getStockUp() {
        return stockUp;
    }

    public void setStockUp(String stockUp) {
        this.stockUp = stockUp;
    }

    public String getStockDown() {
        return stockDown;
    }

    public void setStockDown(String stockDown) {
        this.stockDown = stockDown;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    @Override
    public String toString() {
        return "GoodsDto{" +
                "status=" + status +
                ", name='" + name + '\'' +
                ", categoriesId=" + categoriesId +
                ", categoriesName='" + categoriesName + '\'' +
                ", goodsNo='" + goodsNo + '\'' +
                ", vipSet=" + vipSet +
                ", vipPrice=" + vipPrice +
                ", tradePrice=" + tradePrice +
                ", price=" + price +
                ", bid=" + bid +
                ", goodsImg='" + goodsImg + '\'' +
                ", mainUnit=" + mainUnit +
                ", mainUnitName='" + mainUnitName + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", productionDate=" + productionDate +
                ", shelfLife=" + shelfLife +
                ", stock=" + stock +
                ", stockUp=" + stockUp +
                ", stockDown=" + stockDown +
                ", smallTickets='" + smallTickets + '\'' +
                ", goodsTagss='" + goodsTagss + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
