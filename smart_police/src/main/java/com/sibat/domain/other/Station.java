package com.sibat.domain.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 地铁站点信息表
 * Created by tgw61 on 2017/5/3.
 * insert into station(station_id,station_name,lat,lon,zdbh,xlbh,xlmc) select id,name,display_y,display_x,zdbh,xlbh,xlmc from station_copy;
 * update station set station_id =id;
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "station")
public class Station {
    @Id
    private String stationId;
    private String stationName;
    private String lat;
    private String lon;
    private String zdbh;
    private String xlbh;
    private String xlmc;//几号线
    private String address;
}
