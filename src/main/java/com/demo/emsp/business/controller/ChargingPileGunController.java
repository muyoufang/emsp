package com.demo.emsp.business.controller;


import com.demo.emsp.business.dto.PileGunCreateDto;
import com.demo.emsp.business.dto.PileGunDto;
import com.demo.emsp.business.dto.PileGunStatusDto;
import com.demo.emsp.business.entity.ChargingPileGun;
import com.demo.emsp.business.service.IChargingPileGunService;
import com.demo.emsp.code.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
@RestController
@RequestMapping("/business/charging_pile_gun")
@Api(value = "充电桩连接口 Controller", tags = {"充电桩连接口"})
public class ChargingPileGunController {
    @Autowired
    private IChargingPileGunService service;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public R selectPage(PileGunDto dto,
                        @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        R r = service.selectPage(dto, pageNo, limit);
        return r;
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public R selectList(PileGunDto dto) {
        R r = service.selectList(dto);
        return r;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public R add(@Validated @RequestBody PileGunCreateDto dto) {
        R r = service.add(dto);
        return r;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "详情")
    public ResponseEntity<R> getInfo(@NotNull(message = "id不能为空") @PathVariable("id") Integer id) {
        ChargingPileGun result = service.getById(id);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(R.error("查询数据不存在"));
        }
        return ResponseEntity.ok(R.ok(result));
    }

    @PostMapping("/update")
    public R update(@RequestBody PileGunDto dto) {
        R r = service.updateObjById(dto);
        return r;
    }

    @PostMapping("/updateStatus")
    public R updateStatus(@RequestBody PileGunStatusDto dto) {
        return service.updateStatus(dto);
    }
}
