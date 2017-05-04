package com.sibat.domain.other;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 警情类型按月统计表
 * Created by tgw61 on 2017/5/3.
 * insert into event_category_count(time,name,count) select time,category,count(*) from subway_event group by category,time order by time desc;
 */
@Entity
@Table(name = "EventCategoryCount")
public class EventCategoryCount {
    @Id
    @GeneratedValue
    private int id;
    private String name;//类型
    private String count;//统计值
    private String time;//yyyy-MM
    private Date createTime;
    private Date updateTime;
    private String type;//地铁,公交,公交线路

    public EventCategoryCount(int id, String name, String count, String time, Date createTime, Date updateTime, String type) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.time = time;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.type = type;
    }

    public EventCategoryCount() {
    }

    public EventCategoryCount(String name, String count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
