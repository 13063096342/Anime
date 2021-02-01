package com.java.sdk.work.freshnews;

/**
 * @author chenfh
 * @date 2020-10-26 13:51
 */
public abstract class AbstractFriendNews extends DefaultFreshNews{

    AbstractFriendNews(FreshNewsMqModel freshNewsMqModel) {
        super(freshNewsMqModel);
    }

    @Override
    public String getCode() {
        return FreshNewsType.FRIEND_NEWS.getCode();
    }
}
