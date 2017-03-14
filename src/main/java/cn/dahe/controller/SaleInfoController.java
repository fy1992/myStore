package cn.dahe.controller;

import cn.dahe.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * 销售统计
 * Created by fy on 2017/3/9.
 */
@Controller
@RequestMapping("server/saleInfo")
public class SaleInfoController {
    private static Logger logger = LoggerFactory.getLogger(SaleInfoController.class);

    /**
     * 营业概况
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String saleInfoList(HttpSession session, Model model){
        User user = (User) session.getAttribute("loginUser");
        return "saleInfo/salesInfo";
    }
}