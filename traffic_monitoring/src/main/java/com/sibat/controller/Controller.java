package com.sibat.controller;

import com.alibaba.fastjson.JSONObject;
import com.sibat.domain.*;
import com.sibat.service.DBService;
import com.sibat.util.DateUtil;
import com.sibat.util.Response;
import org.jfaster.mango.operator.Mango;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/15.
 */
@RestController
@RequestMapping(value = "api/")
public class Controller {
    @Autowired
    TrafficCongestionDao trafficCongestionDao;
    @Autowired
    DBService dbService;

    /**
     * 拥堵系数
     *
     * 计算:时间粒度1min
     * 1.192.168.40.130 subway数据库的subway表得到当前时间各区间flow
     * 2.每个flow相加求和得到sum ,每个flow/sum得到权重q
     * 3.flow/fullFlow得到各区间的拥堵系数f
     * 4.总拥堵系数各区间拥堵系数f*权重q 保存到自有主数据库中
     * 5.查询参数date格式yyyy-MM-dd 得到当日拥堵系数按时间降序排列,第一个为实时拥堵系数
     *
     * @param date yyyy-MM-dd
     * @return
     */
    @RequestMapping(value = "traffic_congestion", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response count(@RequestParam("date") String date) {
        JSONObject result = new JSONObject();
        List<TrafficCongestion> tcs = trafficCongestionDao.select(date + "%");
        if (tcs != null && !tcs.isEmpty()) {
            result.put("current", tcs.get(0));
            result.put("history", tcs);
        }
        return new Response("200", result);
    }

    /**
     * 实时站点累计进出站
     *
     * 查询allin_*表
     * @return
     */
    @RequestMapping(value = "traffic_flow_in_out", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response traffic_flow_in_out() {
        String date = DateUtil.getCurrentTimeyyyy_MM_dd();
        JSONObject result = new JSONObject();
        Mango mango = dbService.getMango130();
        DBService.AllInDao allInDao = mango.create(DBService.AllInDao.class);
        DBService.AllOutDao allOutDao = mango.create(DBService.AllOutDao.class);
        List<SumIn> in = allInDao.findByTime(date);
        List<SumOut> out = allOutDao.findByTime(date);
        if (in != null && !in.isEmpty()
                && out != null && !out.isEmpty()) {
            result.put("allIn", in);
            result.put("allout", out);
        }
        return new Response("200", result);
    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String parseDateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat _df = new SimpleDateFormat(pattern);
        return _df.format(date);
    }

    public static String getCurrentTimeString() {
        return parseDateToString(Calendar.getInstance().getTime(),
                "yyyy-MM-dd HH:mm");
    }

    @Autowired
    FlowPredictedDao flowPredictedDao;
    @Autowired
    PassengerPredictedDao passengerPredictedDao;

    /**
     * 站点客流预测（进站+出站)
     *
     * 预测区间客流
     * 1.数据计算由戴浩负责存储在PassengerPredicted表
     * 2.根据站点id,当前时间,后35min时间查询按时间降序排列获得list
     * 3.取第一个数值
     *
     * @param station
     * @return
     */
    @RequestMapping(value = "predict_traffic_flow_in_out", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response predict_traffic_flow_in_out(@RequestParam("station") String station) {
        String current = getCurrentTimeString();
        String end = DateUtil.getNext35Min(current);
        List<PassengerPredicted> list = passengerPredictedDao.findByStatus(station, current, end);
        if (list != null && !list.isEmpty())
            return new Response("200", list.get(0));
        else
            return new Response("200", new JSONObject());
    }

    /**
     * 预测区间客流
     * 1.数据计算由戴浩负责存储在FlowPredicted表
     * 2.根据起始站点id,当前时间,后35min时间查询按时间降序排列获得list
     * 3.取第一个数值
     *
     * @param start 站点id
     * @param end 站点id
     * @return
     */
    @RequestMapping(value = "predict_traffic_flow", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response predict_traffic_flow(@RequestParam("start") String start,
                                         @RequestParam("end") String end) {
        String currentTime = getCurrentTimeString();
        String endTime = DateUtil.getNext35Min(currentTime);
        List<FlowPredicted> result = flowPredictedDao.findByStatus(start, end, currentTime, endTime);
        if (result != null && !result.isEmpty())
            return new Response("200", result.get(0));
        else
            return new Response("200", new JSONObject());

    }
}
