package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Store;
import cn.dahe.model.StoreGoodsTraffic;
import cn.dahe.model.User;
import cn.dahe.service.IStoreService;
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
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addStore(Store store){
        AjaxObj json = new AjaxObj();
        storeService.add(store);
        json.setMsg("门店添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 店铺修改跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String editStore(@PathVariable int id, Model model){
        Store store = storeService.get(id);
        model.addAttribute("store", store);
        return "store/edit";
    }

    /**
     *店铺修改
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editStore(Store store){
        AjaxObj json = new AjaxObj();
        storeService.update(store);
        json.setMsg("门店信息修改成功");
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
     * 子门店货流设置列表
     * @return
     */
    @RequestMapping(value = "storeGoodsTrafficList", method = RequestMethod.GET)
    public String storeGoodsTraffic(){
        return "store/storeGoodsTrafficList";
    }

    /**
     * 子门店货流设置列表
     * @return
     */
    @RequestMapping(value = "storeGoodsTrafficList", method = RequestMethod.POST)
    @ResponseBody
    public List<StoreGoodsTraffic> storeGoodsTrafficData(){
        return  storeService.findAllStoreGoodsTraffic();
    }

    /**
     * 子门店货流设置
     * @return
     */
    @RequestMapping(value = "storeGoodsTraffic", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj storeGoodsTraffic(String storeGoodsTraffics){
        AjaxObj json = new AjaxObj();
        storeService.updateStoreGoodsTraffics(storeGoodsTraffics);
        json.setResult(1);
        json.setMsg("自门店货流设置完成");
        return json;
    }
}
