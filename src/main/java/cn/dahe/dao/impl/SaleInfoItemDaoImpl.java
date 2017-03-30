package cn.dahe.dao.impl;

import cn.dahe.dao.ISaleInfoItemDao;
import cn.dahe.model.SaleInfoItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 销售单据明细
 * Created by fy on 2017/3/30.
 */
@Repository("salesInfoItemDao")
public class SaleInfoItemDaoImpl extends BaseDaoImpl<SaleInfoItem> implements ISaleInfoItemDao {
    @Override
    public List<SaleInfoItem> findBySaleInfoId(int saleInfoId) {
        String hql = "from SaleInfoItem where saleInfoId = ?";
        return this.list(hql, saleInfoId);
    }
}
