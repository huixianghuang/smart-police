package com.sibat.service;

import com.sibat.domain.SumIn;
import com.sibat.domain.FullFlow;
import com.sibat.domain.Subway;
import com.sibat.domain.SumOut;
import com.sibat.util.DateUtil;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.annotation.Sharding;
import org.jfaster.mango.annotation.TableShardingBy;
import org.jfaster.mango.datasource.DriverManagerDataSource;
import org.jfaster.mango.operator.Mango;
import org.jfaster.mango.sharding.TableShardingStrategy;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/22.
 */
@org.springframework.stereotype.Service
public class DBService {

    public Mango getMango87() {
        String driverClass = "org.postgresql.Driver";
        String url = "jdbc:postgresql://192.168.40.99/police_prevention";
        String userName = "postgres";
        String password = "root";
        DataSource ds = new DriverManagerDataSource(driverClass, url, userName, password);
        return Mango.newInstance(ds);
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

    public Mango getMango130() {
        String driverClass = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.40.130:3306/subway";
        String userName = "root";
        String password = "123456";
        DataSource ds = new DriverManagerDataSource(driverClass, url, userName, password);
        return Mango.newInstance(ds);
    }


    @DB(table = "subway_")
    @Sharding(tableShardingStrategy = SubwayShardingStrategy.class)
    public interface SubwayDao {
        @SQL("select * from #table where time like :1")
        List<Subway> findByTime(@TableShardingBy String time);
    }

    static class SubwayShardingStrategy implements TableShardingStrategy<String> {
        @Override
        public String getTargetTable(String table, String time) {
            String currentTime = DateUtil.getCurrentTimePATTERN_yyyyMM();
            return table + currentTime;
        }
    }

    @DB(table = "allin_")
    @Sharding(tableShardingStrategy = SubwayShardingStrategy.class)
    public interface AllInDao {
        @SQL("select time,sumin from #table where Date(time) = :1 order by time desc")
        List<SumIn> findByTime(@TableShardingBy String time);
    }

    @DB(table = "allout_")
    @Sharding(tableShardingStrategy = SubwayShardingStrategy.class)
    public interface AllOutDao {
        @SQL("select time,sumout from #table where Date(time)=:1 order by time desc")
        List<SumOut> findByTime(@TableShardingBy String time);
    }
}
