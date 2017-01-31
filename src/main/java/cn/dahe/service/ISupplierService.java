package cn.dahe.service;

import cn.dahe.dto.Pager;
import cn.dahe.model.Supplier;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by fy on 2017/1/23.
 */
public interface ISupplierService {
    void add(Supplier t);
    void del(int id);
    void update(Supplier t);
    Supplier get(int id);
    Supplier load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @param storeId
     * @return
     */
    Pager<Supplier> findByParams(String aDataSet, int storeId);

    /**
     * 通过excel导入供应商
     * @param file
     * @param storeId 门店id
     */
    Map<String, Object> importSupplierExcel(MultipartFile file, int storeId);

    List<Supplier> findAll(int storeId);

    /**
     * 根据名称查询
     * @param name
     * @param storeId
     * @return
     */
    List<Supplier> findByName(String name, int storeId);
}
