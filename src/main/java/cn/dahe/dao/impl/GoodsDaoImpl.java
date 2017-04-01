package cn.dahe.dao.impl;

import cn.dahe.dao.IGoodsDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import cn.dahe.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/13.
 */
@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements IGoodsDao{
    @Override
    public Pager<Goods> findByParam(int start, int pageSize, Pager<Object> params) {
        int status = params.getStatus();
        int categoriesId = params.getIntParam1();
        int supplierId = params.getIntParam2();
        int tagsId = params.getIntParam3();
        int storeId = params.getIntParam4();
        String goodsInfo = params.getStringParam1();
        int stockPage = params.getState();
        List<Object> objectList = new ArrayList<>();
        StringBuffer hql = new StringBuffer("from Goods goods where 1=1 and goods.status = ?");
        objectList.add(status);
        if (StringUtils.isNotEmpty(goodsInfo) && StringUtils.isNotBlank(goodsInfo)){
            if(StringUtil.isNumber(goodsInfo)){
                hql.append(" and goods.goodsNo = ?");
                objectList.add(goodsInfo);
            }else if(StringUtil.isPinyin(goodsInfo)){
                hql.append(" and goods.pinyin like ?");
                objectList.add("%" + goodsInfo + "%");
            }else{
                hql.append(" and goods.name like ?");
                objectList.add("%" + goodsInfo + "%");
            }
        }
        if(categoriesId != -1){
            hql.append(" and goods.categoriesId = ?");
            objectList.add(categoriesId);
        }
        if(supplierId != -1){
            hql.append(" and goods.supplierId = ?");
            objectList.add(supplierId);
        }
        if(tagsId != -1){
            hql.append(" and goods.tags.id = ?");
            objectList.add(tagsId);
        }
        if(storeId != 0){
            hql.append(" and goods.storeId = ?");
            objectList.add(storeId);
        }
        if(stockPage == 1){
            hql.append(" and ( goods.finishedStock.goodNum <= 0 or goods.overdueDay > 0 )");
        }

        hql.append(" order by "+ params.getOrderColumn() + " " +params.getOrderDir());
        return this.find(hql.toString(), objectList, start, pageSize);
    }

    @Override
    public List<Goods> findByParam(Pager<Object> params) {
        if(params == null){
            return null;
        }
        int categoriesId = params.getIntParam1(); //分类id
        int unitId = params.getIntParam2(); //单位id
        String hql = "from Goods goods where 1=1";
        if(categoriesId != 0){
            hql += " and goods.categoriesId = ?";
            return this.list(hql, categoriesId);
        }
        if(unitId != 0){
            hql += " and goods.mainUnitId = ?";
            return this.list(hql, unitId);
        }
        return this.list(hql);
    }

    @Override
    public Goods findByGoodsNo(String goodsNo, int storeId) {
        String hql = "from Goods goods where goods.goodsNo = ? and goods.storeId = ?";
        return (Goods)this.queryByHql(hql, new Object[]{goodsNo,storeId});
    }

    @Override
    public List<Goods> findAll(int storeId) {
        String hql = "from Goods goods";
        if(storeId != 0){
            hql += " where goods.storeId = ?";
            return this.list(hql, storeId);
        }
        return this.list(hql);
    }

    @Override
    public List<Goods> findIntermediaryGoods(int storeId) {
        String hql = "from Goods where storeId = ? and intermediary = 0 and autoFinished = 1";
        return this.list(hql, storeId);
    }
}
