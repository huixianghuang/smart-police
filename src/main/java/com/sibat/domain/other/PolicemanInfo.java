package com.sibat.domain.other;

import java.sql.Blob;

/**
 * Created by tgw61 on 2017/5/8.
 */
public class PolicemanInfo {
    private int id;
    private String name;//姓名
    private String policeId;//警号
    private String sex;//性别
    private String educationBackground;//学历
    private Blob picture;//照片
    private String type;//人员类型
    private String department;//单位
    private String phoneNumber;//手机
}
