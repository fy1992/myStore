package cn.dahe.service;

import cn.dahe.dto.GoodsDto;
import cn.dahe.dto.Pager;
import cn.dahe.model.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 商品
 * Created by fy on 2017/1/13.
 */
public interface IGoodsService{
    boolean add(Goods t);
    void del(int id);
    void update(Goods t);
    Goods get(int id);
    Goods load(int id);

    boolean add(GoodsDto goodsDto, int storeId);

    void update(GoodsDto goodsDto, int storeId);
    /**
     * 商品列表
     * @param aDataSet dataTables 参数
     * @param storeId 门店id
     */
    Pager<GoodsDto> goodsList(String aDataSet, int storeId);

    /**
     * 商品排序
     * @param ids 商品ids
     */
    void goodsSort(String ids);

    /**
     * 商品复制
     * @param storeId 门店id
     * @param ids 商品ids
     */
    void goodsCopy(int storeId, String ids);

    /**
     * 通过excel导入商品
     * @param file 文件
     * @param storeId  门店id
     * @param isCreateNewCategories 是否自动创建不存在的栏目 0 不创建 1 创建
     * @param isCreateNewUnit  是否自动创建不存在的单位 0 不创建 1 创建
     */
    Map<String, Object> importGoodsExcel(MultipartFile file, int storeId, int isCreateNewCategories, int isCreateNewUnit);

    /**
     * 转换
     * @param goods 商品
     */
    GoodsDto formatGoodsToGoodsDto(Goods goods);

    /**
     * 商品图片上传
     * @param file 图片
     */
    String upload(MultipartFile file);

    /**
     * 查询全部商品
     * @param storeId 门店id
     */
    List<Goods> findAll(int storeId);

    /**
     * 查询半成品列表
     * @param storeId 门店id
     */
    List<Object> findIntermediaryGoods(int storeId);

    /**
     * 半成品制作
     * @param goodsNo 商品编码
     * @param storeId 门店id
     * @param num 制作数量
     */
    void updateGoodsIntermediary(String goodsNo, int num, int storeId);

    /**
     * 根据参数查询
     * @param params 参数
     */
    List<Goods> findByParams(Pager<Object> params);
}
