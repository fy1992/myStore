package cn.dahe.filter;


import cn.dahe.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Created by fy on 2016/12/29.
 */
public class XssAndSqlInjectClear extends HttpServletRequestWrapper{
    public XssAndSqlInjectClear(HttpServletRequest servletRequest){
        super(servletRequest);
    }

    public String[] getParameterValues(String parameter){
        String[] values = super.getParameterValues(parameter);
        if(values == null){
            return null;
        }
        int len = values.length;
        String[] results = new String[len];
        for(int i = 0; i < len; i++){
            results[i] = StringUtil.filterSqlInjectAndXss(values[i]);
         }
         return results;
    }

    public String getParameter(String parameter){
        String value = super.getParameter(parameter);
        if(value == null){
            return null;
        }
        return StringUtil.filterSqlInjectAndXss(value);
    }

    public String getHeader(String name){
        String value = super.getHeader(name);
        if (value == null) {
            return null;
        }
        return StringUtil.filterSqlInjectAndXss(value);
    }
}
