package cn.dahe.dao.impl;

import cn.dahe.dao.ITasteGroupDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.TasteGroup;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2017/1/18.
 */
@Repository("tasteGroupDao")
public class TasteGroupDaoImpl extends BaseDaoImpl<TasteGroup> implements ITasteGroupDao {
    @Override
    public Pager<TasteGroup> findByParam(int start, int pageSize, Pager<Object> params) {
        return null;
    }
}
