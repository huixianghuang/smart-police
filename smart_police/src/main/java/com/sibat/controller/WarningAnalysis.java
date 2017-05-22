package com.sibat.controller;

import com.sibat.domain.origin.BusWarningDao;
import com.sibat.domain.origin.JqfxJqlrDtDao;
import com.sibat.util.DateUtil;
import com.sibat.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tgw61 on 2017/5/10.
 */
@RestController
public class WarningAnalysis {
    Logger logger = Logger.getLogger(WarningAnalysis.class);
    @Autowired
    BusWarningDao busWarningDao;
    @Autowired
    JqfxJqlrDtDao jqfxJqlrDtDao;
    /**
     * 警情分析
     *
     * 警情总数 按天 get
     * 各派出所警情数
     * 案件上报时间平均值
     * 案件上报时间最大值
     * 警情类型
     * 警情性质 get
     * 审批状态
     * @param start
     * @param end
     * @return
     */
    @RequestMapping(value = "analysis", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response analysis(@RequestParam("start") String start,
                             @RequestParam("end") String end) {
        List<String> times = DateUtil.getSerialDays(start,end);
        for(int i=0,size =times.size();i<size;i++){
            int bus =busWarningDao.count(times.get(i)+"%");
            int subway = jqfxJqlrDtDao.count(times.get(i)+"%");
        }
        int busAll =busWarningDao.count(start,end);
        int subwayAll = jqfxJqlrDtDao.count(start,end);
        List<Object[]> busobj=busWarningDao.countByPolice(start,end);
        logger.info("ha");

        return new Response("200","success");
    }
}
