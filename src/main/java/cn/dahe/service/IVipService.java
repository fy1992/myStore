package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Vip;

import java.util.List;


/**
 * Created by fy on 2017/3/15.
 */
public interface IVipService {
    void add(Vip t);
    boolean add(Vip t, int storeId);
    void del(int id);
    void update(Vip t);
    Vip get(int id);
    Vip load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @return
     */
    Pager<Vip> findByParams(String aDataSet);

    Vip findByOpenId(String openId);

    Vip findByStorIdAndVipNo(String vipNo, int storeId);

    /**
     * 卡号、手机、姓名查询
     * @param params
     * @param storeId
     * @return
     */
    List<Vip> findByVipInfo(String params, int storeId);
}
