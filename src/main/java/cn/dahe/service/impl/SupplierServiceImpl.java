package cn.dahe.service.impl;

import cn.dahe.dao.ISupplierDao;
import cn.dahe.dto.Pager;
import cn.dahe.model.Supplier;
import cn.dahe.service.ISupplierService;
import cn.dahe.util.PoiUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fy on 2017/1/24.
 */
@Service("supplierService")
public class SupplierServiceImpl implements ISupplierService{
    private static Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);
    @Resource
    private ISupplierDao supplierDao;

    @Override
    public boolean add(Supplier t, int storeId) {
        Supplier supplier = supplierDao.findByNo(t.getSupplierNo(), storeId);
        if(supplier == null){
            logger.info("供应商编码还没有被使用");
            supplier.setStoreId(storeId);
            supplierDao.add(t);
            return true;
        }
        return false;
    }

    @Override
    public void del(int id) {
        supplierDao.delete(id);
    }

    @Override
    public void update(Supplier t) {
        Supplier supplier = get(t.getId());
        supplier.setAddr(t.getAddr());
        supplier.setAuthorize(t.getAuthorize());
        supplier.setContacts(t.getContacts());
        supplier.setDescription(t.getDescription());
        supplier.setEmail(t.getEmail());
        supplier.setName(t.getName());
        supplier.setPackingFeePoint(t.getPackingFeePoint());
        supplier.setPhone(t.getPhone());
        supplier.setPinyin(t.getPinyin());
        supplier.setRebatePoint(t.getRebatePoint());
        supplier.setStatus(t.getStatus());
        supplierDao.update(supplier);
    }

    @Override
    public Supplier get(int id) {
        return supplierDao.get(id);
    }

    @Override
    public Supplier load(int id) {
        return supplierDao.load(id);
    }

    @Override
    public Pager<Supplier> findByParams(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = 1;
        String keywords = "";
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("status")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("supplierInfo")) {
                    keywords = jsonObject.get("value").toString();
                }
            }
            Pager<Object> params = new Pager<>();
            params.setStatus(status);
            params.setOrderColumn("supplier.id");
            params.setOrderDir("desc");
            params.setIntParam1(storeId);
            params.setStringParam1(keywords);
            return supplierDao.findByParam(start, pageSize, params);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, Object> importSupplierExcel(MultipartFile file, int storeId) {
        Supplier supplier;
        Map<String, Object> map = new HashMap<>();
        try{
            InputStream inputStream = file.getInputStream();
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
            for(int numSheet = 0, len = hssfWorkbook.getNumberOfSheets(); numSheet < len; numSheet++){
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                        HSSFCell supplierNo = hssfRow.getCell(0);
                        supplier = supplierDao.findByNo(PoiUtils.getValue(supplierNo), storeId);
                        if(supplier == null){
                            supplier = new Supplier();
                            HSSFCell name = hssfRow.getCell(1);
                            HSSFCell pinyin = hssfRow.getCell(2);
                            HSSFCell contacts = hssfRow.getCell(3);
                            HSSFCell phone = hssfRow.getCell(4);
                            HSSFCell email = hssfRow.getCell(5);
                            HSSFCell status = hssfRow.getCell(6);
                            HSSFCell packingFeePoint = hssfRow.getCell(7);
                            HSSFCell rebatePoint = hssfRow.getCell(8);
                            HSSFCell addr = hssfRow.getCell(9);
                            HSSFCell description = hssfRow.getCell(10);
                            supplier.setAddr(PoiUtils.getValue(addr));
                            supplier.setEmail(PoiUtils.getValue(email));
                            supplier.setContacts(PoiUtils.getValue(contacts));
                            supplier.setDescription(PoiUtils.getValue(description));
                            supplier.setName(PoiUtils.getValue(name));
                            supplier.setPhone(PoiUtils.getValue(phone));
                            supplier.setPinyin(PoiUtils.getValue(pinyin));
                            supplier.setPackingFeePoint(PoiUtils.getValue(packingFeePoint));
                            supplier.setRebatePoint(PoiUtils.getValue(rebatePoint));
                            supplier.setStatus(PoiUtils.getValue(status).equals("启用") ? 1 : 0);
                            supplierDao.add(supplier);
                        }
                    }
                }
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Supplier> findAll(int storeId) {
        return supplierDao.findAll(storeId);
    }

    @Override
    public List<Supplier> findByName(String name) {
        return supplierDao.findByName(name);
    }
}
