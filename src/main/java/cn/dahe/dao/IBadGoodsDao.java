package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.BadGoods;

/**
 * Created by fy on 2017/3/20.
 */
public interface IBadGoodsDao extends IBaseDao<BadGoods>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<BadGoods> findByParam(int start, int pageSize, Pager<Object> params);
}
