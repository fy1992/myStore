package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import cn.dahe.model.GoodsTraffic;
import cn.dahe.model.OrderGoodsInfo;
import cn.dahe.model.User;
import cn.dahe.service.IGoodsTrafficService;
import cn.dahe.service.IOrderGoodsInfoService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 货流管理
 * Created by fy on 2017/1/23.
 */
@Controller
@RequestMapping("server/goodsTraffic")
public class GoodsTrafficController {
    private static Logger logger = LoggerFactory.getLogger(GoodsTrafficController.class);
    @Resource
    private IGoodsTrafficService goodsTrafficService;
    @Resource
    private IOrderGoodsInfoService orderGoodsInfoService;

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
    public Pager<GoodsTraffic> getGoodsTrafficList(HttpSession session, String aDataSet) {
        logger.info("--- goodsTraffic list begin ---");
        User user = (User) session.getAttribute("loginUser");
        return goodsTrafficService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 门店订单审核
     * @param step  1 审核 2 配货 3 完成
     * @return
     */
    @RequestMapping(value = "audit/{step}/{id}", method = RequestMethod.GET)
    public String audit(@PathVariable int step, @PathVariable int id, Model model){
        model.addAttribute("goodsTrafficId", id);
        if(step == 1){
            GoodsTraffic goodsTraffic = goodsTrafficService.get(id);
            model.addAttribute("status", goodsTraffic.getStatus());
            List<OrderGoodsInfo> list = orderGoodsInfoService.findOrderGoodsInfosByGoodsTrafficId(id);
            Set<Integer> c_set = new HashSet<>();
            long sum = 0, goodsSum = 0;
            for(OrderGoodsInfo orderGoodsInfo : list){
                c_set.add(orderGoodsInfo.getCategoriesId());
                sum += orderGoodsInfo.getPrice() * orderGoodsInfo.getOrderNum();
                goodsSum += orderGoodsInfo.getOrderNum();
            }
            model.addAttribute("num", goodsSum);
            model.addAttribute("categoriesNum", c_set.size());
            model.addAttribute("totalprice", sum);
            model.addAttribute("list", list);
            return "goodsTraffic/audit";
        }else if(step == 2){
            return "goodsTraffic/goodsPrepare";
        }else if(step == 3){
            return "goodsTraffic/auditFinished";
        }
        return null;
    }

    /**
     * 门店订单审核
     * @param id
     * @param type 0 作废 1 审核通过 2 恢复
     * @return
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj audit(int id, int type){
        AjaxObj json = new AjaxObj();
        goodsTrafficService.updateAuditGoodsTraffic(id, type);
        if(type == -1){
            json.setResult(-1);
            json.setMsg("该订单已作废");
        }else if(type == 1){
            json.setResult(1);
            json.setMsg("<%=request.getContextPath()%>/server/goodsTraffic/audit/2");
        }else{
            json.setResult(0);
            json.setMsg("该订单已恢复");
        }
        return json;
    }

    /**
     * 门店订单配货
     * @return
     */
    @RequestMapping(value = "prepare", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj goodsPrepare(int id, String orderGoodsInfos){
        AjaxObj json = new AjaxObj();
        goodsTrafficService.prepareGoods(id, orderGoodsInfos);
        json.setMsg("<%=request.getContextPath()%>/server/goodsTraffic/audit/3");
        json.setResult(1);
        return json;
    }

    /**
     * 通过订单id 查询订单商品
     * @param id
     * @return
     */
    @RequestMapping(value = "findOrderGoodsInfosByGoodsTrafficId", method = RequestMethod.POST)
    @ResponseBody
    public List<OrderGoodsInfo> findOrderGoodsInfosByGoodsTrafficId(int id){
        return  orderGoodsInfoService.findOrderGoodsInfosByGoodsTrafficId(id);
    }

}
