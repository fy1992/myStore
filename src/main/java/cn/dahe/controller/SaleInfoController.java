package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.SaleCount;
import cn.dahe.model.SaleInfo;
import cn.dahe.model.SaleInfoItem;
import cn.dahe.model.User;
import cn.dahe.service.ISaleInfoService;
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
import java.util.List;

/**
 * 销售统计
 * Created by fy on 2017/3/9.
 */
@Controller
@RequestMapping("server/saleInfo")
public class SaleInfoController {
    private static Logger logger = LoggerFactory.getLogger(SaleInfoController.class);
    @Resource
    private ISaleInfoService saleInfoService;
    /**
     * 营业概况
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String saleInfoList(){
        return "saleInfo/saleInfo";
    }

    /**
     * 营业概况
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj saleInfoList(String startTime, String endTime, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User) session.getAttribute("loginUser");
        List<SaleCount> list = saleInfoService.findByDay(startTime, endTime, user.getStoreId());
        json.setResult(1);
        json.setObject(list);
        return json;
    }

    /**
     * 销售单据
     * @return
     */
    @RequestMapping(value = "saleList", method = RequestMethod.GET)
    public String saleList(){
        return "saleInfo/saleList";
    }

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "saleList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<SaleInfo> saleList(HttpSession session, String aDataSet){
        logger.info("--- saleList list begin ---");
        User user = (User)session.getAttribute("loginUser");
        return saleInfoService.saleInfoList(aDataSet, user.getStoreId());
    }

    /**
     * 销售单据明细
     */
    @RequestMapping(value = "saleInfoItem/{id}", method = RequestMethod.GET)
    public String saleInfoItem(@PathVariable int id, Model model){
        List<SaleInfoItem> list = saleInfoService.findBySaleId(id);
        model.addAttribute("list", list);
        return "saleInfo/saleInfoItem";
    }
}
