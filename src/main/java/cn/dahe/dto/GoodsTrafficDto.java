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
    private String goodsMapStr;
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

    public String getGoodsMapStr() {
        return goodsMapStr;
    }

    public void setGoodsMapStr(String goodsMapStr) {
        this.goodsMapStr = goodsMapStr;
    }

    @Override
    public String toString() {
        return "GoodsTrafficDto{" +
                "wishTime='" + wishTime + '\'' +
                ", description='" + description + '\'' +
                ", goodsMapStr='" + goodsMapStr + '\'' +
                '}';
    }
}
