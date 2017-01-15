package cn.dahe.service.impl;

import cn.dahe.dao.IGoodsTagsDao;
import cn.dahe.model.GoodsTags;
import cn.dahe.service.IGoodsTagsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/1/13.
 */
@Service("goodsTagsService")
public class GoodsTagsServiceImpl extends BaseServiceImpl<GoodsTags> implements IGoodsTagsService{
    private static Logger logger = LoggerFactory.getLogger(GoodsTagsServiceImpl.class);
    @Resource
    private IGoodsTagsDao goodsTagsDao;

}
