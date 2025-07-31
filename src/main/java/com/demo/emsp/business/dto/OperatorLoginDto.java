package com.demo.emsp.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

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
public class OperatorLoginDto {
    @NotEmpty(message = "登录名不能为空")
    @ApiModelProperty(value = "登录名")
    private String loginName;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String pswd;
}
