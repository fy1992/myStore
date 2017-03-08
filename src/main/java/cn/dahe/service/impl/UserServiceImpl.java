package cn.dahe.service.impl;

import cn.dahe.dao.IUserDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.User;
import cn.dahe.service.IUserService;
import cn.dahe.util.SecurityUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by fy on 2016/12/30.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private IUserDao userDao;

    @Override
    public void add(User t) {
        t.setRegisterDate(new Date());
        userDao.add(t);
    }

    @Override
    public void del(int id) {
        userDao.delete(id);
    }

    @Override
    public void update(User t) {
        userDao.update(t);
    }

    @Override
    public User get(int id) {
        return userDao.get(id);
    }

    @Override
    public User load(int id) {
        return userDao.load(id);
    }

    @Override
    public User findByLoginName(String loginName) {
        return userDao.findByLoginName(loginName);
    }

    @Override
    public Pager<User> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = 1;
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("status")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            params.setOrderColumn("user.id");
            params.setOrderDir("desc");
            params.setStatus(status);
            params.setIntParam1(storeId);
            return userDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePassword(int id, String newPassword) {
        User user = get(id);
        user.setPassword(SecurityUtil.MD5(newPassword));
        update(user);
    }

    @Override
    public User findByStoreId(int storeId) {
        return userDao.findByStoreId(storeId);
    }
}
