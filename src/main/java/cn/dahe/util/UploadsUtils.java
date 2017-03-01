package cn.dahe.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件上传工具类
 * Created by fy on 2017/1/30.
 */
public class UploadsUtils {
    private static Log log = LogFactory.getLog(UploadsUtils.class);
    /**
     * 转换文件名
     * */
    public static String changeFileName(String str){
        if(StringUtils.isEmpty(str) || str.equals("")){
            return "";
        }
        if(str.contains(".")){
            String firstStr = str.substring(0, str.lastIndexOf("."));
            String lastStr = str.substring(str.lastIndexOf("."));
            str = SecurityUtil.getHash(firstStr + System.currentTimeMillis()) + lastStr;
        }
        return str.toLowerCase();
    }

    /**
     * 检查文件后缀
     * @param str
     * @param targetPostFix
     * @return
     */
    public static boolean checkFilePostfix(String str, String targetPostFix){
        if(StringUtils.isEmpty(str) || str.equals("")){
            return false;
        }
        if(str.contains(".")){
            String lastStr = str.substring(str.lastIndexOf("."));
            return lastStr.equals(targetPostFix);
        }else{
            return false;
        }
    }


    public static void upload(MultipartFile file, String path){
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
            bos.write(bytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
