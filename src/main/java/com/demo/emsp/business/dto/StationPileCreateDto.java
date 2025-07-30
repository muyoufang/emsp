package com.demo.emsp.business.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
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
public class StationPileCreateDto {
    @NotBlank(message = "充电桩编号不能为空")
    @ApiModelProperty(name = "充电桩编号")
    private String pileNumber;
    @NotBlank(message = "充电桩名称不能为空")
    @ApiModelProperty(name = "充电桩名称")
    private String pileName;
    @NotBlank(message = "设备类型不能为空")
    @ApiModelProperty(name = "设备类型(直流、交流、交直一体、其它)")
    private String pileType;
    //有线 无线
    @ApiModelProperty(name = "网络")
    //@NotBlank(message = "网络不能为空")
    private String network;
    @NotNull(message = "场站ID不能为空")
    @ApiModelProperty(name = "场站ID")
    private Integer stationId;
}
