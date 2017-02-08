package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.GoodsTrafficDto;
import cn.dahe.model.Cashier;
import cn.dahe.service.IEmployeeService;
import cn.dahe.service.IGoodsTrafficService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 客户端请求
 * Created by fy on 2017/2/6.
 */
@Controller
@RequestMapping("client")
public class ClientController {
    private static Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Resource
    private IEmployeeService employeeService;
    @Resource
    private IGoodsTrafficService goodsTrafficService;
    /**
     * 客户端订货
     * @param  goodsTrafficDto
     * @return
     */
    @RequestMapping(value = "orderGoods", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj orderGoods(GoodsTrafficDto goodsTrafficDto, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier) session.getAttribute("cashier");
        goodsTrafficService.add(goodsTrafficDto,  cashier.getStoreId());
        return json;
    }
}
