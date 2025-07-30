package com.demo.emsp.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.emsp.business.dto.PileGunStatusDto;
import com.demo.emsp.business.dto.StationPileCreateDto;
import com.demo.emsp.business.dto.StationPileDto;
import com.demo.emsp.business.entity.ChargingPileGun;
import com.demo.emsp.business.entity.ChargingStation;
import com.demo.emsp.business.entity.ChargingStationPile;
import com.demo.emsp.business.enums.PileStatusEnum;
import com.demo.emsp.business.enums.PileTypeEnum;
import com.demo.emsp.business.mapper.ChargingStationsPileMapper;
import com.demo.emsp.business.service.IChargingOperatorStationService;
import com.demo.emsp.business.service.IChargingPileGunService;
import com.demo.emsp.business.service.IChargingStationsPileService;
import com.demo.emsp.business.vo.PileVo;
import com.demo.emsp.code.entity.R;
import com.demo.emsp.code.exception.SiteBadException;
import com.demo.emsp.code.exception.SiteConfictException;
import com.demo.emsp.code.utils.MapUtil;
import com.demo.emsp.code.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
@Service
public class ChargingStationsPileServiceImpl extends ServiceImpl<ChargingStationsPileMapper, ChargingStationPile> implements IChargingStationsPileService {
    @Autowired
    private IChargingOperatorStationService stationService;
    @Autowired
    private ChargingStationsPileMapper pileMapper;

    @Override
    public R selectPage(String stationLng, String stationLat, Integer pageNo, Integer limit) {
        Map<String, Object> params = new HashMap<>();
        params.put("stationLng", stationLng);
        params.put("stationLat", stationLat);
        if (pageNo < 0) {
            pageNo = 1;
        }
        if (limit < 0) {
            limit = 10;
        }
        long startIndex = (pageNo - 1) * limit;
        params.put("startIndex", startIndex);
        params.put("pageSize", limit);
        List<PileVo> items = pileMapper.findListByParams(params);
        Long count = pileMapper.findCountByParams(params);
        IPage<PileVo> page = new Page<>(pageNo, limit, count);
        page.setRecords(items);
        return R.ok(page);
    }

    @Override
    public R selectList(StationPileDto dto){
        Map<String, Object> params = MapUtil.bean2MapIgnoreNullValue(dto);
        QueryWrapper<ChargingStationPile> queryWrapper = new QueryWrapper<ChargingStationPile>().allEq(params).orderByDesc("update_time");
        List<ChargingStationPile> result = getBaseMapper().selectList(queryWrapper);
        return R.ok(result);
    }

    @Override
    public R add(StationPileCreateDto dto) {
        String pileNumber = dto.getPileNumber();
        Integer stationId = dto.getStationId();
        String pileType = dto.getPileType();
        PileTypeEnum typeEnum = PileTypeEnum.getByValue(pileType);
        if (typeEnum == null) {
            return R.error("充电桩类型：直流,交流,交直一体,其它");
        }

        Pattern pattern = Pattern.compile("^[A-Z]{2}\\*[A-Z]{3}\\*EVSE[0-9a-zA-Z]{5,30}$");
        if (!pattern.matcher(pileNumber).matches()) {
            throw new SiteBadException("充电桩编码格式不正确");
        }
        if (getBaseMapper().selectOne(new QueryWrapper<ChargingStationPile>().eq("pile_number", pileNumber)) != null) {
            return R.error("充电桩编号已存在");
        }
        ChargingStationPile obj = new ChargingStationPile();
        BeanUtil.copyProperties(dto, obj);
        obj.setCreateTime(TimeUtils.getCreateTime());
        obj.setUpdateTime(TimeUtils.getCreateTime());
        obj.setPileStatus(PileStatusEnum.AVAILABLE.getValue());
        boolean save = save(obj);
        return save ? R.ok() : R.error();
    }

