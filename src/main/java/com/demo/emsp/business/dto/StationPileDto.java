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
public class StationPileDto {

    @ApiModelProperty(value = "ID标识")
    private Integer id;
    @ApiModelProperty(name = "充电桩编号")
    private String pileNumber;
    @ApiModelProperty(name = "充电桩名称")
    private String pileName;
    //@ApiModelProperty(name = "额定总功率")
    //private String power;
    @ApiModelProperty(name = "充电桩设备类型(直流、交流、交直一体、其它)")
    private String pileType;
    //@ApiModelProperty(name = "设备型号")
    //private String pileModel;
    //有线 无线
    @ApiModelProperty(name = "充电桩网络")
    private String network;
    @ApiModelProperty(name = "充电桩状态")
    private String pileStatus;
    @ApiModelProperty(name = "场站ID")
    private Integer stationId;
}
