package com.demo.emsp.business.dto;

import com.baomidou.mybatisplus.annotation.TableField;
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
public class PileGunDto {

    @ApiModelProperty(value = "ID标识")
    private Integer id;
    @ApiModelProperty(name = "充电枪名称")
    private String gunName;
    @ApiModelProperty(name = "额定总功率")
    private String power;
    @ApiModelProperty(name = "电流")
    private String electricity;
    @ApiModelProperty(name = "最大电压")
    private String voltageUpperLimits;
    @ApiModelProperty(name = "最低电压")
    private String voltageLowerLimits;
    @ApiModelProperty(name = "充电桩ID")
    @TableField("pile_id")
    private Integer pileId;
}
