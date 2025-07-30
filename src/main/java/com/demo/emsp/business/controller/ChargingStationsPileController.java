package com.demo.emsp.business.controller;


import com.demo.emsp.business.dto.PileGunStatusDto;
import com.demo.emsp.business.dto.StationPileCreateDto;
import com.demo.emsp.business.dto.StationPileDto;
import com.demo.emsp.business.entity.ChargingStationPile;
import com.demo.emsp.business.service.IChargingStationsPileService;
import com.demo.emsp.code.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
@RestController
@RequestMapping("/business/charging_stations_pile")
@Api(value = "充电桩 Controller",tags = {"充电桩"})
public class ChargingStationsPileController {
    @Autowired
    private IChargingStationsPileService service;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public R selectPage(@RequestParam(name = "stationLng", required = false) String stationLng,
                        @RequestParam(name = "stationLat", required = false) String stationLat,
                        @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        R r = service.selectPage(stationLng, stationLat, pageNo, limit);
        return r;
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public R selectList(StationPileDto dto) {
        R r = service.selectList(dto);
        return r;
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public R add(@Validated @RequestBody StationPileCreateDto dto) {
        R r = service.add(dto);
        return r;
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "详情")
    public ResponseEntity<R> getInfo(@NotNull(message = "id不能为空") @PathVariable("id") Integer id) {
        ChargingStationPile result = service.getById(id);
        if(result == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(R.error("查询数据不存在"));
        }
        return ResponseEntity.ok(R.ok(result));
    }

    @PutMapping("/update")
    public R update(@RequestBody StationPileDto dto) {
        R r = service.updateObjById(dto);
        return r;
    }

    @PutMapping("/updateStatus")
    public R updateStatus(@RequestBody PileGunStatusDto dto) {
        return service.updateStatus(dto);
    }
}
