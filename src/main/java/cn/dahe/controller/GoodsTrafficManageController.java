package cn.dahe.controller;

import cn.dahe.dto.Pager;
import cn.dahe.model.TrafficManage;
import cn.dahe.model.User;
import cn.dahe.service.ITrafficManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


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
    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getTrafficList() {
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
}
