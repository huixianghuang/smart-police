package com.sibat.domain.pojo;

/**
 * Created by tgw61 on 2017/5/4.
 */
public class StationCount {
    private String stationName;
    private Long count;

    public StationCount(String stationName, Long count) {
        this.stationName = stationName;
        this.count = count;
    }

    public StationCount() {
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
