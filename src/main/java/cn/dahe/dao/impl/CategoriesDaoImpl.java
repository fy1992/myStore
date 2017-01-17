package cn.dahe.dao.impl;

import cn.dahe.dao.ICategoriesDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Categories;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/17.
 */
@Repository("categoriesDao")
public class CategoriesDaoImpl extends BaseDaoImpl<Categories> implements ICategoriesDao{
    @Override
    public List<Categories> findByPid(int pid, int storeId) {
        String hql = "from Categories categories where categories.parent.id = ? and categories.storeId = ?";
        List<Object> list = new ArrayList<>();
        list.add(pid);
        list.add(storeId);
        return this.find(hql, list);
    }

    @Override
    public List<Categories> findAll(int storeId) {
        String hql = "from Categories where storeId = ?";
        return this.list(hql, storeId);
    }

    @Override
    public Pager<Categories> findByParam(int start, int pageSize, Pager<Object> params) {
        int storeId = params.getIntParam1();
        String hql = "from Categories categories where 1=1";
        List<Object> list = new ArrayList<>();
        if(storeId != 0){
            hql += " and categories.storeId = ?";
            list.add(storeId);
        }
        return this.find(hql, list, start, pageSize);
    }
}
