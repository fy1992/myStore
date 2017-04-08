package cn.dahe.service.impl;

import cn.dahe.dao.IClientOrderDao;
import cn.dahe.dao.IClientOrderItemDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrder;
import cn.dahe.model.ClientOrderItem;
import cn.dahe.service.IClientOrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/3/16.
 */
@Service("clientOrderItemService")
public class ClientOrderItemServiceImpl implements IClientOrderItemService {
	@Resource
	private IClientOrderItemDao clientOrderItemDao;
	@Resource
	private IClientOrderDao clientOrderDao;

	@Override
	public void add(ClientOrderItem t) {
		clientOrderItemDao.add(t);
	}

	@Override
	public void del(int id) {
		clientOrderItemDao.delete(id);
	}

	@Override
	public void update(ClientOrderItem t) {
		clientOrderItemDao.update(t);
	}

	@Override
	public ClientOrderItem get(int id) {
		return clientOrderItemDao.get(id);
	}

	@Override
	public ClientOrderItem load(int id) {
		return clientOrderItemDao.load(id);
	}

	@Override
	public List<ClientOrderItem> findByClientOrderId(int clientOrderId) {
		return clientOrderItemDao.findByClientOrderId(clientOrderId);
	}

	@Override
	public List<ClientOrderItem> findByParams(Pager<Object> param) {
		Pager<ClientOrder> clientOrderPager = clientOrderDao.findByParam(0, 200, param);
		List<ClientOrder> clientOrders = clientOrderPager.getAaData();
		if (clientOrders != null && clientOrders.size() > 0) {
			List<ClientOrderItem> result = new ArrayList<>();
			clientOrders.forEach(clientOrder -> {
				List<ClientOrderItem> clientOrderItems = findByClientOrderId(clientOrder.getId());
				result.addAll(clientOrderItems);
			});
			return result;
		}
		return null;
	}
}
