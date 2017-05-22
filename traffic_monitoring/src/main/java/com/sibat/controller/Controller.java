package com.sibat.controller;

import com.alibaba.fastjson.JSONObject;
import com.sibat.domain.pojo.TrafficCongestion;
import com.sibat.domain.pojo.TrafficCongestionDao;
import com.sibat.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/15.
 */
@RestController
@RequestMapping(value = "api/")
public class Controller {
    @Autowired
    TrafficCongestionDao trafficCongestionDao;

    /**
     * 拥堵系数
     * @param date
     * @return
     */
    @RequestMapping(value = "traffic_congestion", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response count(@RequestParam("date") String date) {
        JSONObject result =new JSONObject();
        List<TrafficCongestion> tcs = trafficCongestionDao.select(date+"%");
        if(tcs!=null&&!tcs.isEmpty()){
            result.put("current",tcs.get(0));
            result.put("history",tcs);
        }
        return new Response("200", result);
    }
}
