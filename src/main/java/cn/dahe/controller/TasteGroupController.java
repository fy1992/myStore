package cn.dahe.controller;

import cn.dahe.dto.Pager;
import cn.dahe.dto.TasteDto;
import cn.dahe.model.User;
import cn.dahe.service.ITasteGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by fy on 2017/1/18.
 */
@Controller
@RequestMapping("server/tasteGroup")
public class TasteGroupController {
    private static Logger logger = LoggerFactory.getLogger(TasteGroupController.class);
    @Resource
    private ITasteGroupService tasteGroupService;
    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getTasteList(){
        return "taste/list";
    }

    /**
     * 列表页查询
     * */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Pager<TasteDto> getTasteList(HttpSession session, String aDataSet){
        logger.info("--- taste list begin ---");
        User user = (User)session.getAttribute("loginUser");
        return tasteGroupService.tasteList(aDataSet, user.getStoreId());
    }
}
