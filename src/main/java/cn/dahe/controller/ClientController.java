package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.GoodsDtoSimple;
import cn.dahe.dto.GoodsTrafficDto;
import cn.dahe.model.Cashier;
import cn.dahe.model.Categories;
import cn.dahe.service.ICategoriesService;
import cn.dahe.service.IEmployeeService;
import cn.dahe.service.IGoodsService;
import cn.dahe.service.IGoodsTrafficService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 客户端请求
 * Created by fy on 2017/2/6.
 */
@Controller
@RequestMapping("client")
public class ClientController {
    private static Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Resource
    private IEmployeeService employeeService;
    @Resource
    private IGoodsTrafficService goodsTrafficService;
    @Resource
    private IGoodsService goodsService;
    @Resource
    private ICategoriesService categoriesService;

    /**
     * 客户端订货
     * @param  goodsTrafficDto
     */
    @RequestMapping(value = "orderGoods", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj orderGoods(GoodsTrafficDto goodsTrafficDto, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier) session.getAttribute("clientUser");
        goodsTrafficService.add(goodsTrafficDto,  cashier.getStoreId());
        return json;
    }

    /**
     * 通过类别查询商品
     * @param categoriesId
     */
    @RequestMapping(value = "goodsList", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj getGoodsListByCategorise(int categoriesId){
        AjaxObj json = new AjaxObj();
        List<GoodsDtoSimple> list = goodsService.goodsListByCategories(categoriesId);
        json.setResult(1);
        json.setObject(list);
        return json;
    }

    /**
     * 查询该门店下的所有商品类别
     */
    @RequestMapping(value = "categoriesList", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj getGoodsCategoriesList(HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier)session.getAttribute("clientUser");
        List<Categories> categoriesList = categoriesService.findAll(cashier.getStoreId());
        json.setResult(1);
        json.setObject(categoriesList);
        return json;
    }
}
