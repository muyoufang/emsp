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
@TableName("charging_operator_station")
public class ChargingStation extends BaseEntity {
    @ApiModelProperty(name = "充电站编码")
    private String stationCode;
    @ApiModelProperty(name = "充电站名称")
    private String stationName;
//    @ApiModelProperty(name = "充电桩数")
//    private Integer deviceNumber;
//    @ApiModelProperty(name = "交流充电枪数")
//    private Integer acGunNumber;
//    @ApiModelProperty(name = "直流充电枪数")
//    private Integer dcGunNumber;
//    @ApiModelProperty(name = "交流额定功率")
//    private Integer acRatePower;
//    @ApiModelProperty(name = "直流额定功率")
//    private Integer dcRatePower;
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
    //1自营、2联营、3专用
    //@ApiModelProperty(name = "场站模式")
//    private Integer stationModel;
    @ApiModelProperty(name = "充电站状态")
    private Integer stationStatus;
    //1公共、2个人、3专用、4其它
    //@ApiModelProperty(name = "场站类型")
    //private Integer stationType;
    @ApiModelProperty(name = "运营商ID")
    private Integer operatorId;
    @ApiModelProperty(name = "停车费")
    private Integer parkFee;
    @ApiModelProperty(value = "营业开始时间")
    private String startTime;
    @ApiModelProperty(value = "营业结束时间")
    private String endTime;
}
