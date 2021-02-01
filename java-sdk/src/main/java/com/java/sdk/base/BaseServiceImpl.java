package com.java.sdk.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.java.sdk.dao.mapper.BaseMapper;
import com.java.sdk.dao.model.BaseModel;
import com.java.sdk.exception.ExceptionCodeEnums;
import com.java.sdk.exception.ServicesException;
import com.java.sdk.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenfh
 * @date 2020-10-27 15:11
 */
@Slf4j
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel> implements IBaseService<T> {

    @Autowired
    protected M baseMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class, timeout = 60)
    public void delete(Integer id) {
        try {
            if (id == null) {
                return;
            }
            baseMapper.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServicesException(ExceptionCodeEnums.DB_DELETE_ERROR.getCode(), ExceptionCodeEnums.DB_DELETE_ERROR.getDesc());
        }
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public T update(T record) {
        try {
            record.setUpdateTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
            baseMapper.updateById(record);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServicesException(ExceptionCodeEnums.DB_UPDATE_ERROR.getCode(), ExceptionCodeEnums.DB_UPDATE_ERROR.getDesc());
        }
        return record;
    }

    @Override
    public boolean add(T record) {
        try {
            long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
            completeAddRecord(record, currentTime);
            baseMapper.insert(record);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServicesException(ExceptionCodeEnums.DB_INSERT_ERROR.getCode(), ExceptionCodeEnums.DB_INSERT_ERROR.getDesc());
        }
        return true;
    }

    @Override
    public boolean addAll(List<T> records) {
        if (CollectionUtils.isEmpty(records)) {
            return true;
        }
        long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        records.forEach(record -> completeAddRecord(record, currentTime));
        baseMapper.insertAll(records);
        return true;
    }

    @Override
    public boolean addOrUpdateAll(List<T> records, Class<T> tClass) {
        if (CollectionUtils.isEmpty(records)) {
            return true;
        }
        long currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        records.forEach(record -> completeAddRecord(record, currentTime));
        baseMapper.insertOrUpdateAll(records);
        return true;
    }

    @Override
    public T queryById(Integer id) {
        try {
            List<T> list = baseMapper.selectList(new QueryWrapper<T>().eq("id", id));
            return CollectionUtils.isEmpty(list) ? null : list.get(0);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new ServicesException(ExceptionCodeEnums.DB_SELECT_ERROR.getCode(), ExceptionCodeEnums.DB_SELECT_ERROR.getDesc());
        }
    }

    @Override
    public List<T> queryByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return baseMapper.selectList(new QueryWrapper<T>().in("id", ids));
    }

    @Override
    public List<T> queryList(T param) {
        Map<String, Object> map = getNotNullColumnAndValueToMap(param);

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        for (Map.Entry entry : map.entrySet()) {
            queryWrapper.eq(entry.getKey().toString(), entry.getValue());
        }
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<T> queryPageByFieldsOrKeyword(Integer pageNo, Integer pageSize, Boolean asc, String columns, T param) {
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = 10;
        }
        Page<T> tPage = new Page<>(pageNo, pageSize);
        Map<String, Object> map = getNotNullColumnAndValueToMap(param);

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        for (Map.Entry entry : map.entrySet()) {
            queryWrapper.eq(entry.getKey().toString(), entry.getValue());
        }
        if (StringUtils.isEmpty(columns)) {
            columns = "id";
        }
        String orderColumns = camelToUnderline(columns);
        String[] split = orderColumns.split(",");
        if (asc == null || asc) {
            queryWrapper.orderByAsc(split);
        } else {
            queryWrapper.orderByDesc(split);
        }
        return baseMapper.selectPage(tPage, queryWrapper);
    }

    @Override
    public boolean checkIfExist(T record, boolean excludeId) {
        Map<String, Object> map = getNotNullColumnAndValueToMap(record);
        if (CollectionUtils.isEmpty(map)) {
            return true;
        }
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        for (Map.Entry entry : map.entrySet()) {
            queryWrapper.eq(entry.getKey().toString(), entry.getValue());
        }
        if (excludeId) {
            queryWrapper.ne("id", record.getId());
        }
        return baseMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return;
        }
        baseMapper.deleteBatchIds(ids);
    }

    private void completeAddRecord(T record, Long currentDateTime) {
        record.setUpdateTime(currentDateTime);
        if (record.getCreateTime() == null) {
            record.setCreateTime(currentDateTime);
        }
    }

    private Map<String, Object> getNotNullColumnAndValueToMap(T record) {
        if (null == record) {
            return Collections.emptyMap();
        }
        Map<String, Object> map = new HashMap<>();
        try {

            for (Class<?> clazz = record.getClass(); clazz != Object.class && clazz != null; clazz = clazz.getSuperclass()) {
                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field field : declaredFields) {
                    String fieldName = field.getName();
                    if ("id".equals(fieldName)) {
                        continue;
                    }
                    Method method = clazz.getMethod("get" + captureFirstChar(fieldName));
                    Object value = method.invoke(record);
                    if (value != null && !"".equals(value.toString())) {
                        map.put(camelToUnderline(fieldName), value);
                    }
                }
            }
        } catch (Exception e) {
            throw new ServicesException(ExceptionCodeEnums.REFLECT_ERROR);
        }
        return map;
    }

    public String captureFirstChar(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        return new StringBuffer(str).replace(0, 1, String.valueOf(Character.toUpperCase(str.charAt(0)))).toString();
    }

    public String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_');
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
