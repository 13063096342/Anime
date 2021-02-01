package com.java.sdk.dao.mapper;

import com.java.sdk.dao.SqlTemplate;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 扩展通用方法
 *
 * @author chenfh
 * @date 2020-10-30 10:31
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {

    /**
     * 批量增加
     * @param list
     * @return
     */
    @SelectProvider(type = SqlTemplate.class, method = "insertAll")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    Integer insertAll(@Param("list") List<T> list);

    /**
     * 批量增加
     * @param list
     * @return
     */
    @SelectProvider(type = SqlTemplate.class, method = "insertOrUpdateAll")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    Integer insertOrUpdateAll(@Param("list") List<T> list);
}
