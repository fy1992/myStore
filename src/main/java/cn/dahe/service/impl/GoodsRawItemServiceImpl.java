package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsRawItemDao;
import cn.dahe.dao.IGoodsUnitDao;
import cn.dahe.model.GoodsRawItem;
import cn.dahe.model.GoodsUnit;
import cn.dahe.service.IGoodsRawItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 冯源 on 2017/3/26.
 */
@Service("goodsRawItemService")
public class GoodsRawItemServiceImpl implements IGoodsRawItemService{
    @Resource
    private IGoodsRawItemDao goodsRawItemDao;
    @Resource
    private IGoodsUnitDao goodsUnitDao;
    @Override
    public void add(GoodsRawItem t) {
        goodsRawItemDao.add(t);
    }

    @Override
    public void del(int id) {
        goodsRawItemDao.delete(id);
    }

    @Override
    public void update(GoodsRawItem t) {
        goodsRawItemDao.update(t);
    }

    @Override
    public GoodsRawItem get(int id) {
        return goodsRawItemDao.get(id);
    }

    @Override
    public GoodsRawItem load(int id) {
        return goodsRawItemDao.load(id);
    }

    @Override
    public List<GoodsRawItem> findByGoodsId(int goodsId) {
        return goodsRawItemDao.findByGoodsId(goodsId);
    }
}
