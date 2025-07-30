package com.demo.emsp.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.emsp.business.dto.ChargingNationCodeDto;
import com.demo.emsp.business.entity.ChargingNationCode;
import com.demo.emsp.code.entity.R;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
public interface IChargingNationCodeService extends IService<ChargingNationCode> {
    R selectPage(ChargingNationCodeDto dto, Integer pageNo, Integer limit);

    R selectList(ChargingNationCodeDto dto);

    R add(ChargingNationCodeDto obj);

    R delectIds(List<Integer> ids);

    R updateObjById(ChargingNationCodeDto dto);
}
