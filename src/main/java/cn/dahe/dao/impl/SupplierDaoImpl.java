package cn.dahe.dao.impl;

import cn.dahe.dao.ISupplierDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Supplier;
import org.apache.commons.lang3.StringUtils;
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
        int status = params.getStatus();
        String keywords = params.getStringParam1();
        int storeId = params.getIntParam1();
        hql.append(" and supplier.status = ?");
        list.add(status);
        if(StringUtils.isNotBlank(keywords)){
            hql.append(" and supplier.name like ?");
            list.add("%" + keywords + "%");
        }
        if(storeId != -1){
            hql.append(" and supplier.storeId = ? ");
            list.add(storeId);
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public List<Supplier> findByName(int storeId, String name) {
        String hql = "from Supplier supplier where supplier.storeId = ? and supplier.name = ? and supplier.status = 1";
        return this.list(hql, new Object[]{storeId, name});
    }

    @Override
    public List<Supplier> findAll(int storeId) {
        String hql = "from Supplier supplier where supplier.storeId = ? and supplier.status = 1";
        return this.list(hql, storeId);
    }

    @Override
    public Supplier findByNo(String supplierNo) {
        String hql = "from Supplier supplier where supplier.supplierNo = ?";
        return (Supplier)this.queryByHql(hql, supplierNo);
    }
}
