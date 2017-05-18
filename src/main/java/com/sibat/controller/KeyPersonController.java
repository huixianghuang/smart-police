package com.sibat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sibat.domain.origin.KeyPerson;
import com.sibat.domain.origin.KeyPersonDao;
import com.sibat.domain.origin.PersonPath;
import com.sibat.domain.origin.PersonPathDao;
import com.sibat.domain.pojo.LikeMap;
import com.sibat.util.ConvertUtil;
import com.sibat.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/16.
 */
@RestController
@RequestMapping(value = "api/key_person/")
public class KeyPersonController {
    @Autowired
    KeyPersonDao keyPersonDao;
    @Autowired
    PersonPathDao personPathDao;

    /**
     * 重点人员预警统计
     *
     * @return
     */
    @RequestMapping(value = "group_by_police", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response group_by_police() {
        try {
            List<Object[]> policeCount = keyPersonDao.countSumGroupByPolice();
            if (policeCount != null && !policeCount.isEmpty()) {
                //防止空值报错
                for (Object[] array : policeCount) {
                    if (array[0] == null)
                        policeCount.remove(array);
                }

                List<LikeMap> likeMapList = ConvertUtil.castEntity(policeCount, LikeMap.class);
                return new Response("200", likeMapList);
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
     * @return
     */
    @RequestMapping(value = "group_by_type", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response group_by_type() {
        JSONObject reuslt = new JSONObject();
        List<Object[]> typeCount = keyPersonDao.countSumGroupByType();
        if (typeCount != null && !typeCount.isEmpty()) {
            try {
                List<LikeMap> likeMapList = ConvertUtil.castEntity(typeCount, LikeMap.class);
                int sum = 0;
                for (LikeMap lm : likeMapList) {
                    switch (lm.getKey()) {
                        case "10":
                            reuslt.put("普通关注", lm.getValue());
                            break;
                        case "11":
                            reuslt.put("积极关注", lm.getValue());
                            break;
                        case "12":
                            reuslt.put("重点关注", lm.getValue());
                            break;
                        case "13":
                            reuslt.put("立即处置", lm.getValue());
                            break;
                        default:
                            sum = (int) (sum + lm.getValue().doubleValue());
                            break;
                    }
                }
                reuslt.put("其它未标属性", sum);
                return new Response("200", reuslt);
            } catch (Exception e) {
                e.printStackTrace();
                return new Response("500", "some errors happend");
            }
        } else {
            return new Response("200", "未找到");
        }
    }

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
                    KeyPerson kp = keyPersonDao.findByID(pp.getS_ID_NUMBER());
                    if (kp != null) {
                        if (kp.getZDRYSTATE() != null)
                            obj.put("state", convertState(kp.getZDRYSTATE()));
                        else
                            obj.put("state", null);
                        if (kp.getZDRYTYPE() != null)
                            obj.put("type", convertType(kp.getZDRYTYPE()));
                        else
                            obj.put("type", null);
                    }else{
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
}
