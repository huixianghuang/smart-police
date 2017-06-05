package com.sibat.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/15.
 */
@Entity
@Table
public class TrafficCongestion {
    @Id
    @GeneratedValue
    private int id;
    private String time;
    private String trafficCongestionIndex;

    public TrafficCongestion() {
    }

    public TrafficCongestion(String time, String trafficCongestionIndex) {
        this.time = time;
        this.trafficCongestionIndex = trafficCongestionIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrafficCongestionIndex() {
        return trafficCongestionIndex;
    }

    public void setTrafficCongestionIndex(String trafficCongestionIndex) {
        this.trafficCongestionIndex = trafficCongestionIndex;
    }
}
