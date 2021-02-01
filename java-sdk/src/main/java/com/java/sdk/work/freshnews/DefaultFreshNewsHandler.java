package com.java.sdk.work.freshnews;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author chenfh
 * @date 2020-10-26 14:21
 */
public class DefaultFreshNewsHandler {

    private IFreshNews freshNews;

    private FreshNewsFactory freshNewsFactory;

    public void setFreshNews(IFreshNews freshNews) {
        this.freshNews = freshNews;
    }

    public FreshNewsModel buildFreshNewsModel() {
        FreshNewsModel freshNewsModel = new FreshNewsModel();
        freshNewsModel.setCode(freshNews.getCode());
        freshNewsModel.setSubCode(freshNews.getSubCode());
        freshNewsModel.setUserId(freshNews.getUserId());
        //freshNewsModel.setContent(freshNews.getContent());
        freshNewsModel.setFansId(freshNews.getFansId());
        freshNewsModel.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        return freshNewsModel;
    }
}
