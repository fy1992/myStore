package cn.dahe.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * 商品
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_goods")
@Entity
public class Goods {
    @Id
    @GeneratedValue
    private int id;
    //商品名称
    @Column(name = "goods_name")
    private String goodsName;
    //商品类型
    @Column(name = "categories_id")
    private int categoriesId;
    //商品价格
    private int price;
    //商品进价
    private int bid;
    //图片访问链接
    @Column(name = "img_url")
    private String imgUrl;
    //商品口味
    private String taste;
    //条码
    @Column(name = "goods_no")
    private String goodsNo;
    //是否启用商品扩展信息  0 不启用 1 启用
    @Column(name = "is_ex")
    private int isEx;
    //是否开启会员折扣 0 不开启  1 开启
    @Column(name = "is_vip_set")
    private int isVipSet;
    //会员价
    @Column(name = "vip_price")
    private int vipPrice;
    //折扣价
    @Column(name = "discount_price")
    private int discountPrice;
    //主单位
    @Column(name = "main_unit")
    private int mainUnit;
    //订货单位
    @Column(name = "order_unit")
    private int orderUnit;
    //拼音码
    private String pinyin;
    //供货商Id
    @Column(name = "supplier_id")
    private int supplierId;
    //供货商名称
    @Column(name = "supplier_name")
    private String supplierName;
    //生产日期
    @Column(name = "production_date")
    private Date productionDate;
    //保质期
    @Column(name = "shelf_life")
    private Date shelfLife;
    //库存上限
    @Column(name = "stock_up")
    private int stockUp;
    //库存下限
    @Column(name = "stock_down")
    private int stockDown;
    //备注
    private String description;
    //小票
    @ManyToMany
    @JoinTable(name = "t_goods_smallTicket",
            joinColumns = {@JoinColumn(name = "goods_id")},
            inverseJoinColumns = {@JoinColumn(name = "ticket_id")})
    private List<SmallTicket> smallTicketList;
    //商品标签
    @ManyToMany
    @JoinTable(name = "t_goods_goodsTags",
            joinColumns = {@JoinColumn(name = "goods_id")},
            inverseJoinColumns = {@JoinColumn(name = "tags_id")})
    private List<GoodsTags> goodsTagsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public int getIsEx() {
        return isEx;
    }

    public void setIsEx(int isEx) {
        this.isEx = isEx;
    }

    public int getIsVipSet() {
        return isVipSet;
    }

    public void setIsVipSet(int isVipSet) {
        this.isVipSet = isVipSet;
    }

    public int getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(int vipPrice) {
        this.vipPrice = vipPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getMainUnit() {
        return mainUnit;
    }

    public void setMainUnit(int mainUnit) {
        this.mainUnit = mainUnit;
    }

    public int getOrderUnit() {
        return orderUnit;
    }

    public void setOrderUnit(int orderUnit) {
        this.orderUnit = orderUnit;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SmallTicket> getSmallTicketList() {
        return smallTicketList;
    }

    public void setSmallTicketList(List<SmallTicket> smallTicketList) {
        this.smallTicketList = smallTicketList;
    }

    public List<GoodsTags> getGoodsTagsList() {
        return goodsTagsList;
    }

    public void setGoodsTagsList(List<GoodsTags> goodsTagsList) {
        this.goodsTagsList = goodsTagsList;
    }
}
