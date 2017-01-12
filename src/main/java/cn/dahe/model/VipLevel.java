package cn.dahe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会员等级
 * Created by fy on 2017/1/11.
 */
@Table(name = "t_vip_level")
@Entity
public class VipLevel {
    @Id
    @GeneratedValue
    private int id;
    //等级名称
    private String name;
    //优惠折扣
    private int rebate;
    //是否自动升级 0 不自动 1 自动
    @Column(name = "is_auto")
    private int isAuto;
    //自动升级积分界限
    @Column(name = "auto_line")
    private int autoLine;
    //有效期 0 永久 1  1年
    private int expiryDate;



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

    public int getRebate() {
        return rebate;
    }

    public void setRebate(int rebate) {
        this.rebate = rebate;
    }

    public int getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(int isAuto) {
        this.isAuto = isAuto;
    }

    public int getAutoLine() {
        return autoLine;
    }

    public void setAutoLine(int autoLine) {
        this.autoLine = autoLine;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(int expiryDate) {
        this.expiryDate = expiryDate;
    }
}
