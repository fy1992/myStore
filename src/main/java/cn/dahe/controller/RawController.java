package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.GoodsRaw;
import cn.dahe.model.User;
import cn.dahe.service.IGoodsRawService;
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
 * 原材料
 * Created by 冯源 on 2017/3/24.
 */
@Controller
@RequestMapping("server/raw")
public class RawController {
    private static Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Resource
    private IGoodsRawService goodsRawService;

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
        model.addAttribute("goodsRaw", goodsRaw);
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
    public AjaxObj usedList(){
        AjaxObj json = new AjaxObj();
        List<GoodsRaw> goodsRawList = null;
        json.setResult(1);
        json.setObject("");
        return json;
    }
}
