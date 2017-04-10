package cn.dahe.util;

import java.util.Date;

/**
 * 生成token
 * Created by fy on 2017/3/13.
 */
public class TokenUtil {
    // token = username + storeId + date + storeNo + password + storeId + 3位随机数
    public static String getToken(String username, String password, String storeNo, int storeId){
        String token =  SecurityUtil.MD5(username + storeId + new Date().getTime() + storeNo + password);
        token = token + storeId + NumberUtils.getNo(3);
        return token;
    }
}
