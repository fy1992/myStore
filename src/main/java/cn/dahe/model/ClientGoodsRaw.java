package cn.dahe.model;


import javax.persistence.*;

/**
 * 客户端原材料
 * Created by 冯源 on 2017/3/16.
 */
@Table(name = "t_client_goods_raw")
@Entity
public class ClientGoodsRaw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //商品編碼
    @Column(name = "raw_no")
    private String rawNo;
    //商品名称
    @Column(name = "raw_name")
    private String rawName;
    //商品所属门店
    @Column(name = "store_id")
    private int storeId;
    //商品分類id
    @Column(name = "categories_id")
    private int categoriesId;
    //商品分類名稱
    @Column(name = "categoreis_name")
    private String categoriesName;
    //商品圖片
    @Column(name = "img_url")
    private String imgUrl;
    //售價
    private double price;
    //商品庫存
    @Column(name = "raw_num")
    private int rawNum;
    //商品单位
    @Column(name = "raw_unit")
    private String rawUnit;

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

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
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

    public int getRawNum() {
        return rawNum;
    }

    public void setRawNum(int rawNum) {
        this.rawNum = rawNum;
    }

    public String getRawUnit() {
        return rawUnit;
    }

    public void setRawUnit(String rawUnit) {
        this.rawUnit = rawUnit;
    }
}
