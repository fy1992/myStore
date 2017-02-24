package cn.dahe.service.impl;

import cn.dahe.dao.IIndustryDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dao.IUserDao;
import cn.dahe.dto.RegisterDto;
import cn.dahe.model.Industry;
import cn.dahe.model.Store;
import cn.dahe.model.User;
import cn.dahe.service.IRegisterService;
import cn.dahe.util.NumberUtils;
import cn.dahe.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by fy on 2017/2/17.
 */
@Service("registerService")
public class RegisterServiceImpl implements IRegisterService{
    @Resource
    private IUserDao userDao;
    @Resource
    private IStoreDao storeDao;
    @Resource
    private IIndustryDao iIndustryDao;

    @Override
    public void register(RegisterDto registerDto) {
        Store store = new Store();
        store.setCreateDate(new Date());
        store.setStatus(1);
        store.setStoreNo(Long.toString(NumberUtils.getNo(4)));
        store.setAddr(registerDto.getAddr());
        Industry industry = iIndustryDao.get(registerDto.getIndustryId());
        store.setIndustry(industry.getId());
        int storeId = storeDao.addAndGetId4Integer(store);

        User user = new User();
        user.setLoginName(registerDto.getLoginName());
        user.setPassword(SecurityUtil.MD5(registerDto.getPassword()));
        user.setStatus(1);
        user.setAddr(registerDto.getAddr());
        user.setEmail(registerDto.getEmail());
        user.setStoreId(storeId);
        user.setPhone(registerDto.getMobile());
        userDao.addAndGetId4Integer(user);
    }
}
