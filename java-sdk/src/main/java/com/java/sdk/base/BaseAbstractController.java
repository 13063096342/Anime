package com.java.sdk.base;

import com.java.sdk.dao.model.BaseModel;
import com.java.sdk.model.Response;
import com.java.sdk.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author chenfh
 * @date 2020-02-25 10:09
 */
public class BaseAbstractController<T extends IBaseService> {
    @Autowired
    protected T baseService;

    public Response insert(BaseModel record) {
        baseService.add(record);
        return ResponseUtil.ok();
    }

    public Response deleteByPrimaryKey(Integer id) {
        baseService.delete(id);
        return ResponseUtil.ok();
    }

    public Response selectByPrimaryKey(Integer id) {
        return ResponseUtil.ok(baseService.queryById(id));
    }

    public Response updateByPrimaryKey(BaseModel record) {
        baseService.update(record);
        return ResponseUtil.ok();
    }

}
