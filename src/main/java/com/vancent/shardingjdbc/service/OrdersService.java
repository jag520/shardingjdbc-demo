package com.vancent.shardingjdbc.service;

import com.vancent.shardingjdbc.entity.Orders;
import com.vancent.shardingjdbc.model.MOrderF;

import java.util.List;

public interface OrdersService {

    int insertOrders(Orders orders);

    Orders selectById(String id);

    List<Orders> queryOrderByParam(MOrderF form);

    /**
     * 分页查询数据
     * @param id
     * @param current
     * @param pageSize
     * @return
     */
    List<Orders> queryOrdersPage(String id,int current,int pageSize);

    List<Orders> queryByIds(List<String> ids);

    List<Orders> queryBetweenDate(String startTime,String endTime);
}
