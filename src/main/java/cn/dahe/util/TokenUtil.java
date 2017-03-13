package cn.dahe.util;

import java.util.Date;

/**
 * Created by fy on 2017/3/13.
 */
public class TokenUtil {

    public static String getToken(String username, String password){
        return SecurityUtil.MD5(username + new Date().getTime() + password);
    }
}
