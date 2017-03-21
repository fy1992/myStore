package cn.dahe.dao.impl;

import cn.dahe.dao.ISaleCountDao;
import cn.dahe.model.SaleCount;
import org.springframework.stereotype.Repository;

/**
 * 销售统计
 * Created by 冯源 on 2017/3/22.
 */
@Repository("saleCountDao")
public class SaleCountDaoImpl extends BaseDaoImpl<SaleCount> implements ISaleCountDao{

}
