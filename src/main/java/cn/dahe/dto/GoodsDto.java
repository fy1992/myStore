package cn.dahe.dto;

import cn.dahe.model.Goods;

import java.util.Date;

/**
 * Created by fy on 2017/1/13.
 */
public class GoodsDto {
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
    private int vipPrice;
    //批发价
    private int tradePrice;
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
    private int supplierId;
    private String supplierName;
    //生产日期
    private Date productionDate;
    //保质期
    private int shelfLife;
    //是否开启会员折扣 0 不开启  1 开启
    private int isVipSet;
    //库存
    private int stock;
    //库存上限
    private int stockUp;
    //库存下限
    private int stockDown;
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

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getStockUp() {
        return stockUp;
    }

    public void setStockUp(int stockUp) {
        this.stockUp = stockUp;
    }

    public int getStockDown() {
        return stockDown;
    }

    public void setStockDown(int stockDown) {
        this.stockDown = stockDown;
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

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public int getIsVipSet() {
        return isVipSet;
    }

    public void setIsVipSet(int isVipSet) {
        this.isVipSet = isVipSet;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    @Override
    public String toString() {
        return "GoodsDto{" +
                "name='" + name + '\'' +
                ", categoriesName='" + categoriesName + '\'' +
                ", goodsNo='" + goodsNo + '\'' +
                ", vipPrice=" + vipPrice +
                ", tradePrice=" + tradePrice +
                ", price=" + price +
                ", bid=" + bid +
                ", goodsImg='" + goodsImg + '\'' +
                ", mainUnit='" + mainUnit + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", supplier='" + supplierName + '\'' +
                ", productionDate=" + productionDate +
                ", shelfLife=" + shelfLife +
                ", isVipSet=" + isVipSet +
                ", stock='" + stock + '\'' +
                '}';
    }
}
