package cn.aiyangkeji.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gaoweiwei on 15/5/19.
 */
public class TimeUtil {

    /**
     * 获取时间，几个小时前，几天前
     * @return
     */
    public static String getTimeDiff(Long longTime) {
        try {
//	        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//			Date date=df.parse(strTime);
            Calendar cal = Calendar.getInstance();
            long diff = 0;
            Date dnow = cal.getTime();
            String str = "";
            //diff = dnow.getTime() - date.getTime();
            diff = dnow.getTime() - longTime;

            if(diff > 2592000000L) {//30 * 24 * 60 * 60 * 1000=2592000000 毫秒
                str="1个月前";
            } else if(diff > 1814400000) {//21 * 24 * 60 * 60 * 1000=1814400000 毫秒
                str="3周前";
            } else if (diff > 1209600000) {//14 * 24 * 60 * 60 * 1000=1209600000 毫秒
                str="2周前";
            } else if(diff > 604800000) {//7 * 24 * 60 * 60 * 1000=604800000 毫秒
                str="1周前";
            }else if (diff > 86400000) {//24 * 60 * 60 * 1000=86400000 毫秒
                //System.out.println("X天前");
                str=(int) Math.floor(diff/86400000f) + "天前";
            } else if (diff > 18000000 ) {//5 * 60 * 60 * 1000=18000000 毫秒
                //System.out.println("X小时前");
                str=(int) Math.floor(diff/18000000f) + "小时前";
            } else if (diff > 60000) {//1 * 60 * 1000=60000 毫秒
                //System.out.println("X分钟前");
                str=(int) Math.floor(diff/60000) +"分钟前";
            }else{
                str=(int) Math.floor(diff/1000) +"秒前";
            }
            return str;
        }catch(Exception ex){
            return "";
        }
    }

    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }


    public static String transferLongToDate(String millSec){
        DateFormat df = new SimpleDateFormat("MM/dd HH:mm");
        Date date= new Date(Long.valueOf(millSec));
        return df.format(date);
    }


    public static String getLiveTime(Long beginTime, Long endTime)
    {
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1= new Date(beginTime);
        DateFormat df2 = new SimpleDateFormat("MM-dd");
        Date date2= new Date(endTime);


        return df1.format(date1)+" 至 "+df2.format(date2);
    }

    /**
     * 判断当前的时间
     * @param l
     * @return
     */
    public static boolean isInCurrentDay(long l){
        long comparedMillons = l;

        //当前年月日
        Calendar c = Calendar.getInstance();
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d = c.get(Calendar.DAY_OF_MONTH);


        c.set(y, m, (d+1), 0,0,0);
        //下一日零点
        long currDayMillions = c.getTimeInMillis();
        c.set(y, m, d, 0,0,0);
        //当日零点
        long preDayMillions = c.getTimeInMillis();

        //零点算新的一天
        return (comparedMillons >= preDayMillions && comparedMillons < currDayMillions);
    }


    /**
     * 获取首页列表上次刷新时间
     */
    public static String getLastRefreshTime(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        String time=sharedPreferences.getString(((Activity)context).getClass().getSimpleName(), "");
        String currentTime=getCurrentTime("yyyy-MM-dd  HH:mm");
        if("".equals(time))
        {
           time=currentTime;
        }
        sharedPreferences.edit().putString(((Activity)context).getClass().getSimpleName(), currentTime).commit();
        return  time;

    }

    /**
     * 获取首页列表上次刷新时间
     */
    public static String formatMasterCodeTime(String curTime){
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(curTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy/MM/dd").format(date);

    }

    /**
     * 获取首页列表上次刷新时间
     */
    public static String formatMasterCodeTime2(Context context, String curTime){
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(curTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(date);

    }

    /**
     * 获取首页列表上次刷新时间
     */
    public static String formatLineChartTime(Context context, String curTime){
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(curTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("MM/dd").format(date);

    }



}
