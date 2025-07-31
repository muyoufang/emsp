package com.demo.emsp.business.dto;

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
public class OperatorDto {
    @ApiModelProperty(value = "ID标识")
    private Integer id;

    @ApiModelProperty(value = "登录名")
    private String loginName;

//    @ApiModelProperty(value = "密码")
//    private String pswd;

    @ApiModelProperty(value = "运营商编号")
    private String operatorNumber;

    @ApiModelProperty(value = "国家名称")
    private String operatorName;

    @ApiModelProperty(value = "经营业务")
    private String business;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "地址")
    private String address;

}
