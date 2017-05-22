package com.sibat.service;

import com.sibat.domain.pojo.FullFlow;
import com.sibat.domain.pojo.Subway;
import com.sibat.util.DateUtil;
import org.apache.log4j.Logger;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.Sharding;
import org.jfaster.mango.annotation.TableShardingBy;
import org.jfaster.mango.datasource.DriverManagerDataSource;
import org.jfaster.mango.operator.Mango;
import org.jfaster.mango.sharding.TableShardingStrategy;
import org.junit.Test;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
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

    @Test
    public void test() {
        getTrafficCongestionIndex();
//        String time = DateUtil.getCurrentTimeString() + "%";
//        logger.info(time);
    }

    public Map<String, Integer> startEndMap() {
        Map<String, Integer> startEndMap = new HashMap<>();
        Mango mango = getMango87();
        FullFlowDao fullFlowDao = mango.create(FullFlowDao.class);
        List<FullFlow> fullFlows = fullFlowDao.findAll();
        for (FullFlow ff : fullFlows) {
            startEndMap.put(ff.getStart() + "_" + ff.getEnd(), ff.getFullFlow());
        }
        return startEndMap;
    }

    DecimalFormat df = new DecimalFormat("##.###");

    private Mango getMango87() {
        String driverClass = "org.postgresql.Driver";
        String url = "jdbc:postgresql://192.168.40.99/police_prevention";
        String userName = "postgres";
        String password = "root";

        DataSource ds = new DriverManagerDataSource(driverClass, url, userName, password);
        return Mango.newInstance(ds);
    }

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void getTrafficCongestionIndex() {
        BigDecimal result = new BigDecimal(0);
        Mango mango = getMango130();
        SubwayDao subwayDao = mango.create(SubwayDao.class);
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
        Mango mango2 = getMango87();
        TrafficCongestionDao tcdao = mango2.create(TrafficCongestionDao.class);
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

    private Mango getMango130() {
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.40.130:3306/subway";
        String userName = "root";
        String password = "123456";
        DataSource ds = new DriverManagerDataSource(driverClass, url, userName, password);
        return Mango.newInstance(ds);
    }

    static class SubwayShardingStrategy implements TableShardingStrategy<String> {
        @Override
        public String getTargetTable(String table, String time) {
            String currentTime = DateUtil.getCurrentTimePATTERN_yyyyMM();
            return table + currentTime;
        }
    }

    @DB(table = "full_flow")
    public interface FullFlowDao {
        @SQL("select * from #table")
        List<FullFlow> findAll();
    }

    @DB(table = "traffic_congestion")
    public interface TrafficCongestionDao {
        @SQL("insert into #table(time,traffic_congestion_index) values(:1,:2)")
        void addObj(String time, String trafficCongestionIndex);
    }

    @DB(table = "subway_")
    @Sharding(tableShardingStrategy = SubwayShardingStrategy.class)
    public interface SubwayDao {
        @SQL("select * from #table where time like :1")
        List<Subway> findByTime(@TableShardingBy String time);
    }

//    private DataSource getDataSource() {
//        String driverClass = "com.mysql.jdbc.Driver";
//        String url = "jdbc:mysql://192.168.40.130:3306/subway";
//        String userName = "root";
//        String password = "123456";
//        return new DriverManagerDataSource(driverClass, url, userName, password);
//    }


}
