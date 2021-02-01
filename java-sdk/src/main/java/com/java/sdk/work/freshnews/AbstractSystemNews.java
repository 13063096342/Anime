package com.java.sdk.work.freshnews;

/**
 * @author chenfh
 * @date 2020-10-26 13:51
 */
public abstract class AbstractSystemNews extends DefaultFreshNews{

    AbstractSystemNews(FreshNewsMqModel freshNewsMqModel) {
        super(freshNewsMqModel);
    }

    @Override
    public String getCode() {
        return FreshNewsType.SYSTEM_NEWS.getCode();
    }
}
