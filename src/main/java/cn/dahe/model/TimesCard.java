package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 次卡
 * Created by fy on 2017/1/11.
 */
@Table(name = "t_times_card")
@Entity
public class TimesCard {
    @Id
    @GeneratedValue
    private int id;
    //名称
    private String name;
    //商品id
    @Column(name = "goods_id")
    private int goodsId;
    //商品名称
    @Column(name = "goods_name")
    private String goodsName;
    //可用次数
    @Column(name = "use_num")
    private int useNum;
    //销售价格
    private int price;
    //使用期限 0 不限制 1 自定义 2 固定天数
    @Column(name = "service_time")
    private int serviceTime;
    //使用期限（固定天数）
    @Column(name = "service_day")
    private int serviceDay;
    //使用期限（自定义天数） 起始时间
    @Column(name = "service_start")
    private Date serviceStart;
    //使用期限（自定义天数） 到期时间
    @Column(name = "service_stop")
    private Date serviceStop;
    //所属分店
    @Column(name = "store_id")
    private int storeId;
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

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getUseNum() {
        return useNum;
    }

    public void setUseNum(int useNum) {
        this.useNum = useNum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(int serviceDay) {
        this.serviceDay = serviceDay;
    }

    public Date getServiceStart() {
        return serviceStart;
    }

    public void setServiceStart(Date serviceStart) {
        this.serviceStart = serviceStart;
    }

    public Date getServiceStop() {
        return serviceStop;
    }

    public void setServiceStop(Date serviceStop) {
        this.serviceStop = serviceStop;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
