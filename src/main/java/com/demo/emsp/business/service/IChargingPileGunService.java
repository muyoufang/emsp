package com.demo.emsp.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.emsp.business.dto.PileGunCreateDto;
import com.demo.emsp.business.dto.PileGunDto;
import com.demo.emsp.business.dto.PileGunStatusDto;
import com.demo.emsp.business.entity.ChargingPileGun;
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
public interface IChargingPileGunService extends IService<ChargingPileGun> {
    R selectPage(PileGunDto dto, Integer pageNo, Integer limit);

    R selectList(PileGunDto dto);

    R add(PileGunCreateDto obj);

    R delectIds(List<Integer> ids);

    R updateObjById(PileGunDto dto);

    R updateStatus(PileGunStatusDto dto);
}
