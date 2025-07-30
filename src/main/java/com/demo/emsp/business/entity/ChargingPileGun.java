package com.demo.emsp.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
@TableName("charging_pile_gun")
public class ChargingPileGun  extends BaseEntity {

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
    //1直 2交流 3交直一体 4其它
    //@ApiModelProperty(name = "充电枪类型")
    //private Integer gunType;
    @ApiModelProperty(name = "充电桩ID")
    @TableField("pile_id")
    private Integer pileId;
}
