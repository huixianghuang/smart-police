package com.sibat.controller;

import com.alibaba.fastjson.JSONObject;
import com.sibat.domain.origin.PersonPath;
import com.sibat.domain.origin.PersonPathDao;
import com.sibat.domain.origin.Suspect;
import com.sibat.domain.origin.SuspectDao;
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
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

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
     * 6.各个预警类型预警案件数 zdryTYPE ：类型，预警案件数，占比.无预警类型字段
     * 类型类别(“扒窃”1、“盗窃”2、“诈骗”3、“抢夺”4、“抢劫”5、“涉毒”6、“涉黑”7、“其他”8.)
     * 7.查询的信息构造成JSON数据保存在result并返回给前台
     *
     * @param start 起始时间
     * @param end   结束时间
     * @param dept_id 派出所名
     * @return JSON
     */
    @RequestMapping(value = "suspect_analysis", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response suspect_analysis(@RequestParam("start") String start,
                                     @RequestParam("end") String end,
                                     @RequestParam(value = "dept_id", defaultValue = "all") String dept_id) {
        JSONObject result = new JSONObject();
        //场所
        Map<String, Integer> site = new ConcurrentHashMap<>();
        //高危人员
        Map<String, Integer> name = new ConcurrentHashMap<>();
        //二十四小时
        Map<String, Integer> time = new ConcurrentHashMap<>();
        //籍贯
        Map<String, Integer> nativePlace = new ConcurrentHashMap<>();
        //预警类别数
        Map<String, Integer> zdryState = new ConcurrentHashMap<>();
        //预警类型预警案件数
        Map<String, Integer> zdryType = new ConcurrentHashMap<>();

        try {
            Timestamp startTimestamp = new Timestamp(format.parse(start).getTime());
            Timestamp endTimestamp = new Timestamp(format.parse(end).getTime());
            Boolean isAllBool = isAll(dept_id);

            if (isAllBool) {
                List<Suspect> suspects = suspectDao.findAllByTimeAndPolice(startTimestamp, endTimestamp);
                List<PersonPath> personPaths = personPathDao.selectByTime(startTimestamp, endTimestamp);

                if (personPaths != null && suspects != null) {
                    dealSiteMap(site, personPaths);
                    dealNameMap(name, personPaths);
                    dealTimeMap(time, personPaths);
                    dealZdryType(zdryType, suspects);
                    dealZdryState(zdryState, suspects);
                    dealNativePlace(nativePlace, personPaths);
                }
            } else {
                dept_id = ConvertUtil.getDeptName(dept_id);
//                dept_id = "公交分局";
                List<Suspect> suspects = suspectDao.findByTimeAndPolice(startTimestamp, endTimestamp, dept_id);

                if (suspects.size() > 0) {
                    List<String> personPathId = new ArrayList<>();
                    for (Suspect s : suspects) {
                        personPathId.add(s.getId_number_18());
                    }
                    List<PersonPath> personPaths = personPathDao.findByS_ID_NUMBER(personPathId);

                    if (personPaths != null && suspects != null) {
                        dealSiteMap(site, personPaths);
                        dealNameMap(name, personPaths);
                        dealTimeMap(time, personPaths);
                        dealZdryType(zdryType, suspects);
                        dealZdryState(zdryState, suspects);
                        dealNativePlace(nativePlace, personPaths);
                    }
                }
            }

            result.put("site", sortMapDesc(site));
            result.put("name", sortMapDesc(name));
            result.put("time", time);
            result.put("zdryType", zdryType);
            result.put("zdryState", zdryState);
            result.put("nativePlace", sortMapDesc(nativePlace));

            return new Response("200", result);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Response("500", "sorry for errors!");
        }
    }


    private boolean isAll(String police) {
        if ("all".equals(police)) {
            return true;
        }
        return false;
    }

    private void dealNativePlace(Map<String, Integer> nativePlace, List<PersonPath> obj) {

        for (PersonPath p : obj) {
            if (p != null && p.getS_ID_NUMBER() != null && p.getS_ID_NUMBER().length() > 6) {
                String brithplace = identityDao.findPlaceById(p.getS_ID_NUMBER().substring(0, 6));
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
    }

    private void dealZdryState(Map<String, Integer> zdryState, List<Suspect> suspects) {

        for (Suspect suspect: suspects) {
            if (suspect != null && suspect.getZDRYSTATE() != null) {
                String type = ConvertUtil.getPreWarnState(suspect.getZDRYSTATE());
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
    }
    private void dealZdryType(Map<String, Integer> zdryType, List<Suspect> suspects) {
        for (Suspect suspect: suspects) {
            if (suspect != null && suspect.getZDRYTYPE() != null) {
                String type = ConvertUtil.getPreWarnType(suspect.getZDRYTYPE());
                if (type != null) {
                    if (zdryType.get(type) != null) {
                        int value = zdryType.get(type);
                        zdryType.put(type, ++value);
                    } else {
                        zdryType.put(type, 1);
                    }
                }
            }
        }
    }

    private void dealSiteMap(Map<String, Integer> site, List<PersonPath> obj) {
        for (PersonPath p : obj) {
            if (p.getDWMC() != null) {
                if (site.get(p.getDWMC()) != null) {
                    int value = site.get(p.getDWMC());
                    site.put(p.getDWMC(), ++value);
                } else {
                    site.put(p.getDWMC(), 1);
                }
            }
        }
    }

    private void dealNameMap(Map<String, Integer> name, List<PersonPath> obj) {
        for (PersonPath p : obj) {
            if (p.getNAME() != null) {
                if (name.get(p.getNAME()) != null) {
                    int value = name.get(p.getNAME());
                    name.put(p.getNAME(), ++value);
                } else {
                    name.put(p.getNAME(), 1);
                }
            }
        }
    }

    private void dealTimeMap(Map<String, Integer> time, List<PersonPath> obj) {
        for (PersonPath p : obj) {
            if (p.getCREATE_DATE() != null) {
                String key = p.getCREATE_DATE().toString().split(" ")[1];
                key = key.split(":")[0];
                if (time.get(key) != null) {
                    int value = time.get(key);
                    time.put(key, ++value);
                } else {
                    time.put(key, 1);
                }
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
