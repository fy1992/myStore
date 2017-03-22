package cn.dahe.dao;

import cn.dahe.model.Industry;

import java.util.List;

/**
 * 门店行业
 * Created by fy on 2017/2/17.
 */
public interface IIndustryDao extends IBaseDao<Industry>{
    /**
     *根据名称查询
     * @param name
     * @return
     */
    Industry findByName(String name);

    /**
     *查询全部
     * @return
     */
    List<Industry> findAll();
}
