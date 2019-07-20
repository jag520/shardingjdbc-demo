package model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description:
 * @Author: vancent
 * @Date: 2019/7/20 12:03
 */
@ApiModel("订单查询-MOrderF")
public class MOrderF {

    @ApiModelProperty("订单id")
    private String id;

    @ApiModelProperty("业务平台的订单uuid")
    private String parentOrdersUuid;

    @ApiModelProperty("业务平台的订单id")
    private String parentOrdersId;

    @ApiModelProperty("订单来源")
    private String orderOrigin;

    @ApiModelProperty("订单类型")
    private String orderType;

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
}
