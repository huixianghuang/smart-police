package com.sibat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sibat.Service.UtilService;
import com.sibat.domain.origin.BusWarningDao;
import com.sibat.domain.origin.SubwayWarningDao;
import com.sibat.domain.origin.BusWarning;
import com.sibat.domain.other.*;
import com.sibat.domain.pojo.*;
import com.sibat.util.ConvertUtil;
import com.sibat.util.DateUtil;
import com.sibat.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgw61 on 2017/4/28.
 * 注意询问并发量的大小
 */
@RestController
@RequestMapping(value = "api/event/metro")
public class WarningController {
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(WarningController.class);
    @Autowired
    SubwayWarningDao subwayWarningDao;
    @Autowired
    BusWarningDao busWarningDao;
    @Autowired
    SubwayEventDao subwayEventDao;
    @Autowired
    StationDao stationDao;
    @Autowired
    UtilService utilService;


    /**
     * 查询线网所有站点某一天的警情数量
     *
     * @return
     */
    @RequestMapping(value = "count", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response count(@RequestParam("date") String date) {
        JSONArray result = new JSONArray();
        try {
            if (date == "") {
                String currentTime = DateUtil.getCurrentMonth();
                date = DateUtil.getLastMonth(currentTime);
            }
            List<Object[]> list = subwayEventDao.selectByEventTime(date + "%");
            List<StationCount> scList = new ArrayList<>();
            if (list != null && !list.isEmpty())
                scList = ConvertUtil.castEntity(list, StationCount.class);
            JSONObject obj;
            int sum = 0;
            if (scList != null && !scList.isEmpty())
                for (StationCount sc : scList) {
                    obj = new JSONObject();
                    Station station = stationDao.findByStationName(utilService.convertStation(sc.getStationName()));
                    if (station != null) {
                        obj.put("stationId", station.getStationId());
                        obj.put("stationName", station.getStationName());
                        obj.put("lat", station.getLat());
                        obj.put("lon", station.getLon());
                        obj.put("eventCount", sc.getCount());
                        sum += sc.getCount();
                        result.add(obj);
                    }
                }
            logger.info("sum=" + sum);
        } catch (Exception e) {
            logger.error("api_count", e.getCause());
            return new Response("500", "some errors happened");
        }
        return new Response("200", result);
    }


    /**
     * 查询一个站点某一天的警情列表
     *
     * @param date 需要查询的日期，如果为空则默认为昨天
     * @return
     */
    @RequestMapping(value = "station/list", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response stationList(@RequestParam("date") String date
            , @RequestParam("station_id") String station_id) {
        JSONObject result = new JSONObject();
        try {
            if (date == "") {
                String currentTime = DateUtil.getCurrentMonth();
                date = DateUtil.getLastMonth(currentTime);
            }
            List<Object[]> objects = subwayEventDao.findByStationIdAndTime(station_id, date + "%");
            List<Event> events = new ArrayList<>();
            if (objects != null && !objects.isEmpty())
                events = ConvertUtil.castEntity(objects, Event.class);
            Station station = stationDao.findByStationId(station_id);
            if (station != null && events != null && !events.isEmpty()) {
                result.put("stationId", station.getStationId());
                result.put("stationName", station.getStationName());
                result.put("lat", station.getLat());
                result.put("lon", station.getLon());
                result.put("eventCount", events.size());
                result.put("eventList", events);
            }
        } catch (Exception e) {
            logger.error("api_station/list", e.getCause());
            return new Response("500", "some errors happened");
        }
        if (result.isEmpty())
            return new Response("404", "not found");
        else
            return new Response("200", result);

    }

    @Autowired
    EventCategoryCountDao eventCategoryCountDao;
    @Autowired
    EventCountDao eventCountDao;
    @Autowired
    LocalPoliceEventDao localPoliceEventDao;

    /**
     * 当月警情
     * 按类型 派出所 总数 进行警情显示
     * @param date
     * @return
     */
    @RequestMapping(value = "statistic/month", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response statistic_month(@RequestParam("date") String date) {
        JSONObject result = new JSONObject();
        try {
            if (date == "")
                date = DateUtil.getCurrentMonth();
            String lastMonth = DateUtil.getLastMonth(date);
            String lastYear = DateUtil.getLastYear(date);

            Integer currentMonthCount = eventCountDao.findByTime(date);
            Integer lastMonthCount = eventCountDao.findByTime(lastMonth);
            Integer lastYearCount = eventCountDao.findByTime(lastYear);

            List<Object[]> CategoryCounts = eventCategoryCountDao.findByTime(date);
            List<CategoryCount> eventCategoryCountList = new ArrayList<>();
            if (!CategoryCounts.isEmpty())
                eventCategoryCountList = ConvertUtil.castEntity(CategoryCounts, CategoryCount.class);

            List<Object[]> localPoliceEvents = localPoliceEventDao.findByTime(date);
            List<LocalPoliceEventCount> localPoliceEventList = new ArrayList<>();
            if (!localPoliceEvents.isEmpty())
                localPoliceEventList = ConvertUtil.castEntity(localPoliceEvents, LocalPoliceEventCount.class);

            result.put("currentMonth", currentMonthCount);
            result.put("lastMonth", lastMonthCount);
            result.put("lastYear", lastYearCount);
            result.put("category", eventCategoryCountList);
            result.put("department", localPoliceEventList);

            if (result.isEmpty())
                return new Response("404", "not found");
            else
                return new Response("200", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("500", "some errors happened");
        }
    }



    /**
     * @param warningCondition
     * @return
     */
    @RequestMapping(value = "select", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response select(WarningCondition warningCondition) {
        return new Response("404", "failed");
    }

    /**
     * 公交站点警情
     * 站点ID,站点经纬度，警情数
     *
     * @return
     */
    @RequestMapping(value = "bus_station", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response warning_bus_station() {
        //String currentTime = DateUtil.getCurrentTimeyyyy_MM_dd();
        String currentTime = "2012/01/06";
        List<BusWarning> busWarningList = busWarningDao.findByAF_TIME(currentTime + " 00:00:00.000", currentTime + " 23:59:59.000");
        if (busWarningList != null && !busWarningList.isEmpty()) {
            return new Response("200", busWarningList);
        } else {
            return new Response("404", "failed");
        }
    }

    /**
     * 公交线路警情
     * 站点ID,站点经纬度，线路ID,警情数
     *
     * @return
     */
    @RequestMapping(value = "bus_line", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response warning_bus_line() {
        //String currentTime = DateUtil.getCurrentTimeyyyy_MM_dd();
        JSONObject result = new JSONObject();
        String currentTime = "2012/01/06";
        List<String> lineIdList = busWarningDao.findByAF_TIMEGroupByLine(currentTime + " 00:00:00.000", currentTime + " 23:59:59.000");
        if (lineIdList != null && !lineIdList.isEmpty()) {
            for (String lineId : lineIdList) {
                if (lineId != null) {
                    List<BusWarning> busWarningList = busWarningDao.findByAF_TIMEAndLineId(currentTime + " 00:00:00.000", currentTime + " 23:59:59.000", lineId);
                    if (busWarningList != null && !busWarningList.isEmpty()) {
                        result.put(lineId.trim(), busWarningList);
                    }
                }
            }
            return new Response("200", result);
        } else {
            return new Response("404", "failed");
        }
    }

    /**
     * 警情列表
     * 全部警情表字段，提供匹配站点ID的警情列表
     *
     * @param station_id
     * @return
     */
    @RequestMapping(value = "list", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response warning_lsit(@RequestParam("station_id") String station_id) {
        return new Response("404", "failed");
    }

    /**
     * 警情概况
     * 本月警情总数，环比，同比
     *
     * @return
     */
    @RequestMapping(value = "survey", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response warning_survey() {
        return new Response("404", "failed");
    }

    /**
     * 警情类型
     * 本月刑事警情，治安警情，其他数量
     *
     * @return
     */
    @RequestMapping(value = "type", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response warning_type() {
        return new Response("404", "failed");
    }

    /**
     * 警情类别
     * 本月两枪，扒窃，盗窃，诈骗，故意伤害，遗失，求助，其他，故意杀人，抢劫，抢夺，重复报警数量
     *
     * @return
     */
    @RequestMapping(value = "classes", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response warning_classes() {
        return new Response("404", "failed");
    }

    /**
     * 派出所警情
     * 本月派出所ID,警情数
     *
     * @return
     */
    @RequestMapping(value = "from_local_police_station", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response warning_from_local_police_station() {
        return new Response("404", "failed");
    }
}
