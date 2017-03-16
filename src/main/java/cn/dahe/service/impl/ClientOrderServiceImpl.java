package cn.dahe.service.impl;

import cn.dahe.dao.IClientOrderDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.ClientOrderDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrder;
import cn.dahe.model.ClientOrderItem;
import cn.dahe.model.Store;
import cn.dahe.service.IClientOrderService;
import cn.dahe.util.DateUtil;
import cn.dahe.util.NumberUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
@Service("clientOrderService")
public class ClientOrderServiceImpl implements IClientOrderService{
    @Resource
    private IClientOrderDao clientOrderDao;
    @Resource
    private IStoreDao storeDao;

    @Override
    public void add(ClientOrderDto t) {
        String orderItemInfo = t.getOrderItemInfo();
        ClientOrder clientOrder = new ClientOrder();
        clientOrder.setClientOrderNo(NumberUtils.getNoByTime());
        clientOrder.setDescription(t.getDescription());
        clientOrder.setOrderAddr(t.getOrderAddr());
        clientOrder.setOrderName(t.getOrderName());
        clientOrder.setPayType(t.getPayType());
        clientOrder.setPhone(t.getPhone());
        clientOrder.setType(0);
        int id = add(clientOrder);
        //TODO
        JSONArray json = JSONArray.parseArray(orderItemInfo);
        for(int i = 0, len = json.size(); i < len; i++){
            JSONObject map = JSONObject.parseObject(json.get(i).toString());
            ClientOrderItem clientOrderItem = new ClientOrderItem();

        }
    }

    @Override
    public int add(ClientOrder t) {
        t.setOrderTime(new Date());
        return clientOrderDao.addAndGetId4Integer(t);
    }

    @Override
    public void del(int id) {
        clientOrderDao.delete(id);
    }

    @Override
    public void update(ClientOrder t) {
        ClientOrder clientOrder = clientOrderDao.findByClientOrderNo(t.getClientOrderNo());
        clientOrderDao.update(clientOrder);
    }

    @Override
    public ClientOrder get(int id) {
        return clientOrderDao.get(id);
    }

    @Override
    public ClientOrder load(int id) {
        return clientOrderDao.load(id);
    }

    @Override
    public Pager<ClientOrder> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = -1;
        String startTime = "", endTime = "", orderNo = "";
        int s_id = 0;
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                }else if (jsonObject.get("name").equals("status")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                }else if(jsonObject.get("name").equals("startTime")){
                    startTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("endTime")) {
                    endTime = jsonObject.get("value").toString();
                }else if (jsonObject.get("name").equals("storeId")){
                    s_id = Integer.parseInt(jsonObject.get("value").toString());
                }else if (jsonObject.get("name").equals("orderNo")){
                    orderNo = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            if(StringUtils.isNotBlank(startTime)){
                params.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
            }
            if(StringUtils.isNotBlank(endTime)){
                params.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
            }
            params.setStatus(status);
            params.setOrderColumn("clientOrder.id");
            params.setOrderDir("desc");
            if(s_id != 0){
                storeId = s_id;
            }
            List<Store> stores = storeDao.findByPid(storeId);
            if(stores != null && stores.size() > 0) {
                StringBuffer sb = new StringBuffer();
                for (Store store : stores) {
                    sb.append(store.getId());
                }
                sb.deleteCharAt(sb.length() - 1);
                params.setStringParam1(sb.toString());
            }

            params.setStringParam2(orderNo);
            return clientOrderDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ClientOrder> findByVipNo(String vipNo) {
        return clientOrderDao.findByVipNo(vipNo);
    }

    @Override
    public List<ClientOrder> findByStoreId(int storeId) {
        return clientOrderDao.findByStoreId(storeId);
    }

    @Override
    public ClientOrder findByClientOrderNo(String clientOrderNo) {
        return clientOrderDao.findByClientOrderNo(clientOrderNo);
    }
}