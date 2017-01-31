package cn.dahe.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.ArrayList;

/**
 * excel 表格导入模板
 * Created by fy on 2017/1/30.
 */
public class ExcelTemplateUtils {
    /**
     * 供货商资料模板
     */
    public static HSSFWorkbook supplierExcelTemplate(){
        return PoiUtils.exportExcel("供货商资料模板", new String[]{"供货商编号（必填）","供货商名称（必填）","拼音码",
        "联系人","联系电话","邮箱","状态","配送费返点","固定返利点","地址","备注"}, new String[] {}, new ArrayList<>());
    }

    /**
     * 商品信息模板
     */
    public static HSSFWorkbook goodsExcelTemplate(){
        return PoiUtils.exportExcel("商品导入模板（餐饮）", new String[]{"名称（必填）","分类（必填）","条码",
                "主单位","库存量（必填）","进货价（必填）","销售价（必填）","批发价","会员价","会员折扣","积分商品",
                "库存上限","库存下限","标签打印","供货商","生产日期","保质期","拼音码","商品状态","商品描述"}, new String[] {}, new ArrayList<>());
    }

    /**
     * 进货模板
     */
    public static HSSFWorkbook purchaseExcelTemplate(){
        return PoiUtils.exportExcel("货单模板", new String[]{"商品名称","条码（必填）","货流量（必填）",
                "单位","货流价"}, new String[] {}, new ArrayList<>());
    }
}
