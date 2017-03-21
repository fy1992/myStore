package cn.dahe.task;

import cn.dahe.dao.ISaleCountDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.model.SaleCount;
import cn.dahe.model.Store;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 各种过期时间的赋值
 * Created by 冯源 on 2017/3/19.
 */
@Component
public class FreshTask {
    @Resource
    private ISaleCountDao saleCountDao;
    @Resource
    private IStoreDao storeDao;
    //每天凌晨1点初始化 销售统计
    @Scheduled(cron = "0 0 1 * * ?")
    public void execute(){
        List<Store> storeList = storeDao.findAll();
        if(storeList != null){
            storeList.forEach(store -> {
                SaleCount saleCount = new SaleCount();
                saleCount.setCountDate(new Date());
                saleCount.setInfo("");
                saleCount.setStoreId(store.getId());
                saleCount.setPaidByMoney(0);
                saleCountDao.add(saleCount);
            });
        }
    }
}
