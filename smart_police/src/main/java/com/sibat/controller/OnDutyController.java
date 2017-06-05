package com.sibat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sibat.domain.origin.*;
import com.sibat.domain.pojo.Duty;
import com.sibat.util.DateUtil;
import com.sibat.util.Response;
import org.apache.log4j.Logger;
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
import java.util.*;

/**
 * Created by tgw61 on 2017/5/23.
 */
@RestController
@RequestMapping("/api/police")
public class OnDutyController {
    Logger logger = Logger.getLogger(OnDutyController.class);
    @Autowired
    TDtJlbPcsDao tDtJlbPcsDao;
    @Autowired
    TDtJlbDao tDtJlbDao;
    @Autowired
    ViewPoliceDataDao viewPoliceDataDao;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 警员值班API
     *
     * 返回警员指定日期date值班情况
     *
     * 1. 根据date得到所在周时间
     * 2. 有police，data两个参数在TDtJlbPcs上获得值班领导记录，在TDtJlb获得值班警员记录
     * 3. 获得的记录构造成JSON格式数据存储在result中并返回给前台
     * 注警员值班记录有重复,需要去重
     *
     * @param date 当date为空时,取当前时间yyyy-MM-dd
     * @param police
     * @return
     */
    @RequestMapping(value = "duty", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response duty(@RequestParam("date") String date,
                         @RequestParam("police") String police) {
        JSONArray result = new JSONArray();
        JSONObject obj;
        try {
            if (date == "")
                date = DateUtil.getCurrentTimeyyyy_MM_dd();
            List<String> times = DateUtil.getDays(date.toString());
            for (String time : times) {
                Timestamp da = new Timestamp(format.parse(time + " 00:00:00").getTime());
                List<TDtJlbPcs> leader = tDtJlbPcsDao.findByPoliceAndTime(police, da);
                List<TDtJlb> duty = tDtJlbDao.findByPoliceAndTime(police, da);
                obj = getDutyResuslt(duty);
                getLeaderResult(obj, time, leader);
                if (!obj.isEmpty())
                    result.add(obj);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return new Response("500", "some errors happened");
        }
        return new Response("200", result);
    }

    private void getLeaderResult(JSONObject obj, String time, List<TDtJlbPcs> leader) throws ParseException {
        if (!leader.isEmpty()) {
            obj.put("date", time);
            String weekNo = getWeekNo(time);
            obj.put("time", weekNo);
            StringBuffer leaderSb = new StringBuffer(leader.get(0).getLD_ZB());
            leaderSb.append("/").append(leader.get(0).getLD_FB());
            obj.put("leader", leaderSb.toString());
            obj.put("duty", leader.get(0).getZBMJ());
        }
    }

    private JSONObject getDutyResuslt(List<TDtJlb> duty) {
        JSONObject obj=new JSONObject();
        Map<String, String> duty_map_mor = new HashMap<>();
        Map<String, String> duty_map_eve = new HashMap<>();
        if (!duty.isEmpty()) {
            JSONArray detail = new JSONArray();
            for (TDtJlb tDtJlb : duty) {
                if (tDtJlb.getPB_TYPE() == 0) {
                    putStationDutyMap(duty_map_mor, tDtJlb);
                } else if(tDtJlb.getPB_TYPE() == 1){
                    putStationDutyMap(duty_map_eve, tDtJlb);
                }
            }
            JSONObject stationDuty;
            for (Map.Entry<String, String> entry : duty_map_mor.entrySet()) {
                stationDuty = new JSONObject();
                stationDuty.put("station", entry.getKey());
                stationDuty.put("mor", entry.getValue());
                stationDuty.put("eve", duty_map_eve.get(entry.getKey()));
                detail.add(stationDuty);
            }
            obj.put("detail", detail);
        }
        return obj;
    }

    DateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

    private String getWeekNo(String time) throws ParseException {
        String weekNo;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(yyyy_MM_dd.parse(time));
        int now = calendar.get(Calendar.DAY_OF_WEEK);
        switch (now) {
            case 1:
                weekNo = "星期日";
                break;
            case 2:
                weekNo = "星期一";
                break;
            case 3:
                weekNo = "星期二";
                break;
            case 4:
                weekNo = "星期三";
                break;
            case 5:
                weekNo = "星期四";
                break;
            case 6:
                weekNo = "星期五";
                break;
            case 7:
                weekNo = "星期六";
                break;
            default:
                weekNo = "出错了";
        }
        return weekNo;
    }

    /**
     * 设立站点-值班警员对应map
     * @param duty_map_mor
     * @param tDtJlb
     */
    private void putStationDutyMap(Map<String, String> duty_map_mor, TDtJlb tDtJlb) {
        if (tDtJlb.getZQNAME()!=null&& duty_map_mor.get(tDtJlb.getZQNAME()) != null
                && !duty_map_mor.get(tDtJlb.getZQNAME()).contains(tDtJlb.getRYNAME())) {
            StringBuffer value = new StringBuffer(duty_map_mor.get(tDtJlb.getZQNAME()));
            value.append(",").append(tDtJlb.getRYNAME());
            duty_map_mor.put(tDtJlb.getZQNAME(), value.toString());
        } else {
            duty_map_mor.put(tDtJlb.getZQNAME(), tDtJlb.getRYNAME());
        }
    }

    private Map<String, String> getDutyMap(List<Duty> list) {
        Map<String, String> dutyMap = new HashMap<>();
        for (Duty mor : list) {
            if (dutyMap.get(mor.getStationName()) == null) {
                dutyMap.put(mor.getStationName(), mor.getPoliceManName());
            } else {
                StringBuffer value = new StringBuffer(dutyMap.get(mor.getStationName()));
                value.append(",").append(mor.getPoliceManName());
                dutyMap.put(mor.getStationName(), value.toString());
            }
        }
        return dutyMap;
    }

    /**
     * 警员查询API
     *
     * 查询派出所警员信息/查询全部警员信息
     * localhost:8997/api/event/metro/search_police
     * localhost:8997/api/event/metro/search_police?deptName=福田公交派出所
     *
     * 1.根据参数viewPoliceData中的属性deptName查询派出所警员信息
     * 2.deptName为空时是全员查询，不为空则按照deptName指定的派出所查询
     * 3.查询的信息构造成分页JSON数据保存在polices中并返回给前台
     *
     * @param viewPoliceData 警员信息库，包含属性deptName
     * @param page 页码，默认为0
     * @param size 每一页大小，默认为10
     * @return JSON
     */
    @RequestMapping(value = "search_police", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response search_police(ViewPoliceData viewPoliceData,
                                  @RequestParam(value = "page", defaultValue = "0") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        Sort sort = new Sort(Sort.Direction.ASC, "userCode");
        Pageable pageable = new PageRequest(page, size, sort);
        Specification<ViewPoliceData> spec = getPolice(viewPoliceData);
        Page<ViewPoliceData> polices = viewPoliceDataDao.findAll(spec, pageable);
        return new Response("200", polices);
    }

    private Specification<ViewPoliceData> getPolice(ViewPoliceData viewPoliceData) {
        return new Specification<ViewPoliceData>() {
            @Override
            public Predicate toPredicate(Root<ViewPoliceData> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (null != viewPoliceData.getDeptCode()) {
                    predicate = cb.equal(root.get("deptCode").as(String.class), viewPoliceData.getDeptCode());
                } else if (null != viewPoliceData.getDeptName()) {
                    predicate = cb.equal(root.get("deptName").as(String.class), viewPoliceData.getDeptName());
                } else if (null != viewPoliceData.getUserCode()) {
                    predicate = cb.equal(root.get("userCode").as(String.class), viewPoliceData.getUserCode());
                }
                return predicate;
            }
        };
    }

    /**
     * 警员模糊查询
     *
     * 1.根据参数content模糊搜索警员信息 涉及:userCode警号,name姓名,mobilePhone电话
     * 2.查询的信息构造成分页JSON数据保存在polices中并返回给前台
     *
     * @param content 搜索文本
     * @param page 页码，默认为0
     * @param size 每一页大小，默认为10
     * @return JSON
     */
    @RequestMapping(value = "fuzzy_search_police", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response fuzzy_search_police(@RequestParam("content") String content,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        Sort sort = new Sort(Sort.Direction.ASC, "userCode");
        Pageable pageable = new PageRequest(page, size, sort);
        Specification<ViewPoliceData> spec = getFuzzyPolice(content);
        Page<ViewPoliceData> polices = viewPoliceDataDao.findAll(spec, pageable);
        return new Response("200", polices);
    }

    private Specification<ViewPoliceData> getFuzzyPolice(String content) {
        return new Specification<ViewPoliceData>() {
            @Override
            public Predicate toPredicate(Root<ViewPoliceData> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                StringBuffer sb = new StringBuffer("%");
                sb.append(content).append("%");
                Predicate predicate = cb.disjunction();
                if (null != sb) {
                    predicate = cb.or(predicate, cb.like(root.get("userCode").as(String.class), sb.toString()));
                    predicate = cb.or(predicate, cb.like(root.get("name").as(String.class), sb.toString()));
                    predicate = cb.or(predicate, cb.like(root.get("mobilePhone").as(String.class), sb.toString()));
                }
                return predicate;
            }
        };
    }
}
