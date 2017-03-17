package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Vip;


/**
 * Created by fy on 2017/3/15.
 */
public interface IVipService {
    void add(Vip t);
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
}
