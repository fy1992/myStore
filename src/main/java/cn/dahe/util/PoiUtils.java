package cn.dahe.util;

import cn.dahe.model.Goods;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析 读取 生成 excel文件
 * Created by fy on 2017/1/29.
 */
public class PoiUtils {
    /**
     * 根据实体类导出excel
     * @param tableName 表格名称
     * @param excelHeader 表格头
     * @param headerClassName 表格头对应的实体类属性
     * @param list 表格数据
     * */
    public static void exportExcel(String tableName, String[] excelHeader, String[] headerClassName, List<Object> list){
        //声明一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet(tableName);
        sheet.setDefaultColumnWidth(20*256);
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        //生成一个样式
        HSSFCellStyle style = wb.createCellStyle();

        //样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for(int i = 0, len = excelHeader.length; i < len; i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
        try {
            for (int i = 0, len = list.size(); i < len; i++) {
                row = sheet.createRow(i + 1);
                for (int j = 0, length = headerClassName.length; j < length; j++) {
                    Object obj = ReflectUtils.getFieldValue(list.get(i), headerClassName[j]);
                    if(obj != null){
                        row.createCell(j).setCellValue(obj.toString());
                    }else{
                        row.createCell(j).setCellValue("");
                    }
                }
            }
           /* response.setHeader("Content-disposition", "attachment;filename=" + new String(tableName.getBytes("gb2312"), "iso8859-1") + ".xls");
            response.setContentType("application/vnd.ms-excel");
            OutputStream outputStream = response.getOutputStream();*/
            OutputStream outputStream = new FileOutputStream(new File("D:\\BaiduYunDownload\\text.xls"));
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //读取excel


    public static void main(String[] args) {
        Goods goods1 = new Goods();
        goods1.setName("可乐");
        goods1.setPinyin("kele");
        Goods goods2 = new Goods();
        goods2.setName("雪碧");
        goods2.setPinyin("xuebi");
        List<Object> list = new ArrayList<>();
        list.add(goods1);
        list.add(goods2);
        exportExcel("测试", new String[]{"名称", "价格"}, new String[]{"name", "pinyin"}, list);
    }
}
