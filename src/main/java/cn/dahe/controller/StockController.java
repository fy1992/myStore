package cn.dahe.controller;

import cn.dahe.service.IStockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by fy on 2017/3/2.
 */
@Controller
@RequestMapping("server/stock")
public class StockController {
    @Resource
    private IStockService stockService;

}
