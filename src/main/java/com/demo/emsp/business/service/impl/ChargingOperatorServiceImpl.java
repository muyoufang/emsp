package com.demo.emsp.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.emsp.business.dto.OperatorDto;
import com.demo.emsp.business.dto.OperatorCreateDto;
import com.demo.emsp.business.dto.OperatorLoginDto;
import com.demo.emsp.business.entity.ChargingOperator;
import com.demo.emsp.business.mapper.ChargingOperatorMapper;
import com.demo.emsp.business.service.IChargingOperatorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.emsp.code.entity.R;
import com.demo.emsp.code.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

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
@Slf4j
public class ChargingOperatorServiceImpl extends ServiceImpl<ChargingOperatorMapper, ChargingOperator> implements IChargingOperatorService {
    @Override
    public R selectPage(OperatorDto dto, Integer pageNo, Integer limit) {
        Map<String, Object> params = MapUtil.bean2MapIgnoreNullValue(dto);
        QueryWrapper<ChargingOperator> queryWrapper = new QueryWrapper<ChargingOperator>().allEq(params).orderByDesc("update_time");
        IPage<ChargingOperator> IPage = getBaseMapper().selectPage(new Page<>(pageNo, limit), queryWrapper);
        List<ChargingOperator> records = IPage.getRecords();
        setPasswordNull(records);
        return R.ok(IPage);
    }

    private static void setPasswordNull(List<ChargingOperator> records) {
        if (CollectionUtil.isNotEmpty(records)) {
            for (ChargingOperator record : records) {
                record.setPswd("");
            }
        }
    }

    @Override
    public R selectList(OperatorDto dto){
        Map<String, Object> params = MapUtil.bean2MapIgnoreNullValue(dto);
        QueryWrapper<ChargingOperator> queryWrapper = new QueryWrapper<ChargingOperator>().allEq(params).orderByDesc("update_time");
        List<ChargingOperator> result = getBaseMapper().selectList(queryWrapper);
        setPasswordNull(result);
        return R.ok(result);
    }

    @Override
    public R add(OperatorCreateDto dto) {
        String operatorNumber = dto.getOperatorNumber();
        if (getBaseMapper().selectOne(new QueryWrapper<ChargingOperator>().eq("operator_number", operatorNumber)) != null) {
            return R.error("运营商编码已存在");
        }
        ChargingOperator obj = new ChargingOperator();
        obj.setCreateTime(TimeUtils.getCreateTime());
        obj.setUpdateTime(TimeUtils.getCreateTime());
        BeanUtil.copyProperties(dto, obj);
        obj.setPswd(MD5Util.getMd5("123456"));
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
    public R updateObjById(OperatorDto dto) {
        dto.setLoginName(null); //登录名不能修改
        dto.setOperatorNumber(null); //运营商编号不能修改
        Integer id = dto.getId();
        if(ObjectUtil.isNull( id)){
            return R.error("请输入修改对象Id");
        }
        ChargingOperator obj = getBaseMapper().selectById(dto.getId());
        if (obj == null) {
            return R.error("未找到对象");
        }
        BeanUtil.copyProperties(dto, obj);
        obj.setUpdateTime(TimeUtils.getCreateTime());
        getBaseMapper().updateById(obj);
        return R.ok();
    }

    @Override
    public R login(OperatorLoginDto dto){
        String loginName = dto.getLoginName();
        if (StringUtils.isEmpty(loginName)) {
            return R.error("登录名不能为空");
        }
        String password = dto.getPswd();
        if (StringUtils.isEmpty(password)) {
            return R.error("密码不能为空");
        }
        LambdaQueryWrapper<ChargingOperator> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChargingOperator::getLoginName, loginName);
        ChargingOperator operator = getBaseMapper().selectOne(queryWrapper);
        if (operator == null) {
            log.error("登录名{}登录信息不存在，请联系管理员",loginName);
            return R.error("用户不存在，请联系管理员");
        }
        String md5Pass = MD5Util.getMd5(password);
        if (md5Pass.equals(operator.getPswd())) {
            SSOToken ssoToken = SSOToken
                    .create()
                    //.setIp(request)
                    //.setOrigin(TokenOrigin.HTML5)
                    .setId(operator.getId())
                    .setIssuer(loginName);
            log.error("登录名{}登录成功",loginName);
            return R.ok(ssoToken.getToken());
        } else {
            log.error("登录名{}登录密码不正确",loginName);
            return R.error("密码不正确");
        }
    }
}
