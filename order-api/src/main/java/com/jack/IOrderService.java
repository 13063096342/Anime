package com.jack;

/**
 * @author chenfh
 * @date 2021-08-12 09:29
 */
public interface IOrderService {
    String queryOrderList();

    String orderById(String id);
}
