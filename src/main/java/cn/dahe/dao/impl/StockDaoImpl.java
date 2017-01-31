package cn.dahe.dao.impl;

import cn.dahe.dao.IStockDao;
import cn.dahe.model.Stock;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2017/1/31.
 */
@Repository("stockDao")
public class StockDaoImpl extends BaseDaoImpl<Stock> implements IStockDao{
    @Override
    public Stock findByGoodsNo(String goodsNo) {
        String hql = "from Stock stock where stock.goods.goodsNo = ?";
        return (Stock)this.queryByHql(hql, goodsNo);
    }
}
