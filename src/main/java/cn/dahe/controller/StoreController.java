package cn.dahe.controller;

import cn.dahe.dto.Pager;
import cn.dahe.model.Store;
import cn.dahe.model.User;
import cn.dahe.service.IStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by fy on 2017/1/24.
 */
@Controller
@RequestMapping("store")
public class StoreController {
    private static Logger logger = LoggerFactory.getLogger(StoreController.class);
    @Resource
    private IStoreService storeService;
    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getGoodsTrafficList() {
        return "goodsTraffic/list";
    }

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Store> getGoodsTrafficList(HttpSession session, String aDataSet) {
        logger.info("--- goodsTraffic list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return storeService.findByParams(aDataSet, user.getStoreId());
    }
}
