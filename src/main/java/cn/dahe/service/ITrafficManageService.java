package cn.dahe.service;

import cn.dahe.dto.ClientDataDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.TrafficManage;

/**
 * 货流管理
 * Created by fy on 2017/1/30.
 */
public interface ITrafficManageService {
    void add(TrafficManage t);
    void del(int id);
    void update(TrafficManage t);
    TrafficManage get(int id);
    TrafficManage load(int id);

    /**
     * 根据参数查询
     * @param aDataSet  dataables参数
     * @param storeId 门店id
     * @return
     */
    Pager<TrafficManage> findByParams(String aDataSet, int storeId);

    /**
     * 通过excel导入货流信息
     * @param storeId 门店id
     */
    void importTrafficManageExcel(int storeId);

    /**
     * 客户端配货审核
     * @param id  货流单id
     * @param type  是否通过退货操作 0 不通过 1 通过
     * @param trafficType 货流类型 0 进货单 1 退货单
     */
    TrafficManage updatePrepare(int id, int type, int trafficType);

    /**
     * 客户端退货
     * @param storeId 门店id
     */
    void addReturnedGoods(ClientDataDto clientDataDto, int storeId);
}
