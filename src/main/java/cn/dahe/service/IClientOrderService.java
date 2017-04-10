package cn.dahe.service;

import cn.dahe.dto.ClientOrderDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrder;
import cn.dahe.model.Vip;

import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
public interface IClientOrderService {

	void add(ClientOrder t);

	void del(int id);

	void update(ClientOrder t);

	ClientOrder get(int id);

	ClientOrder load(int id);

	/**
	 * 根据参数查询
	 * 
	 * @param aDataSet
	 * @param storeId
	 * @return
	 */
	Pager<ClientOrder> findByParams(String aDataSet, int storeId);

	/**
	 * 根据openId编号查询
	 * 
	 * @param openId
	 * @return
	 */
	List<ClientOrder> findByOpenId(String openId);

	/**
	 * 根据vip编号查询
	 * 
	 * @param vipNo
	 * @return
	 */
	List<ClientOrder> findByVipNo(String vipNo);

	/**
	 * 根据店铺id和状态查询
	 * @param storeId
	 * @param status
	 * @return
	 */
	List<ClientOrder> findByStoreId(int storeId, String status);

	/**
	 * 根据订单编号查询订单
	 * @param clientOrderNo
	 * @param storeId
	 * @return
	 */
	ClientOrder findByClientOrderNo(String clientOrderNo, int storeId);
	
	/**
	 * 微信点餐
	 * 
	 * @param vip
	 * @param clientOrderDto
	 * @return
	 */
	ClientOrder addOrderByWechat(Vip vip, ClientOrderDto clientOrderDto);

	/**
	 * 订单处理
	 */
	void auditOrder(int id, int status, int storeId);

}
