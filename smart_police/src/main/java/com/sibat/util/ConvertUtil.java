package com.sibat.util;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgw61 on 2017/5/4.
 */
public class ConvertUtil {

    public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {
        List<T> returnList = new ArrayList();
        if (isNotNull(list)) {
            Object[] co = list.get(list.size() - 1);
            Class[] c2 = new Class[co.length];
            //确定构造方法
            for (int i = 0; i < co.length; i++) {
                if (co[i] != null)
                    c2[i] = co[i].getClass();
            }
            for (Object[] o : list) {
                if (o[0] != null) {
                    Constructor<T> constructor = clazz.getConstructor(c2);
                    returnList.add(constructor.newInstance(o));
                }
            }
        }
        return returnList;
    }

    public static <T> boolean isNotNull(List<T> list) {
        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isJqlb(String str) {
        switch (str) {
            case "两抢":
            case "扒窃":
            case "盗窃":
            case "诈骗":
            case "故意伤害":
            case "遗失":
            case "求助":
            case "其他":
            case "故意杀人":
            case "抢劫":
            case "抢夺":
            case "重复报警":
                return true;
            default:
                return false;
        }
    }

    public static String getAjlx(Integer str) {
        switch (str) {
            case 1:
                return "侵财";
            case 2:
                return "涉毒";
            case 3:
                return "其他";
            default:
                return "未定义";
        }
    }

    public static String getClcs(String str) {
        switch (str) {
            case "1":
                return "取保候审";
            case "2":
                return "刑事拘留人员";
            default:
                return "没有处理";
        }
    }

    public static boolean isJqxzSubway(String str) {
        switch (str) {
            case "重复报警":
            case "非地铁警情":
            case "地铁其他警情":
            case "地铁刑事警情":
            case "地铁治安警情":
                return true;
            default:
                return false;
        }
    }


    public static boolean isJqxzBus(String str) {
        switch (str) {
            case "重复报警":
            case "非公交警情":
            case "公交其他警情":
            case "公交刑事警情":
            case "公交治安警情":
                return true;
            default:
                return false;
        }
    }

    //    预警类别(10=A.普通关注、11=B.积极关注、12=C.重点关注,13=D.立即处置)
    public static String getPreWarnState(String str) {
        switch (str) {
            case "10":
                return "普通关注";
            case "11":
                return "积极关注";
            case "12":
                return "重点关注";
            case "13":
                return "立即处置";
        }
        return null;
    }

    //    重点人员类别(“扒窃”1、“盗窃”2、“诈骗”3、“抢夺”4、“抢劫”5、“涉毒”6、“涉黑”7、“其他”8.)
    public static String getPreWarnType(String str) {
        switch (str) {
            case "1":
                return "扒窃";
            case "2":
                return "盗窃";
            case "3":
                return "诈骗";
            case "4":
                return "抢夺";
            case "5":
                return "抢劫";
            case "6":
                return "涉毒";
            case "7":
                return "涉黑";
            case "8":
                return "其他";
        }
        return null;
    }

    public static String getCflx(Integer cflx) {
        switch (cflx) {
            case 1:
                return "行政拘留";
            case 2:
                return "罚款";
            case 3:
                return "警告";
            default:
                return "未定义";
        }
    }

    public static String getDeptName(String deptName){
        switch (deptName) {
            case "440396650000":
                return "布吉公交派出所";
            case "440396540000":
                return "罗湖公交派出所";
            case "440396630000":
                return "龙华公交派出所";
            case "440396570000":
                return "宝安公交派出所";
            case "440396550000":
                return "南山公交派出所";
            case "440396530000":
                return "福田公交派出所";
            case "440396580000":
                return "龙岗公交派出所";
            case "440396610000":
                return "香蜜湖公交派出所";
            case "440396600000":
                return "坪山公交派出所";
            case "440396660000":
                return "东站公交派出所";
            case "440396640000":
                return "北站公交派出所";
            case "440396620000":
                return "福永公交派出所";
            case "440396710000":
                return "福田枢纽公交派出所";
            case "440396590000":
                return "光明公交派出所";
            case "440396560000":
                return "盐田公交派出所";
        }

        return null;
    }


}

