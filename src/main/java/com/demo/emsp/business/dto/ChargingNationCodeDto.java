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
public class ChargingNationCodeDto {
    @ApiModelProperty(value = "ID标识")
    private Integer id;

    @ApiModelProperty(value = "国家名称")
    private String nationName;

    @ApiModelProperty(value = "国家编码")
    private String nationCode;
}
