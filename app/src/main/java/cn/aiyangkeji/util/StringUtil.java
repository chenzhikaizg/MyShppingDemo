package cn.aiyangkeji.util;

import android.text.Html;
import android.text.Spanned;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import cn.aiyangkeji.app.AppConfig;

/**
 * Created by gaoweiwei on 15/5/19.
 */
public class StringUtil {

    public static final int FILL_LEADING = 0;
    public static final int FILL_ENDING = 1;

    public static final String EMPTY = "";

    public static final String BLANK_SPACE = " ";


    public static String moneyFormat(float d){
        DecimalFormat decimalFormat = new DecimalFormat("#######0.00");
        return decimalFormat.format(d);
    }

    public static String decimalFormatTwo(float d){
        DecimalFormat decimalFormat = new DecimalFormat("#######0");
        return decimalFormat.format(d);
    }



    public static JSONObject mapToJson(HashMap hashMap) {
        JSONObject jsonObject = new JSONObject();
        Gson gson = new Gson();
        String s = gson.toJson(hashMap);
        try {
            jsonObject = new JSONObject(s);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonObject;

    }


    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * get请求拼接url字符串
     * @param
     * @param params
     * @return
     */
    public static String setUrlJoin(Map<String,String> params)
    {
            StringBuilder sb = new StringBuilder("");
            if(params != null){
                sb.append('?');
                for(Map.Entry<String, String> entry : params.entrySet()){
                    try {
                        sb.append(entry.getKey()).append('=')
                                .append(URLEncoder.encode(entry.getValue(), AppConfig.CODE_TYPE)).append('&');
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                sb.deleteCharAt(sb.length()-1);
            }
         return sb.toString();
    }

    public static boolean isEmpty(String str) {
        return isNullOrEmpty(str);
    }

    /**
     * Returns true if the String is null or empty.
     *
     * @param str
     *            String to check
     * @return true- is null or empty false - is not null or empty.
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().compareTo("") == 0 || str.equals("null");
    }

    /**
     * 给指定文本设置颜色
     * @param strs  字符串数组 按顺序
     * @param colors 颜色数组 按顺序
     */
    public static Spanned setApointTextColor(String[] strs, String[]separators, String[]colors){
        StringBuilder strHtml=new StringBuilder();
        String start="<html><body>";
        String end="</body></html>";
        for(int i=0;i<strs.length;i++){
           strHtml.append("<font color='"+colors[i]+"'>"+strs[i]+"</font>"+separators[i]);
        }
        return Html.fromHtml(start+strHtml.toString()+end);
    }

    /**
     * 给指定文本设置颜色
     * @param strValue  字符串'*'隔开;
     * @param colorValue 颜色字符串 *隔开 按字符顺序添加
     */
    public static Spanned setTextColor(String strValue, String colorValue){
        StringBuilder strHtml=new StringBuilder();
        String[] strs=strValue.split("[*]");
        String[] colors=colorValue.split("[*]");
        for(int i=0;i<strs.length;i++){
            strHtml.append("<font color='"+ colors[i]+"'>"+strs[i]+"</font>");
        }
        return Html.fromHtml(strHtml.toString());

    }

}
