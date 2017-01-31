package cn.dahe.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
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
    public static HSSFWorkbook exportExcel(String tableName, String[] excelHeader, String[] headerClassName, List<Object> list){
        //声明一个工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        //声明一个单子并命名
        HSSFSheet sheet = wb.createSheet(tableName);
        sheet.setDefaultColumnWidth(200*256);
        //创建第一行
        HSSFRow row = sheet.createRow(0);
        //生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setWrapText(true);//设置自动换行
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //水平布局：居中
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 10); //字体高度
        font.setColor(HSSFFont.COLOR_NORMAL); //字体颜色
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗
        font.setFontName("宋体"); //字体
        style.setFont(font);
        //样式字体居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        CellStyle cellStyle = wb.createCellStyle();
        HSSFFont cellFont = wb.createFont();
        cellFont.setFontName("宋体");
        cellFont.setColor(HSSFFont.COLOR_NORMAL);
        cellStyle.setFont(cellFont);
        cellStyle.setWrapText(true);
        for(int i = 0, len = excelHeader.length; i < len; i++){
            HSSFCell cell = row.createCell(i);
            row.setRowStyle(style);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(cellStyle);
        }

        try {
            if(list.size() > 0){
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
            }
            return wb;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析excel的数据
     * @param hssfRow
     * @return
     */
    public static String getValue(HSSFCell hssfRow) {
         if (hssfRow.getCellType() == hssfRow.CELL_TYPE_BOOLEAN) {
                 return String.valueOf(hssfRow.getBooleanCellValue());
             } else if (hssfRow.getCellType() == hssfRow.CELL_TYPE_NUMERIC) {
                 return String.valueOf(hssfRow.getNumericCellValue());
             } else {
                 return String.valueOf(hssfRow.getStringCellValue());
         }
    }

    public static void main(String[] args) {
        ExcelTemplateUtils.goodsExcelTemplate();
    }
}
