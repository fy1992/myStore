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
    public List<Categories> findByPid(int pid) {
        String hql = "from Categories categories where categories.parent.id = ?";
        List<Object> list = new ArrayList<>();
        list.add(pid);
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
        int pid = params.getIntParam2();
        String hql = "from Categories categories where 1=1";
        List<Object> list = new ArrayList<>();
        if(pid != 0){
            hql += " and categories.parent.id = ?";
            list.add(pid);
        }else{
            hql += " and categories.parent is null";
        }
        //if(storeId != 0){
            hql += " and categories.storeId = ?";
            list.add(storeId);
        //}
        return this.find(hql, list, start, pageSize);
    }

    @Override
    public Categories findByName(String name, int storeId) {
        String hql = "from Categories categories where categories.name = ?";
        if(storeId != 0){
            hql += " and categories.storeId = ?";
            return (Categories)this.queryByHql(hql, new Object[]{name, storeId});
        }else{
            return (Categories)this.queryByHql(hql, name);
        }
    }
}
