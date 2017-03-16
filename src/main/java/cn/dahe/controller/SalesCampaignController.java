package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.SalesCampaign;
import cn.dahe.model.User;
import cn.dahe.service.ISalesCampaignService;
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

/**
 * 营销
 * Created by fy on 2017/3/16.
 */
@RequestMapping("server/salesCampaign")
@Controller
public class SalesCampaignController {
    private static Logger logger = LoggerFactory.getLogger(SalesCampaignController.class);
    @Resource
    private ISalesCampaignService salesCampaignService;

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getSCList(){
        return "salesCampaign/list";
    }

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<SalesCampaign> getSCList(HttpSession session, String aDataSet){
        logger.info("--- sc list begin ---");
        User user = (User)session.getAttribute("loginUser");
        return salesCampaignService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 添加
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addUser(HttpSession session, Model model){
        User user = (User)session.getAttribute("loginUser");
        model.addAttribute("store", user.getStoreId());
        return "salesCampaign/add";
    }

    /**
     *添加
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addSC(SalesCampaign salesCampaign){
        AjaxObj json = new AjaxObj();
        salesCampaignService.add(salesCampaign);
        json.setMsg("添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 修改跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editSC(@PathVariable int id, Model model){
        SalesCampaign salesCampaign = salesCampaignService.get(id);
        model.addAttribute("salesCampaign", salesCampaign);
        return "salesCampaign/edit";
    }

    /**
     *用户修改
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editUser(SalesCampaign salesCampaign){
        AjaxObj json = new AjaxObj();
        salesCampaignService.update(salesCampaign);
        json.setMsg("修改成功");
        json.setResult(1);
        return json;
    }
}