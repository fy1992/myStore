package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import cn.dahe.model.GoodsTags;
import cn.dahe.model.GoodsUnit;
import cn.dahe.model.SmallTicket;
import cn.dahe.model.User;
import cn.dahe.service.IGoodsService;
import cn.dahe.service.IGoodsTagsService;
import cn.dahe.service.IGoodsUnitService;
import cn.dahe.service.ISmallTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


/**
 * 商品相关
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
    @Resource
    private ISmallTicketService smallTicketService;

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
    @RequestMapping(value = "addGoodsUnit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addGoodsUnit(String name, HttpSession session){
        logger.info("-- addGoodsUnit --");
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        if(goodsUnitService.findByName(name, user) == null) {
            goodsUnitService.add(name, user.getStoreId());
            json.setMsg("单位添加成功");
            json.setResult(1);
        }else{
            json.setMsg("该单位已存在");
            json.setResult(0);
        }
        return json;
    }

    /**
     * 删除商品单位
     * @param id
     * @return
     */
    @RequestMapping(value = "delGoodsUnit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj delGoodsUnit(int id){
        logger.info("-- delGoodsUnit --");
        AjaxObj json = new AjaxObj();
        goodsUnitService.del(id);
        json.setResult(1);
        json.setMsg("单位删除成功");
        return json;
    }

    /**
     * 修改商品单位
     * @param name
     * @return
     */
    @RequestMapping(value = "editGoodsUnit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editGoodsUnit(int id, String name, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        if(goodsUnitService.findByName(name, user) == null) {
            goodsUnitService.update(id, name);
            json.setMsg("单位修改成功");
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
     * 根据参数查询标签页
     * @param aDataSet
     * @param session
     * @return
     */
    @RequestMapping(value = "goodsTagsList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<GoodsTags> goodsTagsList(String aDataSet, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return goodsTagsService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 添加商品标签
     * @param name
     * @return
     */
    @RequestMapping(value = "addGoodsTags", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addGoodsTags(String name, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        if(goodsTagsService.findByName(name, user) == null) {
            goodsTagsService.add(name, user.getStoreId());
            json.setMsg("标签添加成功");
            json.setResult(1);
        }else{
            json.setMsg("该标签已存在");
            json.setResult(0);
        }
        return json;
    }

    /**
     * 修改商品标签
     * @param name
     * @return
     */
    @RequestMapping(value = "editGoodsTags", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editGoodsTags(int id, String name, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        if(goodsTagsService.findByName(name, user) == null) {
            goodsTagsService.update(id, name);
            json.setMsg("标签修改成功");
            json.setResult(1);
        }else{
            json.setMsg("该标签已存在");
            json.setResult(0);
        }
        return json;
    }

    /**
     * 删除商品标签
     * @param id
     * @return
     */
    @RequestMapping(value = "delGoodsTags", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj delGoodsTags(int id){
        logger.info("-- delGoodsTags --");
        AjaxObj json = new AjaxObj();
        goodsTagsService.del(id);
        json.setResult(1);
        json.setMsg("标签删除成功");
        return json;
    }

    /***
     * 添加商品小票
     * @return
     */
    @RequestMapping(value = "smallTicket", method = RequestMethod.GET)
    public String addSmallTicket(){
        return "goods/smallTicket";
    }

    /**
     * 小票添加
     * @param name
     * @param session
     * @return
     */
    @RequestMapping(value = "addSmallTicket", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addSmallTicket(String name, int type, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        smallTicketService.add(name, type, user.getStoreId());
        json.setMsg("小票添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 小票删除
     * @param id
     * @return
     */
    @RequestMapping(value = "delSmallTicket", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj delSmallTicket(int id){
        AjaxObj json = new AjaxObj();
        smallTicketService.del(id);
        json.setMsg("小票删除成功");
        json.setResult(1);
        return json;
    }

    /**
     * 根据参数查询单位页
     * @param aDataSet
     * @param session
     * @return
     */
    @RequestMapping(value = "smallTicketList", method = RequestMethod.POST)
    @ResponseBody
    public Pager<SmallTicket> smallTicketList(String aDataSet, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return smallTicketService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 商品添加页面跳转
     * @return
     */
    @RequestMapping(value = "addGoods", method = RequestMethod.GET)
    public String addGoods(){
        return "goods/add";
    }

    /**
     * 商品添加
     * @param goods
     * @param session
     * @return
     */
    @RequestMapping(value = "addGoods", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addGoods(Goods goods, HttpSession session){
        AjaxObj json = new AjaxObj();

        json.setResult(1);
        json.setMsg("商品添加成功");
        return json;
    }

    /**
     * 商品删除
     * @param id
     * @return
     */
    @RequestMapping(value = "delGoods", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj delGoods(int id){
        AjaxObj json = new AjaxObj();
        goodsService.del(id);
        json.setResult(1);
        json.setMsg("商品删除成功");
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

    /**
     * 通过类别查询
     * @param categoriesId
     * @param session
     * @return
     */
    @RequestMapping(value = "/mobile/goodslist", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj getGoodsListByCategorise(int categoriesId, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        goodsService.goodsListByCategories(categoriesId, user.getStoreId());
        return json;
    }

    /**
     * 商品排序
     * @param ids
     */
    @RequestMapping(value = "goodsSort", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj goodsSort(String ids){
        AjaxObj json = new AjaxObj();
        goodsService.goodsSort(ids);
        json.setMsg("商品排序已保存");
        json.setResult(1);
        return json;
    }
}
