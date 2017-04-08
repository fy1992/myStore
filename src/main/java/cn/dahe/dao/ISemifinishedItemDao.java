package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.SemifinishedItem;

/**
 * 半成品记录
 * Created by 冯源 on 2017/4/8.
 */
public interface ISemifinishedItemDao extends IBaseDao<SemifinishedItem>{
    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<SemifinishedItem> findByParam(int start, int pageSize, Pager<Object> params);
}
