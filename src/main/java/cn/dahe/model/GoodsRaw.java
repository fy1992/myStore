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
    @Column(name = "store_name")
    private String storeName;
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
    //过期时间
    @Column(name = "over_due_time", columnDefinition = "INT DEFAULT 0")
    private Date overdueTime;
    //过期天数
    @Column(name = "over_due_day")
    private int overdueDay;
    //库存
    private int stock;
    //库存上限
    @Column(name = "stock_up")
    private String stockUp;
    //库存下限
    @Column(name = "stock_down")
    private String stockDown;
    //供貨商
    @Column(name = "supplier_id", columnDefinition = "INT DEFAULT 0")
    private int supplierId;
    @Column(name = "suplier_name")
    private String supplierName;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
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

    public Date getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    public int getOverdueDay() {
        return overdueDay;
    }

    public void setOverdueDay(int overdueDay) {
        this.overdueDay = overdueDay;
    }
}
