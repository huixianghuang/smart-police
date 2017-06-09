package com.sibat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sibat.domain.origin.*;
import com.sibat.domain.other.Police;
import com.sibat.domain.other.PoliceDao;
import com.sibat.domain.pojo.LikeMap;
import com.sibat.domain.pojo.SuspectPerson;
import com.sibat.util.ConvertUtil;
import com.sibat.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by tgw61 on 2017/5/16.
 */
@RestController
@RequestMapping(value = "api/suspect/")
public class SuspectController {
    @Autowired
    SuspectDao suspectDao;
    @Autowired
    PersonPathDao personPathDao;
    @Autowired
    PoliceDao policeDao;
    @Autowired
    CbPersonDao cbPersonDao;
    @Autowired
    QjPersonDao qjPersonDao;
    @Autowired
    XjPersonDao xjPersonDao;
    @Autowired
    XzPersonDao xzPersonDao;

    /**
     * 重点人员预警统计
     *
     * 1.按照tb_gjzd_person里的deptname字段进行重点人员统计分类
     * 2.将分类出来的信息去除包含深圳市公安局的文字,以及不在15个派出所标准中的派出所名
     * 3.处理好的数据构造成JSON数据保存在result中并返回给前台
     *
     * @return
     */
    @RequestMapping(value = "group_by_police", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response group_by_police() {
        List<LikeMap> result = new ArrayList<>();
        try {
            List<Object[]> policeCount = suspectDao.countSumGroupByPolice();
            if (policeCount != null && !policeCount.isEmpty()) {
                List<LikeMap> likeMapList = ConvertUtil.castEntity(policeCount, LikeMap.class);
                if (likeMapList != null && !likeMapList.isEmpty())
                    for (LikeMap obj : likeMapList) {
                        if (obj.getKey().contains("深圳市公安局")) {
                            String key = obj.getKey().replace("深圳市公安局", "");
                            if (policeDao.findByPoliceName(key) != null)
                                result.add(new LikeMap(key, obj.getValue()));
                        } else if (policeDao.findByPoliceName(obj.getKey()) != null) {
                            result.add(new LikeMap(obj.getKey(), obj.getValue()));
                        }
                    }
                return new Response("200", result);
            } else {
                return new Response("200", "未找到");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Response("500", "some errors happend");
        }
    }

    /**
     * 预警类别统计
     *
     * 1.按照tb_gjzd_person里的zdrystate字段进行统计分类
     * 2.分别对10,11,12,13按照common,positive,emphasis,immediate_disposal进行统计
     * 3.对于其他类别,统一按照other统计
     * 4.处理好的数据构造成JSON数据保存在result中并返回给前台
     *
     * @return
     */
    @RequestMapping(value = "group_by_type", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response group_by_type() {
        JSONObject reuslt = new JSONObject();
        List<Object[]> typeCount = suspectDao.countSumGroupByType();
        if (typeCount != null && !typeCount.isEmpty()) {
            try {
                List<LikeMap> likeMapList = ConvertUtil.castEntity(typeCount, LikeMap.class);
                int sum = 0;
                for (LikeMap lm : likeMapList) {
                    switch (lm.getKey()) {
                        case "10":
                            reuslt.put("common", lm.getValue());
                            break;
                        case "11":
                            reuslt.put("positive", lm.getValue());
                            break;
                        case "12":
                            reuslt.put("emphasis", lm.getValue());
                            break;
                        case "13":
                            reuslt.put("immediate_disposal", lm.getValue());
                            break;
                        default:
                            sum = (int) (sum + lm.getValue().doubleValue());
                            break;
                    }
                }
                reuslt.put("other", sum);
                return new Response("200", reuslt);
            } catch (Exception e) {
                e.printStackTrace();
                return new Response("500", "some errors happend");
            }
        } else {
            return new Response("200", "未找到");
        }
    }

    /**
     * 重点人员预警列表
     *
     * 1.根据传入参数date在t_gjzd_person_gj_new表中得到筛选重点人员轨迹,按时间降序排列
     * 2.根据t_gjzd_person_gj_new中的id在重点人员信息表中查找相应的重点人员信息.(时常会出现查找不到的情况)
     * 3.处理好的数据构造成JSON数据保存在result中并返回给前台
     *
     * @param date yyyy-MM-dd
     * @return
     */
    @RequestMapping(value = "warning_person", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response warning_person(@RequestParam("date") String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date dateClass = null;
        try {
            dateClass = fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray result = new JSONArray();
        JSONObject obj;
        List<PersonPath> pps = personPathDao.findByTime(dateClass);
        try {
            if (pps != null && !pps.isEmpty()) {
                for (PersonPath pp : pps) {
                    obj = new JSONObject();
                    obj.put("person_path", pp);
                    Suspect kp = suspectDao.findByID(pp.getS_ID_NUMBER());
                    if (kp != null) {
                        if (kp.getZDRYSTATE() != null)
                            obj.put("state", convertState(kp.getZDRYSTATE()));
                        else
                            obj.put("state", null);
                        if (kp.getZDRYTYPE() != null)
                            obj.put("type", convertType(kp.getZDRYTYPE()));
                        else
                            obj.put("type", null);
                    } else {
                        obj.put("state", null);
                        obj.put("type", null);
                    }
                    result.add(obj);
                }
            }
            return new Response("200", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response("200", "null");
    }

    /**
     * 重点人员信息查询
     * 查询重点人员基础信息，重点人员预警记录，提供轨迹点信息（包含地理位置数据及时间数据）
     *
     * 1.根据提供的ID查询重点人员基础信息（目前以身份证或者MAC作为ID），
     * 以及人员的预警记录（包含：预警时间，预警地点，预警类型信息）。
     * 2.处理好的数据构造成JSON数据保存在result中并返回给前台
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "search_suspect_path", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response search_suspect_path(@RequestParam("id") String id) {
        JSONObject result = new JSONObject();
        Suspect suspect = suspectDao.findByID(id);
        List<PersonPath> personPaths = personPathDao.findByIdtype2(id);
        List<PersonPath> fs = personPathDao.findByName("阿卜拉江·艾麦提");
        result.put("information", suspect);
        result.put("path", personPaths);
        return new Response("200", result);
    }

    /**
     * 重点人员查询
     *
     * @param sp
     * @return
     */
    @RequestMapping(value = "search_suspect", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response search_suspect(SuspectPerson sp) {
        JSONObject result = new JSONObject();
        return new Response("200", result);
    }

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 嫌疑人处置数据统计分析
     * 问题:
     * xj分析维度:age, sex, hj, ajlx, punish, ajxz
     * xz分析维度:age, sex, hj, punish, ajxz
     * qj分析维度:age, sex, hj, ajxz
     * cb分析维度:无
     *
     * 1.根据传入的参数进行嫌疑人处置数据统计分析
     *
     * 查询条件：派出所，嫌疑人类别（4类），时间，粒度（小时/天/月）；
     *
     * 统计维度：
     * 办案单位：派出所名，侦办案件数，占比；
     * 年龄（0-17，18-40，41-65，>66):年龄段，侦办案件数，占比；
     * 性别：男，女；
     * 户籍：省，侦办案件数，占比；
     * 案件类型：类型，侦办案件数，占比；
     * 处罚类型：类型，侦办案件数，占比；
     * 案件性质：性质分类，侦办案件数，占比
     *
     * @param start 起始时间 yyyy-MM-dd HH:mm:ss
     * @param end 结束时间 yyyy-MM-dd HH:mm:ss
     * @param police 派出所
     * @param type 嫌疑人类别  //刑拘xj, 行政xz, 戒毒qj, 逮捕cb
     * @param dimension 粒度  //年龄age，性别sex，户籍hj，办案单位police，案件性质ajxz，案件类型ajlx，处罚类型punish
     * @return
     */
    @RequestMapping(value = "dispose_suspect", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response dispose_suspect(@RequestParam("start") String start,
                                    @RequestParam("end") String end,
                                    @RequestParam("police") String police,
                                    @RequestParam("type") String type,
                                    @RequestParam("dimension") String dimension) {
        Map map = new HashMap();
        JSONObject result = new JSONObject();
        Timestamp time_start = null;
        Timestamp time_end = null;
        try {
            time_start = new Timestamp(format.parse(start + " 00:00:00").getTime());
            time_end = new Timestamp(format.parse(end + " 00:00:00").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //按年龄统计
        Map<String, Integer> age = new LinkedHashMap<>();
        age.put("<17", 0);
        age.put("18-40", 0);
        age.put("41-65", 0);
        age.put(">66", 0);
        //性别 sex
        Map<String, Integer> sexMap = new LinkedHashMap<>();
        sexMap.put("男", 0);
        sexMap.put("女", 0);
        //户籍HJ取前两个字
        Map<String, Integer> hjMap = new LinkedHashMap<>();
        //案件类型AJLX
        Map<String, Integer> ajlxMap = new LinkedHashMap<>();
        //处罚类型
        Map<String, Integer> punishMap = new LinkedHashMap<>();
        punishMap.put("没有处理", 0);
        //案件性质
        Map<String, Integer> ajxzMap = new LinkedHashMap<>();
        ajxzMap.put("未定义", 0);
        switch (type) {
            case "xj":
                map = getSwitchXj(police, dimension, result, time_start, time_end, age, sexMap, hjMap, ajlxMap, punishMap, ajxzMap);
                break;
            case "xz":
                map = getSwitchXz(police, dimension, result, time_start, time_end, age, sexMap, hjMap, punishMap, ajxzMap);
                break;
            case "qj":
                map = getSwitchQj(police, dimension, result, time_start, time_end, age, sexMap, hjMap, ajxzMap);
                break;
            case "cb":
                List<CbPerson> cbList = cbPersonDao.findByDateAndPolice(time_start, time_end, police);
                break;
        }
        return new Response("200", map);
    }

    private Map getSwitchQj(@RequestParam("police") String police, @RequestParam("dimension") String dimension, JSONObject result, Timestamp time_start, Timestamp time_end, Map<String, Integer> age, Map<String, Integer> sexMap, Map<String, Integer> hjMap, Map<String, Integer> ajxzMap) {
        List<QjPerson> qjList = qjPersonDao.findByDateAndPolice(time_start, time_end, police);
        if (ConvertUtil.isNotNull(qjList)) {
            switch (dimension) {
                case "age":
                    return dealAge(age, qjList, QjPerson.class);
                case "sex":
                    return dealSex(sexMap, qjList, QjPerson.class);
                case "hj":
                    return dealHj(hjMap, qjList, QjPerson.class);
                case "ajxz":
                    return dealAjxz(ajxzMap, qjList, QjPerson.class);
            }
        }
        return new HashMap();
    }

    private Map getSwitchXz(@RequestParam("police") String police, @RequestParam("dimension") String dimension, JSONObject result, Timestamp time_start, Timestamp time_end, Map<String, Integer> age, Map<String, Integer> sexMap, Map<String, Integer> hjMap, Map<String, Integer> punishMap, Map<String, Integer> ajxzMap) {
        List<XzPerson> xzList = xzPersonDao.findByDateAndPolice(time_start, time_end, police);
        if (ConvertUtil.isNotNull(xzList)) {
            switch (dimension) {
                case "age":
                    return dealAge(age, xzList, XzPerson.class);
                case "sex":
                    return dealSex(sexMap, xzList, XzPerson.class);
                case "hj":
                    return dealHj(hjMap, xzList, XzPerson.class);
                case "ajxz":
                    return dealAjxz(ajxzMap, xzList, XzPerson.class);
                case "punish":
                    return dealPunish_xz(punishMap, xzList);
            }
        }
        return new HashMap();
    }

    private Map getSwitchXj(@RequestParam("police") String police, @RequestParam("dimension") String dimension, JSONObject result, Timestamp time_start, Timestamp time_end, Map<String, Integer> age, Map<String, Integer> sexMap, Map<String, Integer> hjMap, Map<String, Integer> ajlxMap, Map<String, Integer> punishMap, Map<String, Integer> ajxzMap) {
        List<XjPerson> xjList = xjPersonDao.findByDateAndPolice(time_start, time_end, police);
        if (ConvertUtil.isNotNull(xjList)) {
            switch (dimension) {
                case "age":
                    return dealAge(age, xjList, XjPerson.class);
                case "sex":
                    return dealSex(sexMap, xjList, XjPerson.class);
                case "hj":
                    return dealHj(hjMap, xjList, XjPerson.class);
                case "ajxz":
                    return dealAjxz(ajxzMap, xjList, XjPerson.class);
                case "punish":
                    return dealPunish(punishMap, xjList);
                case "ajlx":
                    return dealAjlx(ajlxMap, xjList);
            }
        }
        return new HashMap();
    }

    private <T> Map dealAjxz(Map<String, Integer> ajxzMap, List<T> list, Class<T> clazz) {
        for (T obj : list) {
            try {
                Method mtd = clazz.getMethod("getAjxz");
                String ajxz = mtd.invoke(obj).toString();
                if (ajxz != null) {
                    if (ajxzMap.get(ajxz) != null) {
                        int value = ajxzMap.get(ajxz);
                        ajxzMap.put(ajxz, ++value);
                    } else {
                        ajxzMap.put(ajxz, 1);
                    }
                } else {
                    int value = ajxzMap.get("未定义");
                    ajxzMap.put("未定义", ++value);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return ajxzMap;
    }

    private <T> Map dealSex(Map<String, Integer> sexMap, List<T> list, Class<T> clazz) {
        for (T obj : list) {
            try {
                Method mtd = clazz.getMethod("getSex");
                String sex = mtd.invoke(obj).toString();
                if (sex != null) {
                    if ("男".equals(sex)) {
                        int value = sexMap.get("男");
                        sexMap.put("男", ++value);
                    } else if ("女".equals(sex)) {
                        int value = sexMap.get("女");
                        sexMap.put("女", ++value);
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return sexMap;
    }

    private <T> Map dealHj(Map<String, Integer> hjMap, List<T> list, Class<T> clazz) {
        for (T obj : list) {
            try {
                Method mtd = clazz.getMethod("getHj");
                String hj = mtd.invoke(obj).toString();
                if (hj != null) {
                    String key = hj.substring(0, 2);
                    if (hjMap.get(key) != null) {
                        Integer value = hjMap.get(key);
                        hjMap.put(key, ++value);
                    } else {
                        hjMap.put(key, 1);
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return hjMap;
    }

    private void dealAjxz(Map<String, Integer> ajxzMap, XjPerson obj) {
        if (obj.getAjxz() != null) {
            if (ajxzMap.get(obj.getAjxz()) != null) {
                int value = ajxzMap.get(obj.getAjxz());
                ajxzMap.put(obj.getAjxz(), ++value);
            } else {
                ajxzMap.put(obj.getAjxz(), 1);
            }
        } else {
            int value = ajxzMap.get("未定义");
            ajxzMap.put("未定义", ++value);
        }
    }

    private void dealPunish(Map<String, Integer> punishMap, XjPerson obj) {
        if (obj.getClcs() != null) {
            if (punishMap.get(obj.getClcs()) != null) {
                int value = punishMap.get(obj.getClcs());
                punishMap.put(obj.getClcs(), ++value);
            } else {
                punishMap.put(obj.getClcs(), 1);
            }
        } else {
            int value = punishMap.get("没有处理");
            punishMap.put("没有处理", ++value);
        }
    }

    private Map dealPunish(Map<String, Integer> punishMap, List<XjPerson> list) {
        for (XjPerson obj : list) {
            if (obj.getClcs() != null) {
                String key = obj.getClcs();
                if (punishMap.get(key) != null) {
                    int value = punishMap.get(key);
                    punishMap.put(key, ++value);
                } else {
                    punishMap.put(key, 1);
                }
            } else {
                int value = punishMap.get("没有处理");
                punishMap.put("没有处理", ++value);
            }
        }
        return punishMap;
    }

    private Map dealPunish_xz(Map<String, Integer> punishMap, List<XzPerson> list) {
        for (XzPerson obj : list) {
            if (obj.getCflx() != null) {
                String key = ConvertUtil.getCflx(obj.getCflx());
                if (punishMap.get(key) != null) {
                    int value = punishMap.get(key);
                    punishMap.put(key, ++value);
                } else {
                    punishMap.put(key, 1);
                }
            } else {
                int value = punishMap.get("没有处理");
                punishMap.put("没有处理", ++value);
            }
        }
        return punishMap;
    }

    private Map dealAjlx(Map<String, Integer> ajlxMap, List<XjPerson> list) {
        for (XjPerson obj : list) {
            if (obj.getAjlx() != null) {
                String key = ConvertUtil.getAjlx(obj.getAjlx());
                if (ajlxMap.get(key) != null) {
                    int value = ajlxMap.get(key);
                    ajlxMap.put(key, ++value);
                } else {
                    ajlxMap.put(key, 1);
                }
            }
        }
        return ajlxMap;
    }


    private void dealHj(Map<String, Integer> hjMap, XjPerson obj) {
        if (obj.getHj() != null) {
            String key = obj.getHj().substring(0, 2);
            if (hjMap.get(key) != null) {
                Integer value = hjMap.get(key);
                hjMap.put(key, ++value);
            } else {
                hjMap.put(key, 1);
            }
        }
    }

    private void dealSex(Map<String, Integer> sexMap, XjPerson obj) {
        if (obj.getSex() != null) {
            if ("男".equals(obj.getSex())) {
                int value = sexMap.get("男");
                sexMap.put("男", ++value);
            } else if ("女".equals(obj.getSex())) {
                int value = sexMap.get("女");
                sexMap.put("女", ++value);
            }
        }
    }

    private <T> Map dealAge(Map<String, Integer> age, List<T> list, Class<T> clazz) {
        for (T obj : list) {
            try {
                Method mtd = clazz.getMethod("getAge");
                mtd.invoke(obj);
                Integer ageint = Integer.parseInt(mtd.invoke(obj).toString());
                if (ageint <= 17) {
                    int value = age.get("<17");
                    age.put("<17", ++value);
                } else if (ageint > 17 && ageint <= 40) {
                    int value = age.get("18-40");
                    age.put("18-40", ++value);
                } else if (ageint > 40 && ageint <= 65) {
                    int value = age.get("41-65");
                    age.put("41-65", ++value);
                } else {
                    int value = age.get(">66");
                    age.put(">66", ++value);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                continue;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                continue;
            }
        }
        return age;
    }

    Logger logger = Logger.getLogger(SuspectController.class);

    private String convertState(String zdrystate) {
        switch (zdrystate) {
            case "10":
                return "普通关注";
            case "11":
                return "积极关注";
            case "12":
                return "重点关注";
            case "13":
                return "立即处置";
            default:
                return "无此类别";
        }
    }

    private String convertType(String zdrytype) {
        switch (zdrytype) {
            case "1":
                return "扒窃";
            case "2":
                return "盗窃";
            case "3":
                return "诈骗";
            case "4":
                return "抢夺";
            case "5":
                return "抢劫";
            case "6":
                return "涉毒";
            case "7":
                return "涉黑";
            case "8":
                return "其他";
            default:
                return "无此类别";
        }
    }

    private String convertAjlx(String classes) {
        switch (classes) {
            case "1":
                return "侵财";
            case "2":
                return "涉毒";
            case "3":
                return "盗窃";
            case "4":
                return "故意伤害";
            case "5":
                return "买卖身份证";
            case "6":
                return "诈骗";
            case "7":
                return "持有伪造的发票案";
            case "8":
                return "制造毒品罪，容留他人吸毒罪";
            case "9":
                return "贩卖、制造毒品";
            case "10":
                return "抢劫";
            case "11":
                return "出售非法制造的发票案";
            case "12":
                return "伪造事业单位印";
            case "13":
                return "寻衅滋事";
            case "14":
                return "贩毒";
            case "15":
                return "非法持有毒品";
            default:
                return "其他";
        }
    }
}
