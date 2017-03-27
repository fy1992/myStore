package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.ClientOrderItem;
import cn.dahe.model.GoodsRaw;
import cn.dahe.model.GoodsRawItem;
import cn.dahe.model.User;
import cn.dahe.service.IClientOrderItemService;
import cn.dahe.service.IGoodsRawItemService;
import cn.dahe.service.IGoodsRawService;
import cn.dahe.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
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
    private IClientOrderItemService clientOrderItemService;
    @Resource
    private IGoodsRawItemService goodsRawItemService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, true));

        binder.registerCustomEditor(int.class, new CustomNumberEditor(Integer.class, true));
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
     * @return
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
     * @return
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
    @RequestMapping(value = "usedList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj usedList(@RequestParam(required = false, defaultValue = "") String startTime,
                            @RequestParam(required = false, defaultValue = "") String endTime, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User) session.getAttribute("loginUser");
        Pager<Object> param = new Pager<>();
        if(StringUtils.isBlank(startTime)){
            startTime = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        param.setStartTime(DateUtil.format(startTime, "yyyy-MM-dd"));
        if(StringUtils.isNotBlank(endTime)){
            param.setEndTime(DateUtil.format(endTime, "yyyy-MM-dd"));
        }
        param.setIntParam1(user.getStoreId());
        List<ClientOrderItem> clientOrderItems = clientOrderItemService.findByParams(param);
        json.setResult(1);
        json.setObject("");
        return json;
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
     */
    @RequestMapping(value = "addRawItem", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addRawItem(int goodsId, String rawItems){
        AjaxObj json = new AjaxObj();
        goodsRawItemService.addRawItems(goodsId, rawItems);
        json.setResult(1);
        json.setMsg("商品原材料设置完成");
        return json;
    }
}
