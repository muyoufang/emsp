package com.demo.emsp.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class PileGunStatusDto {
    @NotNull(message = "ID标识不能为空")
    @ApiModelProperty(value = "ID标识")
    private Integer id;

    @ApiModelProperty(name = "状态码")
    @NotEmpty(message = "状态码不能为空")
    private String targetStatus;
}
