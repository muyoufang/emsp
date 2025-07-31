package com.demo.emsp.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.demo.emsp.code.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("charging_operator")
public class ChargingOperator extends BaseEntity {

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String pswd;

    @ApiModelProperty(value = "运营商编号")
    private String operatorNumber;

    @ApiModelProperty(value = "运营商名称")
    private String operatorName;

    @ApiModelProperty(value = "经营业务")
    private String business;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String address;
}