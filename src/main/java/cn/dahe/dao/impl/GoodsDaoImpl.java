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
        List<Object> objectList = new ArrayList<>();
        StringBuffer hql = new StringBuffer("from Goods goods where 1=1 and goods.status = ?");
        objectList.add(status);
        if (StringUtils.isNotEmpty(goodsInfo) && StringUtils.isNotBlank(goodsInfo)){
            if(StringUtil.isNumber(goodsInfo)){
                hql.append(" and goods.goodsNo = ?");
            }else if(StringUtil.isPinyin(goodsInfo)){
                hql.append(" and goods.pinyin = ?");
            }else{
                hql.append(" and goods.name = ?");
            }
            objectList.add(goodsInfo);
        }
        if(categoriesId != -1){
            hql.append(" and goods.categories.id = ?");
            objectList.add(categoriesId);
        }
        if(supplierId != -1){
            hql.append(" and goods.categories.id = ?");
            objectList.add(categoriesId);
        }
        if(tagsId != -1){
            hql.append(" and goods.tags.id = ?");
            objectList.add(tagsId);
        }
        if(storeId != 0){
            hql.append(" and goods.storeId = ?");
            objectList.add(storeId);
        }
        hql.append(" order by "+ params.getOrderColumn() + " " +params.getOrderDir());
        return this.find(hql.toString(), objectList, start, pageSize);
    }
}
