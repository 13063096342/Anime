package com.java.sdk.enums;

/**
 * @author chenfh
 * @date 2020-09-02 17:25
 */
public enum ProductExceptionCode {
    OK("00000", "成功"),
    SYSTEM_ERROR("10001", "服务内部异常"),
    PARAM_ERROR("10010", "参数通用错误"),
    INVALID_REQUEST_FORMAT("11500", "请求参数格式错误"),
    SIGN_ERROR("11008", "签名错误"),
    SIGN_NOT_FIND("11008", "未发现签名信息"),
    BUSINESS_COED_KEY_EXITS("22501", "该业务code与业务key组合已存在"),
    BUSINESS_COED_KEY_NOT_EXITS("22502", "该业务code与业务key组合不存在"),
    BUSINESS_COED_NOT_EXITS("22503", "该业务code不存在"),
    BUSINESS_ITEM_EXIT("22504", "不能重复绑定商品"),
    ITEM_SKU_NOT_EXIT("22505", "商品不存在"),
    PRODUCT_VALIDATOR_ERROR("22506", "商品详细请求参数验证错误"),
    PRODUCT_DETAIL_NOT_EXISTS_ERROR("22507", "商品不存在"),
    THRID_API_ERROR("22508", "第三方接口错误"),
    PRODUCT_DETAIL_UNAUTHORIZED("22509", "没有查看该商品的权限"),
    PRODUCT_SOURCE_ERROR("22510", "渠道不存在"),
    DATA_NOT_EXIT("22511", "数据不存在"),
    BATCH_LIMIT_NUM("22512", "批量操作数据每次限制1000条"),
    PRODUCT_AREA_ITEM_EXIST("22513", "商品不能重复设置相同地区"),
    PRODUCT_AREA_NOT_EXIST("22514", "地区不存在"),
    IMPORT_FILE_FORMAT_ERROR("22515", "导入文件格式异常"),
    PRODUCT_RECOMMEND_VALIDATOR_ERROR("22516", "商品推荐请求参数验证错误"),
    UPLOAD_FILE_NO_EXIST("22517", "上传文件不存在"),
    UPLOAD_FILE_CONTENT_TYPE_ERROR("22519", "文件格式错误"),
    DOWNLOAD_FILE_ERROR("22520", "下载文件异常"),
    CAR_CONFIG_NO_EXIST("22521", "汽车配置不存在"),
    ITEM_UPDATE_ERROR("22522", "更新商品信息失败");

    private String code;
    private String desc;

    private ProductExceptionCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}

