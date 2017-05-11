package com.sibat.domain.other;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 地铁站点信息表
 * Created by tgw61 on 2017/5/3.
 * insert into station(station_id,station_name,lat,lon,zdbh,xlbh,xlmc) select id,name,display_y,display_x,zdbh,xlbh,xlmc from station_copy;
 * update station set station_id =id;
 *
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
    private String zdbh;
    private String xlbh;
    private String xlmc;//几号线
    private String address;
    public Station() {
    }

    public Station(String stationId, String stationName, String lat, String lon) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.lat = lat;
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZdbh() {
        return zdbh;
    }

    public void setZdbh(String zdbh) {
        this.zdbh = zdbh;
    }

    public String getXlbh() {
        return xlbh;
    }

    public void setXlbh(String xlbh) {
        this.xlbh = xlbh;
    }

    public String getXlmc() {
        return xlmc;
    }

    public void setXlmc(String xlmc) {
        this.xlmc = xlmc;
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
