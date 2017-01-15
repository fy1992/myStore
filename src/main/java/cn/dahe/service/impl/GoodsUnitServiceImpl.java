package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsUnitDao;
import cn.dahe.model.GoodsUnit;
import cn.dahe.service.IGoodsUnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/1/13.
 */
@Service("goodsUnitService")
public class GoodsUnitServiceImpl extends BaseServiceImpl<GoodsUnit> implements IGoodsUnitService{
    @Resource
    private IGoodsUnitDao goodsUnitDao;


}
