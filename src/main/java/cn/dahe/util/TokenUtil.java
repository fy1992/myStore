package cn.dahe.util;

import java.util.Date;

/**
 * 生成token
 * Created by fy on 2017/3/13.
 */
public class TokenUtil {

    public static String getToken(String username, String password, String storeNo, int storeId){
        return SecurityUtil.MD5(username + storeId + new Date().getTime() + storeNo + password);
    }
}
