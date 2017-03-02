package cn.dahe.service.impl;

import cn.dahe.dao.IStockDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Stock;
import cn.dahe.service.IStockService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/3/2.
 */
@Service("stockService")
public class StockServiceImpl implements IStockService{
    @Resource
    private IStockDao stockDao;

    @Override
    public boolean add(Stock t) {
        stockDao.addAndGetId4Integer(t);
        return false;
    }

    @Override
    public void del(int id) {
        stockDao.delete(id);
    }

    @Override
    public void update(Stock t) {
        Stock stock = get(t.getId());
        stockDao.update(stock);
    }

    @Override
    public Stock get(int id) {
        return null;
    }

    @Override
    public Stock load(int id) {
        return null;
    }

    @Override
    public Pager<Stock> findByParams(String aDataSet) {
        return null;
    }
}
