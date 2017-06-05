package com.sibat.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tgw61 on 2017/4/28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarningAnalysis {
    //派出所，线路，站点，各警情类型，各警情性质
    private String start;//分析时段起
    private String end;//分析时段止
    private String type;//地铁公交
    private String dimension;//分析纬度 police/line/station/jqlb/jqxz
}
