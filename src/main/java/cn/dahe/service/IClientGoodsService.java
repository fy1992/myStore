package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.ClientGoods;

import java.util.List;

/**
 * Created by fy on 2017/3/14.
 */
public interface IClientGoodsService {

	void del(int id);

	void update(ClientGoods t);

	boolean add(ClientGoods t);

	ClientGoods get(int id);

	ClientGoods load(int id);

	/**
	 * 根据类别查询
	 * 
	 * @param categories
	 * @return
	 */
	List<ClientGoods> goodsListByCategories(int categories, int storeId);

	/**
	 * 根据商品编号查找商品
	 * 
	 * @param goodsNo
	 * @param storeId
	 * @return
	 */
	ClientGoods findByGoodsNo(String goodsNo, int storeId);

	/**
	 * 查找店铺下所有商品
	 * 
	 * @param storeId
	 * @return
	 */
	List<ClientGoods> findAll(int storeId);

	/**
	 * 半成品记录
	 * @param aDataSet
	 * @param storeId
	 * @return
	 */
	Pager<ClientGoods> semifinishedList(String aDataSet, int storeId);
}
