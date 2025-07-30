package com.demo.emsp.business.mapper;

import com.demo.emsp.business.entity.ChargingStationPile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.emsp.business.vo.PileVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
public interface ChargingStationsPileMapper extends BaseMapper<ChargingStationPile> {
    List<PileVo> findListByParams(Map<String, Object> params);

    Long findCountByParams(Map<String, Object> params);
}
