package cn.dahe.service;

import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.GoodsDtoSimple;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by fy on 2017/1/13.
 */
public interface IGoodsService{
    boolean add(Goods t);
    void del(int id);
    void update(Goods t);
    Goods get(int id);
    Goods load(int id);

    boolean add(GoodsDto goodsDto);

    void update(GoodsDto goodsDto);
    /**
     * 商品列表
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<GoodsDto> goodsList(String aDataSet, int storeId);

    /**
     * 根据类别查询
     * @param categories
     * @return
     */
    List<GoodsDtoSimple> goodsListByCategories(int categories);

    /**
     * 商品排序
     * @param ids
     */
    void goodsSort(String ids);

    /**
     * 商品复制
     * @param storeId
     * @param ids
     */
    void goodsCopy(int storeId, String ids);

    /**
     * 通过excel导入商品
     * @param file
     * @param storeId  门店id
     * @param isCreateNewCategories 是否自动创建不存在的栏目 0 不创建 1 创建
     * @param isCreateNewUnit  是否自动创建不存在的单位 0 不创建 1 创建
     * @return
     */
    Map<String, Object> importGoodsExcel(MultipartFile file, int storeId, int isCreateNewCategories, int isCreateNewUnit);

    /**
     * 转换
     * @param goods
     * @return
     */
    GoodsDto formatGoodsToGoodsDto(Goods goods);

    /**
     * 商品图片上传
     * @param file
     */
    String upload(MultipartFile file);

    /**
     * 查询全部商品
     * @param storeId
     * @return
     */
    List<Goods> findAll(int storeId);
}
