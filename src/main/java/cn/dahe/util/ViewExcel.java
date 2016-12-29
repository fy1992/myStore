package cn.dahe.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ViewExcel extends AbstractExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		/*String excelName="report.xml";
		// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename="+excelName);
		
		List<Article> arts = (List<Article>) model.get("list");
		//产生Excel表头
		HSSFSheet sheet = workbook.createSheet("articleList");
		HSSFRow header = sheet.createRow(0);//第0行
		
		// 产生标题列
		header.createCell(0).setCellValue("编号");
		header.createCell(1).setCellValue("标题");
		header.createCell(2).setCellValue("");*/
		
	}

}
