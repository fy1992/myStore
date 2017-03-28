package cn.dahe.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品货流管理
 * Created by fy on 2017/1/29.
 */
public class GoodsTrafficDto {
    //期望发货时间
    private String wishTime;
    //备注
    private String description;
    //订的商品信息 商品id , 订的数量
    private String orderInfo;
    //订货类型 0 商品类型 1 原材料
    private int type;
    public String getWishTime() {
        return wishTime;
    }

    public void setWishTime(String wishTime) {
        this.wishTime = wishTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GoodsTrafficDto{" +
                "wishTime='" + wishTime + '\'' +
                ", description='" + description + '\'' +
                ", orderInfo='" + orderInfo + '\'' +
                '}';
    }
}
