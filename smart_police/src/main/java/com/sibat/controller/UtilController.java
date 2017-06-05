package com.sibat.controller;

import com.sibat.Service.UtilService;
import com.sibat.domain.origin.BusWarning;
import com.sibat.domain.origin.BusWarningDao;
import com.sibat.domain.origin.JqfxJqlrDt;
import com.sibat.domain.origin.JqfxJqlrDtDao;
import com.sibat.domain.other.*;
import com.sibat.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by tgw61 on 2017/5/10.
 */
@RestController
public class UtilController {
    Logger logger = Logger.getLogger(UtilController.class);
    @Autowired
    JqfxJqlrDtDao jqfxJqlrDtDao;
    @Autowired
    SubwayEventDao subwayEventDao;
    @Autowired
    UtilService utilService;
    @Autowired
    StationDao stationDao;
    @Autowired
    BusWarningDao busWarningDao;
    @Autowired
    BusEventDao busEventDao;

    /**
     * 将地铁警情数据保存到新库的subway_event
     *
     * 1.地铁警情数据保存在jqfx_jqlr_dt表中
     * 2.查询出jqfx_jqlr_dt表中的所有数据
     * 3.将每一条数据构造SubwayEvent对象，以此保存到新库的subway_event中
     *
     * @return
     */
    @RequestMapping(value = "saveStationData", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response saveStationData() {
        List<SubwayEvent> seList = new ArrayList<>();
        List<JqfxJqlrDt> jqfxJqlrDtList = jqfxJqlrDtDao.findAllObj();
        if (jqfxJqlrDtList != null & !jqfxJqlrDtList.isEmpty()) {
            for (JqfxJqlrDt sw : jqfxJqlrDtList) {
                String stationId = null, stationName = null, lineId = null, lineName = null, eventId = null,
                        category = null, type = null, content = null, eventTime = null, police = null, policeId = null, time = null;
                int id = sw.getDT_ID();
                if (sw.getDDMC() != null) {
                    stationName = utilService.convertStation(sw.getDDMC());
                    stationId = stationDao.findStationIdByStationName(stationName);
                }
                if (sw.getXL_ID() != null) {
                    lineId = sw.getXL_ID();
                }
                if (sw.getXL() != null) {
                    lineName = sw.getXL();
                }
                if (sw.getJCJ_ID() != null) {
                    eventId = sw.getJCJ_ID();
                }
                if (sw.getJQXZ() != null) {
                    category = sw.getJQXZ();
                }
                if (sw.getJQLB() != null) {
                    type = sw.getJQLB().trim();
                }
                if (sw.getREMARK() != null) {
                    content = sw.getREMARK();
                }
                if (sw.getAF_TIME() != null) {
                    eventTime = sw.getAF_TIME().toString();
                }
                if (sw.getJJDW_NAME() != null) {
                    police = sw.getJJDW_NAME();
                }
                if (sw.getJJDW_ID() != null) {
                    policeId = sw.getJJDW_ID();
                }
                if (sw.getAF_TIME() != null) {
                    time = sw.getAF_TIME().toString();
                    time = time.substring(0, time.lastIndexOf("-"));
                }
                seList.add(new SubwayEvent(id, stationId, stationName, lineId,
                        lineName, eventId, category,
                        type, content, eventTime, police, policeId, time));
            }
            subwayEventDao.save(seList);
            logger.info("添加subway_event成功");
        }
        return new Response("200", "success");
    }

    /**
     * 将公交警情数据保存到新库的bus_event表
     *
     * 1.公交警情数据保存在jqfx_jqlr_gj表中
     * 2.查询出jjqfx_jqlr_gj表中的所有数据
     * 3.将每一条数据构造构造BusEvent对象，以此保存到新库的bus_event中
     *
     * @return
     */
    @RequestMapping(value = "saveBusData", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response saveBusData() {
        Sort sort = new Sort(Sort.Direction.ASC, "XL");
        Page<BusWarning> busWarnings;
        for (int i = 14; i < 19; i++) {
            Pageable pageable = new PageRequest(i, 5000, sort);
            busWarnings = busWarningDao.findAll(pageable);
            List<BusEvent> bes = new ArrayList<>();
            for (BusWarning bw : busWarnings) {
                try {
                    String id = null, stationName = null, lineId = null, eventId = null,
                            category = null, type = null, content = null, eventTime = null,
                            police = null, policeId = null, submitTime = null, dataFlowStatus = null;
                    if (bw.getGJ_ID() != null)
                        id = bw.getGJ_ID().toString();
                    if (bw.getDDMC() != null)
                        stationName = bw.getDDMC().trim();
                    if (bw.getXL_ID() != null)
                        lineId = bw.getXL_ID().trim();
                    if (bw.getJCJ_ID() != null)
                        eventId = bw.getJCJ_ID().trim();
                    if (bw.getJQXZ() != null)
                        category = bw.getJQXZ().trim();
                    if (bw.getJQLB() != null)
                        type = bw.getJQLB().trim().trim();
                    if (bw.getREMARK() != null)
                        content = bw.getREMARK().trim();
                    if (bw.getAF_TIME() != null)
                        eventTime = bw.getAF_TIME().toString().split(" ")[0];
                    if (bw.getJJDW_NAME() != null)
                        police = bw.getJJDW_NAME().trim();
                    if (bw.getJJDW_ID() != null)
                        policeId = bw.getJJDW_ID().trim();
                    if (bw.getSUBMIT_TIME() != null)
                        submitTime = bw.getSUBMIT_TIME().toString();
                    if (bw.getDATA_PRV_STATUS() != null)
                        dataFlowStatus = bw.getDATA_PRV_STATUS();
                    bes.add(new BusEvent(id, stationName, lineId, eventId, category, type, content, eventTime, police, policeId, submitTime, dataFlowStatus));

                } catch (Exception e) {
                    logger.info("出错了");
                    e.printStackTrace();
                    continue;
                }
            }
            busEventDao.save(bes);
            logger.info("保存部分成功");
        }
        logger.info("保存完成");
        return new Response("200", "success");
    }


    /**
     * 修改地铁警情表subway_event的station_id
     *
     * @return
     */
    @RequestMapping(value = "dealSubwayId", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response dealSubwayId() {
        List<SubwayEvent> seList = subwayEventDao.findAll();
        for (SubwayEvent se : seList) {
            try {
                Station station = stationDao.findByStationName(se.getStationName());
                se.setStationId(station.getStationId());
                subwayEventDao.saveAndFlush(se);
            } catch (Exception e) {
                continue;
            }
        }
        return new Response("200", "success");
    }


}
