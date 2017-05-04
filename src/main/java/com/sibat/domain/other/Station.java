package com.sibat.domain.other;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 地铁站点信息表
 * Created by tgw61 on 2017/5/3.
 */
@Entity
@Table(name = "station")
public class Station {
    @Id
    @GeneratedValue
    private int id;
    private String stationId;
    private String stationName;
    private String lat;
    private String lon;

    public Station() {
    }

    public Station(String stationId, String stationName, String lat, String lon) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
