package com.demo.emsp.business.controller;


import com.baomidou.kisso.security.token.SSOToken;
import com.demo.emsp.business.dto.OperatorCreateDto;
import com.demo.emsp.business.dto.OperatorDto;
import com.demo.emsp.business.dto.OperatorLoginDto;
import com.demo.emsp.business.entity.ChargingOperator;
import com.demo.emsp.business.service.IChargingOperatorService;
import com.demo.emsp.code.entity.R;
import com.demo.emsp.code.utils.MD5Util;
import com.demo.emsp.code.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/business/charging_operator")
@Api(value = "运营商 Controller",tags = {"运营商接口"})
public class ChargingOperatorController {
    @Autowired
    private IChargingOperatorService service;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public R selectPage(OperatorDto dto,
                        @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        R r = service.selectPage(dto, pageNo, limit);
        return r;
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public R selectList(OperatorDto dto) {
        R r = service.selectList(dto);
        return r;
    }
/*
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public R delete(@RequestBody List<Integer> ids) {
        R r = service.delectIds(ids);
        return r;
    }
*/

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public R add(@Validated @RequestBody OperatorCreateDto dto) {
        R r = service.add(dto);
        return r;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "详情")
    public ResponseEntity<R> getInfo(@NotNull(message = "id不能为空") @PathVariable("id") Integer id) {
        ChargingOperator operator = service.getById(id);
        if (operator != null) {
            operator.setPswd("");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(R.error("查询数据不存在"));
        }
        return ResponseEntity.ok(R.ok(operator));
    }

    @PostMapping("/update")
    public R update(@RequestBody OperatorDto dto) {
        R r = service.updateObjById(dto);
        return r;
    }

    @PostMapping("/updatePwd")
    public R updatePwd(
            @NotBlank(message = "id不能为空") @RequestParam(name = "oldPassword") String oldPassword,
            @NotBlank(message = "id不能为空") @RequestParam(name = "password") String password,
            @NotBlank(message = "id不能为空") @RequestParam(name = "token") String token) {
        try {
            SSOToken parser = SSOToken.parser(token, false);
            String operatorId = parser.getId();
            ChargingOperator operator = service.getById(operatorId);
            operator.setUpdateTime(TimeUtils.getCreateTime());
            String oldPasswords = MD5Util.getMd5(oldPassword);
            if (oldPasswords.equals(operator.getPswd())) {
                String md5 = MD5Util.getMd5(password);
                operator.setPswd(md5);
                service.updateById(operator);
                return R.ok();
            } else {
                return R.error("原始密码错误");
            }
        }catch (Exception e){
            return R.error("token信息不正确");
        }

    }

    @PostMapping("/login")
    public R login(@Validated @RequestBody OperatorLoginDto dto) {
        R r = service.login(dto);
        return r;
    }
}
