package com.demo.emsp.business.vo;

import com.demo.emsp.business.dto.StationPileDto;
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
public class PileVo extends StationPileDto {
    @ApiModelProperty(value = "ID标识")
    private Integer stationId;
    @ApiModelProperty(name = "充电站编码")
    private String stationCode;
    @ApiModelProperty(name = "充电站名称")
    private String stationName;

    @ApiModelProperty(name = "省份")
    private String province;
    @ApiModelProperty(name = "城市")
    private String city;
    @ApiModelProperty(name = "详细地址")
    private String address;
    @ApiModelProperty(name = "经度")
    private String stationLng;
    @ApiModelProperty(name = "纬度")
    private String stationLat;

    @ApiModelProperty(name = "场站状态")
    private Integer stationStatus;

    @ApiModelProperty(name = "运营商ID")
    private Integer operatorId;
    @ApiModelProperty(name = "停车费")
    private Integer parkFee;
    @ApiModelProperty(value = "营业开始时间")
    private String startTime;
    @ApiModelProperty(value = "营业结束时间")
    private String endTime;
}
