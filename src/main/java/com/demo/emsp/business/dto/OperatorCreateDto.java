package com.demo.emsp.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class OperatorCreateDto {

    @NotBlank(message = "登录名不能为空")
    @ApiModelProperty(value = "登录名")
    private String loginName;

//    @ApiModelProperty(value = "密码")
//    private String pswd;

    @NotBlank(message = "运营商编号不能为空")
    @ApiModelProperty(value = "运营商编号")
    private String operatorNumber;

    @NotBlank(message = "运营商名称不能为空")
    @ApiModelProperty(value = "运营商名称")
    private String operatorName;

    @ApiModelProperty(value = "经营业务")
    private String business;

    @Pattern(regexp = "^1\\d{10}$", message = "手机号格式不正确")
    @NotBlank(message = "手机联系方式不能为空")
    @ApiModelProperty(value = "手机联系方式")
    private String phone;

    @NotBlank(message = "地址不能为空")
    @ApiModelProperty(value = "地址")
    private String address;
/*
    @ApiModelProperty(value = "电费")
    private String powerRate;

    @ApiModelProperty(value = "服务费")
    private String serviceFee;

    @ApiModelProperty(value = "支付方式")
    private String payType;
    */
}
