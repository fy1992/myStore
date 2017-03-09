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
    boolean add(Supplier t, int storeId);
    void del(int id);
    void update(Supplier t);
    Supplier get(int id);
    Supplier load(int id);

    /**
     * 根据参数查询
     * @param aDataSet
     * @return
     */
    Pager<Supplier> findByParams(String aDataSet);

    /**
     * 通过excel导入供应商
     * @param file
     */
    Map<String, Object> importSupplierExcel(MultipartFile file, int storeId);

    List<Supplier> findAll(int storeId);

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    List<Supplier> findByName(String name);
}
