package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 会员优惠券
 * Created by fy on 2017/3/16.
 */
@Table(name = "t_vip_coupon")
@Entity
public class VipCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //优惠券编号
    @Column(name = "coupon_no")
    private String couponNo;
    @Column(name = "vip_no")
    private String vipNo;
    @Column(name = "store_id")
    private int storeId;
    //有效期
    @Column(name = "shelf_life")
    private int shelfLife;
    //出券日期
    @Column(name = "create_time")
    private Date createTime;
    //到期日期
    @Column(name = "overdue_time")
    private Date overdueTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVipNo() {
        return vipNo;
    }

    public void setVipNo(String vipNo) {
        this.vipNo = vipNo;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }


}
