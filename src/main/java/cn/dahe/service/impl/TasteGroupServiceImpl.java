package cn.dahe.service.impl;

import cn.dahe.dao.ITasteGroupDao;
import cn.dahe.dto.Pager;
import cn.dahe.dto.TasteDto;
import cn.dahe.model.TasteGroup;
import cn.dahe.service.ITasteGroupService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/18.
 */
@Service("tasteGroupService")
public class TasteGroupServiceImpl implements ITasteGroupService{
    private static Logger logger = LoggerFactory.getLogger(TasteGroupServiceImpl.class);
    @Resource
    private ITasteGroupDao tasteGroupDao;
    @Override
    public void add(TasteGroup t) {
        tasteGroupDao.add(t);
    }

    @Override
    public void del(int id) {
        tasteGroupDao.delete(id);
    }

    @Override
    public void update(TasteGroup t) {
        tasteGroupDao.update(t);
    }

    @Override
    public TasteGroup get(int id) {
        return tasteGroupDao.get(id);
    }

    @Override
    public TasteGroup load(int id) {
        return tasteGroupDao.load(id);
    }

    @Override
    public Pager<TasteDto> tasteList(String aDataSet, int storeId) {
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
            params.setOrderColumn("tasteGroup.id");
            params.setOrderDir("desc");
            params.setIntParam4(storeId);
            Pager<TasteGroup> tasteGroup_pager = tasteGroupDao.findByParam(start, pageSize, params);
            Pager<TasteDto> tasteGroup_dto_pager = new Pager<>();
            tasteGroup_dto_pager.setiTotalDisplayRecords(tasteGroup_pager.getiTotalDisplayRecords());
            tasteGroup_dto_pager.setiTotalRecords(tasteGroup_pager.getiTotalRecords());
            List<TasteGroup> tasteGroupList = tasteGroup_pager.getAaData();
            List<TasteDto> tasteDtoList = new ArrayList<>(tasteGroupList.size());
            for(TasteGroup tasteGroup : tasteGroupList){
                tasteDtoList.add(new TasteDto().init(tasteGroup));
            }
            tasteGroup_dto_pager.setAaData(tasteDtoList);
            return tasteGroup_dto_pager;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
