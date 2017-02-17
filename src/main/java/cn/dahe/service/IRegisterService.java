package cn.dahe.service;

import cn.dahe.dto.RegisterDto;

/**
 * Created by fy on 2017/2/17.
 */
public interface IRegisterService {
    /**
     * 注册
     */
    void register(RegisterDto registerDto);
}
