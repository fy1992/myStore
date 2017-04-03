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
        String result = "";
        for(int i = 0; i < num; i++) {
            result += random.nextInt(10);
        }
        return Long.parseLong(result);
    }

    public static String getNoByTime(){
        String time = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        long num = getNo(2);
        return time + num;
    }

    public static void main(String[] args) {
        System.out.println(getNoByTime());
        try {
            System.out.println(DecimalUtil.div(199, 100, 2));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
