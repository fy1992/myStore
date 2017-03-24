package cn.dahe.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 商品原材料
 * Created by 冯源 on 2017/3/16.
 */
@Table(name = "t_goods_raw")
@Entity
public class GoodsRaw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //条码
    @Column(name = "raw_no")
    private String rawNo;
    //所属门店
    @Column(name = "storeId")
    private int storeId;
    @Column(name = "raw_num")
    private int rawNum;
    //原材料名称
    private String name;
    //原材料价格（销售价）
    private double price;
    //原材料进价
    private double bid;
    //主单位
    @Column(name = "main_unit_id")
    private int mainUnitId;
    @Column(name = "main_unit_name")
    private String mainUnitName;
    //拼音码
    private String pinyin;
    //原材料类型
    @Column(name = "categories_id")
    private int categoriesId;
    @Column(name = "categories_name")
    private String categoriesName;
    //图片访问链接
    @Column(name = "img_url")
    private String imgUrl;
    //生产日期
    @Column(name = "production_date")
    private Date productionDate;
    //保质期
    @Column(name = "shelf_life")
    private int shelfLife;
    //库存
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id")
    private Stock stock;
    //库存上限
    @Column(name = "stock_up")
    private int stockUp;
    //库存下限
    @Column(name = "stock_down")
    private int stockDown;

    //状态  0 停用 1 启用
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRawNo() {
        return rawNo;
    }

    public void setRawNo(String rawNo) {
        this.rawNo = rawNo;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getRawNum() {
        return rawNum;
    }

    public void setRawNum(int rawNum) {
        this.rawNum = rawNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public int getMainUnitId() {
        return mainUnitId;
    }

    public void setMainUnitId(int mainUnitId) {
        this.mainUnitId = mainUnitId;
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

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
