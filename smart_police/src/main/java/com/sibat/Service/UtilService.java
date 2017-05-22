package com.sibat.Service;

import org.springframework.stereotype.Service;

/**
 * Created by tgw61 on 2017/5/10.
 */
@Service
public class UtilService {
    public static String convertStation(String stationName) {
        if (stationName.contains("站"))
            return stationName.substring(0, stationName.indexOf("站"));
        else if (stationName.contains("("))
            return stationName.substring(0, stationName.indexOf("("));
        else if (stationName.contains("（"))
            return stationName.substring(0, stationName.indexOf("（"));
        else
            return stationName;
    }
}
