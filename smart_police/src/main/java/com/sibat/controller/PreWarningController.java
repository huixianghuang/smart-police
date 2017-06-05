package com.sibat.controller;

import com.alibaba.fastjson.JSONObject;
import com.sibat.domain.origin.PersonPath;
import com.sibat.domain.origin.PersonPathDao;
import com.sibat.domain.origin.Suspect;
import com.sibat.domain.origin.SuspectDao;
import com.sibat.domain.other.Identity;
import com.sibat.domain.other.IdentityDao;
import com.sibat.util.ConvertUtil;
import com.sibat.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by tgw61 on 2017/5/3.
 */
@RestController
@RequestMapping(value = "api/pre_warning")
public class PreWarningController {
    @Autowired
    SuspectDao suspectDao;
    @Autowired
    PersonPathDao personPathDao;
    @Autowired
    IdentityDao identityDao;
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    Logger logger = Logger.getLogger(PreWarningController.class);

    /**
     * 预警数据分析(还需多线程)
     * <p>
     * 出现的问题:
     * 1. 预警表与重点人员信息表不对应
     * 2. 预警数据库中没有相关派出所的字段,没有预警类别,预警类型,籍贯相关字段
     * 3. 在重点人员信息表中,籍贯字段大部分数据为空,比例15/19,无预警类型字段
     * 注:此问题经反馈为测试数据不完整缘故,待核实
     * 4.预警表无派出所字段,因此默认所有派出所
     * <p>
     * 选择条件：时间，派出所(未选择默认所有派出所）
     * 根据参数start，end 限定的时间选出该时间段的预警数据,
     * 1.高发预警场所列表（top10) site ：场所名，预警案件数，占比
     * 2.高危重点人员（top10）name ：人员姓名，预警案件数，占比（按出现的次数进行排序）
     * 3.24小时各个小时时段的预警案件数 time ：小时时段，预警案件数
     * 4.高发预警籍贯（TOP10，到省级）nativePlace ：省名，预警案件数，占比
     * 构建了身份证id前6位对应籍贯表,根据嫌疑人身份证判断籍贯
     * 5.各个预警类别预警数 zdryState ：预警类别，预警案件数，占比
     * 预警类别(10=A.普通关注、11=B.积极关注、12=C.重点关注,13=D.立即处置)
     * 6.各个预警类型预警案件数：类型，预警案件数，占比.无预警类型字段
     * 7.查询的信息构造成JSON数据保存在result并返回给前台
     *
     * @param start 起始时间
     * @param end   结束时间
     * @return JSON
     */
    @RequestMapping(value = "suspect_analysis", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response suspect_analysis(@RequestParam("start") String start,
                                     @RequestParam("end") String end) {
        JSONObject result = new JSONObject();
        //场所
        Map<String, Integer> site = new ConcurrentHashMap<>();
        //高危人员
        Map<String, Integer> name = new HashMap<>();
        //二十四小时
        Map<String, Integer> time = new HashMap<>();
        //籍贯
        Map<String, Integer> nativePlace = new HashMap<>();
        //预警类别数
        Map<String, Integer> zdryState = new HashMap<>();
        try {
            Timestamp startTimestamp = new Timestamp(format.parse(start).getTime());
            Timestamp endTimestamp = new Timestamp(format.parse(end).getTime());
            List<PersonPath> personPaths = personPathDao.selectByTime(startTimestamp, endTimestamp);

            if (ConvertUtil.isNotNull(personPaths)) {
                for (PersonPath obj : personPaths) {
                    dealSiteMap(site, obj);
                    dealNameMap(name, obj);
                    dealTimeMap(time, obj);
                    if (obj.getS_ID_NUMBER() != null) {
                        Suspect suspect = suspectDao.findByID(obj.getS_ID_NUMBER());
                        dealZdryState(zdryState, suspect);
                        dealNativePlace(nativePlace, obj);
                    }
                }
            }
            //排序
            site = sortMapDesc(site);
            name = sortMapDesc(name);
            nativePlace = sortMapDesc(nativePlace);

            result.put("site", site);
            result.put("name", name);
            result.put("time", time);
            result.put("zdryState", zdryState);
            result.put("nativePlace", nativePlace);
            return new Response("200", result);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Response("500", "sorry for errors!");
        }
    }

    private void dealNativePlace(Map<String, Integer> nativePlace, PersonPath obj) {
        if (obj != null && obj.getS_ID_NUMBER() != null && obj.getS_ID_NUMBER().length() > 6) {
            String brithplace = identityDao.findPlaceById(obj.getS_ID_NUMBER().substring(0, 6));
            if (brithplace != null) {
                brithplace = brithplace.substring(0, 2);
                if (nativePlace.get(brithplace) != null) {
                    int value = nativePlace.get(brithplace);
                    nativePlace.put(brithplace, ++value);
                } else {
                    nativePlace.put(brithplace, 1);
                }
            }
        }
    }

    private void dealZdryState(Map<String, Integer> zdryState, Suspect suspect) {
        if (suspect != null && suspect.getZDRYSTATE() != null) {
            String type = ConvertUtil.getPreWarnType(suspect.getZDRYSTATE());
            if (type != null) {
                if (zdryState.get(type) != null) {
                    int value = zdryState.get(type);
                    zdryState.put(type, ++value);
                } else {
                    zdryState.put(type, 1);
                }
            }
        }
    }

    private void dealSiteMap(Map<String, Integer> site, PersonPath obj) {
        if (obj.getDWMC() != null) {
            if (site.get(obj.getDWMC()) != null) {
                int value = site.get(obj.getDWMC());
                site.put(obj.getDWMC(), ++value);
            } else {
                site.put(obj.getDWMC(), 1);
            }
        }
    }

    private void dealNameMap(Map<String, Integer> name, PersonPath obj) {
        if (obj.getNAME() != null) {
            if (name.get(obj.getNAME()) != null) {
                int value = name.get(obj.getNAME());
                name.put(obj.getNAME(), ++value);
            } else {
                name.put(obj.getNAME(), 1);
            }
        }
    }

    private void dealTimeMap(Map<String, Integer> time, PersonPath obj) {
        if (obj.getCREATE_DATE() != null) {
            String key = obj.getCREATE_DATE().toString().split(" ")[1];
            key = key.split(":")[0];
            if (time.get(key) != null) {
                int value = time.get(key);
                time.put(key, ++value);
            } else {
                time.put(key, 1);
            }
        }
    }

    private Map<String, Integer> sortMapDesc(Map<String, Integer> site) {
        Map<String, Integer> result = new LinkedHashMap<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(site.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            //降序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int count = 0;
        for (Map.Entry<String, Integer> mapping : list) {
            if (count == 10)
                break;
            result.put(mapping.getKey(), mapping.getValue());
            count++;
        }
        return result;
    }
}
