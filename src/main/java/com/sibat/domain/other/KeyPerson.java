package com.sibat.domain.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/15.
 * 重点人员表
 */

@Entity
@Table(name = "key_person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyPerson {

//    @Id
//    @GeneratedValue
//    private int id;
//    private String idNumber;//证件号码
//    private String name;//姓名
//    private String formerName;//曾用名
//    private String foreignName;//外文名字
//    private String sex;//性别
//    private String birthday;//出生日期
//    private String nationalLity;//国籍
//    private String education;//文化程度
//    private String national;//民族
//    private String maritalStatus;//婚姻状况
//    private String residenceAddr;//户籍地址
//    private String createTime;//记录创建时间(填表时间)
//    private String householdFlag;//户籍状态：2=非户籍人口，1=户籍人口
//    private String nowLive;//现居住地址
//    private String idNumber18;//18位身份证号码
//    private String deptId;//责任单位ID
//    private String zdrytype;    //重点人员类别(“扒窃”1、“盗窃”2、“诈骗”3、“抢夺”4、“抢劫”5、“涉毒”6、“涉黑”7、“其他”8.)
//    private String zdrystate;//预警类别(10=A.普通关注、11=B.积极关注、12=C.重点关注,13=D.立即处置)
//    private String globalManage;//全局共管(0否,1 是)
//    private String nickName;//别名
//    private String birthPlace;//	籍　贯
//    private String currentWork;//现从业情况
//    private String convictions;//前科情况或现实表现
//    private String mainControl;    //主要管控措施
//    private String deptuser;//责任人ID
//    private String deptuserName;//责任人姓名
//    private String deptName;    //责任单位名称
//    private String rybh;//人员编号
//    private String zjzl;//证件种类
//    private String zhcs;//是否多次抓获（曾经抓获次数）
//    private String ch;//（绰号）
//    private String sf;//	身份
//    private String wetherxd;//是否吸毒
//    private String lx;    //脸型
//    private String sg;//身高
//    private String remark;//备注
//    private String gjryzt;//公交重点人员状态：待审批0，审批通过1，审批不通过2，已删除3
//    private String addperson;//	填表人
//    private String sztkh;//深圳通卡号
//    private String phone;//电话
//    private String qq;//QQ
//    private String email;//邮箱
//    private String addpersonid;    //填表人ID
//    private String sjly;//数据来源
//    private String updateTime;//记录修改时间
//    private String checkStatus;//是否有效1有效,0待审批

    @Id
    private String ID_NUMBER;//证件号码
    private String NAME;//姓名
    private String FORMER_NAME;// 曾用名
    private String FOREIGN_NAME;//外文名字
    private String SEX;//性别
    private String BIRTHDAY;// 出生日期
    private String NATIONALITY;//国籍
    private String NATIONAL;//民族
    private String EDUCATION;//文化程度
    private String MARITAL_STATUS; //婚姻状况
    private String RESIDENCE_ADDR;//户籍地址
    private String CREATE_TIME;// DATE 记录创建时间(填表时间)
    private String HOUSEHOLD_FLAG; //户籍状态：2=非户籍人口，1=户籍人口
    private String NOWLIVE; //现居住地址
    private String ID_NUMBER_18;    //			18位身份证号码
    private String DEPTID;//责任单位ID
    private String ZDRYTYPE;// 重点人员类别(“扒窃”1、“盗窃”2、“诈骗”3、“抢夺”4、“抢劫”5、“涉毒”6、“涉黑”7、“其他”8.)
    private String ZDRYSTATE;//预警类别(10=A.普通关注、11=B.积极关注、12=C.重点关注,13=D.立即处置)
    private String GLOBALMANAGE;//全局共管(0否,1是)
    private String NICKNAME; //别名
    private String BIRTHPLACE; //籍　贯
    private String CURRENTWORK;// 现从业情况
    private String CONVICTIONS; //前科情况或现实表现
    private String MAINCONTROL;//主要管控措施
    private String DEPTUSER; //责任人ID
    private String DEPTUSERNAME;//责任人姓名
    private String DEPTNAME;//责任单位名称
    private String RYBH;//人员编号
    private String ZJZL;//证件种类
    private String ZHCS;//是否多次抓获（曾经抓获次数）
    private String CH;//（绰号）
    private String SF; //身份
    private String WETHERXD;//是否吸毒
    private String LX;//脸型
    private String SG;    //	身高
    private String REMARK;        //	备注
    private String GJRYZT;        //公交重点人员状态：待审批0，审批通过1，审批不通过2，已删除3
    private String ADDPERSON;    //	填表人
    private String SZTKH;    //	深圳通卡号
    private String PHONE;    //	电话
    private String QQ;        //qq号码
    private String EMAIL;        //	邮箱
    private String ADDPERSONID;        //		填表人ID
    private String SJLY;        //	数据来源
    private String UPDATE_TIME;        //记录修改时间
    private String CHECK_STATUS;        //	是否有效1有效,0待审批
}
