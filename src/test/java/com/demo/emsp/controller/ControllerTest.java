package com.demo.emsp.controller;

import cn.hutool.json.JSONUtil;
import com.demo.emsp.business.dto.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


/**
 * @Title: TestController
 * @Description: TODO
 * @author: muyoufang
 * @create: 2025-07-24 16:41
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@Rollback(false)  // 关键：不回滚
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testOperatorAdd() throws Exception {
        OperatorCreateDto dto = new OperatorCreateDto();
        dto.setAddress("天津滨海新区洞庭1号");
        dto.setLoginName("dtyys");
        dto.setOperatorName("洞庭1号运营商");
        dto.setOperatorNumber("dtyys");
        dto.setPhone("13800000000");
        mockMvc.perform(post("/business/charging_operator/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(2)
    public void testOperatorGetInfo() throws Exception {
        mockMvc.perform(get("/business/charging_operator/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("1"));
    }

    @Test
    @Order(3)
    public void testStationAdd() throws Exception {
        StationCreateDto dto = new StationCreateDto();
        dto.setStationCode("dongting_station");
        dto.setStationName("洞庭1号充电站");
        dto.setProvince("天津");
        dto.setCity("滨海新区");
        dto.setAddress("洞庭路1号");
        dto.setStationLng("117.20");
        dto.setStationLat("39.66");
        dto.setStationStatus(0);
        dto.setOperatorId(1);
        dto.setParkFee(0);
        dto.setStartTime("上午9点");
        dto.setEndTime("下午18点");
        mockMvc.perform(post("/business/charging_operator_stations/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(4)
    public void testStationAdd2() throws Exception {
        StationCreateDto dto = new StationCreateDto();
        dto.setStationCode("zhidishan_station");
        dto.setStationName("智谛山充电站");
        dto.setProvince("天津");
        dto.setCity("滨海新区");
        dto.setAddress("洞庭路智谛山");
        dto.setStationLng("120");
        dto.setStationLat("40");
        dto.setStationStatus(0);
        dto.setOperatorId(1);
        dto.setParkFee(0);
        dto.setStartTime("上午9点");
        dto.setEndTime("下午18点");
        mockMvc.perform(post("/business/charging_operator_stations/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(5)
    public void testStationAdd3() throws Exception {
        StationCreateDto dto = new StationCreateDto();
        dto.setStationCode("hmdq_station");
        dto.setStationName("海门大桥");
        dto.setProvince("天津");
        dto.setCity("滨海新区");
        dto.setAddress("海门大桥");
        dto.setStationLng("117");
        dto.setStationLat("39");
        dto.setStationStatus(0);
        dto.setOperatorId(1);
        dto.setParkFee(0);
        dto.setStartTime("上午9点");
        dto.setEndTime("下午18点");
        mockMvc.perform(post("/business/charging_operator_stations/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(6)
    public void testStationUpdate() throws Exception {
        StationDto dto = new StationDto();
        dto.setId(1);
        dto.setStationLng("120");
        dto.setStationLat("40");
        mockMvc.perform(put("/business/charging_operator_stations/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.stationLng").value("120"));
    }

    //US*ABC*EVSE123456 格式不正确 400
    @Test
    @Order(7)
    public void testPileAdd() throws Exception {
        Thread.sleep(5000);
        StationPileCreateDto dto = new StationPileCreateDto();
        dto.setNetwork("无线");
        dto.setPileName("充电桩1#");
        dto.setPileNumber("CHINA*DF*EVSE123456");
        dto.setPileType("直流");
        dto.setStationId(1);
        mockMvc.perform(post("/business/charging_stations_pile/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(8)
    public void testPileAdd2() throws Exception {
        StationPileCreateDto dto = new StationPileCreateDto();
        dto.setNetwork("无线");
        dto.setPileName("充电桩1#");
        dto.setPileNumber("ZH*ABC*EVSE123456");
        dto.setPileType("直流");
        dto.setStationId(1);
        mockMvc.perform(post("/business/charging_stations_pile/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isOk());
    }
    @Test
    @Order(9)
    public void testPileGetInfo() throws Exception {
        mockMvc.perform(get("/business/charging_stations_pile/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value("1"));
    }
    @Order(10)
    @Test
    public void testPileUpdateStatus() throws Exception {
        PileGunStatusDto dto = new PileGunStatusDto();
        dto.setId(1);
        dto.setTargetStatus("INOPERATIVE");
        mockMvc.perform(put("/business/charging_stations_pile/updateStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(11)
    public void testPileUpdateStatus2() throws Exception {
        PileGunStatusDto dto = new PileGunStatusDto();
        dto.setId(1);
        dto.setTargetStatus("BLOCKED");
        mockMvc.perform(put("/business/charging_stations_pile/updateStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isConflict());
    }

    @Test
    @Order(12)
    public void testPileUpdateStatus3() throws Exception {
        PileGunStatusDto dto = new PileGunStatusDto();
        dto.setId(1);
        dto.setTargetStatus("AVAILABLE");
        mockMvc.perform(put("/business/charging_stations_pile/updateStatus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @Order(13)
    public void testGunAdd() throws Exception {
        PileGunCreateDto dto = new PileGunCreateDto();
        dto.setElectricity("20A");
        dto.setGunName("左连接器");
        dto.setPileId(1);
        dto.setPower("5000w");
        dto.setVoltageLowerLimits("110v");
        dto.setVoltageUpperLimits("220v");
        mockMvc.perform(post("/business/charging_pile_gun/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSONUtil.toJsonStr(dto)))
                .andExpect(status().isOk());
    }

    @Order(14)
    @Test
    public void testPilePage() throws Exception {
        mockMvc.perform(get("/business/charging_stations_pile/page")
                        .param("pageNo", "1")
                        .param("limit", "10")
                        .param("stationLat", "40")
                        .param("stationLng", "120"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.total").value(greaterThan(0)));;
    }
}
