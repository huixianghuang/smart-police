package com.sibat.Service;

import com.sibat.domain.origin.SubwayWarningDao;
import com.sibat.domain.other.StationDao;
import com.sibat.domain.other.SubwayEventDao;
import com.sibat.util.DateUtil;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by tgw61 on 2017/5/4.
 */
@Service
public class ScheduleService {
    Logger logger = Logger.getLogger(ScheduleService.class);

    @Test
    public void testDate() {
        logger.info(DateUtil.getLastDayPattern2("2017/05/01"));
    }

    @Autowired
    StationDao stationDao;
    @Autowired
    BatchService batchService;
    @Autowired
    SubwayEventDao subwayEventDao;
    @Autowired
    SubwayWarningDao subwayWarningDao;

    @Scheduled(cron = "0 35 02 ? * *")
    public void saveEventCategoryCount(){

    }

    @Scheduled(cron = "0 40 02 ? * *")
    public void saveEventCount(){

    }

    @Scheduled(cron = "0 45 02 ? * *")
    public void saveLocalPoliceEvent(){

    }

    // @Scheduled(cron = "0 26 02 ? * *")
//    public void saveSubwayEvent() {
//        List<SubwayEvent> seList = new ArrayList<>();
//        String currentTime = DateUtil.getCurrentTimePATTERN_yyyy_MM_dd2();
//        String lastDay = DateUtil.getLastDayPattern2(currentTime) + "%";
//        List<SubwayWarning> subwayWarningList = subwayWarningDao.findByTime(lastDay);
//        if (subwayWarningList != null & !subwayWarningList.isEmpty()) {
//            for (SubwayWarning sw : subwayWarningList) {
//                String stationId = null, stationName = null, lineId = null, lineName = null, eventId = null,
//                        category = null, type = null, content = null, eventTime = null, police = null, time = null;
//                if (sw.getDDMC() != null) {
//                    stationName = sw.getDDMC().trim();
//                    stationId = stationDao.findStationIdByStationName(stationName);
//                }
//                if (sw.getXL_ID() != null) {
//                    lineId = sw.getXL_ID().trim();
//                }
//                if (sw.getXL() != null) {
//                    lineName = sw.getXL().trim();
//                }
//                if (sw.getJCJ_ID() != null) {
//                    eventId = sw.getJCJ_ID().trim();
//                }
//                if (sw.getJQXZ() != null) {
//                    category = sw.getJQXZ().trim();
//                }
//                if (sw.getJQLB() != null) {
//                    type = sw.getJQLB().trim();
//                }
//                if (sw.getREMARK() != null) {
//                    content = sw.getREMARK().trim();
//                }
//                if (sw.getAF_TIME() != null) {
//                    eventTime = sw.getAF_TIME().trim();
//                }
//                if (sw.getJJDW_NAME() != null) {
//                    police = sw.getJJDW_NAME().trim();
//                }
//                if (sw.getAF_TIME() != null) {
//                    try {
//                        time = sw.getAF_TIME().split(" ")[0];
//                        time = time.split("/")[0] + "-" + time.split("/")[1];
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        logger.info(time);
//                    }
//                }
//                seList.add(new SubwayEvent(stationId, stationName, lineId, lineName, eventId, category, type, content, eventTime, police,policeId time));
//            }
//            batchService.batchInsert(seList);
//        }
//    }
}
