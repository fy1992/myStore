package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTraffic;
import cn.dahe.model.Store;
import cn.dahe.model.StoreGoodsTraffic;
import cn.dahe.model.User;
import cn.dahe.service.IStoreService;
import cn.dahe.service.IUserService;
import cn.dahe.util.NumberUtils;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fy on 2017/1/24.
 */
@Controller
@RequestMapping("server/store")
public class StoreController {
    private static Logger logger = LoggerFactory.getLogger(StoreController.class);
    @Resource
    private IStoreService storeService;
    @Resource
    private IUserService userService;
    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getStoreList() {
        return "store/list";
    }

    /**
     * 列表页查询
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Store> getStoreList(HttpSession session, String aDataSet) {
        logger.info("--- store list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return storeService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 查询所有店面
     * @param session
     * @return
     */
    @RequestMapping(value = "/allStore", method = RequestMethod.POST)
    @ResponseBody
    public List<Store> getAllStore(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return storeService.findAll(user.getStoreId());
    }

    /**
     *店铺添加
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addStore(HttpSession session, Model model){
        User user = (User)session.getAttribute("loginUser");
        model.addAttribute("user", user);
        return "store/add";
    }

    /**
     *店铺添加
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addStore(Store store, User u, int roleId, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        if(storeService.add(store, u, user, roleId)){
            json.setInfo("添加成功");
            json.setStatus("y");
        }else{
            json.setInfo("该门店编号已存在");
            json.setStatus("n");
        }
        return json;
    }

    /**
     * 店铺修改跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editStore(@PathVariable int id, Model model){
        Store store = storeService.get(id);
        model.addAttribute("store", store);
        User user = userService.findByStoreId(store.getId());
        model.addAttribute("user", user);
        return "store/edit";
    }

    /**
     *店铺修改
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editStore(Store store, User user){
        AjaxObj json = new AjaxObj();
        storeService.update(store, user);
       /* json.setMsg("门店信息修改成功");
        json.setResult(1);*/
        json.setInfo("门店信息修改成功");
        json.setStatus("y");
        return json;
    }

    /**
     *店铺删除
     */
    @RequestMapping(value = "delStore", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj delStore(int id){
        AjaxObj json = new AjaxObj();
        storeService.del(id);
        json.setMsg("门店删除成功");
        json.setResult(1);
        return json;
    }

    /**
     * 生成门店编号
     * @return
     */
    @RequestMapping("newStoreNo")
    @ResponseBody
    public AjaxObj newStoreNo(){
        AjaxObj json = new AjaxObj();
        json.setMsg(Long.toString(NumberUtils.getNo(4)));
        json.setResult(1);
        return json;
    }

    /**
     * 子门店货流设置
     * @return
     */
    @RequestMapping(value = "addGoodsTraffic", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj storeGoodsTraffic(StoreGoodsTraffic storeGoodsTraffic){
        AjaxObj json = new AjaxObj();
        storeService.updateStoreGoodsTraffics(storeGoodsTraffic);
        /*json.setResult(1);
        json.setMsg("门店货流设置完成");*/
        json.setStatus("y");
        json.setInfo("门店货流设置完成");
        return json;
    }

    /**
     * 子门店货流设置
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "findStoreGoodsTraffic/{id}", method = RequestMethod.GET)
    public String getStoreGoodsTraffic(@PathVariable int id, Model model){
        StoreGoodsTraffic storeGoodsTraffic = storeService.findByStoreId(id);
        model.addAttribute("storeGoodsTraffic", storeGoodsTraffic);
        return "store/storeGoodsTraffic";
    }
}
