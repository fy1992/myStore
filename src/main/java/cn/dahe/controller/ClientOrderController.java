package cn.dahe.controller;

import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrder;
import cn.dahe.model.User;
import cn.dahe.service.IClientOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 网店订单
 * Created by fy on 2017/3/20.
 */
@Controller
@RequestMapping("order")
public class ClientOrderController {
    private static Logger logger = LoggerFactory.getLogger(ClientOrderController.class);
    @Resource
    private IClientOrderService clientOrderService;

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getOrderList() {
        return "order/list";
    }

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<ClientOrder> getOrderList(String aDataSet, HttpSession session) {
        logger.info("--- order list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return clientOrderService.findByParams(aDataSet, user.getStoreId());
    }
}
