package cn.dahe.task;

import cn.dahe.model.SaleCount;
import cn.dahe.model.SalesCampaign;
import cn.dahe.model.Store;
import cn.dahe.service.ISaleInfoService;
import cn.dahe.service.ISalesCampaignService;
import cn.dahe.service.IStoreService;
import cn.dahe.util.DateUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    private ISaleInfoService saleInfoService;
    @Resource
    private IStoreService storeService;
    @Resource
    private ISalesCampaignService salesCampaignService;
    //每天凌晨1点初始化
    @Scheduled(cron = "0 0 1 * * ?")
//    @Scheduled(cron = "0 45 20 * * ?")
    @Transactional
    public void execute(){
        List<Store> storeList = storeService.findAll();
        if(storeList != null){
            storeList.forEach(store -> {
                //销售统计  新建
                SaleCount saleCount = new SaleCount();
                saleCount.setCountDate(DateUtil.format(new Date(), "yyyy-MM-dd"));
                saleCount.setStoreId(store.getId());
                saleCount.setPaidByMoney(0);
                saleInfoService.addSaleCount(saleCount);

                //营销活动 过期判断
                List<SalesCampaign> salesCampaigns = salesCampaignService.findAll(store.getId());
                salesCampaigns.forEach(salesCampaign -> {
                    Date time = salesCampaign.getEndDate();
                    if(new Date().after(time)){
                        salesCampaign.setOverdue(0);
                        salesCampaignService.update(salesCampaign);
                    }
                });
            });
        }
    }
}
