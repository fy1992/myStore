package cn.dahe.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 商品
 * Created by fy on 2016/12/29.
 */
@Table(name = "t_goods")
@Entity
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //商品名称
    private String name;
    //商品类型
    @Column(name = "categories_id")
    private int categoriesId;
    //图片访问链接
    @Column(name = "img_url")
    private String imgUrl;
    //商品口味
    @ManyToMany
    @JoinTable(name = "t_goods_tasteGroup",
            joinColumns = {@JoinColumn(name = "goods_id")},
            inverseJoinColumns = {@JoinColumn(name = "tasteGroup_id")})
    private Set<TasteGroup> tasteGroupSet = new HashSet<>();
    //条码
    @Column(name = "goods_no")
    private String goodsNo;
    //是否启用商品扩展信息  0 不启用 1 启用
    @Column(name = "is_ex", columnDefinition = "INT DEFAULT 1")
    private int ex;
    //是否开启会员折扣 0 不开启  1 开启
    @Column(name = "is_vip_set")
    private int vipSet;
    //会员价
    @Column(name = "vip_price")
    private int vipPrice;
    //批发价
    @Column(name = "trade_price")
    private int tradePrice;
    //商品价格（销售价）
    private int price;
    //商品进价
    private int bid;
    //主单位
    @Column(name = "main_unit_id")
    private int mainUnitId;
    @Column(name = "main_unit_name")
    private String mainUnitName;
    //副单位
    @Column(name = "unit_ids")
    private String unitIds;
    //订货单位
    @Column(name = "order_unit")
    private int orderUnit;
    //拼音码
    private String pinyin;
    //供货商
    @Column(name = "supplier_id")
    private int supplierId;
    @Column(name = "supplier_name")
    private String supplierName;
    //生产日期
    @Column(name = "production_date")
    private Date productionDate;
    //保质期
    @Column(name = "shelf_life")
    private int shelfLife;
    //库存
    @OneToOne(mappedBy = "goods", fetch = FetchType.EAGER)
    private Stock stock;
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
    private Set<SmallTicket> smallTicketSet = new HashSet<>();
    //商品标签
    @ManyToMany
    @JoinTable(name = "t_goods_goodsTags",
            joinColumns = {@JoinColumn(name = "goods_id")},
            inverseJoinColumns = {@JoinColumn(name = "tags_id")})
    private Set<GoodsTags> goodsTagsSet = new HashSet<>();
    //所属店面
    @Column(name = "store_id")
    private int storeId;
    //状态  0 停用 1 启用
    private int status;
    //排序
    @Column(columnDefinition = "INT DEFAULT 0")
    private int seq;
    //是否打印 0 不打印 1 打印
    @Column(name = "is_print")
    private int print;
    //是否是积分商品 0 不是 1 是
    @Column(name = "is_score")
    private int score;
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

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public int getEx() {
        return ex;
    }

    public void setEx(int ex) {
        this.ex = ex;
    }

    public int getVipSet() {
        return vipSet;
    }

    public void setVipSet(int vipSet) {
        this.vipSet = vipSet;
    }

    public int getPrint() {
        return print;
    }

    public void setPrint(int print) {
        this.print = print;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
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

    public Set<SmallTicket> getSmallTicketSet() {
        return smallTicketSet;
    }

    public void setSmallTicketSet(Set<SmallTicket> smallTicketSet) {
        this.smallTicketSet = smallTicketSet;
    }

    public Set<GoodsTags> getGoodsTagsSet() {
        return goodsTagsSet;
    }

    public void setGoodsTagsSet(Set<GoodsTags> goodsTagsSet) {
        this.goodsTagsSet = goodsTagsSet;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public String getUnitIds() {
        return unitIds;
    }

    public void setUnitIds(String unitIds) {
        this.unitIds = unitIds;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<TasteGroup> getTasteGroupSet() {
        return tasteGroupSet;
    }

    public void setTasteGroupSet(Set<TasteGroup> tasteGroupSet) {
        this.tasteGroupSet = tasteGroupSet;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoriesId=" + categoriesId +
                ", imgUrl='" + imgUrl + '\'' +
                ", tasteGroupSet=" + tasteGroupSet +
                ", goodsNo='" + goodsNo + '\'' +
                ", ex=" + ex +
                ", vipSet=" + vipSet +
                ", vipPrice=" + vipPrice +
                ", tradePrice=" + tradePrice +
                ", price=" + price +
                ", bid=" + bid +
                ", mainUnitId=" + mainUnitId +
                ", mainUnitName='" + mainUnitName + '\'' +
                ", unitIds='" + unitIds + '\'' +
                ", orderUnit=" + orderUnit +
                ", pinyin='" + pinyin + '\'' +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", productionDate=" + productionDate +
                ", shelfLife=" + shelfLife +
                ", stock=" + stock +
                ", stockUp=" + stockUp +
                ", stockDown=" + stockDown +
                ", description='" + description + '\'' +
                ", storeId=" + storeId +
                ", status=" + status +
                ", seq=" + seq +
                ", print=" + print +
                ", score=" + score +
                '}';
    }
}
