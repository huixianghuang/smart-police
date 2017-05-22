package com.sibat.domain.other;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 警情按月统计表
 * Created by tgw61 on 2017/5/3.
 * 插入历史数据
 * insert into event_count(time,event_sum) select time,count(*) from subway_event group by time order by time;
 */
@Data
@Entity
@Table(name = "EventCount")
public class EventCount {
    @Id
    @GeneratedValue
    private int id;
    private String time;
    private Integer eventSum;
    private Date createTime;
    private Date updateTime;
    private String type;//地铁,公交,公交线路

    public EventCount() {
    }

    public EventCount(String time, Integer eventSum, Date createTime, Date updateTime) {
        this.time = time;
        this.eventSum = eventSum;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public EventCount(String time, Integer eventSum, Date createTime, String type) {
        this.time = time;
        this.eventSum = eventSum;
        this.createTime = createTime;
        this.type = type;
    }

}
