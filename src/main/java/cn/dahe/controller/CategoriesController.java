package cn.dahe.controller;

import cn.dahe.dto.Pager;
import cn.dahe.dto.Tree;
import cn.dahe.model.Categories;
import cn.dahe.model.User;
import cn.dahe.service.ICategoriesService;
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
 * 商品类别
 * Created by fy on 2017/1/17.
 */
@RequestMapping("categories")
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
}
