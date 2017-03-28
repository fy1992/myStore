package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 会员制度
 * Created by fy on 2017/1/11.
 */
@Entity
@Table(name = "t_vip_sys")
public class VipSys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //会员卡背景图片
    @Column(name = "img_url")
    private String imgUrl;
    //微店新会员
    //---------------------------------
    //赠送积分
    @Column(name = "wd_point")
    private int wdPoint;
    //赠送优惠券
    @Column(name = "wd_sales_campaign_id")
    private int wdSalesCampaignId;
    //默认会员等级Id
    @Column(name = "default_vip_level_id")
    private int defaultVipLevelID;
    //默认会员等级名称
    @Column(name = "default_vip_level_name")
    private int defaultVipLevelName;
    //---------------------------------
    //转介绍奖励
    //赠送积分
    @Column(name = "zjs_point")
    private int zjsPoint;
    @Column(name = "zjs_sales_campaign_id")
    private int zjsSalesCampaignId;
    //---------------------------------
    //所属分店
    @Column(name = "store_id")
    private int storeId;
    //会员日
    @Column(name = "vip_date")
    private Date vipDate;
    //积分制 0 按金额积分 1 按商品积分
    @Column(name = "point_type", columnDefinition = "INT DEFAULT 0")
    private int pointType;
    //金额积分消费额度
    @Column(name = "point_price", columnDefinition = "DOUBLE DEFAULT 1")
    private double pointPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getWdPoint() {
        return wdPoint;
    }

    public void setWdPoint(int wdPoint) {
        this.wdPoint = wdPoint;
    }

    public int getDefaultVipLevelID() {
        return defaultVipLevelID;
    }

    public void setDefaultVipLevelID(int defaultVipLevelID) {
        this.defaultVipLevelID = defaultVipLevelID;
    }

    public int getDefaultVipLevelName() {
        return defaultVipLevelName;
    }

    public void setDefaultVipLevelName(int defaultVipLevelName) {
        this.defaultVipLevelName = defaultVipLevelName;
    }

    public int getZjsPoint() {
        return zjsPoint;
    }

    public void setZjsPoint(int zjsPoint) {
        this.zjsPoint = zjsPoint;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public Date getVipDate() {
        return vipDate;
    }

    public void setVipDate(Date vipDate) {
        this.vipDate = vipDate;
    }

    public int getPointType() {
        return pointType;
    }

    public void setPointType(int pointType) {
        this.pointType = pointType;
    }

    public double getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(double pointPrice) {
        this.pointPrice = pointPrice;
    }

    public int getWdSalesCampaignId() {
        return wdSalesCampaignId;
    }

    public void setWdSalesCampaignId(int wdSalesCampaignId) {
        this.wdSalesCampaignId = wdSalesCampaignId;
    }

    public int getZjsSalesCampaignId() {
        return zjsSalesCampaignId;
    }

    public void setZjsSalesCampaignId(int zjsSalesCampaignId) {
        this.zjsSalesCampaignId = zjsSalesCampaignId;
    }
}
