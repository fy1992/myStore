package cn.dahe.dao.impl;

import cn.dahe.dao.IBadGoodsItemDao;
import cn.dahe.model.BadGoodsItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
@Repository("badGoodsItemDao")
public class BadGoodsItemDaoImpl extends BaseDaoImpl<BadGoodsItem> implements IBadGoodsItemDao {

    @Override
    public List<BadGoodsItem> findByBadGoodsId(int badGoodsId) {
        String hql = "from BadGoodsItem where badGoodsId = ?";
        return this.list(hql, badGoodsId);
    }
}
