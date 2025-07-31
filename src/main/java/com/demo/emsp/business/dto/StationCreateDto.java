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
public class StationCreateDto {

    @NotBlank(message = "充电站编码不能为空")
    @ApiModelProperty(name = "充电站编码")
    private String stationCode;
    @NotBlank(message = "充电站名称不能为空")
    @ApiModelProperty(name = "充电站名称")
    private String stationName;

    @NotBlank(message = "省份不能为空")
    @ApiModelProperty(name = "省份")
    private String province;
    @NotBlank(message = "城市不能为空")
    @ApiModelProperty(name = "城市")
    private String city;
    @NotBlank(message = "详细地址不能为空")
    @ApiModelProperty(name = "详细地址")
    private String address;
    @NotBlank(message = "经度不能为空")
    //@Pattern(regexp = "^[1-9]+\\.{0,1}[0-9]{0,2}$", message = "经度必须是数字")
    @ApiModelProperty(name = "经度")
    //@Pattern(regexp = "^[1-9]+\\.{0,1}[0-9]{0,2}$", message = "纬度必须是数字")
    private String stationLng;
    @NotBlank(message = "纬度不能为空")
    @ApiModelProperty(name = "纬度")
    private String stationLat;

    @NotNull(message = "场站状态不能为空")
    @ApiModelProperty(name = "场站状态")
    private Integer stationStatus;

    @NotNull(message = "登录名不能为空")
    @ApiModelProperty(name = "运营商ID")
    private Integer operatorId;
    @NotNull(message = "停车费不能为空")
    //@Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "停车费必须是数字")
    @ApiModelProperty(name = "停车费")
    private Integer parkFee;
    @NotBlank(message = "营业开始时间不能为空")
    @ApiModelProperty(value = "营业开始时间")
    private String startTime;
    @NotBlank(message = "营业结束时间不能为空")
    @ApiModelProperty(value = "营业结束时间")
    private String endTime;
}
