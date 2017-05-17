package com.sibat.controller;

import com.sibat.Service.ScheduleService;
import com.sibat.Service.UtilService;
import com.sibat.domain.origin.BusWarning;
import com.sibat.domain.origin.BusWarningDao;
import com.sibat.domain.origin.SubwayWarning;
import com.sibat.domain.origin.SubwayWarningDao;
import com.sibat.domain.other.*;
import com.sibat.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/10.
 */
@RestController
public class UtilController {
    Logger logger = Logger.getLogger(UtilController.class);
    @Autowired
    SubwayWarningDao subwayWarningDao;
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

    @Autowired
    ScheduleService scheduleService;
    /**
     * 将数据保存到新库
     *
     * @return
     */
    @RequestMapping(value = "saveStationData", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response saveStationData() {
//        List<SubwayEvent> seList = new ArrayList<>();
//        List<SubwayWarning> swList = subwayWarningDao.findAll();
//        for (SubwayWarning sw : swList) {
//            String stationId = null, stationName = null, lineId = null, lineName = null, eventId = null,
//                    category = null, type = null, content = null, eventTime = null, police = null, policeId = null, time = null;
////            if(sw.getDDMC()!=null){
////                stationId=sw.getDDMC();
////            }
//            if (sw.getDDMC() != null) {
//                stationName = utilService.convertStation(sw.getDDMC().trim());
//            }
//            if (sw.getXL_ID() != null) {
//                lineId = sw.getXL_ID().trim();
//            }
//            if (sw.getXL() != null) {
//                lineName = sw.getXL().trim();
//            }
//            if (sw.getJCJ_ID() != null) {
//                eventId = sw.getJCJ_ID().trim();
//            }
//            if (sw.getJQXZ() != null) {
//                category = sw.getJQXZ().trim();
//            }
//            if (sw.getJQLB() != null) {
//                type = sw.getJQLB().trim();
//            }
//            if (sw.getREMARK() != null) {
//                content = sw.getREMARK().trim();
//            }
//            if (sw.getAF_TIME() != null) {
//                eventTime = sw.getAF_TIME().trim();
//            }
//
//            if (sw.getJJDW_NAME() != null) {
//                police = sw.getJJDW_NAME().trim();
//            }
//            if (sw.getJJDW_ID() != null) {
//                policeId = sw.getJJDW_ID().trim();
//            }
//
//            if (sw.getAF_TIME() != null) {
//                try {
//                    time = sw.getAF_TIME().split(" ")[0];
//                    time = time.split("/")[0] + "/" + time.split("/")[1];
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    logger.info(time);
//                }
//            }
//            seList.add(new SubwayEvent(stationId, stationName, lineId, lineName, eventId, category, type, content, eventTime, police, policeId, time));
//        }
//        subwayEventDao.save(seList);//batchService.batchInsert(seList);
        scheduleService.saveSubwayEvent();
        return new Response("200", "success");
    }


    @RequestMapping(value = "test", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response test(){
        scheduleService.saveLocalPoliceEvent();
        return new Response("200","success");
    }

    /**
     * 保存公交警情数据
     * @return
     */
    @RequestMapping(value = "saveBusData", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response saveBusData() {
        List<BusWarning> busWarnings = busWarningDao.findAll();
        List<BusEvent> bes = new ArrayList<>();
        for (BusWarning bw : busWarnings) {
            String id = null, stationName = null, lineId = null, eventId = null,
                    category = null, type = null, content = null, eventTime = null,
                    police = null, policeId = null, submitTime = null, dataFlowStatus = null;
            if (bw.getGJ_ID() != null)
                id = bw.getGJ_ID().trim();
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
                eventTime = bw.getAF_TIME().trim();
            if (bw.getJJDW_NAME() != null)
                police = bw.getJJDW_NAME().trim();
            if (bw.getJJDW_ID() != null)
                policeId = bw.getJJDW_ID().trim();
            if (bw.getSUBMIT_TIME() != null)
                submitTime = bw.getSUBMIT_TIME().trim();
            if (bw.getDATA_FLOW_STATUS() != null)
                dataFlowStatus = bw.getDATA_FLOW_STATUS().trim();
            bes.add(new BusEvent(id, stationName, lineId, eventId, category, type, content, eventTime, police, policeId, submitTime, dataFlowStatus));
        }
        busEventDao.save(bes);
        return new Response("200", "success");
    }

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
