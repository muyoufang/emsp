package com.demo.emsp.business.controller;


import com.demo.emsp.business.dto.StationCreateDto;
import com.demo.emsp.business.dto.StationDto;
import com.demo.emsp.business.entity.ChargingStation;
import com.demo.emsp.business.service.IChargingOperatorStationService;
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
 *  前端控制器
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
@RestController
@RequestMapping("/business/charging_operator_stations")
@Api(value = "充电站 Controller",tags = {"充电站接口"})
public class ChargingStationController {
    @Autowired
    private IChargingOperatorStationService service;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public R selectPage(StationDto dto,
                        @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        R r = service.selectPage(dto, pageNo, limit);
        return r;
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public R selectList(StationDto dto) {
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
    public R add(@Validated @RequestBody StationCreateDto dto) {
        R r = service.add(dto);
        return r;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "详情")
    public ResponseEntity<R> getInfo(@NotNull(message = "id不能为空") @PathVariable("id") Integer id) {
        ChargingStation result = service.getById(id);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(R.error("查询数据不存在"));
        }
        return ResponseEntity.ok(R.ok(result));
    }

    @PutMapping("/update")
    public R update(@RequestBody StationDto dto) {
        R r = service.updateObjById(dto);
        return r;
    }
}
