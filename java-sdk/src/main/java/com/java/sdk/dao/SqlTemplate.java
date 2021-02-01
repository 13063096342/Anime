package com.java.sdk.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.google.common.base.Joiner;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.java.sdk.dao.model.BaseModel;
import com.java.sdk.util.ClassUtils;
import com.java.sdk.util.CollectionUtil;
import com.java.sdk.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 语句模板类
 *
 * @author Chenfh
 */
@Slf4j
public class SqlTemplate<T extends BaseModel> {

    private final static String LIST_VALUE_PATTERN = "#{%s.%s}";

    private static List<String> DEFAULT_IGNORE_UPDATE_FIELDS = Arrays.asList("id", "createTime");

    /**
     * 批量插入语句
     */
    public String insertAll(Map<String, Object> params) {
        List<T> list = (List<T>) params.get("list");
        if (CollectionUtil.isEmpty(list)) {
            throw new IllegalArgumentException("list can't be empty");
        }
        T entity = list.get(0);

        BiMap<String, String> attrMap = getCodeToDbColMap(entity);

        List<String> valuesSql = getListValueSql(list, attrMap);

        String sql = String.format("insert into %s(%s) values%s", tableName(entity),
                Joiner.on(",").join(attrMap.values()), Joiner.on(",").join(valuesSql));
        log.info("sql:{}", sql);
        return sql;
    }

    public String insertOrUpdateAll(Map<String, Object> params) {
        List<T> list = (List<T>) params.get("list");
        if (CollectionUtil.isEmpty(list)) {
            throw new IllegalArgumentException("list can't be empty");
        }
        T entity = list.get(0);

        BiMap<String, String> insertMap = getCodeToDbColMap(entity);

        BiMap<String, String> updateMap = HashBiMap.create(insertMap);
        for (String field : DEFAULT_IGNORE_UPDATE_FIELDS) {
            updateMap.remove(field);
        }

        List<String> valuesSql = getListValueSql(list, insertMap);

        List<String> values = CollectionUtil.selectList(insertMap.values(), this::completeColName);
        String sql = String.format("insert into %s(%s) values%s ON DUPLICATE KEY UPDATE %s",
                tableName(entity),
                Joiner.on(",").join(values),
                Joiner.on(",").join(valuesSql),
                StringUtil.join(CollectionUtil.map(updateMap.values(), key -> key + "= VALUES(" + key + ")"), ","));
        log.info("sql:{}", sql);
        return sql;
    }

    private List<String> getListValueSql(List<T> list, BiMap<String, String> insertMap) {
        List<String> valuesSql = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            final String listIndexStr = String.format("list[%s]", i);
            List<String> attributes = CollectionUtil.selectList(insertMap.keySet(), from ->
                    String.format(LIST_VALUE_PATTERN, listIndexStr, from));
            valuesSql.add(String.format("(%s)", Joiner.on(",").join(attributes)));
        }
        return valuesSql;
    }

    private BiMap<String, String> getCodeToDbColMap(T obj) {
        BiMap<String, String> codeToDbColMap = HashBiMap.create();
        Class<?> aClass = obj.getClass();

        List<Field> fields = ClassUtils.getAllFields(aClass);
        // 循环添加属性
        for (Field field : fields) {
            String fieldName = field.getName();
            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField == null) {
                codeToDbColMap.put(fieldName, changeAttrToDatabase(fieldName));
            } else if (tableField.exist()) {
                codeToDbColMap.put(fieldName, tableField.value());
            }
        }
        return codeToDbColMap;
    }


    private String tableName(T model) {
        return tableName((Class<T>) model.getClass());
    }

    private String tableName(Class<T> tClass) {
        String tableName = tClass.getSimpleName();
        return changeAttrToDatabase(tableName);
    }

    private String changeAttrToDatabase(String attr) {
        StringBuilder newAttr = new StringBuilder();
        boolean isFirstChar = true;
        for (char c : attr.toCharArray()) {
            newAttr.append(Character.isUpperCase(c) ?
                    (isFirstChar ? Character.toLowerCase(c) : "_" + Character.toLowerCase(c)) : c);
            isFirstChar = false;
        }
        return newAttr.toString();
    }

    private String completeColName(String colName) {
        return "`" + colName + "`";
    }
}
