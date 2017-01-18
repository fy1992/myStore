package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.TasteGroup;

/**
 * Created by fy on 2017/1/18.
 */
public interface ITasteGroupDao extends IBaseDao<TasteGroup>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<TasteGroup> findByParam(int start, int pageSize, Pager<Object> params);
}
