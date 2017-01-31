package cn.dahe.util;

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

    public static void main(String[] args) {
        System.out.println(getNo(18));
    }
}
