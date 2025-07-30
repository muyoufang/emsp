package com.demo.emsp.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.emsp.business.dto.ChargingNationCodeDto;
import com.demo.emsp.business.entity.ChargingNationCode;
import com.demo.emsp.business.mapper.ChargingNationCodeMapper;
import com.demo.emsp.business.service.IChargingNationCodeService;
import com.demo.emsp.code.entity.PageInfo;
import com.demo.emsp.code.entity.R;
import com.demo.emsp.code.utils.MapUtil;
import com.demo.emsp.code.utils.TimeUtils;
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
public class ChargingNationCodeServiceImpl extends ServiceImpl<ChargingNationCodeMapper, ChargingNationCode> implements IChargingNationCodeService {
    @Override
    public R selectPage(ChargingNationCodeDto dto, Integer pageNo, Integer limit) {
        Map<String, Object> params = MapUtil.bean2MapIgnoreNullValue(dto);
        QueryWrapper<ChargingNationCode> queryWrapper = new QueryWrapper<ChargingNationCode>().allEq(params).orderByDesc("update_time");
        IPage<ChargingNationCode> IPage = getBaseMapper().selectPage(new Page<>(pageNo, limit), queryWrapper);
        return R.ok(IPage);
    }

    @Override
    public R selectList(ChargingNationCodeDto dto){
        Map<String, Object> params = MapUtil.bean2MapIgnoreNullValue(dto);
        QueryWrapper<ChargingNationCode> queryWrapper = new QueryWrapper<ChargingNationCode>().allEq(params).orderByDesc("update_time");
        List<ChargingNationCode> result = getBaseMapper().selectList(queryWrapper);
        return R.ok(result);
    }

    @Override
    public R add(ChargingNationCodeDto dto) {
        String nationCode = dto.getNationCode();
        String nationName = dto.getNationName();
        if (ObjectUtil.isEmpty(nationCode)) {
            return R.error("请输入国家编码");
        }
        if (ObjectUtil.isEmpty(nationName)) {
            return R.error("请输入国家名称");
        }
        if (nationCode.length() != 2) {
            return R.error("国家编码2位字母");
        }
        if (getBaseMapper().selectOne(new QueryWrapper<ChargingNationCode>().eq("nation_code", nationCode)) != null) {
            return R.error("国家编码已存在");
        }
        ChargingNationCode obj = new ChargingNationCode();
        obj.setCreateTime(TimeUtils.getCreateTime());
        obj.setNationCode(nationCode);
        obj.setNationName(nationName);
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
    public R updateObjById(ChargingNationCodeDto dto) {
        ChargingNationCode obj = getBaseMapper().selectById(dto.getId());
        if (obj == null) {
            return R.error("未找到对象");
        }
        BeanUtil.copyProperties(dto, obj);
        obj.setUpdateTime(TimeUtils.getCreateTime());
        getBaseMapper().updateById(obj);
        return R.ok();
    }
}
