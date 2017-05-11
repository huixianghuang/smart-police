package com.sibat.util;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateUtil {
    Logger logger = Logger.getLogger(DateUtil.class);

    static DecimalFormat df = new DecimalFormat("##.##");
    private static String PATTERN_yyyy_MM_dd = "yyyy-MM-dd";
    private static String PATTERN_yyyy_MM = "yyyy-MM";
    private static String PATTERN_yy_MM_dd_HHmmss = "yy-MM-dd HH:mm:ss";
    private static String PATTERN_yyyyMMdd_yyyyMMdd = "yyyy-MM-dd_yyyy-MM-dd";
    private static String PATTERN_yyyyMM_yyyyMM = "yyyy-MM_yyyy-MM";
    private static String PATTERN_yyyy = "yyyy";
    private static String PATTERN_MM_dd = "MM-dd";
    private static String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    private static String PATTERN_yyyy_MM_dd_HHmmss = "yyyy-MM-dd HH:mm:ss";
    private static String PATTERN_HHmmss = "HH:mm:ss";
    private static String PATTERN_yyyyMMdd = "yyyyMMdd";
    private static String PATTERN_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    private static String PATTERN_HTTP = "EEE, dd MMM yyyy HH:mm:ss zzz";

    private static String PATTERN_yy_MM_dd_HHmmss2 = "yy/MM/dd HH:mm:ss";
    private static String PATTERN_yyyy_MM_dd2 = "yyyy/MM/dd";
    private static String PATTERN_yyyyMM = "yyyy/MM";

    @Test
    public void test() {
//        List<String> reustl = getSerialDays("2017/02/01", "2017/02/10");
//        logger.info(reustl);
        logger.info(timeInterval("2016/08/06 12:34:00.000", "2016/08/07 07:50:03.000"));
        logger.info("success");
    }

    public static String timeInterval(String start, String end) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yy_MM_dd_HHmmss2);
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        try {
            calendar1.setTime(sdf.parse(start));
            calendar2.setTime(sdf.parse(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long result = calendar2.getTime().getTime() - calendar1.getTime().getTime();
        return df.format(result / 1000 / 60 / 60.0);
    }

    public static List<String> getSerialDays(String start, String end) {
        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyy_MM_dd2);
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        try {
            calendar1.setTime(sdf.parse(start));
            calendar2.setTime(sdf.parse(end));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayNum = calendar2.get(Calendar.DAY_OF_YEAR) - calendar1.get(Calendar.DAY_OF_YEAR);
        for (int i = 0; i <= dayNum; i++) {
            result.add(sdf.format(calendar1.getTime()));
            calendar1.add(Calendar.DATE, 1);
        }
        return result;
    }

    public static String getCurrentTimeString() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_yy_MM_dd_HHmmss2);
    }

    public static String getCurrentTimePATTERN_yyyy_MM_dd2() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_yyyy_MM_dd2);
    }

    public static String getCurrentTimeString2() {
        return parseDateToString(Calendar.getInstance().getTime(),
                PATTERN_yy_MM_dd_HHmmss);
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
//        System.out.println(isMoreThanOneHour("17:23:00", "18:23:00"));
        try {
            logger.info(getLastMonth("2016/01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentMonth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyyMM);
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    public static String getLastMonth(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyyMM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(time));
        calendar.add(Calendar.MONTH, -1);
        return sdf.format(calendar.getTime());
    }


    public static String getLastYear(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyyMM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(time));
        calendar.add(Calendar.YEAR, -1);
        return sdf.format(calendar.getTime());
    }

    public static String getLast150Seconds(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yy_MM_dd_HHmmss2);
        Calendar c1 = Calendar.getInstance();
        try {
            c1.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c1.add(Calendar.SECOND, -1050);
        return sdf.format(c1.getTime());
    }

    public static String getNext150Seconds(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yy_MM_dd_HHmmss2);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c.add(Calendar.SECOND, -750);
        return sdf.format(c.getTime());
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
//        calendar.setEventTime(YYYY_MM_dd.parse(time));
//        // calendar.add(Calendar.DATE, 1);
//        int now = calendar.get(Calendar.DAY_OF_WEEK);
//        if (now <= 4) {
//            calendar.add(Calendar.DATE, 4 - now);
//            wednesday = YYYY_MM_dd.format(calendar.getEventTime());
//            calendar.add(Calendar.DATE, -6);
//            thursday = YYYY_MM_dd.format(calendar.getEventTime());
//        } else {
//            calendar.add(Calendar.DATE, 11 - now);
//            wednesday = YYYY_MM_dd.format(calendar.getEventTime());
//            calendar.add(Calendar.DATE, -6);
//            thursday = YYYY_MM_dd.format(calendar.getEventTime());
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
        logger.info(getLastMonthTime("2016-02"));
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
//      c1.setEventTime(new SimpleDateFormat("yyyy-MM-dd").parse(time[1]));
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

    public static String getLastDayPattern2(String time) {
        String lastDayString = null;
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_yyyy_MM_dd2);
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


}
