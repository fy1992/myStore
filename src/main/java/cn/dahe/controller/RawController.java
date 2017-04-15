package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import cn.dahe.model.GoodsRaw;
import cn.dahe.model.GoodsRawItem;
import cn.dahe.model.GoodsRawUsed;
import cn.dahe.model.User;
import cn.dahe.service.IGoodsRawItemService;
import cn.dahe.service.IGoodsRawService;
import cn.dahe.service.IGoodsRawUsedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
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
 * 原材料
 * Created by 冯源 on 2017/3/24.
 */
@Controller
@RequestMapping("server/raw")
public class RawController {
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Resource
    private IGoodsRawService goodsRawService;
    @Resource
    private IGoodsRawUsedService goodsRawUsedService;
    @Resource
    private IGoodsRawItemService goodsRawItemService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));

        binder.registerCustomEditor(int.class, new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(double.class, new CustomNumberEditor(Double.class, true));
    }

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String goodsRawList(){
        return "goods/rawList";
    }

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<GoodsRaw> goodsRawList(HttpSession session, String aDataSet){
        logger.info("--- goodsRaw list begin ---");
        User user = (User)session.getAttribute("loginUser");
        return goodsRawService.goodsRawList(aDataSet, user.getStoreId());
    }

    /**
     * 商品原材料添加页面跳转
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(){
        return "goods/rawAdd";
    }

    /**
     * 商品原材料添加
     * @param goodsRaw
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addGoods(GoodsRaw goodsRaw, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user =  (User) session.getAttribute("loginUser");
        goodsRaw.setStoreId(user.getStoreId());
        goodsRawService.add(goodsRaw);
        json.setMsg("原材料添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 商品原材料编辑页面跳转
     * @param id 原材料id
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, Model model){
        GoodsRaw goodsRaw = goodsRawService.get(id);
        model.addAttribute("raw", goodsRaw);
        return "goods/rawEdit";
    }

    /**
     * 商品原材料编辑
     * @param GoodsRaw
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj edit(GoodsRaw GoodsRaw){
        AjaxObj json = new AjaxObj();
        goodsRawService.update(GoodsRaw);
        json.setMsg("原材料编辑成功");
        json.setResult(1);
        return json;
    }

    /**
     * 原材料消耗
     */
    @RequestMapping(value = "usedList", method = RequestMethod.GET)
    public String usedList(){
        return "stock/goodsRawUse";
    }

    /**
     * 原材料消耗
     */
    @RequestMapping(value = "usedList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<GoodsRawUsed> usedList(HttpSession session, String aDataSet){
        User user = (User) session.getAttribute("loginUser");
        return goodsRawUsedService.goodsRawUsedList(aDataSet, user.getStoreId());
    }


    /**
     * 原材料删除
     * @param id
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj delGoods(int id){
        AjaxObj json = new AjaxObj();
        goodsRawService.del(id);
        json.setResult(1);
        json.setMsg("原材料删除成功");
        return json;
    }

    /**
     * 根据商品id查询配方明细
     * @param id
     * @return
     */
    @RequestMapping(value = "goodsRawItemList", method = RequestMethod.POST)
    @ResponseBody
    public List<GoodsRawItem> goodsRawList(int id){
        return goodsRawItemService.findByGoodsId(id);
    }

    /**
     * 选择原材料
     * @return
     */
    @RequestMapping(value = "rawToGoodsList/{id}", method = RequestMethod.GET)
    public String rawToGoodsList(@PathVariable int id,  Model model){
        model.addAttribute("goodsId", id);
        return "goods/rawToGoodsList";
    }

    /**
     * 查询原材料列表
     * @return
     */
    @RequestMapping(value = "rawList", method = RequestMethod.POST)
    @ResponseBody
    public List<GoodsRaw> rawList(int cid, String rawName, HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        Pager<Object> param = new Pager<>();
        param.setIntParam1(cid);
        param.setIntParam2(user.getStoreId());
        param.setStringParam1(rawName);
        return goodsRawService.findByParam(param);
    }

    /**
     * 保存配方详单
     * @param rawItems
     * @return
     */
    @RequestMapping(value = "addRawItem", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addRawItem(Goods goods, String rawItems){
        AjaxObj json = new AjaxObj();
        goodsRawItemService.addRawItems(goods.getId(), rawItems, goods.getUseRawPrice(), goods.getSemifinished(), goods.getAutoFinished(), goods.getTargetGoodsId(), goods.getTargetGoodsName(), goods.getTargetGoodsNum());
        json.setResult(1);
        json.setMsg("商品原材料设置完成");
        return json;
    }
}
