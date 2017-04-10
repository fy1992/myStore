package cn.dahe.util;


import java.util.Date;
import java.util.Random;

/**
 * 数字工具类
 * Created by fy on 2017/1/27.
 */
public class NumberUtils {
    /**
     * 生成随机数
     * @param  num
     * @return
     */
    public static long getNo(int num){
        Random random = new Random();
        StringBuffer result = new StringBuffer();
        for(int i = 0; i < num; i++) {
            result.append(random.nextInt(10));
        }
        return Long.parseLong(result.toString());
    }

    public static String getNoByTime(){
        String time = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        long num = getNo(2);
        return time + num;
    }

    public static void main(String[] args) {
        String str = TokenUtil.getToken("001", "123456", "3535", 4);
        System.out.println(str);
        str = str.substring(str.length() - 4);
        int storeId = Integer.parseInt(str.substring(0, 1));
        System.out.println(storeId);
    }
}
