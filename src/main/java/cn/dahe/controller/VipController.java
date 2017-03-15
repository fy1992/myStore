package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.User;
import cn.dahe.model.Vip;
import cn.dahe.service.IVipLevelService;
import cn.dahe.service.IVipService;
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
 * Created by fy on 2017/3/15.
 */
@Controller
@RequestMapping("server/vip")
public class VipController {
    private static Logger logger = LoggerFactory.getLogger(VipController.class);

    @Resource
    private IVipService vipService;
    @Resource
    private IVipLevelService vipLevelService;

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getVipList(){
        return "vip/list";
    }

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Vip> getVipList(String aDataSet){
        logger.info("--- vip list begin ---");
        return vipService.findByParams(aDataSet);
    }

    /**
     * 会员添加
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addVip(HttpSession session, Model model){
        User user = (User)session.getAttribute("loginUser");
        model.addAttribute("store", user.getStoreId());
        return "vip/add";
    }


    /**
     *会员添加
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addUser(Vip vip){
        AjaxObj json = new AjaxObj();
        vipService.add(vip);
        json.setMsg("用户添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 会员修改跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String editVip(@PathVariable int id, Model model){
        Vip vip = vipService.get(id);
        model.addAttribute("vip", vip);
        return "vip/edit";
    }

    /**
     *用户修改
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editVip(Vip vip){
        AjaxObj json = new AjaxObj();
        vipService.update(vip);
        json.setMsg("会员修改成功");
        json.setResult(1);
        return json;
    }
}
