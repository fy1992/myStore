package cn.dahe.dao.impl;

import cn.dahe.dao.ICashierDao;
import cn.dahe.model.Cashier;
import org.springframework.stereotype.Repository;

/**
 * Created by fy on 2017/1/17.
 */
@Repository("cashierDao")
public class CashierDaoImpl extends BaseDaoImpl<Cashier> implements ICashierDao{
}
