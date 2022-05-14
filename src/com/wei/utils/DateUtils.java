package com.wei.utils;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
    //转换的内部实现方法
    private static DateFormat format2= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static java.sql.Timestamp StringToDate(String datestr){
        java.util.Date temp_date = null;
        java.sql.Timestamp dayDateSql = null;
        try {
            temp_date = format2.parse(datestr);
            dayDateSql = new java.sql.Timestamp(temp_date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayDateSql;
    }

    public static String DateToString(Date date){
        try {
            return format2.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

