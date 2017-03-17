package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.ClientOrderDto;
import cn.dahe.model.ClientOrder;
import cn.dahe.model.Vip;
import cn.dahe.service.IClientOrderService;
import cn.dahe.service.IVipLevelService;
import cn.dahe.service.IVipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 微信接口
 * Created by fy on 2017/3/17.
 */
@Controller
@RequestMapping("wechat")
public class WechatController {
    private static Logger logger = LoggerFactory.getLogger(WechatController.class);
    @Resource
    private IVipLevelService vipLevelService;
    @Resource
    private IVipService vipService;
    @Resource
    private IClientOrderService orderService;

    /**
     * 点餐
     * @return
     */
    @RequestMapping(value = "order", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj order(String openId, ClientOrderDto orderInfo){
        AjaxObj json = new AjaxObj();
        orderService.orderByWechat(openId, orderInfo);
        json.setResult(1);
        json.setMsg("点餐成功");
        return json;
    }

    /**
     * 查看所有订单
     * @return
     */
    @RequestMapping(value = "orderList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj orderList(String openId){
        AjaxObj json = new AjaxObj();
        List<ClientOrder> list = orderService.findByOpenId(openId);
        json.setResult(1);
        json.setObject(list);
        return json;
    }

    /**
     * 查看订单状态
     * @return
     */
    @RequestMapping(value = "orderDetail", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj orderDetail(String openId, String orderNo){
        AjaxObj json = new AjaxObj();

        json.setResult(1);
        return json;
    }

    /**
     * 查看会员信息
     * @return
     */
    @RequestMapping(value = "personal", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj personal(String openId){
        AjaxObj json = new AjaxObj();
        Vip vip = vipService.findByOpenId(openId);
        if(vip == null){
            json.setResult(0);
            json.setMsg("请注册");
            return json;
        }
        json.setResult(1);
        json.setObject(vip);
        return json;
    }

    /**
     * 查看优惠券
     * @return
     */
    @RequestMapping(value = "coupon", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj coupon(String openId){
        AjaxObj json = new AjaxObj();
        Vip vip = vipService.findByOpenId(openId);
        if(vip == null){
            json.setResult(0);
            json.setMsg("请注册");
            return json;
        }
        json.setResult(1);
        json.setObject(vip);
        return json;
    }
}
