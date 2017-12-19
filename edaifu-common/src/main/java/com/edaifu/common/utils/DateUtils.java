package com.edaifu.common.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils
{
  public static String pattern = "yyyy-MM-dd";
  public static String datetimePattern = "yyyy-MM-dd HH:mm:ss";
  public static SimpleDateFormat sdf = new SimpleDateFormat(pattern);

  public static Date getNowDate()
  {
    Date d = new Date();
    return d;
  }

  public static String getDate(Date date, String pattern)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    return sdf.format(date);
  }

  public static String getDate()
  {
    return getDate(getNowDate(), pattern);
  }

  public static String getDate(String pattern)
  {
    return getDate(getNowDate(), pattern);
  }

  public static String getDatetime()
  {
    return getDate(getNowDate(), pattern);
  }

  public static long getTime()
  {
    return getNowDate().getTime();
  }

  public static Date parse(String source, String pattern)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    try {
      return sdf.parse(source);
    }
    catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Date addDay(Date date, int count)
  {
    return compute(date, count, 5);
  }

  public static Date addWeek(Date date, int count)
  {
    return compute(date, count, 3);
  }

  public static Date addMonth(Date date, int count)
  {
    return compute(date, count, 2);
  }

  public static Date addMinute(Date date, int count)
  {
    return compute(date, count, 12);
  }

  public static Date addHour(Date date, int count)
  {
    return compute(date, count, 10);
  }

  public static String format(Date date, String format)
  {
    if (date == null) {
      return "";
    }

    SimpleDateFormat fm = new SimpleDateFormat(format);
    return fm.format(date);
  }

  private static Date compute(Date date, int count, int periodFlag) {
    GregorianCalendar gc = new GregorianCalendar();
    gc.setTime(date);
    gc.add(periodFlag, count);
    return gc.getTime();
  }

  public static int getWeek() {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    return (cal.get(7) - 1); }

  public static boolean synchronizedTime(Date time) {
    String dateStr;
    try {
      dateStr = format(time, "yyyy-MM-dd");
      String timeStr = format(time, "HH:mm:ss");

      System.out.println
        ("调整前的系统时间为[" + 
        format(new Date(), 
        "yyyy-MM-dd HH:mm:ss") + 
        "]");
      Process p = Runtime.getRuntime().exec("cmd.exe /c date " + dateStr);
      int r = p.waitFor();
      if (r == 0)
        System.out.println("调整日期成功!");
      else
        System.out.println("调整日期失败!");

      p = Runtime.getRuntime().exec("cmd.exe /c time " + timeStr);
      r = p.waitFor();
      if (r == 0)
        System.out.println("调整时间成功!");
      else
        System.out.println("调整时间失败!");

      System.out.println
        ("较准后的系统时间为[" + 
        format(new Date(), 
        "yyyy-MM-dd HH:mm:ss") + 
        "]");

      return true;
    }
    catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    catch (InterruptedException e) {
      e.printStackTrace(); }
    return false;
  }

  public static boolean isDate(String str, String format)
  {
    boolean flag = true;
    DateFormat df = new SimpleDateFormat(format);
    df.setLenient(false);
    Date d = null;
    try {
      d = df.parse(str);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return flag;
  }

  public static int containOrBeIncluded(String beginTime1, String endTime1, String beginTime2, String endTime2)
  {
    if (endTime1.compareTo(beginTime2) <= 0)
      return -2;
    if ((beginTime1.compareTo(beginTime2) < 0) && 
      (endTime1.compareTo(beginTime2) > 0) && 
      (endTime1.compareTo(endTime2) <= 0))
      return -1;
    if ((beginTime1.compareTo(beginTime2) == 0) && 
      (endTime1.compareTo(endTime2) == 0))
      return 4;
    if ((beginTime1.compareTo(beginTime2) >= 0) && 
      (endTime1.compareTo(endTime2) <= 0))
      return 0;
    if ((beginTime1.compareTo(beginTime2) <= 0) && 
      (endTime1.compareTo(endTime2) >= 0))
      return 3;
    if ((beginTime2.compareTo(beginTime1) < 0) && 
      (endTime2.compareTo(beginTime1) > 0) && 
      (endTime2.compareTo(endTime1) <= 0))
      return 1;
    if (endTime2.compareTo(beginTime1) <= 0)
      return 2;

    return 4;
  }

  public static boolean compareDatesDifference(Date date1, Date date2, long difference)
  {
    boolean flag = false;
    try {
      long time1 = date1.getTime();
      long time2 = date2.getTime();
      long timediff = time2 - time1;
      if (timediff < difference) 
      flag = true;
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return flag;
  }

  public static boolean compareDateInScope(String compareDate, String beginDate, String endDate)
  {
    return ((beginDate.compareTo(compareDate) <= 0) && (endDate.compareTo(compareDate) >= 0));
  }
}