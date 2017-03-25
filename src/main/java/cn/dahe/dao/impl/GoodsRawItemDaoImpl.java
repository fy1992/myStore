package cn.dahe.dao.impl;

import cn.dahe.dao.IGoodsRawItemDao;
import cn.dahe.model.GoodsRawItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 冯源 on 2017/3/26.
 */
@Repository("goodsRawItemDao")
public class GoodsRawItemDaoImpl extends BaseDaoImpl<GoodsRawItem> implements IGoodsRawItemDao{
    @Override
    public List<GoodsRawItem> findByGoodsId(int goodsId) {
        String hql = "from GoodsRawItem where goodsId = ?";
        return this.list(hql, goodsId);
    }
}
