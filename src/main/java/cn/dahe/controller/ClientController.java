package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.ClientDataDto;
import cn.dahe.model.*;
import cn.dahe.service.*;
import cn.dahe.util.CacheUtils;
import cn.dahe.util.SecurityUtil;
import cn.dahe.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
    @Resource
    private IVipLevelService vipLevelService;
    @Resource
    private IChangeShiftsService changeShiftsService;
    @Resource
    private IGoodsRawService goodsRawService;
    @Resource
    private IClientOrderService clientOrderService;
    @Resource
    private IBadGoodsService badGoodsService;
    @Resource
    private ISaleInfoService saleInfoService;
    @Resource
    private ITrafficManageService trafficManageService;
    @Resource
    private IStoreService storeService;
    @Resource
    private IUserService userService;

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(){
        return "test/add";
    }

    /**
     * 门店登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "storeLogin", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj storeLogin(String username, String password){
        AjaxObj json = new AjaxObj();
        User user = userService.findByLoginName(username);
        if(user.getPassword().equals(SecurityUtil.MD5(password))){
            CacheUtils.put("store", "store", username);
            json.setMsg("门店登录成功");
            json.setObject(user.getStoreId());
        }
        return json;
    }

    /**
     * 收银员登录
     * @param sid
     * @param cashierNo
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj cashierLogin(int sid, String cashierNo, String password, HttpSession session){
        String username = (String)CacheUtils.get("store", "store");
        AjaxObj json;
        if(StringUtils.isNotBlank(username)){
            json = employeeService.cashierLogin(sid, cashierNo, password);
            if(json.getResult() == 1){
                Cashier cashier = (Cashier) json.getObject();
                session.setAttribute("clientUser_" + sid, cashier);
                Store store = storeService.get(sid);
                String token = TokenUtil.getToken(cashierNo, password, store.getStoreNo(), sid);
                token += sid;
                CacheUtils.putCashierUser(token, cashier);
                Object obj  = CacheUtils.getChangeShifts("changeShifts_" + sid + cashier.getId());
                if(obj == null){
                    //交接班
                    int id = changeShiftsService.add(new ChangeShifts(null, cashier.getCashierNo(), cashier.getName(), 0, 0, cashier.getStoreId()));
                    CacheUtils.putChangeShifts("changeShifts_" + sid + cashier.getId(), id);
                }
                json.setObject(token);
                json.setResult(1);
                json.setMsg("登录成功");
            }
        }else{
            json = new AjaxObj();
            json.setMsg("请先登录门店");
            json.setResult(0);
        }
        return json;
    }

    /**
     * 收银员退出(交接班)
     * */
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    @ResponseBody
    public AjaxObj cashierLogout(int sid, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier) session.getAttribute("clientUser_" + sid);
        changeShiftsService.logout(cashier);
        session.removeAttribute("clientUser_" + sid);
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
    public AjaxObj orderGoods(int sid, ClientDataDto goodsTrafficDto, HttpSession session){
        AjaxObj json = new AjaxObj();
        logger.info("--- " + goodsTrafficDto.toString());
        Cashier cashier = (Cashier) session.getAttribute("clientUser_" + sid);
        goodsTrafficService.add(goodsTrafficDto,  cashier.getStoreId());
        json.setResult(1);
        json.setMsg("订单已下达，请等待配货");
        return json;
    }

    /**
     * 客户端退货 (原材料)
     * @param  clientDataDto
     */
    @RequestMapping(value = "returnedGoods", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj returnedGoods(int sid, ClientDataDto clientDataDto, HttpSession session){
        AjaxObj json = new AjaxObj();
        logger.info("--- " + clientDataDto.toString());
        Cashier cashier = (Cashier) session.getAttribute("clientUser_" + sid);
        trafficManageService.addReturnedGoods(clientDataDto, cashier.getStoreId());
        json.setResult(1);
        json.setMsg("退货单已下达，请等待审核");
        return json;
    }

    /**
     * 保存销售单据
     * @param saleInfo
     * @param session
     * @return
     */
    @RequestMapping(value = "saleInfo", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addSaleInfo(int sid, SaleInfo saleInfo, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier) session.getAttribute("clientUser_" + sid);
        saleInfoService.add(saleInfo, cashier.getStoreId());
        json.setResult(1);
        return json;
    }

    /**
     * 销售单据列表
     * @param info
     * @param startTime
     * @param endTime
     * @param session
     * @return
     */
    @RequestMapping(value = "saleInfoList", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj saleInfoList(@RequestParam(required = false, defaultValue = "0") String info, int sid, String startTime, String endTime, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier) session.getAttribute("clientUser_" + sid);
        List<SaleInfo> list = saleInfoService.saleInfoList(info, startTime, endTime, cashier.getStoreId());
        json.setResult(1);
        json.setObject(list);
        return json;
    }

    /**
     * 销售单据明细
     * @param saleInfoId
     * @return
     */
    @RequestMapping(value = "findBySaleInfoId", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj findBySaleInfoId(int saleInfoId){
        AjaxObj json = new AjaxObj();
        List<SaleInfoItem> list = saleInfoService.findBySaleId(saleInfoId);
        json.setResult(1);
        json.setObject(list);
        return json;
    }

    /**
     * 所有导购员
     * @return
     */
    @RequestMapping(value = "sales", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj getSalesList(int sid, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier) session.getAttribute("clientUser_" + sid);
        List<Sales> list = employeeService.findAllSales(cashier.getStoreId());
        json.setObject(list);
        json.setResult(1);
        return json;
    }
    /**
     * 通过类别查询商品（菜品）
     * @param categoriesId
     */
    @RequestMapping(value = "goodsList", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj getGoodsListByCategorise(int sid, int categoriesId, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier) session.getAttribute("clientUser_" + sid);
        List<ClientGoods> list = clientGoodsService.goodsListByCategories(categoriesId, cashier.getStoreId());
        json.setResult(1);
        json.setObject(list);
        return json;
    }

    /**
     * 原材料
     * @param categoriesId
     * @return
     */
    @RequestMapping(value = "rawList", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj getGoodsRawListByCategories(int sid, int categoriesId, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier) session.getAttribute("clientUser_" + sid);
        json.setResult(1);
        List<GoodsRaw> goodsRaws = goodsRawService.findByCategoriesId(categoriesId, cashier.getStoreId());
        json.setObject(goodsRaws);
        return json;
    }

    /**
     * 查询该门店下的所有商品（菜品）类别
     */
    @RequestMapping(value = "categoriesList", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj getGoodsCategoriesList(int sid, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier)session.getAttribute("clientUser_" + sid);
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
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj detail(int sid, String goodsNo, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier) session.getAttribute("clientUser_" + sid);
        ClientGoods clientGoods = clientGoodsService.findByGoodsNo(goodsNo, cashier.getStoreId());
        json.setObject(clientGoods);
        json.setResult(1);
        return json;
    }

    /**
     * 会员添加
     */
    @RequestMapping(value = "addVip", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addVip(int sid, Vip vip, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier)session.getAttribute("clientUser_" + sid);
        vip.setStoreId(cashier.getStoreId());
        vip.setCreateCardDate(new Date());
        vip.setStatus(1);
        vipService.add(vip);
        json.setObject("会员添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 会员等级
     * @return
     */
    @RequestMapping(value = "vipLevel", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj vipLevel(int sid, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier)session.getAttribute("clientUser_" + sid);
        List<VipLevel> vipLevelList = vipLevelService.findByStoreId(cashier.getStoreId());
        json.setResult(1);
        json.setObject(vipLevelList);
        return json;
    }

    /**
     * 指定参数查询会员
     * @param param
     * @param session
     * @return
     */
    @RequestMapping(value = "vipInfo", method = RequestMethod.GET)
    @ResponseBody
    public AjaxObj vipInfo(int sid, String param, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier)session.getAttribute("clientUser_" + sid);
        List<Vip> vips = vipService.findByVipInfo(param, cashier.getStoreId());
        json.setResult(1);
        json.setObject(vips);
        return json;
    }

    /**
     * 网店订单
     * @param session
     * @return
     */
    @RequestMapping(value = "netOrder", method = RequestMethod.GET)
    @ResponseBody
    public  AjaxObj netOrder(int sid, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier)session.getAttribute("clientUser_" + sid);
        List<ClientOrder> clientOrders = clientOrderService.findByStoreId(cashier.getStoreId(),"0,1");
        json.setResult(1);
        json.setObject(clientOrders);
        return json;
    }

    /**
     * 商品报损
     * @return
     */
    @RequestMapping(value = "badGoods", method = RequestMethod.POST)
    @ResponseBody
    public  AjaxObj badGoods(int sid, ClientDataDto clientDataDto, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier)session.getAttribute("clientUser_" + sid);
        badGoodsService.add(clientDataDto, cashier);
        json.setResult(1);
        json.setObject("报损提交成功");
        return json;
    }

    /**
     * 半成品列表
     * @param sid
     * @param session
     * @return
     */
    @RequestMapping(value = "intermediaryList", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj intermediaryList(int sid, HttpSession session){
        AjaxObj json = new AjaxObj();
        Cashier cashier = (Cashier)session.getAttribute("clientUser_" + sid);

        return json;
    }
}
