package com.sibat.service;

import com.sibat.domain.FullFlow;
import com.sibat.domain.Subway;
import com.sibat.util.DateUtil;
import org.apache.log4j.Logger;
import org.jfaster.mango.operator.Mango;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tgw61 on 2017/5/12.
 */
@Service
public class ScheduleService {
    Logger logger = Logger.getLogger(ScheduleService.class);
    @Autowired
    DBService dbService;

    @Test
    public void test() {
        getTrafficCongestionIndex();
//        String time = DateUtil.getCurrentTimeString() + "%";
//        logger.info(time);
    }

    public Map<String, Integer> startEndMap() {
        Map<String, Integer> startEndMap = new HashMap<>();
        Mango mango = dbService.getMango87();
        DBService.FullFlowDao fullFlowDao = mango.create(DBService.FullFlowDao.class);
        List<FullFlow> fullFlows = fullFlowDao.findAll();
        for (FullFlow ff : fullFlows) {
            startEndMap.put(ff.getStart() + "_" + ff.getEnd(), ff.getFullFlow());
        }
        return startEndMap;
    }

    DecimalFormat df = new DecimalFormat("##.###");

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void getTrafficCongestionIndex() {
        BigDecimal result = new BigDecimal(0);
        Mango mango = dbService.getMango130();
        DBService.SubwayDao subwayDao = mango.create(DBService.SubwayDao.class);
        String time = DateUtil.getLast1Min(DateUtil.getCurrentTimeString()) + "%";
        //String time = "2017-05-17 17:25%";
        List<Subway> subways = subwayDao.findByTime(time);
        Map<String, Integer> nowStartEndMap = new HashMap<>();
        Map<String, Integer> startEndMap = startEndMap();
        int sumFlow = 0;
        for (Subway sb : subways) {
            nowStartEndMap.put(sb.getStart() + '_' + sb.getEnd(), sb.getFlow());
            sumFlow += sb.getFlow();
        }
        BigDecimal tp = new BigDecimal(sumFlow);
        for (Map.Entry<String, Integer> entry : nowStartEndMap.entrySet()) {
            try {
                result = getBigDecimalResult(result, startEndMap, tp, entry);
            } catch (Exception e) {
                logger.info(entry.getKey());
            }
        }
        logger.info("result---" + result.doubleValue());
        Mango mango2 = dbService.getMango87();
        DBService.TrafficCongestionDao tcdao = mango2.create(DBService.TrafficCongestionDao.class);
        logger.info(DateUtil.parseDateToString(new Date()));
        tcdao.addObj(DateUtil.parseDateToString(new Date()), df.format(result.doubleValue()));
    }

    private BigDecimal getBigDecimalResult(BigDecimal result, Map<String, Integer> startEndMap, BigDecimal tp, Map.Entry<String, Integer> entry) {
        BigDecimal rpi = new BigDecimal(entry.getValue());
        BigDecimal dpi = new BigDecimal(startEndMap.get(entry.getKey()));
        BigDecimal ci = rpi.divide(dpi, 5, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal ri = rpi.divide(tp, 5, BigDecimal.ROUND_HALF_EVEN);
        result = result.add(ci.multiply(ri));
        return result;
    }


}
