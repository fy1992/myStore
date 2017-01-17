package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.dto.Tree;
import cn.dahe.model.Categories;

import java.util.List;

/**
 * Created by fy on 2017/1/17.
 */
public interface ICategoriesService {
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
}
