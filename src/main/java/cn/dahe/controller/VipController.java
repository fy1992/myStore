package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.User;
import cn.dahe.model.Vip;
import cn.dahe.model.VipLevel;
import cn.dahe.model.VipSys;
import cn.dahe.service.IVipLevelService;
import cn.dahe.service.IVipService;
import cn.dahe.service.IVipSysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    @Resource
    private IVipSysService vipSysService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));
    }

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
    public AjaxObj addUser(Vip vip, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User) session.getAttribute("loginUser");
        vipService.add(vip, user.getStoreId());
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
    public String editVip(@PathVariable int id, Model model){
        Vip vip = vipService.get(id);
        model.addAttribute("vip", vip);
        return "vip/edit";
    }

    /**
     *会员修改
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
    //========================= vipLevel begin ==========================================
    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/vipLevelList", method = RequestMethod.GET)
    public String getVipLevelList(){
        return "vipLevel/list";
    }

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/vipLevelList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<VipLevel> getVipLevelList(String aDataSet, HttpSession session){
        logger.info("--- vipLevel list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return vipLevelService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 会员等級添加
     */
    @RequestMapping(value = "vipLevelAdd", method = RequestMethod.GET)
    public String addVipLevel(HttpSession session, Model model){
        User user = (User)session.getAttribute("loginUser");
        model.addAttribute("store", user.getStoreId());
        return "vipLevel/add";
    }


    /**
     *会员等級添加
     */
    @RequestMapping(value = "vipLevelAdd", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addVipLevel(VipLevel vipLevel, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User) session.getAttribute("loginUser");
        vipLevel.setStoreId(user.getStoreId());
        vipLevelService.add(vipLevel);
        json.setMsg("会员等级添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 会员等級修改跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "vipLevelEdit/{id}", method = RequestMethod.GET)
    public String vipLevelEdit(@PathVariable int id, Model model){
        VipLevel vipLevel = vipLevelService.get(id);
        model.addAttribute("vipLevel", vipLevel);
        return "vipLevel/edit";
    }

    /**
     *会员等級修改
     */
    @RequestMapping(value = "vipLevelEdit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj vipLevelEdit(VipLevel vipLevel){
        AjaxObj json = new AjaxObj();
        vipLevelService.update(vipLevel);
        json.setMsg("会员等级修改成功");
        json.setResult(1);
        return json;
    }

    /**
     * 所有會員等級
     * @param session
     * @return
     */
    @RequestMapping(value = "allVipLevel", method = RequestMethod.POST)
    @ResponseBody
    public List<VipLevel> findAllVipLevel(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return vipLevelService.findByStoreId(user.getStoreId());
    }
    //================================================================================================

    /**
     * 会员制度设置
     * @return
     */
    @RequestMapping(value = "vipSysSetting", method = RequestMethod.GET)
    public String vipSysSetting(HttpSession session, Model model){
        User user = (User) session.getAttribute("loginUser");
        model.addAttribute("vipSys", vipSysService.findByStoreId(user.getStoreId()));
        return "vip/vipDetail";
    }

    /**
     * 会员制度保存
     * @return
     */
    @RequestMapping(value = "vipSysSetting", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj vipSysSetting(VipSys vipSys){
        AjaxObj json = new AjaxObj();
        vipSysService.update(vipSys);
        json.setResult(1);
        json.setMsg("设置完成");
        return json;
    }
}
