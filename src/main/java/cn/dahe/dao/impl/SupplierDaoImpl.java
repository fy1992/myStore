package cn.dahe.dao.impl;

import cn.dahe.dao.ISupplierDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/23.
 */
@Repository("supplierDao")
public class SupplierDaoImpl extends BaseDaoImpl<Supplier> implements ISupplierDao{
    @Override
    public Pager<Supplier> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from Supplier supplier where 1=1");
        List<Object> list = new ArrayList<>();

        return null;
    }
}
