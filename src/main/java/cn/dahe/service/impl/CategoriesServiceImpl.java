package cn.dahe.service.impl;

import cn.dahe.dao.ICategoriesDao;
import cn.dahe.dao.IGoodsDao;
import cn.dahe.dto.Pager;
import cn.dahe.dto.Tree;
import cn.dahe.model.Categories;
import cn.dahe.model.Goods;
import cn.dahe.service.ICategoriesService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/17.
 */
@Service("categoriesService")
public class CategoriesServiceImpl implements ICategoriesService{
    private static Logger logger = LoggerFactory.getLogger(CategoriesServiceImpl.class);
    @Resource
    private ICategoriesDao categoriesDao;
    @Resource
    private IGoodsDao goodsDao;

    @Override
    public List<Categories> findByPid(int pid, int storeId) {
        return null;
    }

    @Override
    public List<Tree> initTree(String rootName, int storeId) {
        List<Categories> categoriesList = categoriesDao.findAll(storeId);
        List<Tree> cts = new ArrayList<Tree>();
        for(Categories categories : categoriesList){
            Tree tree = new Tree();
            tree.setName(categories.getName());
            tree.setId(categories.getId());
            tree.setOpen(false);
            tree.setPid(categories.getParent() == null ? 0 : categories.getParent().getId());
            cts.add(tree);
        }
        Tree root = new Tree(0, rootName, -1, true);
        cts.add(root);
        return cts;
    }

    @Override
    public Pager<Categories> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int pid = 0;
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if(jsonObject.get("name").equals("pid")){
                    pid = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("categories.id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            params.setIntParam2(pid);
            return categoriesDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Categories t) {
        categoriesDao.add(t);
    }

    @Override
    public boolean del(int id) {
        List<Categories> categoriesList = categoriesDao.findByPid(id);
        int mark = 0;
        Pager<Object> params = new Pager<>();
        List<Goods> goodsList = null;
        if(categoriesList != null && categoriesList.size() > 0) {
            for (int i = 0, len = categoriesList.size(); i < len; i++) {
                int cid = categoriesList.get(i).getId();
                params.setIntParam1(cid);
                goodsList = goodsDao.findByParam(params);
                //若分类被使用则无法删除, 只删除没有被使用的分类
                if (goodsList != null && goodsList.size() > 0) {
                    mark ++;
                } else {
                    categoriesDao.delete(cid);
                }
            }
        }
        if(mark == 0){
            params.setIntParam1(id);
            goodsList = goodsDao.findByParam(params);
            if(goodsList != null && goodsList.size() > 0){
                return false;
            }else{
                categoriesDao.delete(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(Categories t) {
        categoriesDao.update(t);
    }

    @Override
    public Categories get(int id) {
        return categoriesDao.get(id);
    }

    @Override
    public Categories load(int id) {
        return categoriesDao.load(id);
    }

    @Override
    public void add(String name, int pid, int storeId) {
        Categories categories = new Categories();
        categories.setName(name);
        categories.setStoreId(storeId);
        Categories parent = get(pid);
        categories.setParent(parent);
        add(categories);
    }

    @Override
    public void update(String name, int id, int pid) {
        Categories categories = get(id);
        if(StringUtils.isNotEmpty(name) && StringUtils.isNotBlank(name)) {
            categories.setName(name);
        }
        if(pid != -1){
            Categories parent = get(pid);
            categories.setParent(parent);
        }
        update(categories);
    }

    @Override
    public void categoriesSort(String ids) {
        String[] idsArr = ids.split(",");
        for(int i = 0, len = idsArr.length; i < len; i++){
            Categories categories = get(Integer.parseInt(idsArr[i]));
            categories.setSeq(i);
            update(categories);
        }
    }

    @Override
    public Categories findByName(String name, int storeId) {
        return categoriesDao.findByName(name, storeId);
    }
}
