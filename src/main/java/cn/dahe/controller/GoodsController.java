package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsTags;
import cn.dahe.model.GoodsUnit;
import cn.dahe.model.User;
import cn.dahe.service.IGoodsService;
import cn.dahe.service.IGoodsTagsService;
import cn.dahe.service.IGoodsUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * Created by fy on 2017/1/13.
 */
@Controller
@RequestMapping("goods")
public class GoodsController {
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Resource
    private IGoodsTagsService goodsTagsService;
    @Resource
    private IGoodsUnitService goodsUnitService;
    @Resource
    private IGoodsService goodsService;

    /***
     * 添加商品单位页
     * @return
     */
    @RequestMapping(value = "goodsUnit", method = RequestMethod.GET)
    public String addGoodsUnit(){
        return "goods/goodsUnit";
    }

    /**
     * 根据参数查询单位页
     * @param aDataSet
     * @param session
     * @return
     */
    @RequestMapping(value = "goodsUnitList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<GoodsUnit> goodsUnitList(String aDataSet, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return goodsUnitService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 添加商品单位
     * @param name
     * @return
     */
    @RequestMapping(value = "goodsUnitAdd", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addGoodsUnit(String name, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        if(goodsUnitService.findByName(name, (User)session.getAttribute("loginUser")) == null) {
            goodsUnitService.add(name, user.getStoreId());
            json.setMsg("单位添加成功");
            json.setResult(1);
        }else{
            json.setMsg("该单位已存在");
            json.setResult(0);
        }
        return json;
    }

    /***
     * 添加商品标签页
     * @return
     */
    @RequestMapping(value = "goodsTags", method = RequestMethod.GET)
    public String addGoodsTags(){
        return "goods/goodsTags";
    }

    /**
     * 添加商品标签
     * @param goodsTags
     * @return
     */
    @RequestMapping(value = "goodsTags", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addGoodsTags(GoodsTags goodsTags, HttpSession session){
        AjaxObj json = new AjaxObj();
        if(goodsTagsService.findByName(goodsTags.getName(), (User)session.getAttribute("loginUser")) == null) {
            goodsTagsService.add(goodsTags);
            json.setMsg("标签添加成功");
            json.setResult(1);
        }else{
            json.setMsg("该标签已存在");
            json.setResult(0);
        }
        return json;
    }

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getGoodsList(){
        return "goods/list";
    }

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<GoodsDto> getGoodsList(HttpSession session, String aDataSet){
        logger.info("--- goods list begin ---");
        User user = (User)session.getAttribute("loginUser");
        return goodsService.goodsList(aDataSet, user.getStoreId());
    }
}
