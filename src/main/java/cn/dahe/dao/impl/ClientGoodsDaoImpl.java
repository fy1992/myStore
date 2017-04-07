package cn.dahe.dao.impl;

import cn.dahe.dao.IClientGoodsDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.ClientGoods;
import cn.dahe.util.NumberUtils;
import cn.dahe.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/3/14.
 */
@Repository("clientGoodsDao")
public class ClientGoodsDaoImpl extends BaseDaoImpl<ClientGoods> implements IClientGoodsDao{
    @Override
    public List<ClientGoods> findAll(int storeId) {
        String hql = "from ClientGoods where storeId = ?";
        return this.list(hql, storeId);
    }

    @Override
    public List<ClientGoods> findByCategoriesId(int categoriesId, int storeId) {
        String hql = "from ClientGoods where categoriesId = ? and storeId = ?";
        return this.list(hql, new Object[]{categoriesId, storeId});
    }

    @Override
    public ClientGoods findByGoodsNo(String goodsNo, int storeId) {
        String hql = "from ClientGoods where goodsNo = ? and storeId = ?";
        return (ClientGoods)this.queryByHql(hql, new Object[]{goodsNo, storeId});
    }

    @Override
    public List<ClientGoods> findByStoreId(int storeId) {
        String hql = "from ClientGoods where storeId = ?";
        return this.list(hql, storeId);
    }

    @Override
    public Pager<ClientGoods> findByParam(int start, int pageSize, Pager<Object> params) {
        int storeId = params.getIntParam1();
        Date startTime = params.getStartTime();
        Date endTime = params.getEndTime();
        String info = params.getStringParam1();
        List<Object> list = new ArrayList<>();
        StringBuffer hql = new StringBuffer("from ClientGoods where storeId = ?");
        list.add(storeId);
        hql.append(" and semifinishedNum > 0");
        if(startTime != null){
            hql.append(" and semifinishedTime >= ?");
            list.add(startTime);
        }
        if(endTime != null){
            hql.append(" and semifinishedTime <= ?");
            list.add(endTime);
        }
        if(StringUtils.isNotBlank(info)){
            if(StringUtil.isNumber(info)){
                hql.append(" and goodsNo = ?");
                list.add(info);
            }else{
                hql.append(" and goodsName like ?");
                list.add("%" + info + "%");
            }
        }
        return this.find(hql.toString(), list, start, pageSize);
    }
}
