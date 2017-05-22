package com.sibat.domain.other;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 派出所警情按月统计表
 * Created by tgw61 on 2017/5/3.
 * insert into local_police_event(time,police,police_id,count)select time,police,police_id,count(*) from subway_event group by time,police,police_id order by time desc;
 *
 * insert into local_police_event(time,police_id,count)select time,police_id,count(*) from subway_event group by time,police_id order by time desc;
 *  update local_police_event set police =(select police from subway_event where local_police_event.police_id=subway_event.police_id limit 1);
 */
@Data
@Entity
@Table(name = "LocalPoliceEvent")
public class LocalPoliceEvent {
    @Id
    @GeneratedValue
    private int id;
    private String policeId;
    private String police;
    private String count;
    private String time;
    private Date createTime;
    private Date updateTime;
    private String type;//地铁,公交,公交线路

    public LocalPoliceEvent(String policeId, String police, String count) {
        this.policeId = policeId;
        this.police = police;
        this.count = count;
    }

    public LocalPoliceEvent() {
    }

    public LocalPoliceEvent(String policeId, String police, String count, String time, Date createTime, String type) {
        this.policeId = policeId;
        this.police = police;
        this.count = count;
        this.time = time;
        this.createTime = createTime;
        this.type = type;
    }
}
