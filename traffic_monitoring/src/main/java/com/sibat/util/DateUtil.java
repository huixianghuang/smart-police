package com.sibat.util;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 1.不相干的方法设为private
 */
public class DateUtil {
    Logger logger = Logger.getLogger(DateUtil.class);
    private static String PATTERN_yyyy_MM_dd = "yyyy-MM-dd";
    private static String PATTERN_yy_MM_dd_HHmmss = "yy-MM-dd HH:mm:ss";
    private static String PATTERN_yyyyMMdd_yyyyMMdd = "yyyy-MM-dd_yyyy-MM-dd";
    private static String PATTERN_yyyyMM_yyyyMM = "yyyy-MM_yyyy-MM";
    private static String PATTERN_yyyyMM = "yyyy_M";
    private static String PATTERN_yyyy = "yyyy";
    private static String PATTERN_MM_dd = "MM-dd";
    private static String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    private static String PATTERN_yyyy_MM_dd_HHmmss = "yyyy-MM-dd HH:mm:ss";
    private static String PATTERN_HHmmss = "HH:mm:ss";
    private static String PATTERN_yyyy_MM_dd_HHmm = "yyyy-MM-dd HH:mm";
    private static String PATTERN_yyyyMMdd = "yyyyMMdd";
    private static String PATTERN_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    private static String PATTERN_HTTP = "EEE, dd MMM yyyy HH:mm:ss zzz";

