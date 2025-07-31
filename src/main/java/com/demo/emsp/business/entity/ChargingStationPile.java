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
@TableName("charging_station_pile")
public class ChargingStationPile extends BaseEntity {
    @ApiModelProperty(name = "桩编号")
    private String pileNumber;
    @ApiModelProperty(name = "桩名称")
    private String pileName;
    //    @ApiModelProperty(name = "额定总功率")
//    private String power;
    @ApiModelProperty(name = "设备类型(直流、交流、交直一体、其它)")
    private String pileType;
    //@ApiModelProperty(name = "设备型号")
    //private String pileModel;
    //有线 无线
    @ApiModelProperty(name = "网络")
    private String network;
    @ApiModelProperty(name = "桩状态")
    private String pileStatus;
    @ApiModelProperty(name = "场站ID")
    private Integer stationId;
}
