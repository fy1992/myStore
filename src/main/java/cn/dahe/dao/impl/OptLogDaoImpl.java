package cn.dahe.dao.impl;

import cn.dahe.dao.IOptLogDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.OptLog;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/13.
 */
@Repository("optLogDao")
public class OptLogDaoImpl extends BaseDaoImpl<OptLog> implements IOptLogDao {
    @Override
    public Pager<OptLog> findByParam(int start, int pageSize, Pager<Object> params) {
        int storeId = params.getIntParam1();
        String hql = "from OptLog optLog where 1=1";
        List<Object> list = new ArrayList<>();
        if(storeId != 0){
            hql += " and optLog.storeId = ?";
            list.add(storeId);
        }
        return this.find(hql, list, start, pageSize);
    }
}