    public static String getLast1Min(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyy_MM_dd_HHmm);
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c1.add(Calendar.MINUTE, -1);
        return sdf.format(c1.getTime());
    }
    /**
     * 用到
     *
     * @return
     */
    public static String getCurrentTimeString() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_yyyy_MM_dd_HHmm);
    }

    public static String getCurrentTimePATTERN_yyyyMM() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_yyyyMM);
    }

    public static String getCurrentTimeyyyy_MM_dd() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_yyyy_MM_dd);
    }

    public static String parseDateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat _df = new SimpleDateFormat(pattern);
        return _df.format(date);
    }
    public static String parseDateToString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat _df = new SimpleDateFormat(PATTERN_yyyy_MM_dd_HHmm);
        return _df.format(date);
    }
    public static String getCurrentTimeString2() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_yy_MM_dd_HHmmss);
    }

    /**
     * 起始是否超过1小时
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isMoreThanOneHour(String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_HHmmss);
        try {
            Date d1 = sdf.parse(start);
            Date d2 = sdf.parse(end);
            long startTime = d1.getTime();
            long endTime = d2.getTime();
            if ((endTime - startTime) / (60 * 60 * 1000) < 1) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            //e.printStackTrace();
            return false;
        }
    }


    @Test
    public void testLast150() {
//        System.out.println(DateUtil.getLast150Seconds(DateUtil.getCurrentTimeString()));
//        System.out.println(DateUtil.getNext150Seconds(DateUtil.getCurrentTimeString()));
        //ystem.out.println(DateUtil.getCurrentTimeString());
        System.out.println(isMoreThanOneHour("17:23:00", "18:23:00"));
    }


    /**
     * 查找前第30天
     *
     * @param time
     * @return
     */
    public static String getLast30Day(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c1.add(Calendar.DATE, -30);
        return sdf.format(c1.getTime());
    }

    /**
     * 查找前第20周
     *
     * @param time
     * @return
     */
    public static String getLast20Week(String time) {
        SimpleDateFormat YYYY_MM_dd = new SimpleDateFormat(PATTERN_yyyy_MM_dd);
        String[] days = new String[2];
        try {
            days = getWeek(time).split("_");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(YYYY_MM_dd.parse(days[0]));
            calendar.add(Calendar.DATE, -133);
            String end = YYYY_MM_dd.format(calendar.getTime());
            calendar.add(Calendar.DATE, -6);
            String start = YYYY_MM_dd.format(calendar.getTime());
            return start + "_" + end;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 查找前第24月
     *
     * @param time
     * @return
     */
    public static String getLast24Month(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c1.add(Calendar.MONTH, -24);
        return sdf.format(c1.getTime());
    }

    public static String getLast10Year(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c1.add(Calendar.YEAR, -10);
        return sdf.format(c1.getTime());
    }

    @Test
    public void testgetLast() throws ParseException {
//        logger.debug(DateUtil.getWeeks("2016-12-15_2016-12-21"));
        logger.info(DateUtil.getDays("2016-12-27"));
    }

    /**
     * 传入日期格式为YYYY 得到前10年的年日期list
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static List<String> getLast10Years(String time) throws ParseException {
        SimpleDateFormat YYYY = new SimpleDateFormat(PATTERN_yyyy);
        List<String> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(YYYY.parse(time));
        for (int i = 0; i < 10; i++) {
            result.add(YYYY.format(calendar.getTime()));
            calendar.add(Calendar.YEAR, -1);
        }
        return result;
    }


    @Test
    public void testLast() {
        logger.info(getLast7weeks("2017-01-05_2017-01-11"));
    }

    public static List<String> getLast7weeks(String wtime) {
        List<String> reuslt = new ArrayList<>();
        String[] time = wtime.split("_");
        String lastTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time[0]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reuslt.add(wtime);
        for (int i = 0; i < 7; i++) {
            c1.add(Calendar.DATE, -1);
            lastTime = sdf.format(c1.getTime());
            c1.add(Calendar.DATE, -6);
            lastTime = sdf.format(c1.getTime()) + "_" + lastTime;
            reuslt.add(lastTime);
        }
        return reuslt;
    }

    /**
     * 传入日期格式YYYY-MM
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static List<String> getLast3Years(String time) throws ParseException {
        SimpleDateFormat YYYY = new SimpleDateFormat(PATTERN_yyyy);
        List<String> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(YYYY.parse(time.split("-")[0]));
        for (int i = 0; i < 3; i++) {
            result.add(YYYY.format(calendar.getTime()));
            calendar.add(Calendar.YEAR, -1);
        }
        return result;
    }

    public static List<String> getTimes(String date) throws ParseException {
        List<String> times = new ArrayList<>();
        switch (date.length()) {
            case 10://日
                times = getDays(date);
                break;
            case 21://周
                times = getWeeks(date);
                break;
            case 7://月
                times = getMonths(date);
                break;
            case 4://年
                times = getLast10Years(date);
                break;
            case 15://季 2016-01_2016-03
                times = getSeasons(date);
                break;
        }
        return times;
    }

    /**
     * 获取前6年的日期
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static List<String> getYears(String time) throws ParseException {
        SimpleDateFormat YYYY = new SimpleDateFormat(PATTERN_yyyy);
        List<String> result = new ArrayList<>();
        String[] days = time.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(YYYY.parse(days[0]));
        for (int i = 0; i < 7; i++) {
            result.add(YYYY.format(calendar.getTime()));
            calendar.add(Calendar.YEAR, -1);
        }
        return result;
    }

    @Test
    public void testGetWeek() {
        try {
            logger.info(getWeek("2017-03-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当周日期 周日到周六
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static String getWeek(String time) throws ParseException {
        SimpleDateFormat YYYY_MM_dd = new SimpleDateFormat(PATTERN_yyyy_MM_dd);
        String Monday;
        String Sunday;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(YYYY_MM_dd.parse(time));
        int now = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -now + 1);
        Monday = YYYY_MM_dd.format(calendar.getTime());
        calendar.add(Calendar.DATE, 6);
        Sunday = YYYY_MM_dd.format(calendar.getTime());

        return Monday + "_" + Sunday;
    }


//    /**
//     * 4.当日获取当周日期  周四到下周三的日期获得上周四到这周三的日期
//     *
//     * @param time
//     * @return
//     * @throws ParseException
//     */
//    public static String getWeek(String time) throws ParseException {
//        //SimpleDateFormat MM_dd = new SimpleDateFormat(PATTERN_MM_dd);
//        SimpleDateFormat YYYY_MM_dd = new SimpleDateFormat(PATTERN_yyyy_MM_dd);
//        String wednesday;
//        String thursday;
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(YYYY_MM_dd.parse(time));
//        // calendar.add(Calendar.DATE, 1);
//        int now = calendar.get(Calendar.DAY_OF_WEEK);
//        if (now <= 4) {
//            calendar.add(Calendar.DATE, 4 - now);
//            wednesday = YYYY_MM_dd.format(calendar.getTime());
//            calendar.add(Calendar.DATE, -6);
//            thursday = YYYY_MM_dd.format(calendar.getTime());
//        } else {
//            calendar.add(Calendar.DATE, 11 - now);
//            wednesday = YYYY_MM_dd.format(calendar.getTime());
//            calendar.add(Calendar.DATE, -6);
//            thursday = YYYY_MM_dd.format(calendar.getTime());
//        }
//        return thursday + "_" + wednesday;
//    }

    /**
     * 1.当前日期获取当周四-下周三日期,return list
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Stack<String> getDays(String time) throws ParseException {
        SimpleDateFormat YYYY_MM_dd = new SimpleDateFormat(PATTERN_yyyy_MM_dd);
        Stack<String> result = new Stack<>();
        String[] days = getWeek(time).split("_");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(YYYY_MM_dd.parse(days[1]));
        for (int i = 0; i < 7; i++) {
            result.add(YYYY_MM_dd.format(calendar.getTime()));
            calendar.add(Calendar.DATE, -1);
        }
        return result;
    }

    /**
     * 当周日期获取前7周日期
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static Stack<String> getWeeks(String time) throws ParseException {
        Stack<String> result = new Stack<>();
        SimpleDateFormat YYYY_MM_dd = new SimpleDateFormat(PATTERN_yyyy_MM_dd);
        String[] days = getWeek(time).split("_");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(YYYY_MM_dd.parse(days[0]));
        result.push(getWeek(time));
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.DATE, -1);
            String end = YYYY_MM_dd.format(calendar.getTime());
            calendar.add(Calendar.DATE, -6);
            String start = YYYY_MM_dd.format(calendar.getTime());
            result.push(start + "_" + end);
        }
        return result;
    }

    /**
     * 格式转换
     * yyyy-MM-dd_yyyy-MM-dd to MM.dd-MM.dd
     *
     * @param week_time
     * @return
     */
    public static String convertMM_dd(String week_time) {
        if (week_time.length() != 21) {
            return new String();
        }
        StringBuilder sb = new StringBuilder();
        String[] days = week_time.split("_");
        String[] day_start = days[0].split("-");
        String[] day_end = days[1].split("-");
        sb.append(day_start[1])
                .append(".")
                .append(day_start[2])
                .append("-")
                .append(day_end[1])
                .append(".")
                .append(day_end[2]);
        return sb.toString();
    }

    @Test
    public void calendar() throws ParseException {
        logger.info(getSeasons("2016-01_2016-03"));
//        logger.debug("2016-11-17_2016-11-24".length());

        //String[] days = getWeek("2016-11-23").split("-");
        //logger.debug(convertMM_dd("2016-11-17_2016-11-23"));
    }

    /**
     * 获取上一个月
     *
     * @param time
     * @return
     */
    public static String getLastMonthTime(String time) {
        String lastTime = null;
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08",
                "09", "10", "11", "12"};
        String month = time.split("-")[1];
        for (int i = 0; i < months.length; i++) {
            if (month.equals("01")) {
                lastTime = (Integer.valueOf(time.split("-")[0]) - 1) + "-"
                        + months[11];
            } else if (months[i].equals(month)) {
                lastTime = time.split("-")[0] + "-" + months[i - 1];
            }
        }
        return lastTime;
    }

    /**
     * 获取上一周
     * 2016-12-01_2016-12-07
     *
     * @param wtime
     * @return
     */
    public static String getLastWeek(String wtime) {
        String[] time = wtime.split("_");
        String lastTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time[0]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c1.add(Calendar.DATE, -7);
        lastTime = sdf.format(c1.getTime());
//      c1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(time[1]));
        c1.add(Calendar.DATE, 6);
        lastTime = lastTime + "_" + sdf.format(c1.getTime());
        return lastTime;
    }

    /**
     * 获取上一天
     *
     * @param time
     * @return
     */
    public static String getLastDay(String time) {
        String lastDayString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c1.add(Calendar.DATE, -1);
        lastDayString = sdf.format(c1.getTime());
        return lastDayString;
    }

    public static String getNextDay(String time) {
        String lastDayString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c1.add(Calendar.DATE, 1);
        lastDayString = sdf.format(c1.getTime());
        return lastDayString;
    }

    /**
     * 获得当年12月
     *
     * @param date
     * @return
     */
    public static List<String> getMonths(String date) {
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08",
                "09", "10", "11", "12"};
        List<String> result = new ArrayList<>();
        String year = date.split("-")[0];
        for (int i = months.length - 1; i >= 0; i--) {
            result.add(year + "-" + months[i]);
        }
        return result;
    }

    /**
     * 获得当年的四个季度
     * 2016-01_2016-03
     *
     * @param date 传入参数格式为yyyy-MM 或yyyy-MM_yyyy-mm
     * @return
     */
    public static List<String> getSeasons(String date) {
        String[] str = date.split("-");
        String year = str[0];
        List<String> result = new ArrayList<>();
        StringBuilder firstSeason = new StringBuilder();
        firstSeason.append(year).append("-01_").append(year).append("-03");
        StringBuilder secondSeason = new StringBuilder();
        secondSeason.append(year).append("-04_").append(year).append("-06");
        StringBuilder thirdSeason = new StringBuilder();
        thirdSeason.append(year).append("-07_").append(year).append("-09");
        StringBuilder fourthSeason = new StringBuilder();
        fourthSeason.append(year).append("-10_").append(year).append("-12");
        result.add(fourthSeason.toString());
        result.add(thirdSeason.toString());
        result.add(secondSeason.toString());
        result.add(firstSeason.toString());
        return result;
    }

    /**
     * 将数据库的time转换成别的展现形式
     *
     * @param time
     * @return
     */
    public static String convert(String time) {
        switch (time.length()) {
            //2017-01-10
            case 10:
                String[] days = time.split("-");
                return days[1] + "." + days[2];
            case 7://2017-01
                String[] times = time.split("-");
                if (times[1].startsWith("0")) {
                    return times[1].substring(1) + "月";
                } else {
                    return times[1] + "月";
                }
            case 4:
                return time + "年";
            case 15:
                String[] month = time.split("_");
                String[] str = month[0].split("-");
                if (str[1].equals("01")) {
                    return "第一季度";
                } else if (str[1].equals("04")) {
                    return "第二季度";
                } else if (str[1].equals("07")) {
                    return "第三季度";
                } else if (str[1].equals("10")) {
                    return "第四季度";
                }
                break;
            case 21:
                return convertMM_dd(time);
            default:
                return time;
        }
        return null;
    }

    /**
     * 获取相邻上一期时间
     *
     * @param time
     * @return
     */
    public static String getLastTime(String time) {
        switch (time.length()) {
            //"2016-11-11"
            case 10:
                return getLastDay(time);
            case 21:
                //周2016-11-17_2016-11-24
                return getLastWeek(time);
            case 7:
                return getLastMonthTime(time);
            //月2016-11
            case 15:
                return getLastSeason(time);
            //月2016-10_2016-12
            case 4:
                return getLastYear(time);
            //月2016-11
            default:
                return null;
        }
    }

    @Test
    public void test() throws ParseException {
        logger.info(getLast8Season("2016-10_2016-12"));

        //logger.info(convert("2016-01"));
    }

    public static String getLastYear(String time) {
        String lastTimeString;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c1.add(Calendar.YEAR, -1);
        lastTimeString = sdf.format(c1.getTime());
        return lastTimeString;
    }

    /**
     * 根据频率获取当期日期
     *
     * @param rate
     * @return
     * @throws ParseException
     */
    public static String getNowTime(String rate) throws ParseException {
        String result = null;
        Date date = new Date();
        switch (rate) {
            case "当日":
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                result = sdf.format(date);
                break;
            case "当周":
                result = getWeek(getNowTime("当日"));
                break;
            case "当月":
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(date);
                result = sdf1.format(date);
                break;
            case "当季":
                String month = getNowTime("当月");
                String[] time = month.split("-");
                if (time[1].compareTo("09") > 0 && time[1].compareTo("12") <= 0) {
                    result = time[0] + "-10_" + time[0] + "-12";
                } else if (time[1].compareTo("06") > 0 && time[1].compareTo("09") <= 0) {
                    result = time[0] + "-07_" + time[0] + "-09";
                } else if (time[1].compareTo("03") > 0 && time[1].compareTo("06") <= 0) {
                    result = time[0] + "-04_" + time[0] + "-06";
                } else {
                    result = time[0] + "-01_" + time[0] + "-03";
                }
                break;
            case "当年":
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(date);
                result = sdf2.format(date);
                break;
        }
        return result;
    }

    /**
     * 01/04/07/10
     * 获取上一季
     *
     * @param time
     * @return
     */
    public static String getLastSeason(String time) {
        //2016-01_2016-03
        String[] months = time.split("_");
        String year = months[0].split("-")[0];
        String month = months[0].split("-")[1];
        if (month.equals("10")) {
            return year + "-07_" + year + "-09";
        } else if (month.equals("07")) {
            return year + "-04_" + year + "-06";
        } else if (month.equals("04")) {
            return year + "-01_" + year + "-03";
        } else {
            return getLastYear(year) + "-10_" + getLastYear(year) + "-12";
        }
    }

    public final static long getDateLong() {
        return (new Date()).getTime();
    }

    public static double getCurrentTime_fortoken() {
        return Double.parseDouble(getCurrentTimeString().substring(0, 13));
    }

    public static String parseDateToHttp(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat _df = new SimpleDateFormat(PATTERN_HTTP, Locale.US);
        return _df.format(date);
    }

    public static Date parseLongToDate(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal.getTime();
    }

    public static long getTimeDiff(Date begin_date, Date end_date) {
        return end_date.getTime() - begin_date.getTime();
    }

    /**
     * 获取下一个月
     *
     * @param time
     * @return
     */
    public static String getNextMonthTime(String time) {
        String nextTime = null;
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08",
                "09", "10", "11", "12"};
        String month = time.split("-")[1];
        for (int i = 0; i < months.length; i++) {
            if (month.equals("12")) {
                nextTime = (Integer.valueOf(time.split("-")[0]) + 1) + "-"
                        + months[0];
            } else if (months[i].equals(month)) {
                nextTime = time.split("-")[0] + "-" + months[i + 1];
            }
        }
        return nextTime;
    }

    /**
     * 两个日期的间隔天数 日期格式为yyyy-MM-dd
     *
     * @param beginDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static int getDays(String beginDate, String endDate)
            throws ParseException {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(beginDate));
        c2.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        long length = c2.getTimeInMillis() - c1.getTimeInMillis();
        int days = new Long(length / (1000 * 60 * 60 * 24)).intValue();
        return days;
    }

    public static String getLast8Season(String time) {
        StringBuffer sb = new StringBuffer();
        String[] months = time.split("_");
        String year = months[0].split("-")[0];
        String last2Year = getLastYear(getLastYear(year));
        String month_start = months[0].split("-")[1];
        String month_end = months[1].split("-")[1];
        sb.append(last2Year).append("-").append(month_start).append("-").append(last2Year).append("-").append(month_end);
        return sb.toString();
    }

    public static String getTimeName(String time) {
        switch (time.length()) {
            case 10:
                //"2016-11-11"
                return "日";
            case 21:
                //周2016-11-17_2016-11-24
                return "周";
            case 7:
                //2016-11
                return "月";
            case 15:
                //2016-10_2016-12
                return "季";
            case 4:
                //2016
                return "年";
            default:
                return null;
        }
    }
}
