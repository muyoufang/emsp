package com.demo.emsp.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.emsp.business.dto.StationCreateDto;
import com.demo.emsp.business.dto.StationDto;
import com.demo.emsp.business.entity.ChargingStation;
import com.demo.emsp.business.enums.StationStatusEnum;
import com.demo.emsp.business.mapper.ChargingOperatorStationMapper;
import com.demo.emsp.business.service.IChargingOperatorStationService;
import com.demo.emsp.code.entity.R;
import com.demo.emsp.code.utils.MapUtil;
import com.demo.emsp.code.utils.TimeUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
@Service
public class ChargingOperatorStationServiceImpl extends ServiceImpl<ChargingOperatorStationMapper, ChargingStation> implements IChargingOperatorStationService {
    @Override
    public R selectPage(StationDto dto, Integer pageNo, Integer limit) {
        Map<String, Object> params = MapUtil.bean2MapIgnoreNullValue(dto);
        QueryWrapper<ChargingStation> queryWrapper = new QueryWrapper<ChargingStation>().allEq(params).orderByDesc(
                "update_time");
        IPage<ChargingStation> IPage = getBaseMapper().selectPage(new Page<>(pageNo, limit), queryWrapper);
        List<ChargingStation> records = IPage.getRecords();
        return R.ok(IPage);
    }

    @Override
    public R selectList(StationDto dto) {
        Map<String, Object> params = MapUtil.bean2MapIgnoreNullValue(dto);
        QueryWrapper<ChargingStation> queryWrapper = new QueryWrapper<ChargingStation>().allEq(params).orderByDesc(
                "update_time");
        List<ChargingStation> result = getBaseMapper().selectList(queryWrapper);
        return R.ok(result);
    }

    @Override
    public R add(StationCreateDto dto) {
        String stationCode = dto.getStationCode();
        Integer stationStatus = dto.getStationStatus();
        if (StationStatusEnum.getByValue(stationStatus) == null) {
            return R.error("充电站状态不正确 0正常运营 1 停止运营");
        }
        QueryWrapper<ChargingStation> query = new QueryWrapper<ChargingStation>()
                .eq("station_code", stationCode);
        if (getBaseMapper().selectOne(query) != null) {
            return R.error("充电站编码已存在");
        }
        ChargingStation obj = new ChargingStation();
        obj.setCreateTime(TimeUtils.getCreateTime());
        obj.setUpdateTime(TimeUtils.getCreateTime());
        BeanUtil.copyProperties(dto, obj);
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
    public R updateObjById(StationDto dto) {
        dto.setStationCode(null);  //站点编码不允许修改
        Integer id = dto.getId();
        if (ObjectUtil.isNull(id)) {
            return R.error("请输入修改对象Id");
        }
        Integer stationStatus = dto.getStationStatus();
        if (stationStatus != null) {
            if (StationStatusEnum.getByValue(stationStatus) == null) {
                return R.error("充电站状态不正确 0正常运营 1 停止运营");
            }
        }
        ChargingStation obj = getBaseMapper().selectById(dto.getId());
        if (obj == null) {
            return R.error("未找到对象");
        }
        BeanUtil.copyProperties(dto, obj);
        obj.setUpdateTime(TimeUtils.getCreateTime());
        getBaseMapper().updateById(obj);
        return R.ok(obj);
    }
}
