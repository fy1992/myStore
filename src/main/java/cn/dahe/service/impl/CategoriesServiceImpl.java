package cn.dahe.service.impl;

import cn.dahe.dao.ICategoriesDao;
import cn.dahe.dto.Pager;
import cn.dahe.dto.Tree;
import cn.dahe.model.Categories;
import cn.dahe.service.ICategoriesService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("categories.id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            return categoriesDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
