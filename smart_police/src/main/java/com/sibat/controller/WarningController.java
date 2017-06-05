package com.sibat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sibat.Service.UtilService;
import com.sibat.domain.origin.*;
import com.sibat.domain.other.*;
import com.sibat.domain.pojo.*;
import com.sibat.util.ConvertUtil;
import com.sibat.util.DateUtil;
import com.sibat.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    SubwayEventDao subwayEventDao;
    @Autowired
    StationDao stationDao;
    @Autowired
    UtilService utilService;
    @Autowired
    BusEventDao busEventDao;
    @Autowired
    PoliceDao policeDao;
    @Autowired
    EventCategoryCountDao eventCategoryCountDao;
    @Autowired
    EventCountDao eventCountDao;
    @Autowired
    LocalPoliceEventDao localPoliceEventDao;

    /**
     * 查询地铁线网所有站点某一天的警情数量

     * 1.根据传入的参数date，以站点名分组，查询站点在date这一个月的警情数量 地铁站点名:eventCount
     * 2.根据查询list遍历得到每个站点名,查询地铁站点信息表获得地铁站点相关信息
     *
     * @param date 格式 yyyy-MM，需要查询的日期，如果为空则默认为上月
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
            if (list != null && !list.isEmpty()) {
                if (list.get(0)[0] == null) list.remove(list.get(0));
                scList = ConvertUtil.castEntity(list, StationCount.class);
            }
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
            e.printStackTrace();
            logger.error("api_count", e.getCause());
            return new Response("500", "some errors happened");
        }
        return new Response("200", result);
    }

    /**
     * 查询一个站点某一天的警情列表
     *
     * 1.根据传入的参数station_id，date查询站点在date这个月的警情信息
     *  包括obj.eventId,obj.category,obj.type,obj.content,obj.eventTime
     * 2.然后再通过station_id获取站点详细信息
     * 3.组合成json返回
     * @param date 格式 yyyy-MM 需要查询的日期，如果为空则默认为上个月
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


    /**
     * 查询当月警情
     * 按类型，派出所进行警情总数显示
     *
     * 1.根据传入的参数date获取当前月份,得到上月,上年时间
     * 2.然后根据上述三个时间在event_count,event_category_count,local_police_event3个表进行查询
     * 得到警情统计,警情类型统计，派出所警情统计
     *3.派出所警情统计应符合15个公交派出所标准,最后需做一个filter
     * @param date yyyy-MM
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
            List<LocalPoliceEventCount> localPoliceEventList;

            List<LocalPoliceEventCount> departmentList = new ArrayList<>();
            if (!localPoliceEvents.isEmpty()) {
                localPoliceEventList = ConvertUtil.castEntity(localPoliceEvents, LocalPoliceEventCount.class);
                //对police做筛选
                filterPolice(localPoliceEventList, departmentList);
            }

            result.put("currentMonth", currentMonthCount);
            result.put("lastMonth", lastMonthCount);
            result.put("lastYear", lastYearCount);
            result.put("category", eventCategoryCountList);
            result.put("department", departmentList);

            if (result.isEmpty())
                return new Response("404", "not found");
            else
                return new Response("200", result);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("500", "some errors happened");
        }
    }

    private void filterPolice(List<LocalPoliceEventCount> localPoliceEventList, List<LocalPoliceEventCount> departmentList) {
        for (LocalPoliceEventCount obj : localPoliceEventList) {
            if (obj.getPolice().contains("公交") && !"公交分局".equals(obj.getPolice())
                    && !"公交分局执法专业队".equals(obj.getPolice())
                    && !"公交分局刑警大队".equals(obj.getPolice())) {
                departmentList.add(obj);
            }
        }
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
//        String currentTime = "2012/01/06";
//        List<BusWarning> busWarningList = busWarningDao.findByAF_TIME(currentTime + " 00:00:00.000", currentTime + " 23:59:59.000");
//        if (busWarningList != null && !busWarningList.isEmpty()) {
//            return new Response("200", busWarningList);
//        } else {
//            return new Response("404", "failed");
//        }
        return new Response("404", "failed");
    }

    /**
     * 公交线路警情
     * 站点ID,站点经纬度，线路ID,警情数
     *
     * @return
     */
    @RequestMapping(value = "bus_line", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response warning_bus_line() {
//        //String currentTime = DateUtil.getCurrentTimeyyyy_MM_dd();
//        JSONObject result = new JSONObject();
//        String currentTime = "2012/01/06";
//        List<String> lineIdList = busWarningDao.findByAF_TIMEGroupByLine(currentTime + " 00:00:00.000", currentTime + " 23:59:59.000");
//        if (lineIdList != null && !lineIdList.isEmpty()) {
//            for (String lineId : lineIdList) {
//                if (lineId != null) {
//                    List<BusWarning> busWarningList = busWarningDao.findByAF_TIMEAndLineId(currentTime + " 00:00:00.000", currentTime + " 23:59:59.000", lineId);
//                    if (busWarningList != null && !busWarningList.isEmpty()) {
//                        result.put(lineId.trim(), busWarningList);
//                    }
//                }
//            }
//            return new Response("200", result);
//        } else {
//            return new Response("404", "failed");
//        }
        // TODO: 2017/6/2
        return new Response("404", "failed");
    }

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    /**
     * 警情分析
     *
     * 统计维度
     * 地铁/公交：派出所，线路，站点，各警情类型，各警情性质
     *
     * 1.通过获取参数warningAnalysis的属性type分地铁（dt），公交(gj)类型统计警情
     * 2.限定条件为类型type，起止时间start,end
     *
     *
     * @param warningAnalysis
     * 属性包含start,end，type，dimension
     * start 分析时段起, end 分析时段止, type 地铁/公交, dimension 分析纬度
     *
     * dimension包含police，line，station，jqlb，jqxz
     * police 派出所，line 线路，station 站点，jqlb 各警情类型，jqxz 各警情性质
     *
     * @return
     */
    @RequestMapping(value = "analysis", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response analysis(WarningAnalysis warningAnalysis) {
        JSONObject result = new JSONObject();
        String start = warningAnalysis.getStart();
        String end = warningAnalysis.getEnd();
        try {
            if ("dt".equals(warningAnalysis.getType())) {
                if ("police".equals(warningAnalysis.getDimension())) {
                    analyByPoliceSubway(result, start, end);
                } else if ("line".equals(warningAnalysis.getDimension())) {
                    analyByLineSubway(result, start, end);
                } else if ("station".equals(warningAnalysis.getDimension())) {
                    analyByStationSubway(result, start, end);
                } else if ("jqlb".equals(warningAnalysis.getDimension())) {
                    analyByJqlbSubway(result, start, end);
                } else if ("jqxz".equals(warningAnalysis.getDimension())) {
                    analyByJqxzSubway(result, start, end);
                }
            } else if ("gj".equals(warningAnalysis.getType())) {
                if ("police".equals(warningAnalysis.getDimension())) {
                    analyByPoliceBus(result, start, end);
                } else if ("line".equals(warningAnalysis.getDimension())) {
                    analyByLineBus(result, start, end);
                } else if ("station".equals(warningAnalysis.getDimension())) {
                    analyByStationBus(result, start, end);
                } else if ("jqlb".equals(warningAnalysis.getDimension())) {
                    analyByJqlbBus(result, start, end);
                } else if ("jqxz".equals(warningAnalysis.getDimension())) {
                    analyByJqxzBus(result, start, end);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response("200", result);
    }

    private void analyByJqxzBus(JSONObject result, String start, String end) throws Exception {
        List<Object[]> counts;
        counts = busEventDao.countByCategory(start, end);
        List<LikeMap> likeMapList = ConvertUtil.castEntity(counts, LikeMap.class);
        for (LikeMap obj : likeMapList) {
            if (ConvertUtil.isJqxzBus(obj.getKey()))
                result.put(obj.getKey(), obj.getValue());
        }
    }

    private void analyByJqlbBus(JSONObject result, String start, String end) throws Exception {
        List<Object[]> counts;
        counts = busEventDao.countByType(start, end);
        List<LikeMap> likeMapList = ConvertUtil.castEntity(counts, LikeMap.class);
        for (LikeMap obj : likeMapList) {
            if (ConvertUtil.isJqlb(obj.getKey()))
                result.put(obj.getKey(), obj.getValue());
        }
    }

    private void analyByStationBus(JSONObject result, String start, String end) throws Exception {
        List<Object[]> counts;
        counts = busEventDao.countByStationName(start, end);
        List<LikeMap> likeMapList = ConvertUtil.castEntity(counts, LikeMap.class);
        for (LikeMap obj : likeMapList) {
            result.put(obj.getKey(), obj.getValue());
        }
    }

    private void analyByLineBus(JSONObject result, String start, String end) throws Exception {
        List<Object[]> counts;
        counts = busEventDao.countByLineId(start, end);
        List<LikeMap> likeMapList = ConvertUtil.castEntity(counts, LikeMap.class);
        for (LikeMap obj : likeMapList) {
            result.put(obj.getKey(), obj.getValue());
        }
    }

    private void analyByPoliceBus(JSONObject result, String start, String end) throws Exception {
        List<Object[]> counts;
        counts = busEventDao.countByPolice(start, end);
        List<LikeMap> likeMapList = ConvertUtil.castEntity(counts, LikeMap.class);
        for (LikeMap obj : likeMapList) {
            String polcie_name = policeDao.fingPoliceNameByPoliceId(obj.getKey());
            if (polcie_name != null)
                result.put(polcie_name, obj.getValue());
        }
    }

    private void analyByJqxzSubway(JSONObject result, String start, String end) throws Exception {
        List<Object[]> counts;
        counts = subwayEventDao.countByCategory(start, end);
        List<LikeMap> likeMapList = ConvertUtil.castEntity(counts, LikeMap.class);
        for (LikeMap obj : likeMapList) {
            if (ConvertUtil.isJqxzSubway(obj.getKey()))
                result.put(obj.getKey(), obj.getValue());
        }
    }

    private void analyByJqlbSubway(JSONObject result, String start, String end) throws Exception {
        List<Object[]> counts;
        counts = subwayEventDao.countByType(start, end);
        List<LikeMap> likeMapList = ConvertUtil.castEntity(counts, LikeMap.class);
        for (LikeMap obj : likeMapList) {
            if (ConvertUtil.isJqlb(obj.getKey()))
                result.put(obj.getKey(), obj.getValue());
        }
    }

    private void analyByStationSubway(JSONObject result, String start, String end) throws Exception {
        List<Object[]> counts;
        counts = subwayEventDao.countByStationName(start, end);
        List<LikeMap> likeMapList = ConvertUtil.castEntity(counts, LikeMap.class);
        for (LikeMap obj : likeMapList) {
            result.put(obj.getKey(), obj.getValue());
        }
    }

    private void analyByLineSubway(JSONObject result, String start, String end) throws Exception {
        List<Object[]> counts;
        counts = subwayEventDao.countByLineId(start, end);
        List<LikeMap> likeMapList = ConvertUtil.castEntity(counts, LikeMap.class);
        for (LikeMap obj : likeMapList) {
            result.put(obj.getKey(), obj.getValue());
        }
    }

    private void analyByPoliceSubway(JSONObject result, String start, String end) throws Exception {
        List<Object[]> counts;
        counts = subwayEventDao.countByPolice(start, end);
        List<LikeMap> likeMapList = ConvertUtil.castEntity(counts, LikeMap.class);
        for (LikeMap obj : likeMapList) {
            String polcie_name = policeDao.fingPoliceNameByPoliceId(obj.getKey());
            if (polcie_name != null)
                result.put(polcie_name, obj.getValue());
        }
    }

    /**
     * 未使用
     *
     * @param warningAnalysis
     * @return
     */
    @RequestMapping(value = "analysis_count", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response analysis_count(WarningAnalysis warningAnalysis) {
//        JSONObject result = new JSONObject();
//        String start = warningAnalysis.getStart();
//        String end = warningAnalysis.getEnd();
//        try {
//            Timestamp startTimestamp = new Timestamp(format.parse(start + " 00:00:00").getTime());
//            Timestamp endTimestamp = new Timestamp(format.parse(end + " 00:00:00").getTime());
//            //警情性质
//            int busType = busWarningDao.count(startTimestamp, endTimestamp);
//            int subwayType = jqfxJqlrDtDao.count(startTimestamp, endTimestamp);
//            //警情类型
//            List<Object[]> dtClass = jqfxJqlrDtDao.countByType(startTimestamp, endTimestamp);
//            List<Object[]> gjClass = busWarningDao.countByType(startTimestamp, endTimestamp);
//            //审批状态
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return new Response("200", "success");
    }


}
