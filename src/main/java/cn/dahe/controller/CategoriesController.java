package cn.dahe.controller;

import cn.dahe.dto.AjaxObj;
import cn.dahe.dto.Pager;
import cn.dahe.dto.Tree;
import cn.dahe.model.Categories;
import cn.dahe.model.User;
import cn.dahe.service.ICategoriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 商品类别
 * Created by fy on 2017/1/17.
 */
@RequestMapping("server/categories")
@Controller
public class CategoriesController {
    private static Logger logger = LoggerFactory.getLogger(CategoriesController.class);
    @Resource
    private ICategoriesService categoriesService;
    /**
     * 商品分类跳转
     * @return
     */
    @RequestMapping(value = "treeAndList", method = RequestMethod.GET)
    public String categoriesTree(){
        return "categories/list";
    }

    /**
     * 商品分类树
     * @return
     */
    @RequestMapping(value = "tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> getCategoriesList(HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return  categoriesService.initTree("根目录", user.getStoreId());
    }

    /**
     * 根据参数查询标签页
     * @param aDataSet
     * @param session
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<Categories> categoriesList(String aDataSet, HttpSession session){
        User user = (User)session.getAttribute("loginUser");
        return categoriesService.findByParams(aDataSet, user.getStoreId());
    }

    /**
     * 添加
     * @param name
     * @param pid
     * @return
     */
    @RequestMapping(value = "addCategories", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addCategories(String name, int pid, HttpSession session){
        AjaxObj json = new AjaxObj();
        User user = (User)session.getAttribute("loginUser");
        categoriesService.add(name, pid, user.getStoreId());
        json.setMsg("分类添加成功");
        json.setResult(1);
        return json;
    }

    /**
     * 添加页面路由
     * @return
     */
    @RequestMapping(value = "addCategories/{pid}", method = RequestMethod.GET)
    public String addCategories(@PathVariable int pid, Model model){
        model.addAttribute("pid", pid);
        return "categories/add";
    }

    /**
     * 编辑
     * @param name
     * @param id
     * @return
     */
    @RequestMapping(value = "editCategories", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj editCategories(@RequestParam(defaultValue = "", required = false) String name,
                                  int id, @RequestParam(defaultValue = "-1", required = false) int pid,
                                  HttpSession session){
        AjaxObj json = new AjaxObj();
        categoriesService.update(name, id, pid);
        json.setMsg("分类修改成功");
        json.setResult(1);
        return json;
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "delCategories", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj addCategories(int id, HttpSession session){
        AjaxObj json = new AjaxObj();
        categoriesService.del(id);
        json.setMsg("分类删除成功");
        json.setResult(1);
        return json;
    }

    /**
     * 商品类别排序
     * @param ids
     */
    @RequestMapping(value = "categoriesSort", method = RequestMethod.POST)
    @ResponseBody
    public AjaxObj categoriesSort(String ids){
        AjaxObj json = new AjaxObj();
        categoriesService.categoriesSort(ids);
        json.setMsg("商品类别排序已保存");
        json.setResult(1);
        return json;
    }

    /**
     * 查询全部分类
     * @return
     */
    @RequestMapping(value = "categoriesList", method = RequestMethod.POST)
    @ResponseBody
    public List<Categories> categoriesList(HttpSession session){
        User user = (User) session.getAttribute("loginUser");
        return categoriesService.findAll(user.getStoreId());
    }
}
