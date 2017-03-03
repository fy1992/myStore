package cn.dahe.controller;

import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.Stock;
import cn.dahe.model.StockLog;
import cn.dahe.model.User;
import cn.dahe.service.IStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by fy on 2017/3/2.
 */
@Controller
@RequestMapping("server/stock")
public class StockController {
    private static Logger logger = LoggerFactory.getLogger(StockController.class);

    @Resource
    private IStockService stockService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(){
        return "stock/stockInfo";
    }

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<StockLog> getStoreList(HttpSession session, String aDataSet) {
        logger.info("--- stock list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return stockService.findByParams(aDataSet, user.getStoreId());
    }

}
