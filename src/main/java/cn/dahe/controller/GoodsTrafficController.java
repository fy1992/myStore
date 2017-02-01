package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.GoodsTrafficDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;
import cn.dahe.model.User;
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
 * 货流管理
 * Created by fy on 2017/1/23.
 */
@Controller
@RequestMapping("goodsTraffic")
public class GoodsTrafficController {
    private static Logger logger = LoggerFactory.getLogger(GoodsTrafficController.class);
    @Resource
    private IGoodsTrafficService goodsTrafficService;
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
    public Pager<GoodsTraffic> getGoodsTrafficList(HttpSession session, String aDataSet) {
        logger.info("--- goodsTraffic list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return goodsTrafficService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 客户端订货
     * @param  goodsTrafficDto
     * @return
     */
    @RequestMapping(value = "orderGoods", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj orderGoods(GoodsTrafficDto goodsTrafficDto){
        AjaxObj json = new AjaxObj();
        goodsTrafficService.add(goodsTrafficDto);

        return json;
    }
}
