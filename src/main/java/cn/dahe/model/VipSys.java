package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    //赠送积分
    @Column(name = "wd_point")
    private int wdoint;
    //默认会员等级Id
    @Column(name = "default_vip_level_id")
    private int defaultVipLevelID;
    //默认会员等级名称
    @Column(name = "default_vip_level_name")
    private int defaultVipLevelName;
    //转介绍奖励
    //赠送积分
    @Column(name = "zjs_point")
    private int zjsPpoint;
    //所属分店
    @Column(name = "store_id")
    private int storeId;

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

    public int getWdoint() {
        return wdoint;
    }

    public void setWdoint(int wdoint) {
        this.wdoint = wdoint;
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

    public int getZjsPpoint() {
        return zjsPpoint;
    }

    public void setZjsPpoint(int zjsPpoint) {
        this.zjsPpoint = zjsPpoint;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
