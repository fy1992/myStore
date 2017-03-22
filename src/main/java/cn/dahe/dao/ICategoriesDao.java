package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.Categories;

import java.util.List;

/**
 * 商品/原材料分类
 * Created by fy on 2017/1/17.
 */
public interface ICategoriesDao extends IBaseDao<Categories>{
    /**
     * 根据父id获取子节点
     * @param pid
     * @return
     */
    List<Categories> findByPid(int pid);

    /**
     * 查询所有分类
     * @param storeId
     * @return
     */
    List<Categories> findAll(int storeId);

    /**
     * 根据参数查询
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<Categories> findByParam(int start, int pageSize, Pager<Object> params);

    /**
     * 根据名称查询
     * @param name
     * @param storeId
     * @return
     */
    Categories findByName(String name, int storeId);
}
