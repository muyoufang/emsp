package com.demo.emsp.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.emsp.business.dto.StationCreateDto;
import com.demo.emsp.business.dto.StationDto;
import com.demo.emsp.business.entity.ChargingStation;
import com.demo.emsp.code.entity.R;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
public interface IChargingOperatorStationService extends IService<ChargingStation> {
    R selectPage(StationDto dto, Integer pageNo, Integer limit);

    R selectList(StationDto dto);

    R add(StationCreateDto obj);

    R delectIds(List<Integer> ids);

    R updateObjById(StationDto dto);
}
