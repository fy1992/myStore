package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.GoodsTrafficDto;
import cn.dahe.model.Cashier;
import cn.dahe.model.Categories;
import cn.dahe.model.ClientGoods;
import cn.dahe.model.Sales;
import cn.dahe.model.Vip;
import cn.dahe.service.ICategoriesService;
import cn.dahe.service.IClientGoodsService;
import cn.dahe.service.IEmployeeService;
import cn.dahe.service.IGoodsTrafficService;
import cn.dahe.service.IVipService;
import cn.dahe.util.CacheUtils;
import cn.dahe.util.TokenUtil;
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
 * 客户端接口 （pc软件，android，ios）
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
    private IClientGoodsService clientGoodsService;
    @Resource
    private ICategoriesService categoriesService;
    @Resource
    private IVipService vipService;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(){
        return "test/add";
    }
    //===================================== 管理员 begin ============================================
    /**
     * 收银员登录
     * @param storeId
     * @param cashierNo
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj cashierLogin(int storeId, String cashierNo, String password, HttpSession session){
        AjaxObj json = employeeService.cashierLogin(storeId, cashierNo, password);
        if(json.getResult() == 1){
            session.setAttribute("clientUser", json.getObject());
            String token = TokenUtil.getToken(cashierNo, password);
            CacheUtils.putCashierUser(token, json.getObject());
            json.setObject(token);
            json.setResult(1);
            json.setMsg("登录成功");
        }
        return json;
    }

    /**
     * 收银员退出(交接班)
     * */
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    @ResponseBody
    public AjaxObj cashierLogout(HttpSession session){
        AjaxObj json = new AjaxObj();
        session.removeAttribute("clientUser");

        json.setResult(1);
        json.setMsg("成功退出");
        return json;
    }

    /**
     * 客户端订货 (原材料)
     * @param  goodsTrafficDto
     */
    @RequestMapping(value = "orderGoods", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj orderGoods(GoodsTrafficDto goodsTrafficDto, HttpSession session){
        AjaxObj json = new AjaxObj();
        logger.info("--- " + goodsTrafficDto.toString());
        Cashier cashier = (Cashier) session.getAttribute("clientUser");
        goodsTrafficService.add(goodsTrafficDto,  cashier.getStoreId());
        json.setResult(1);
        json.setMsg("订单已下达，请等待配货");
        return json;
    }

    /**
     * 所有导购员
     * @return
     */
    @RequestMapping(value = "sales", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj getSalesList(HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier) session.getAttribute("clientUser");
        List<Sales> list = employeeService.findAllSales(cashier.getStoreId());
        json.setObject(list);
        json.setResult(1);
        return json;
    }
    //===================================== 管理员 end============================================
    /**
     * 通过类别查询商品（菜品）
     * @param categoriesId
     */
    @RequestMapping(value = "goodsList", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj getGoodsListByCategorise(int categoriesId){
        AjaxObj json = new AjaxObj();
        List<ClientGoods> list = clientGoodsService.goodsListByCategories(categoriesId);
        json.setResult(1);
        json.setObject(list);
        return json;
    }

    /**
     * 查询该门店下的所有商品（菜品）类别
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

    /**
     * 商品细缆
     * @param goodsNo
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj detail(String goodsNo){
        AjaxObj json = new AjaxObj();
        ClientGoods clientGoods = clientGoodsService.findByGoodsNo(goodsNo);
        json.setObject(clientGoods);
        json.setResult(1);
        return json;
    }

    /**
     * 会员添加
     */
    @RequestMapping(value = "addVip", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj addVip(Vip vip, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier)session.getAttribute("clientUser");
        vip.setStoreId(cashier.getStoreId());
        vipService.add(vip);
        json.setObject("会员添加成功");
        json.setResult(1);
        return json;
    }
}
