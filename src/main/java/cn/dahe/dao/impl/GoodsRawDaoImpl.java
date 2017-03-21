package cn.dahe.dao.impl;

import cn.dahe.dao.IGoodsRawDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsRaw;
import cn.dahe.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 冯源 on 2017/3/21.
 */
@Repository("goodsRawDao")
public class GoodsRawDaoImpl extends BaseDaoImpl<GoodsRaw> implements IGoodsRawDao{
    @Override
    public Pager<GoodsRaw> findByParam(int start, int pageSize, Pager<Object> params) {
        int status = params.getStatus();
        int categoriesId = params.getIntParam1();
        int supplierId = params.getIntParam2();
        int storeId = params.getIntParam4();
        String rawInfo = params.getStringParam1();
        int stockPage = params.getState();
        List<Object> objectList = new ArrayList<>();
        StringBuffer hql = new StringBuffer("from GoodsRaw goodsRaw where 1=1 and goodsRaw.status = ?");
        objectList.add(status);
        if (StringUtils.isNotEmpty(rawInfo) && StringUtils.isNotBlank(rawInfo)){
            if(StringUtil.isNumber(rawInfo)){
                hql.append(" and goodsRaw.rawNo = ?");
                objectList.add(rawInfo);
            }else if(StringUtil.isPinyin(rawInfo)){
                hql.append(" and goodsRaw.pinyin like ?");
                objectList.add("%" + rawInfo + "%");
            }else{
                hql.append(" and goodsRaw.name like ?");
                objectList.add("%" + rawInfo + "%");
            }
        }
        if(categoriesId != -1){
            hql.append(" and goodsRaw.categoriesId = ?");
            objectList.add(categoriesId);
        }
        if(supplierId != -1){
            hql.append(" and goodsRaw.supplierId = ?");
            objectList.add(supplierId);
        }
        if(storeId != 0){
            hql.append(" and goodsRaw.storeId = ?");
            objectList.add(storeId);
        }
        if(stockPage == 1){
            hql.append(" and goodsRaw.stock.goodNum <= 0");
        }
        hql.append(" order by "+ params.getOrderColumn() + " " +params.getOrderDir());
        return this.find(hql.toString(), objectList, start, pageSize);
    }

    @Override
    public List<GoodsRaw> findByParam(Pager<Object> params) {
        if(params == null){
            return null;
        }
        int categoriesId = params.getIntParam1(); //分类id
        int unitId = params.getIntParam2(); //单位id
        String hql = "from GoodsRaw goodsRaw where 1=1";
        if(categoriesId != 0){
            hql += " and goodsRaw.categoriesId = ?";
            return this.list(hql, categoriesId);
        }
        if(unitId != 0){
            hql += " and goodsRaw.mainUnitId = ?";
            return this.list(hql, unitId);
        }
        return this.list(hql);
    }

    @Override
    public GoodsRaw findByRawNo(String rawNo) {
        String hql = "from GoodsRaw goodsRaw where goodsRaw.rawNo = ?";
        return (GoodsRaw)this.queryByHql(hql, rawNo);
    }

    @Override
    public List<GoodsRaw> findAll(int storeId) {
        String hql = "from GoodsRaw goodsRaw";
        if(storeId != 0){
            hql += " where goodsRaw.storeId = ?";
            return this.list(hql, storeId);
        }
        return this.list(hql);
    }
}
