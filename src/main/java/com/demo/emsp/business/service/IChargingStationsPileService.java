package com.demo.emsp.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.emsp.business.dto.PileGunStatusDto;
import com.demo.emsp.business.dto.StationPileCreateDto;
import com.demo.emsp.business.dto.StationPileDto;
import com.demo.emsp.business.entity.ChargingStationPile;
import com.demo.emsp.code.entity.R;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
public interface IChargingStationsPileService extends IService<ChargingStationPile> {
    R selectPage(String stationLng, String stationLat, Integer pageNo, Integer limit);

    R selectList(StationPileDto dto);

    R add(StationPileCreateDto obj);

    R delectIds(List<Integer> ids);

    R updateObjById(StationPileDto dto);

    R updateStatus(PileGunStatusDto dto);
}
