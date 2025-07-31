package com.demo.emsp.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.emsp.business.dto.OperatorCreateDto;
import com.demo.emsp.business.dto.OperatorDto;
import com.demo.emsp.business.dto.OperatorLoginDto;
import com.demo.emsp.business.entity.ChargingOperator;
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
public interface IChargingOperatorService extends IService<ChargingOperator> {
    R selectPage(OperatorDto dto, Integer pageNo, Integer limit);

    R selectList(OperatorDto dto);

    R add(OperatorCreateDto obj);

    R delectIds(List<Integer> ids);

    R updateObjById(OperatorDto dto);

    R login(OperatorLoginDto dto);
}
