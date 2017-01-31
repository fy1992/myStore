package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.dto.Tree;
import cn.dahe.model.Categories;

import java.util.List;

/**
 * Created by fy on 2017/1/17.
 */
public interface ICategoriesService {
    void add(Categories t);
    boolean del(int id);
    void update(Categories t);
    Categories get(int id);
    Categories load(int id);

    /**
     * 添加
     * @param name
     * @param pid
     * @param storeId
     */
    void add(String name, int pid, int storeId);

    /**
     * 编辑
     * @param name
     * @param id
     * @param pid
     */
    void update(String name, int id, int pid);
    /**
     * 根据父id获取子节点
     * @param pid
     * @param storeId
     * @return
     */
    List<Categories> findByPid(int pid, int storeId);

    /**
     * 加载树
     * @param rootName
     * @param storeId
     * @return
     */
    List<Tree> initTree(String rootName, int storeId);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<Categories> findByParams(String aDataSet, int storeId);

    /**
     * 商品类别排序
     * @param ids
     */
    void categoriesSort(String ids);

    /**
     * 根据名称查询
     * @param name
     * @param storeId
     * @return
     */
    Categories findByName(String name, int storeId);
}