    @Override
    public R delectIds(List<Integer> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return R.error("请选择删除对象");
        }
        int flag = getBaseMapper().deleteBatchIds(ids);
        return flag > 0 ? R.ok() : R.error();
    }

    @Override
    public R updateObjById(StationPileDto dto) {
        dto.setStationId(null);     //充电站ID不能修改
        dto.setPileStatus(null);    //充电桩状态不能修改
        dto.setPileNumber( null);   //充电桩编号不能修改
        Integer id = dto.getId();
        if (ObjectUtil.isNull(id)) {
            return R.error("请输入修改对象Id");
        }
        String pileType = dto.getPileType();
        if (StrUtil.isNotEmpty(pileType)) {
            PileTypeEnum typeEnum = PileTypeEnum.getByValue(pileType);
            if (typeEnum == null) {
                return R.error("充电桩类型：直流,交流,交直一体,其它");
            }
        }
        ChargingStationPile obj = getBaseMapper().selectById(id);
        if (obj == null) {
            return R.error("未找到对象");
        }
        BeanUtil.copyProperties(dto, obj);
        obj.setUpdateTime(TimeUtils.getCreateTime());
        getBaseMapper().updateById(obj);
        return R.ok();
    }

    @Override
    public R updateStatus(PileGunStatusDto dto) {
        Integer id = dto.getId();
        String targetStatus = dto.getTargetStatus();
        if (ObjectUtil.isNull(id)) {
            R r = R.error("请输入修改对象Id");
            return r;
        }
        ChargingStationPile obj = getBaseMapper().selectById(id);
        if (obj == null) {
            System.out.println("未找到对象-------------------");
            R r = R.error("未找到对象");
            return r;
        } else {
            PileStatusEnum targetEnum = PileStatusEnum.getByValue(targetStatus);
            if (targetEnum == null) {
                R r = R.error("充电桩状态：AVAILABLE,BLOCKED,INOPERATIVE,REMOVED");
                return r;
            }
            String pileStatus = obj.getPileStatus();
            String msg = PileStatusEnum.checkConversion(pileStatus, targetStatus);
            if (StrUtil.isNotEmpty(msg)) {
                throw new SiteConfictException(msg);
            }
            //updateGunStatus(dto);
            obj.setPileStatus(targetStatus);
            obj.setUpdateTime(TimeUtils.getCreateTime());
            getBaseMapper().updateById(obj);
            return R.ok();
        }
    }
/*
    private void updateGunStatus(PileGunStatusDto dto) {
        String targetStatus = dto.getTargetStatus();
        LambdaQueryWrapper<ChargingPileGun> queryWrapper = new LambdaQueryWrapper<ChargingPileGun>()
                .eq(ChargingPileGun::getPileId, dto.getId());
        List<ChargingPileGun> result = gunService.list(queryWrapper);
        Set<String> sets = result.stream().map(ChargingPileGun::getGunStatus).collect(Collectors.toSet());
        if(sets.size() > 1){
            if(PileStatusEnum.INOPERATIVE.getValue().equals(targetStatus)){
                if(sets.contains(PileStatusEnum.BLOCKED.getValue())||sets.contains(PileStatusEnum.REMOVED.getValue())){
                    throw new SiteConfictException("充电枪在BLOCKED状态或REMOVED状态");
                }
            }
            if(PileStatusEnum.BLOCKED.getValue().equals(targetStatus)){
                if(sets.contains(PileStatusEnum.INOPERATIVE.getValue()) || sets.contains(PileStatusEnum.REMOVED.getValue())){
                    throw new SiteConfictException("充电枪在INOPERATIVE状态或REMOVED状态");
                }
            }
        }
        for (ChargingPileGun chargingPileGun : result) {
            chargingPileGun.setGunStatus(targetStatus);
            chargingPileGun.setUpdateTime(TimeUtils.getCreateTime());
            gunService.updateById(chargingPileGun);
        }
    }
   */
}
