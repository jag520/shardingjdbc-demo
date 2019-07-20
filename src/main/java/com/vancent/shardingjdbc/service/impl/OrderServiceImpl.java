package com.vancent.shardingjdbc.service.impl;

import com.vancent.shardingjdbc.dao.OrdersMapper;
import com.vancent.shardingjdbc.entity.Orders;
import com.vancent.shardingjdbc.service.OrdersService;
import com.vancent.shardingjdbc.model.MOrderF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: vancent
 * @Date: 2019/7/20 11:35
 */
@Service
public class OrderServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;


    @Override
    public int insertOrders(Orders orders) {
        return this.ordersMapper.insertOrders(orders);
    }

    @Override
    public Orders selectById(String id) {
        return this.ordersMapper.selectById(id);
    }

    @Override
    public List<Orders> queryOrderByParam(MOrderF form) {
        return this.ordersMapper.queryOrderByParam(form);
    }

    @Override
    public List<Orders> queryOrdersPage(String id, int current, int pageSize) {
        return this.ordersMapper.queryOrdersPage(id, current, pageSize);
    }

    @Override
    public List<Orders> queryByIds(List<String> ids) {
        return this.ordersMapper.queryByIds(ids);
    }

    @Override
    public List<Orders> queryBetweenDate(String startTime, String endTime) {
        return ordersMapper.queryBetweenDate(startTime, endTime);
    }
}
