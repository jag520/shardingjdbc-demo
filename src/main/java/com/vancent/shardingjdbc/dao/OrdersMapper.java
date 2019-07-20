package com.vancent.shardingjdbc.dao;

import com.vancent.shardingjdbc.entity.Orders;
import com.vancent.shardingjdbc.model.MOrderF;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersMapper {

    int insertOrders(Orders orders);

    Orders selectById(String id);

    List<Orders> queryOrderByParam(MOrderF form);

    List<Orders> queryOrdersPage(@Param("id") String id, @Param("current") int current, @Param("pageSize") int pageSize);

    List<Orders> queryByIds(@Param("ids") List<String> ids);

    List<Orders> queryBetweenDate(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
