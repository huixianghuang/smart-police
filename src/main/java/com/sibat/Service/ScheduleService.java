package com.sibat.Service;

import com.sibat.domain.origin.SubwayWarning;
import com.sibat.domain.origin.SubwayWarningDao;
import com.sibat.domain.other.*;
import com.sibat.domain.pojo.LikeMap;
import com.sibat.util.ConvertUtil;
import com.sibat.util.DateUtil;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    UtilService utilService;
    @Autowired
    EventCategoryCountDao eventCategoryCountDao;
    @Autowired
    EventCountDao eventCountDao;
    @Autowired
    LocalPoliceEventDao localPoliceEventDao;


    @Scheduled(cron = "0 35 02 ? * *")
    public void saveEventCategoryCount() {
        List<EventCategoryCount> ecc = new ArrayList<>();
        try {
            String date = DateUtil.getCurrentMonth();
            //String date = "2017/04";
            List<LikeMap> lms = null;
            List<Object[]> array = subwayEventDao.countEventCategory(date);
            if (array != null && !array.isEmpty())
                lms = ConvertUtil.castEntity(array, LikeMap.class);
            if (lms != null && !lms.isEmpty()) {
                List<EventCategoryCount> eccs = eventCategoryCountDao.findObjyTime(date);
                eventCategoryCountDao.delete(eccs);
                logger.info("删除EventCategoryCount成功" + date);
                for (LikeMap lm : lms) {
                    ecc.add(new EventCategoryCount(lm.getKey(), lm.getValue().toString(), date, new Date(), "station"));
                }
                eventCategoryCountDao.save(ecc);
                logger.info("更新EventCategoryCount成功" + date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 40 02 ? * *")
    public void saveEventCount() {
        try {
            String date = DateUtil.getCurrentMonth();
            //String date = "2017/04";
            Integer eventCount = subwayEventDao.countEvent(date);
            if (eventCount != null) {
                List<EventCount> ecs = eventCountDao.findObjByTime(date);
                eventCountDao.delete(ecs);
                logger.info("删除EventCount成功" + date);
                eventCountDao.save(new EventCount(date, eventCount.intValue(), new Date(), "station"));
                logger.info("更新EventCount成功" + date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 45 02 ? * *")
    public void saveLocalPoliceEvent() {
        List<LocalPoliceEvent> ecc = new ArrayList<>();
        try {
            String date = DateUtil.getCurrentMonth();
            //String date = "2017/04";
            List<LikeMap> lms = null;
            List<Object[]> array = subwayEventDao.countLocalPoliceEvent(date);
            if (array != null && !array.isEmpty())
                lms = ConvertUtil.castEntity(array, LikeMap.class);
            if (lms != null && !lms.isEmpty()) {
                List<LocalPoliceEvent> eccs = localPoliceEventDao.findObjByTime(date);
                localPoliceEventDao.delete(eccs);
                logger.info("删除LocalPoliceEvent成功" + date);
                for (LikeMap lm : lms) {
                    String police = subwayEventDao.findPoliceByPoliceId(lm.getKey());
                    ecc.add(new LocalPoliceEvent(lm.getKey(), police, lm.getValue().toString(), date, new Date(), "station"));
                }
                localPoliceEventDao.save(ecc);
                logger.info("更新EventCategoryCount成功" + date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Scheduled(cron = "0 26 02 ? * *")
    public void saveSubwayEvent() {
        List<SubwayEvent> seList = new ArrayList<>();
        String currentTime = DateUtil.getCurrentTimePATTERN_yyyy_MM_dd2();
        String lastDay = DateUtil.getLastDayPattern2(currentTime) + "%";
        //String lastDay1 = "2017/04/10%";
        List<SubwayWarning> subwayWarningList = subwayWarningDao.findByTime(lastDay);
        if (subwayWarningList != null & !subwayWarningList.isEmpty()) {
            for (SubwayWarning sw : subwayWarningList) {
                String stationId = null, stationName = null, lineId = null, lineName = null, eventId = null,
                        category = null, type = null, content = null, eventTime = null, police = null, policeId = null, time = null;
//            if(sw.getDDMC()!=null){
//                stationId=sw.getDDMC();
//            }
                if (sw.getDDMC() != null) {
                    stationName = utilService.convertStation(sw.getDDMC().trim());
                }
                if (sw.getXL_ID() != null) {
                    lineId = sw.getXL_ID().trim();
                }
                if (sw.getXL() != null) {
                    lineName = sw.getXL().trim();
                }
                if (sw.getJCJ_ID() != null) {
                    eventId = sw.getJCJ_ID().trim();
                }
                if (sw.getJQXZ() != null) {
                    category = sw.getJQXZ().trim();
                }
                if (sw.getJQLB() != null) {
                    type = sw.getJQLB().trim();
                }
                if (sw.getREMARK() != null) {
                    content = sw.getREMARK().trim();
                }
                if (sw.getAF_TIME() != null) {
                    eventTime = sw.getAF_TIME().trim();
                }

                if (sw.getJJDW_NAME() != null) {
                    police = sw.getJJDW_NAME().trim();
                }
                if (sw.getJJDW_ID() != null) {
                    policeId = sw.getJJDW_ID().trim();
                }

                if (sw.getAF_TIME() != null) {
                    try {
                        time = sw.getAF_TIME().split(" ")[0];
                        time = time.split("/")[0] + "/" + time.split("/")[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        logger.info(time);
                    }
                }
                seList.add(new SubwayEvent(stationId, stationName, lineId,
                        lineName, eventId, category,
                        type, content, eventTime, police, policeId, time));
            }
            subwayEventDao.save(seList);
        }
    }
}
