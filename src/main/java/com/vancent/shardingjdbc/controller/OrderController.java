package com.vancent.shardingjdbc.controller;


import com.vancent.shardingjdbc.entity.Orders;
import com.vancent.shardingjdbc.service.OrdersService;
import com.vancent.shardingjdbc.util.SnowFlake;
import com.vancent.shardingjdbc.util.UUIDutil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.MOrderF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: vancent
 * @Date: 2019/7/18 22:00
 */
@Api(value = "订单测试", tags = {"订单测试"})
@RestController
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    private SnowFlake snowFlake = new SnowFlake(0, 0);

    @ApiOperation(value = "订单插入")
    @GetMapping("/saveOrderTest")
    public String saveOrderTest(@RequestParam(name = "num", defaultValue = "20") int num){
        if (num < 0) {
            num = 1;
        }
        for (int i=0; i < num; i++) {
            Orders orders = new Orders();
            orders.setId(String.valueOf(snowFlake.nextId()));
            orders.setAdddate(new Date());
            orders.setParentOrdersId(UUIDutil.getUUID());

            orders.setOrderType("0");
            orders.setOrderOrigin("1");
            orders.setParentOrdersUuid(UUIDutil.getUUID());

            ordersService.insertOrders(orders);
        }

        return "success";
    }

    @ApiOperation(value = "订单查询-根据Id查询")
    @GetMapping("/queryOrderByIdTest")
    public ResponseEntity queryOrderByIdTest(@RequestParam(name = "id") String id){
        return ResponseEntity.ok(ordersService.selectById(id));
    }

    @ApiOperation(value = "订单查询-根据请求参数查询")
    @GetMapping("/queryOrderByParamTest")
    public ResponseEntity queryOrderByParamTest(@ModelAttribute MOrderF form) {
        return ResponseEntity.ok(ordersService.queryOrderByParam(form));
    }

    @ApiOperation(value = "订单查询-分页")
    @GetMapping("/queryOrdersPageTest")
    public ResponseEntity queryOrdersPageTest(@RequestParam(name = "id") String id,
                                          @RequestParam(name = "current", defaultValue = "1") int current,
                                          @RequestParam(name = "pageSize", defaultValue = "2") int pageSize) {
        return ResponseEntity.ok(ordersService.queryOrdersPage(id, current, pageSize));
    }

    @ApiOperation(value = "订单查询-根据List<Id>")
    @GetMapping("/queryByIdsTest")
    public ResponseEntity queryByIdsTest(@RequestParam(value = "ids") List<String> ids) {
        return ResponseEntity.ok(ordersService.queryByIds(ids));
    }

    @ApiOperation(value = "订单查询-根据时间区间")
    @GetMapping("/queryBetweenDateTest")
    public ResponseEntity queryBetweenDateTest(String startTime,String endTime) {
        return ResponseEntity.ok(ordersService.queryBetweenDate(startTime, endTime));
    }
}
