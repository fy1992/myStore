package cn.dahe.dao;

import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsRaw;

import java.util.List;

/**
 * Created by 冯源 on 2017/3/21.
 */
public interface IGoodsRawDao extends IBaseDao<GoodsRaw>{
    /**
     * 带条件查询商品
     * @param start
     * @param pageSize
     * @param params
     * @return
     */
    Pager<GoodsRaw> findByParam(int start, int pageSize, Pager<Object> params);

    /**
     * 根据指定参数查询
     * @param params
     * @return
     */
    List<GoodsRaw> findByParam(Pager<Object> params);

    /**
     * 通过商品条码查询
     * @param rawNo
     * @return
     */
    GoodsRaw findByRawNo(String rawNo, int storeId);

    List<GoodsRaw> findByCategoriesId(int categoriesId, int storeId);

    /**
     * 查询全部商品
     * @param storeId
     * @return
     */
    List<GoodsRaw> findAll(int storeId);
}
