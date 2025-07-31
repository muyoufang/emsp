package com.demo.emsp.business.controller;


import com.demo.emsp.business.dto.ChargingNationCodeDto;
import com.demo.emsp.business.service.IChargingNationCodeService;
import com.demo.emsp.code.entity.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author muyoufang
 * @since 2025-07-26
 */
@RestController
@RequestMapping("/business/charging_nation_code")
@Api(value = "国家编码 Controller", tags = {"国家编码接口"})
public class ChargingNationCodeController {
    @Autowired
    private IChargingNationCodeService service;

    @GetMapping("/page")
    @ApiOperation(value = "分页")
    public R selectPage(ChargingNationCodeDto dto,
                        @RequestParam(name = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        R result = service.selectPage(dto, pageNo, limit);
        return result;
    }

    @GetMapping("/list")
    @ApiOperation(value = "列表")
    public R selectList(ChargingNationCodeDto dto) {
        R result = service.selectList(dto);
        return result;
    }
    /*
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除")
    public R delete(List<Integer> ids) {
        return service.delectIds(ids);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加")
    public R add(@RequestBody ChargingNationCodeDto dto) {
        return service.add(dto);
    }
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "详情")
    public R getInfo(@PathVariable("id") Integer id){
        if (id == null) {
            return R.error("参数错误");
        }
        ChargingNationCode bean = service.getById(id);
        return R.ok(bean);
    }

    @PostMapping("/update")
    public R update(@RequestBody ChargingNationCodeDto dto) {
        return service.updateObjById(dto);
    }
    */
}
