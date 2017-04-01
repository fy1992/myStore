package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.OrderGoodsInfo;
import cn.dahe.model.Store;
import cn.dahe.model.TrafficManage;
import cn.dahe.model.User;
import cn.dahe.service.IOrderGoodsInfoService;
import cn.dahe.service.IStoreService;
import cn.dahe.service.ITrafficManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 货流管理
 * Created by fy on 2017/1/30.
 */
@Controller
@RequestMapping("server/goodsTrafficManage")
public class GoodsTrafficManageController {
    private static Logger logger = LoggerFactory.getLogger(GoodsTrafficManageController.class);
    @Resource
    private ITrafficManageService trafficManageService;
    @Resource
    private IStoreService storeService;
    @Resource
    private IOrderGoodsInfoService orderGoodsInfoService;

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getTrafficList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        Store store = storeService.get(user.getStoreId());
        if(store == null){
            store = new Store();
            store.setMultiple(1);
        }
        model.addAttribute("store", store);
        return "trafficManage/list";
    }

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<TrafficManage> getTrafficManageList(HttpSession session, String aDataSet) {
        logger.info("---trafficManage list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return trafficManageService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 配货
     * @return
     */
    @RequestMapping(value = "prepare/{id}", method = RequestMethod.GET)
    public String prepareManage(@PathVariable int id, Model model){
        List<OrderGoodsInfo> orderGoodsInfoList = orderGoodsInfoService.findOrderGoodsInfosByTrafficManageId(id);
        TrafficManage trafficManage = trafficManageService.get(id);
        model.addAttribute("list", orderGoodsInfoList);
        model.addAttribute("trafficManage", trafficManage);
        model.addAttribute("num", orderGoodsInfoList.size());
        return "trafficManage/prepare";
    }

    /**
     * 配货
     * @return
     */
    @RequestMapping(value = "prepare", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj preparemanage(int id, int type){
        AjaxObj json = new AjaxObj();
        TrafficManage trafficManage = trafficManageService.updatePrepare(id, type);
        String msg = type == -1 ? "已拒绝进货" : "已完成进货";
        json.setMsg(msg);
        json.setObject(trafficManage.getOptTime());
        json.setResult(1);
        return json;
    }

    /**
     * 退货
     * @return
     */
    @RequestMapping(value = "returnGoods", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj returnGoods(int id, int type){
        AjaxObj json = new AjaxObj();
        TrafficManage trafficManage = trafficManageService.updateReturnedGoods(id, type);
        String msg = type == -1 ? "已拒绝退货" : "已完成退货";
        json.setMsg(msg);
        json.setObject(trafficManage.getOptTime());
        json.setResult(1);
        return json;
    }
}
