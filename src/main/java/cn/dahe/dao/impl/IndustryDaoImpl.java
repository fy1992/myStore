package cn.dahe.dao.impl;

import cn.dahe.dao.IIndustryDao;
import cn.dahe.model.Industry;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fy on 2017/2/17.
 */
@Repository("industryDao")
public class IndustryDaoImpl extends BaseDaoImpl<Industry> implements IIndustryDao{
    @Override
    public Industry findByName(String name) {
        String hql = "from Industry where name = ?";
        return (Industry)queryByHql(hql, name);
    }

    @Override
    public List<Industry> findAll() {
        String hql = "from Industry";
        return list(hql);
    }
}
