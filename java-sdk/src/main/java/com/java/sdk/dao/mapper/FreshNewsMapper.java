package com.java.sdk.dao.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.sdk.dao.model.FreshNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenfh
 * @date 2020-10-27 09:54
 */
@Mapper
public interface FreshNewsMapper extends BaseMapper<FreshNews> {

    /**
     * 查询数据列表
     *
     * @param request
     * @return
     */
    List<FreshNews> queryList(@Param("cm") FreshNews request);

    /**
     * 查询数据分页列表
     *
     * @param request
     * @param page
     * @return
     */
    Page<FreshNews> queryList(Page<FreshNews> page, @Param("cm") FreshNews request);
}