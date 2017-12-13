package cn.aiyangkeji.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;



import cn.aiyangkeji.bean.UserBean;

public class UserInfoUtil {

    private static UserBean.Customer userInfo;
    private static boolean isLogin;


    public static UserBean.Customer getUserInfo(Context context) {
        if (userInfo == null) {
            userInfo = new UserBean.Customer();
            SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
            userInfo.customerId = sharedPreferences.getInt("userId", 0);
          //  userInfo.token = sharedPreferences.getString("token", "");
            userInfo.mobile = sharedPreferences.getString("phone", "");
            userInfo.nickName = sharedPreferences.getString("nickname","");
            userInfo.headimgurl = sharedPreferences.getString("icon","");
          //  userInfo.diamond = sharedPreferences.getInt("diamond",0);
         //    userInfo.isFirst = sharedPreferences.getBoolean("isFirst",false);

        }
        return userInfo;
    }


//   public static String getToken(Context context) {
//        return getUserInfo(context).token;
//    }


    public static int getUserId(Context context) {
        return getUserInfo(context).customerId;

    }
    public static String getUserIcon(Context context) {
        return getUserInfo(context).headimgurl;

    }
    public static String getUserNickName(Context context) {
        return getUserInfo(context).nickName;

    }

//    public static boolean getUserFirst(Context context) {
//        return getUserInfo(context).isFirst;
//
//    }

    public static boolean isLogin(Context context) {
        int customerId = getUserInfo(context).customerId;
        isLogin = customerId != 0 ? true : false;
        return isLogin;
    }

    public static void saveUserInfo(Context context, UserBean.Customer userInfo) {
        UserInfoUtil.userInfo = userInfo;
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        sharedPreferences.edit().putInt("userId", userInfo.customerId).commit();
   //     sharedPreferences.edit().putString("token", userInfo.token).commit();
        sharedPreferences.edit().putString("phone", userInfo.mobile).commit();
        sharedPreferences.edit().putString("nickname",userInfo.nickName).commit();
        sharedPreferences.edit().putString("icon",userInfo.headimgurl).commit();
        sharedPreferences.edit().putBoolean("isFirst",false).commit();



    }

    public static void setLogout(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        sharedPreferences.edit().putInt("userId", 0).commit();
        sharedPreferences.edit().putString("token", "").commit();
        sharedPreferences.edit().putString("phone", "").commit();
     //t   int customerId = getUserInfo(context).customerId;
      //  userInfo.token = "";
        userInfo.customerId = 0;
    }
    public static void saveUserDiamond(Context context, int diamond) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        sharedPreferences.edit().putInt("diamond",diamond).commit();



    }
    public static void saveUserIsFirst(Context context, boolean isFirst) {
        UserInfoUtil.userInfo = userInfo;
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("isFirst", isFirst).commit();



    }

}
