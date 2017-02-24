package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 门店货流管理
 * Created by fy on 2017/2/2.
 */
@Table(name = "t_store_goods_traffic")
@Entity
public class StoreGoodsTraffic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //门店Id
    @Column(name = "store_id")
    private int storeId;
    @Column(name = "store_name")
    private String storeName;
    //指定配货门店Id 可以为 0 （无 则默认直接从供货商处进货）
    @Column(name = "prepare_store_id")
    private int prepareStoreId;
    @Column(name = "prepare_store_name")
    private String prepareStoreName;
    //配货价格
    //0 无 1 配货门店销售价 2 配货门店进货价
    @Column(name = "prepare_price_type", columnDefinition = "INT DEFAULT 0")
    private int preparePriceType;
    //是否开启在线支付 0 不开启 1 开启
    @Column(name = "is_pay_online", columnDefinition = "INT DEFAULT 0")
    private int payOnline;
    //调货差异操作
    // 0  不允许编辑数量 1 允许编辑数量，需出货方确认 2 允许编辑数量，直接完成进货
    @Column(name = "different_opt", columnDefinition = "INT DEFAULT 0")
    private int differentOpt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getPrepareStoreId() {
        return prepareStoreId;
    }

    public void setPrepareStoreId(int prepareStoreId) {
        this.prepareStoreId = prepareStoreId;
    }

    public int getPreparePriceType() {
        return preparePriceType;
    }

    public void setPreparePriceType(int preparePriceType) {
        this.preparePriceType = preparePriceType;
    }

    public int getPayOnline() {
        return payOnline;
    }

    public void setPayOnline(int payOnline) {
        this.payOnline = payOnline;
    }

    public int getDifferentOpt() {
        return differentOpt;
    }

    public void setDifferentOpt(int differentOpt) {
        this.differentOpt = differentOpt;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPrepareStoreName() {
        return prepareStoreName;
    }

    public void setPrepareStoreName(String prepareStoreName) {
        this.prepareStoreName = prepareStoreName;
    }
}


