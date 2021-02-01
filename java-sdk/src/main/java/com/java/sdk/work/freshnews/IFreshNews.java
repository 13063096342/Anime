package com.java.sdk.work.freshnews;

/**
 * @author chenfh
 * @date 2020-10-26 13:47
 */
public interface IFreshNews {
    String getCode();

    String getSubCode();

    String buildContent();

    Long getUserId();

    Long getFansId();

}
