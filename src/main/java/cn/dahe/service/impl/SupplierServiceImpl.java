package cn.dahe.service.impl;

import cn.dahe.dao.ISupplierDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Supplier;
import cn.dahe.service.ISupplierService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/1/24.
 */
@Service("supplierService")
public class SupplierServiceImpl implements ISupplierService{
    private static Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);
    @Resource
    private ISupplierDao supplierDao;

    @Override
    public void add(Supplier t) {
        supplierDao.add(t);
    }

    @Override
    public void del(int id) {
        supplierDao.delete(id);
    }

    @Override
    public void update(Supplier t) {
        supplierDao.update(t);
    }

    @Override
    public Supplier get(int id) {
        return supplierDao.get(id);
    }

    @Override
    public Supplier load(int id) {
        return supplierDao.load(id);
    }

    @Override
    public Pager<Supplier> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = 1;
        String keywords = "";
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("status")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("keywords")) {
                    keywords = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            params.setStatus(status);
            params.setOrderColumn("supplier.id");
            params.setOrderDir("desc");
            params.setIntParam4(storeId);
            params.setStringParam1(keywords);
            return supplierDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
