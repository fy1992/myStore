package cn.dahe.dao.impl;

import cn.dahe.dao.IClientOrderDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
@Repository("clientOrderDao")
public class ClientOrderDaoImpl extends BaseDaoImpl<ClientOrder> implements IClientOrderDao {
    @Override
    public Pager<ClientOrder> findByParam(int start, int pageSize, Pager<Object> params) {
        StringBuffer hql = new StringBuffer("from ClientOrder clientOrder where 1=1");
        Date startTime = params.getStartTime();
        Date endTime = params.getEndTime();
        int status = params.getStatus();
        String storeIdStr = params.getStringParam1();
        String orderNo = params.getStringParam2();
        int payStatus = params.getIntParam1();
        List<Object> list = new ArrayList<>();
        if(status != -2){
            hql.append(" and clientOrder.status = ?");
            list.add(status);
        }
        if(payStatus != -1){
            hql.append(" and clientOrder.payStatus = ?");
            list.add(payStatus);
        }
        if(startTime != null){
            startTime = new java.sql.Date(startTime.getTime());
            hql.append(" and clientOrder.orderTime >= ?");
            list.add(startTime);
        }
        if(endTime != null){
            endTime = new java.sql.Date(endTime.getTime());
            hql.append(" and clientOrder.orderTime <= ?");
            list.add(endTime);
        }
        if(StringUtils.isNotBlank(orderNo)){
            hql.append(" and clientOrder.clientOrderNo = ?");
            list.add(orderNo);
        }
        if(StringUtils.isNotBlank(storeIdStr)){
            String[] storeIdArr = storeIdStr.split(",");
            hql.append(" and clientOrder.storeId in (");
            for(int i = 0, len = storeIdArr.length; i < len; i++){
                hql.append("?,");
                list.add(Integer.parseInt(storeIdArr[i]));
            }
            hql.deleteCharAt(hql.length() - 1);
            hql.append(")");
        }
        hql.append(" order by " + params.getOrderColumn() + " " + params.getOrderDir());
        return this.find(hql.toString(), list, start, pageSize);
    }

    @Override
    public ClientOrder findByClientOrderNo(String clientOrderNo, int storeId) {
        String hql = "from ClientOrder where clientOrderNo = ? and storeId = ?";
        return (ClientOrder)this.queryByHql(hql, new Object[]{clientOrderNo, storeId});
    }

    @Override
    public List<ClientOrder> findByVipNo(String vipNo) {
        String hql = "from ClientOrder where vipNo = ?";
        return this.list(hql, vipNo);
    }

    @Override
    public List<ClientOrder> findByStoreId(int storeId, String status) {
        StringBuffer hql = new StringBuffer("from ClientOrder where storeId = ?");
        List<Object> list = new ArrayList<>();
        list.add(storeId);
        if(StringUtils.isNotBlank(status)){
            hql.append(" and status in (");
            String[] statusArr = status.split(",");
            for(int i = 0, len = statusArr.length; i < len; i++){
                hql.append("?,");
                list.add(Integer.parseInt(statusArr[i]));
            }
            hql.deleteCharAt(hql.length() - 1);
            hql.append(")");
        }
        return this.list(hql.toString(), list);
    }

    @Override
    public List<ClientOrder> findByOpenId(String openId) {
        String hql = "from ClientOrder where openId = ?";
        return this.list(hql, openId);
    }
}
