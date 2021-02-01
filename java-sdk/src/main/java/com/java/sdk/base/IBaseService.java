package com.java.sdk.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * @author chenfh
 * @date 2020-10-27 15:08
 */
public interface IBaseService<T> {

    /**
     * 删除数据
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新数据
     *
     * @param record
     * @return
     */
    T update(T record);

    /**
     * 增加数据
     *
     * @param record
     * @return
     */
    boolean add(T record);

    /**
     * 批量添加元素
     *
     * @param records
     * @return
     */
    boolean addAll(List<T> records);

    /**
     * 批量添加或者更新元素
     *
     * @param records
     * @param tClass
     * @return
     */
    boolean addOrUpdateAll(List<T> records, Class<T> tClass);

    /**
     * 单条数据
     *
     * @param id
     * @return
     */
    T queryById(Integer id);

    /**
     * 根据Id列表获取数据
     *
     * @param ids
     * @return
     */
    List<T> queryByIds(List<Integer> ids);

    /**
     * 列表筛选
     *
     * @param param
     * @return
     */
    List<T> queryList(T param);

    /**
     * 翻页搜索
     * 根据实体对象字段或者关键字查询带分页列表
     * @param pageNo
     * @param pageSize
     * @param asc 升降序  默认升序
     * @param columns order by 字段，多个以","分割
     * @param param
     *
     */
    IPage<T> queryPageByFieldsOrKeyword(Integer pageNo, Integer pageSize, Boolean asc, String columns, T param);

    /**
     * 判断记录是否存在
     *
     * @param record
     * @param excludeId
     * @return
     */
    boolean checkIfExist(T record, boolean excludeId);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);
}
