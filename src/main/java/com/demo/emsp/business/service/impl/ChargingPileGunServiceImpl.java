package com.demo.emsp.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.emsp.business.dto.PileGunCreateDto;
import com.demo.emsp.business.dto.PileGunDto;
import com.demo.emsp.business.dto.PileGunStatusDto;
import com.demo.emsp.business.entity.ChargingPileGun;
import com.demo.emsp.business.entity.ChargingStationPile;
import com.demo.emsp.business.mapper.ChargingPileGunMapper;
import com.demo.emsp.business.service.IChargingPileGunService;
import com.demo.emsp.business.service.IChargingStationsPileService;
import com.demo.emsp.code.entity.R;
import com.demo.emsp.code.utils.MapUtil;
import com.demo.emsp.code.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
@Service
public class ChargingPileGunServiceImpl extends ServiceImpl<ChargingPileGunMapper, ChargingPileGun> implements IChargingPileGunService {
    @Autowired
    private IChargingStationsPileService pileService;
    @Override
    public R selectPage(PileGunDto dto, Integer pageNo, Integer limit) {
        Map<String, Object> params = MapUtil.bean2MapIgnoreNullValue(dto);
        QueryWrapper<ChargingPileGun> queryWrapper = new QueryWrapper<ChargingPileGun>().allEq(params).orderByDesc("update_time");
        IPage<ChargingPileGun> IPage = getBaseMapper().selectPage(new Page<>(pageNo, limit), queryWrapper);
        List<ChargingPileGun> records = IPage.getRecords();
        return R.ok(IPage);
    }

    @Override
    public R selectList(PileGunDto dto){
        Map<String, Object> params = MapUtil.bean2MapIgnoreNullValue(dto);
        QueryWrapper<ChargingPileGun> queryWrapper = new QueryWrapper<ChargingPileGun>().allEq(params).orderByDesc("update_time");
        List<ChargingPileGun> result = getBaseMapper().selectList(queryWrapper);
        return R.ok(result);
    }

    @Override
    public R add(PileGunCreateDto dto) {
        Integer pileId = dto.getPileId();
        String gunName = dto.getGunName();
        ChargingStationPile pipe = pileService.getById(pileId);
        if (pipe == null) {
            return R.error("充电桩ID" + pileId + "不存在");
        }
        if (getBaseMapper().selectOne(new QueryWrapper<ChargingPileGun>()
                .eq("gun_name", gunName)
                .eq("pile_id",
                pileId)) != null) {
            return R.error("充电枪名称已存在");
        }
        ChargingPileGun obj = new ChargingPileGun();
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
    public R updateObjById(PileGunDto dto) {
        Integer id = dto.getId();
        dto.setGunName(null); // 充电枪名不能修改
        dto.setPileId(null);
        if(ObjectUtil.isNull( id)){
            return R.error("请输入修改对象Id");
        }
        ChargingPileGun obj = getBaseMapper().selectById(dto.getId());
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
        ChargingPileGun obj = getBaseMapper().selectById(id);
        if (obj == null) {
            R r = R.error("未找到对象");
            return r;
        } else {
            /*
            String gunStatus = obj.getGunStatus();
            if (StrUtil.isNotEmpty(targetStatus)) {
                PileStatusEnum targetEnum = PileStatusEnum.getByValue(targetStatus);
                if (targetEnum == null) {
                    R r = R.error("充电桩状态：AVAILABLE,BLOCKED,INOPERATIVE,REMOVED");
                    return r;
                }
                String msg = PileStatusEnum.checkConversion(gunStatus, targetStatus);
                if (StrUtil.isNotEmpty(msg)) {
                    throw new SiteConfictException(msg);
                }
            }
            obj.setGunStatus(targetStatus);
            */
            obj.setUpdateTime(TimeUtils.getCreateTime());
            getBaseMapper().updateById(obj);
            //updatePileStatus(obj);
            return R.ok();
        }
    }
/*
    private void updatePileStatus(ChargingPileGun obj) {
        LambdaQueryWrapper<ChargingPileGun> queryWrapper = new LambdaQueryWrapper<ChargingPileGun>()
                .eq(ChargingPileGun::getPileId, obj.getPileId());
        List<ChargingPileGun> result = getBaseMapper().selectList(queryWrapper);
        Set<String> sets = result.stream().map(ChargingPileGun::getGunStatus).collect(Collectors.toSet());
        if(sets.size() == 1){
            PileGunStatusDto pipDto = new PileGunStatusDto();
            pipDto.setId(obj.getPileId());
            pipDto.setTargetStatus(obj.getGunStatus());
            pileService.updateStatus(pipDto);
        }
    }
    */
}
