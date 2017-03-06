package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.GoodsDtoSimple;
import cn.dahe.dto.Pager;
import cn.dahe.model.Categories;
import cn.dahe.model.Goods;
import cn.dahe.model.GoodsTags;
import cn.dahe.model.GoodsUnit;
import cn.dahe.model.SmallTicket;
import cn.dahe.model.User;
import cn.dahe.service.ICategoriesService;
import cn.dahe.service.IGoodsService;
import cn.dahe.service.IGoodsTagsService;
import cn.dahe.service.IGoodsUnitService;
import cn.dahe.service.ISmallTicketService;
import cn.dahe.util.ExcelTemplateUtils;
import cn.dahe.util.NumberUtils;
import cn.dahe.util.PoiUtils;
import cn.dahe.util.ResourcesUtils;
import cn.dahe.util.UploadsUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 商品相关
 * Created by fy on 2017/1/13.
 */
@Controller
@RequestMapping("server/goods")
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
    @Resource
    private ICategoriesService categoriesService;
    // 商品单位
    //=====================================goodsUnit begin=====================================================
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

    /**
     * 查询所有商品单位
     * @return
     */
    @RequestMapping(value = "getAllGoodsUnit", method = RequestMethod.POST)
    @ResponseBody
    public List<GoodsUnit> goodsUnitList(){
        return goodsUnitService.findAll();
    }
    //=====================================goodsUnit end=====================================================
    //商品标签
    //=====================================goodsTags begin=====================================================
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
     * 得到所有的标签
     * @param session
     * @return
     */
    @RequestMapping(value = "findAllGoodsTags", method = RequestMethod.POST)
    @ResponseBody
    public List<GoodsTags> findAllGoodsTags(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return goodsTagsService.findAll(user.getStoreId());
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
            GoodsTags goodsTags = goodsTagsService.findByName(name, user);
            json.setMsg("标签添加成功");
            json.setResult(1);
            json.setObject(goodsTags.getId());
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

    /**
     * 商品标签选择
     *@param  id
     * @return
     */
    @RequestMapping(value = "goodsTagsSelect", method = RequestMethod.GET)
    public String goodsTagsSelect(@RequestParam(required = false, defaultValue = "0") String id,
                                  @RequestParam(required = false, defaultValue = "0") String tagsIds,
                                  Model model){
        logger.info("--- tagsIds " + tagsIds + " ---");
        if(!id.equals("0")){
            Goods goods = goodsService.get(Integer.parseInt(id));
            if(goods != null){
                Set<GoodsTags> goodsTagsSet = goods.getGoodsTagsSet();
                List<String> list = new ArrayList<>();
                if(goodsTagsSet != null && goodsTagsSet.size() > 0){
                    for(GoodsTags goodsTags : goodsTagsSet){
                        list.add(Integer.toString(goodsTags.getId()));
                    }
                    tagsIds = tagsIds.join(",", list);
                }
                model.addAttribute("type", "edit");
            }else{
                model.addAttribute("type", "add");
            }
        }else{
            model.addAttribute("type", "add");
        }
        logger.info("--- tagsIds " + tagsIds + " ---");
        model.addAttribute("tagsIds", tagsIds);
        return "goods/add_goodsTags";
    }

    @RequestMapping(value = "allGoodsTags", method = RequestMethod.POST)
    @ResponseBody
    public List<GoodsTags> findGoodsTags(HttpSession session){
        User user  = (User)session.getAttribute("loginUser");
        return goodsTagsService.findAll(user.getStoreId());
    }
    //=====================================goodsTags end=====================================================
    //厨打小票
    //=====================================smallTicket begin=====================================================
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
     * @param smallTicketList
     * @param session
     * @return
     */
    @RequestMapping(value = "addSmallTicket", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addSmallTicket(String smallTicketList, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        smallTicketService.add(smallTicketList, user.getStoreId());
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
     * 得到所有的小票
     * @param session
     * @return
     */
    @RequestMapping(value = "findAllSmallTicket", method = RequestMethod.POST)
    @ResponseBody
    public List<SmallTicket> findAllSmallTicket(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return smallTicketService.findAll(user.getStoreId());
    }

    /**
     * 厨房小票选择
     *@param  id
     * @return
     */
    @RequestMapping("smallTicketSelect")
    public String smallTicketSelect(@RequestParam(required = false, defaultValue = "0") String id,
                                    @RequestParam(required = false, defaultValue = "0") String stsIds,
                                    Model model){
        logger.info("--- stsIds : "+stsIds+" ---");
        if(!id.equals("0")){
            Goods goods = goodsService.get(Integer.parseInt(id));
            if(goods != null){
                Set<SmallTicket> smallTicketSet = goods.getSmallTicketSet();
                List<String> list = new ArrayList<>();
                if(smallTicketSet != null && smallTicketSet.size() > 0){
                    for(SmallTicket smallTicket : smallTicketSet){
                        list.add(Integer.toString(smallTicket.getId()));
                    }
                    stsIds = stsIds.join(",", list);
                }
                model.addAttribute("type", "edit");
            }else{
                model.addAttribute("type", "add");
            }
        }else{
            model.addAttribute("type", "add");
        }
        model.addAttribute("stsIds", stsIds);
        logger.info("--- stsIds : "+stsIds+" ---");
        return "goods/add_smallTicket";
    }

    //=======================================smallTicket end=======================================================
    //商品
    //=======================================goods begin=========================================================
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
     * @param goodsDto
     * @return
     */
    @RequestMapping(value = "addGoods", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addGoods(GoodsDto goodsDto){
        AjaxObj json = new AjaxObj();
        goodsService.add(goodsDto);
        json.setInfo("商品添加成功");
        json.setStatus("y");
        return json;
    }

    /**
     * 商品编辑页面跳转
     * @return
     */
    @RequestMapping(value = "editGoods/{id}", method = RequestMethod.GET)
    public String editGoods(@PathVariable int id, Model model){
        Goods goods = goodsService.get(id);
        GoodsDto goodsDto = goodsService.formatGoodsToGoodsDto(goods);
        model.addAttribute("goodsDto", goodsDto);
        return "goods/edit";
    }

    /**
     * 商品编辑
     * @param goodsDto
     * @return
     */
    @RequestMapping(value = "editGoods", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editGoods(GoodsDto goodsDto){
        AjaxObj json = new AjaxObj();
        goodsService.add(goodsDto);
        json.setInfo("商品编辑成功");
        json.setStatus("y");
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
     * 商品排序页面路由
     * @param categoriesId
     * @param model
     * @return
     */
    @RequestMapping(value = "goodsSort", method = RequestMethod.GET)
    public String goodsSort(int categoriesId, Model model){
        Categories categories = categoriesService.get(categoriesId);
        model.addAttribute("categories", categories);
        return "goods/sortPage";
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

    /**
     * 商品复制
     * @param storeId
     * @param  ids
     * @return
     */
    @RequestMapping(value = "goodsCopy", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj goodsCopy(int storeId, String ids){
        AjaxObj json = new AjaxObj();
        goodsService.goodsCopy(storeId, ids);
        json.setMsg("商品复制成功");
        json.setResult(1);
        return json;
    }

    /**
     * 导入excel添加
     * @param file
     * @param isCreateNewUnit
     * @param isCreateNewCategories
     * @return
     */
    @RequestMapping("importExcel")
    @ResponseBody
    public AjaxObj importExcel(MultipartFile file, int isCreateNewCategories, int isCreateNewUnit, HttpSession session){
        AjaxObj json = new AjaxObj();
        if(file == null){
            json.setMsg("请选择文件上传");
            json.setResult(0);
            return json;
        }
        if(!UploadsUtils.checkFilePostfix(file.getOriginalFilename(), "xls")){
            json.setMsg("无效的文件类型，请上传xls类型的文件");
            json.setResult(0);
            return json;
        }
        if(file.getSize() > 3000000){
            json.setMsg("上传失败，文件大小大于3M");
            json.setResult(0);
            return json;
        }
        User user = (User)session.getAttribute("loginUser");
        Map<String, Object> map = goodsService.importGoodsExcel(file, user.getStoreId(), isCreateNewCategories, isCreateNewUnit);
        Object obj = map.get("goods");
        if(obj != null){
            json.setMsg("商品导入成功");
            json.setResult(1);
        }else{
            json.setMsg(map.get("error").toString());
            json.setResult(0);
        }
        return json;
    }

    /**
     * 导出excel
     */
    @RequestMapping("exportExcel")
    @ResponseBody
    public AjaxObj exportExcel(HttpServletResponse response, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User) session.getAttribute("loginUser");
        try {
            String tableName = "商品信息";
            List<Goods> goodsList = goodsService.findAll(user.getStoreId());
            HSSFWorkbook wb = PoiUtils.exportExcel(tableName, null, null, null);
            response.setHeader("Content-disposition", "attachment;filename=" + new String(tableName.getBytes("gb2312"), "iso8859-1") + ".xls");
            response.setContentType("application/vnd.ms-excel");
            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 导出excel模板下载
     */
    @RequestMapping("exportExcelTemplate")
    @ResponseBody
    public AjaxObj exportExcelTemplate(HttpServletResponse response){
        AjaxObj json = new AjaxObj();
        try {
            String tableName = "商品导入模板（餐饮）";
            HSSFWorkbook wb = ExcelTemplateUtils.goodsExcelTemplate();
            response.setHeader("Content-disposition", "attachment;filename=" + new String(tableName.getBytes("gb2312"), "iso8859-1") + ".xls");
            response.setContentType("application/vnd.ms-excel");
            OutputStream outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 生成商品编号
     * @return
     */
    @RequestMapping("newGoodsNo")
    @ResponseBody
    public AjaxObj newGoodsNo(){
        AjaxObj json = new AjaxObj();
        json.setMsg(Long.toString(NumberUtils.getNo(13)));
        json.setResult(1);
        return json;
    }

    /**
     * 商品图片上传
     * @return String
     */
    @RequestMapping(value = "uploadImg", method = RequestMethod.GET)
    public String uploadImgView(){
        return "goods/imgUpload";
    }

    /**
     * 图片上传
     * @param file 图片
     * */
    @RequestMapping(value = "uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj uploadImg(MultipartFile file){
        AjaxObj json = new AjaxObj();
        if(file == null || file.isEmpty()){
            json.setMsg("请选择图片");
            json.setResult(0);
            return json;
        }
        if(file.getSize() > Long.parseLong(ResourcesUtils.getFileSize())){
            json.setMsg("图片大小不能超过3m");
            json.setResult(0);
            return json;
        }
        String url = goodsService.upload(file);
        json.setMsg(url);
        json.setResult(1);
        return json;
    }
    //=======================================goods end=========================================================
}
