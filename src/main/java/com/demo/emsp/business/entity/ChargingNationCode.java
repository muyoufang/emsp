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
@TableName("charging_nation_code")
public class ChargingNationCode extends BaseEntity {

    @ApiModelProperty(value = "国家名称")
    private String nationName;

    @ApiModelProperty(value = "国家编码")
    private String nationCode;
}
