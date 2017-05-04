package com.sibat.controller;

import com.sibat.util.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tgw61 on 2017/5/3.
 */
@RestController
@RequestMapping(value = "pre_warning")
public class PreWarningController {
    /**
     * 嫌疑人处置分析
     * 案件性质	嫌疑人类型ID	根据嫌疑人处置表分类统计	1天更新；当月累计
     * 年龄	嫌疑人类型ID	根据嫌疑人处置表分类统计	1天更新；当月累计
     * 办案单位	嫌疑人类型ID	根据嫌疑人处置表分类统计	1天更新；当月累计
     * 案件类型	嫌疑人类型ID	根据嫌疑人处置表分类统计	1天更新；当月累计
     * 处置时间	嫌疑人类型ID	根据嫌疑人处置表分类统计	1天更新；当月累计
     * 籍贯	嫌疑人类型ID	根据嫌疑人处置表分类统计	1天更新；当月累计
     *
     * @return
     */

    /**
     * 案件性质	嫌疑人类型ID	根据嫌疑人处置表分类统计	1天更新；当月累计
     * @return
     */
    @RequestMapping(value = "from_local_police_station", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
    public Response warning_from_local_police_station() {
        return new Response("404", "failed");
    }
}
