package cn.dahe.service.impl;

import cn.dahe.dao.ICategoriesDao;
import cn.dahe.dao.IGoodsDao;
import cn.dahe.dao.IGoodsUnitDao;
import cn.dahe.dao.IStockDao;
import cn.dahe.dao.IStoreDao;
import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.GoodsDtoSimple;
import cn.dahe.dto.Pager;
import cn.dahe.model.BaseException;
import cn.dahe.model.Categories;
import cn.dahe.model.Goods;
import cn.dahe.model.GoodsUnit;
import cn.dahe.model.Stock;
import cn.dahe.service.IGoodsService;
import cn.dahe.util.DateUtil;
import cn.dahe.util.PoiUtils;
import cn.dahe.util.ResourcesUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fy on 2017/1/13.
 */
@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService{
    private static Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Resource
    private IGoodsDao goodsDao;
    @Resource
    private IStoreDao storeDao;
    @Resource
    private ICategoriesDao categoriesDao;
    @Resource
    private IGoodsUnitDao goodsUnitDao;
    @Resource
    private IStockDao stockDao;

    @Override
    public void add(Goods t) {
        goodsDao.add(t);
    }

    @Override
    public void del(int id) {
        goodsDao.delete(id);
    }

    @Override
    public void update(Goods t) {
        goodsDao.update(t);
    }

    @Override
    public Goods get(int id) {
        return goodsDao.get(id);
    }

    @Override
    public Goods load(int id) {
        return goodsDao.load(id);
    }

    @Override
    public Pager<GoodsDto> goodsList(String aDataSet, int storeId) {
        int start = 0;// 起始
        int pageSize = 20;// size
        int status = 1, categories = -1, supplier = -1, tags = -1;
        String goodsInfo = "";
        try{
            JSONArray json = JSONArray.parseArray(aDataSet);
            int len = json.size();
            for (int i = 0; i < len; i++) {
                JSONObject jsonObject = (JSONObject) json.get(i);
                if (jsonObject.get("name").equals("iDisplayStart")) {
                    start = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("iDisplayLength")) {
                    pageSize = (Integer) jsonObject.get("value");
                } else if (jsonObject.get("name").equals("goodsInfo")) {
                    goodsInfo = jsonObject.get("value").toString();
                } else if (jsonObject.get("name").equals("categories")) {
                    categories = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("supplier")) {
                    supplier = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("tags")) {
                    tags = Integer.parseInt(jsonObject.get("value").toString());
                } else if (jsonObject.get("name").equals("status")) {
                    status = Integer.parseInt(jsonObject.get("value").toString());
                }
            }
            Pager<Object> params = new Pager<>();
            params.setStatus(status);
            params.setOrderColumn("goods.id");
            params.setOrderDir("desc");
            params.setIntParam1(categories);
            params.setIntParam2(supplier);
            params.setIntParam3(tags);
            params.setIntParam4(storeId);
            params.setStringParam1(goodsInfo);
            Pager<Goods> goods_pager = goodsDao.findByParam(start, pageSize, params);
            Pager<GoodsDto> goods_dto_pager = new Pager<>();
            goods_dto_pager.setiTotalDisplayRecords(goods_pager.getiTotalDisplayRecords());
            goods_dto_pager.setiTotalRecords(goods_pager.getiTotalRecords());
            List<Goods> goodsList = goods_pager.getAaData();
            List<GoodsDto> goodsDtoList = new ArrayList<>(goodsList.size());
            for(Goods goods : goodsList){
                goodsDtoList.add(new GoodsDto(goods));
            }
            goods_dto_pager.setAaData(goodsDtoList);
            return goods_dto_pager;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GoodsDtoSimple> goodsListByCategories(int categories) {
        Pager<Object> params = new Pager<>();
        params.setIntParam2(categories);
        List<Goods> goodsList = goodsDao.findByParam(params);
        List<GoodsDtoSimple> goodsDtoSimpleList = new ArrayList<>();
        for(Goods goods : goodsList){
            goodsDtoSimpleList.add(new GoodsDtoSimple(goods));
        }
        return goodsDtoSimpleList;
    }

    @Override
    public void goodsSort(String ids) {
        String[] idsArr = ids.split(",");
        for(int i = 0, len = idsArr.length; i < len; i++){
            Goods goods = get(Integer.parseInt(idsArr[i]));
            goods.setSeq(i);
            update(goods);
        }
    }

    @Override
    public void goodsCopy(int storeId, String ids) {
        String[] idsArr = ids.split(",");
        for(int i = 0, len = idsArr.length; i < len; i++){
            Goods copyGoods = new Goods();
            Goods goods = get(Integer.parseInt(idsArr[i]));
            try {
                BeanUtils.copyProperties(copyGoods, goods);
                copyGoods.setStoreId(storeId);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            add(copyGoods);
        }
    }

    @Override
    public Map<String, Object> importGoodsExcel(MultipartFile file, int storeId, int isCreateNewCategories, int isCreateNewUnit) {
        String filePath = ResourcesUtils.getFilePath();
        Map<String, Object> map = new HashMap<>();
        Goods goods = null;
        filePath = filePath + "/商品导入模板(餐饮).xls";
        try{
            InputStream inputStream = new FileInputStream(filePath);
            //InputStream inputStream = file.getInputStream();
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
            for(int numSheet = 0, len = hssfWorkbook.getNumberOfSheets(); numSheet < len; numSheet++){
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                        goods = new Goods();
                        HSSFCell goodsNo = hssfRow.getCell(2);
                        Goods g = goodsDao.findByGoodsNo(PoiUtils.getValue(goodsNo));
                        if(g == null){
                            HSSFCell name = hssfRow.getCell(0);
                            HSSFCell categoriesName = hssfRow.getCell(1);
                            HSSFCell mainUnit = hssfRow.getCell(3);
                            HSSFCell stock = hssfRow.getCell(4);
                            HSSFCell bid = hssfRow.getCell(5);
                            HSSFCell price = hssfRow.getCell(6);
                            HSSFCell tradePrice = hssfRow.getCell(7);
                            HSSFCell vipPrice = hssfRow.getCell(8);
                            HSSFCell isVipSet = hssfRow.getCell(9);
                            HSSFCell isScore = hssfRow.getCell(10);
                            HSSFCell stockUp = hssfRow.getCell(11);
                            HSSFCell stockDown = hssfRow.getCell(12);
                            HSSFCell isPrint = hssfRow.getCell(13);
                            HSSFCell supplier = hssfRow.getCell(14);
                            HSSFCell productionDate = hssfRow.getCell(15);
                            HSSFCell shelfLife = hssfRow.getCell(16);
                            HSSFCell pinyin = hssfRow.getCell(17);
                            HSSFCell status = hssfRow.getCell(18);
                            HSSFCell description = hssfRow.getCell(19);
                            String c_name = PoiUtils.getValue(categoriesName);
                            Categories c = categoriesDao.findByName(c_name, storeId);
                            if(c == null && isCreateNewCategories == 1){
                                c.setName(c_name);
                                c.setStoreId(storeId);
                                categoriesDao.add(c);
                            }else{
                                throw new BaseException("导入商品失败， 以下商品分类缺失： " + c_name);
                            }
                            String u_name = PoiUtils.getValue(mainUnit);
                            GoodsUnit goodsUnit = goodsUnitDao.findByName(u_name, storeId);
                            if(goodsUnit == null && isCreateNewUnit == 1){
                                goodsUnit.setName(u_name);
                                goodsUnit.setStoreId(storeId);
                                goodsUnitDao.add(goodsUnit);
                            }else{
                                throw new BaseException("导入商品失败， 以下商品单位缺失： " + u_name);
                            }
                            goods.setPinyin(PoiUtils.getValue(pinyin));
                            goods.setName(PoiUtils.getValue(name));
                            goods.setStatus(Integer.valueOf(PoiUtils.getValue(status)));
                            goods.setBid(Integer.valueOf(PoiUtils.getValue(bid)));
                            goods.setDescription(PoiUtils.getValue(description));
                            goods.setShelfLife(Integer.valueOf(PoiUtils.getValue(shelfLife)));
                            goods.setPrice(Integer.parseInt(PoiUtils.getValue(price)));
                            goods.setIsPrint(Integer.parseInt(PoiUtils.getValue(isPrint)));
                            goods.setIsScore(Integer.parseInt(PoiUtils.getValue(isScore)));
                            goods.setIsVipSet(Integer.parseInt(PoiUtils.getValue(isVipSet)));
                            goods.setStockDown(Integer.parseInt(PoiUtils.getValue(stockDown)));
                            goods.setStockUp(Integer.parseInt(PoiUtils.getValue(stockUp)));
                            goods.setVipPrice(Integer.parseInt(PoiUtils.getValue(vipPrice)));
                            goods.setTradePrice(Integer.parseInt(PoiUtils.getValue(tradePrice)));
                            goods.setProductionDate(DateUtil.format(PoiUtils.getValue(productionDate), "yyyy-MM-dd HH:mm:ss"));
                            /*Stock s  = new Stock();
                            s.setStoreId(storeId);
                            s.setGoodNum(Integer.parseInt(PoiUtils.getValue(stock)));
                            goods.setStock(s);*/
                            //goods.setSupplier(s);

                            goodsDao.add(goods);
                        }
                    }
                }
                map.put("success", true);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("error", e.getMessage());
        }
        return map;
    }
}
