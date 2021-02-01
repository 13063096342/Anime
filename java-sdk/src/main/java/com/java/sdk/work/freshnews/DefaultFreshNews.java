package com.java.sdk.work.freshnews;

/**
 * @author chenfh
 * @date 2020-10-26 16:07
 */
public class DefaultFreshNews implements IFreshNews {

    private FreshNewsMqModel freshNewsMqModel;

    DefaultFreshNews(FreshNewsMqModel freshNewsMqModel) {
        this.freshNewsMqModel = freshNewsMqModel;
    }

    @Override
    public String getCode() {
        return freshNewsMqModel.getCode();
    }

    @Override
    public String getSubCode() {
        return freshNewsMqModel.getSubCode();
    }

    @Override
    public String buildContent() {
        return null;
    }

    @Override
    public Long getUserId() {
        return freshNewsMqModel.getUserId();
    }

    @Override
    public Long getFansId() {
        return freshNewsMqModel.getExtend().getFansId();
    }
}
