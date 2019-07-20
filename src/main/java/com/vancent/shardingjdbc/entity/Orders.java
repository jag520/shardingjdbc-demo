package com.vancent.shardingjdbc.entity;

import java.util.Date;

public class Orders {
    /**
     * 订单id
     */
    private String id;

    /**
     *  业务平台的订单id
     */
    private String parentOrdersUuid;
    /**
     * 业务平台的订单编号
     */
    private String parentOrdersId;
    /**
     * 订单来源
     */
    private String orderOrigin;
    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 创建时间
     */
    private Date adddate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentOrdersUuid() {
        return parentOrdersUuid;
    }

    public void setParentOrdersUuid(String parentOrdersUuid) {
        this.parentOrdersUuid = parentOrdersUuid;
    }

    public String getParentOrdersId() {
        return parentOrdersId;
    }

    public void setParentOrdersId(String parentOrdersId) {
        this.parentOrdersId = parentOrdersId;
    }

    public String getOrderOrigin() {
        return orderOrigin;
    }

    public void setOrderOrigin(String orderOrigin) {
        this.orderOrigin = orderOrigin;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }
}
