package cn.dahe.service.impl;

import cn.dahe.dao.ISmallTicketDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.SmallTicket;
import cn.dahe.service.ISmallTicketService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fy on 2017/1/17.
 */
@Service("smallTicketService")
public class SmallTicketServiceImpl implements ISmallTicketService{
    private static Logger logger = LoggerFactory.getLogger(SmallTicketServiceImpl.class);
    @Resource
    private ISmallTicketDao smallTicketDao;

    @Override
    public void add(SmallTicket t) {
        smallTicketDao.add(t);
    }

    @Override
    public void del(int id) {
        smallTicketDao.delete(id);
    }

    @Override
    public void update(SmallTicket t) {
        smallTicketDao.update(t);
    }

    @Override
    public SmallTicket get(int id) {
        return smallTicketDao.get(id);
    }

    @Override
    public SmallTicket load(int id) {
        return smallTicketDao.load(id);
    }

    @Override
    public void add(String name, int type, int storeId) {
        SmallTicket smallTicket = new SmallTicket();
        smallTicket.setStoreId(storeId);
        smallTicket.setName(name);
        smallTicket.setType(type);
        smallTicketDao.add(smallTicket);
    }

    @Override
    public Pager<SmallTicket> findByParams(String aDataSet, int storeId) {
        return null;
    }

    @Override
    public SmallTicket findByName(String name, int storeId) {
        return smallTicketDao.findByName(name, storeId);
    }

    @Override
    public List<SmallTicket> findAll(int storeId) {
        return smallTicketDao.findAll(storeId);
    }

    @Override
    public void add(String smallTicketList, int storeId) {
        List<SmallTicket> smallTickets = JSON.parseArray(smallTicketList, SmallTicket.class);
        for(SmallTicket smallTicket : smallTickets){
            SmallTicket smallTicket1;
            int id = smallTicket.getId();
            if(id == 0){
                smallTicket1 = findByName(smallTicket.getName(), storeId);
                if(smallTicket1 == null) {
                    smallTicket.setStoreId(storeId);
                    add(smallTicket);
                }
            }else{
                smallTicket1 = get(id);
                smallTicket1.setName(smallTicket.getName());
                smallTicket1.setType(smallTicket.getType());
                update(smallTicket1);
            }
        }
    }
}
