package cn.aiyangkeji.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.bulong.rudeness.RudenessScreenHelper;
import com.bumptech.glide.request.target.ViewTarget;

import com.mob.MobApplication;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.huanxin.DemoHelper;

import static com.umeng.analytics.MobclickAgent.EScenarioType.E_UM_NORMAL;

/**
 * Created by 18810 on 2017/10/31.
 */

public class MyApplication extends MobApplication {

    private static MyApplication mInstance;
    // 上下文菜单
    private Context mContext;
    private List<Activity> smallActivitys = new ArrayList<Activity>();//某个区间段的acivity集合 用于一次性返回到想要的页面
    private List<Activity> allActivitys = new ArrayList<Activity>();//所有activity集合
    private List<Activity> againActivitys = new ArrayList<Activity>();
    public static String currentUserNick = "";

    // 记录是否已经初始化
    private boolean isInit = false;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        MultiDex.install(this);
        mContext = this;
        ViewTarget.setTagId(R.id.glide_tag);

        DemoHelper.getInstance().init(mInstance);
        //环信
        // 初始化环信SDK
      //  initEasemob();


        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
       // EMClient.getInstance().setDebugMode(true);

        //屏幕适配
        //设计图标注的宽度
        int designWidth = 750;
        new RudenessScreenHelper(this, designWidth).activate();


        //友盟
        UMConfigure.init(this, "5a1e6d63f43e486c7e000108", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);
        MobclickAgent.setScenarioType(this, E_UM_NORMAL);
        //禁止默认的页面统计方式，这样将不会再自动统计Activity
        MobclickAgent.openActivityDurationTrack(false);

        getDeviceInfo(mContext);

        UMShareAPI.get(this);//初始化sdk
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式


    }


    //各个平台的配置
    {
        Config.DEBUG = true;
        //微信
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //新浪微博(第三个参数为回调地址)
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com/sina2/callback");
        //QQ
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;

            String mac = null;
            FileReader fstream = null;
            try {
                fstream = new FileReader("/sys/class/net/wlan0/address");
            } catch (FileNotFoundException e) {
                fstream = new FileReader("/sys/class/net/eth0/address");
            }
            BufferedReader in = null;
            if (fstream != null) {
                try {
                    in = new BufferedReader(fstream, 1024);
                    mac = in.readLine();
                } catch (IOException e) {
                } finally {
                    if (fstream != null) {
                        try {
                            fstream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            json.put("mac", mac);
            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     *
     */
//    private void initEasemob() {
//        // 获取当前进程 id 并取得进程名
//        int pid = android.os.Process.myPid();
//        String processAppName = getAppName(pid);
//        /**
//         * 如果app启用了远程的service，此application:onCreate会被调用2次
//         * 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
//         * 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
//         */
//        if (processAppName == null || !processAppName.equalsIgnoreCase(mContext.getPackageName())) {
//            // 则此application的onCreate 是被service 调用的，直接返回
//            return;
//        }
//        if (isInit) {
//            return;
//        }
//        /**
//         * SDK初始化的一些配置
//         * 关于 EMOptions 可以参考官方的 API 文档
//         * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
//         */
//        EMOptions options = new EMOptions();
//        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
//        // options.setAppKey("guaju");
//        // 设置自动登录
//        options.setAutoLogin(true);
//        // 设置是否需要发送已读回执
//        options.setRequireAck(true);
//        // 设置是否需要发送回执，TODO 这个暂时有bug，上层收不到发送回执
//        options.setRequireDeliveryAck(true);
//        // 设置是否需要服务器收到消息确认
//       // options.setRequireServerAck(true);
//
//        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
//        options.setAcceptInvitationAlways(false);
//        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
//        options.setAutoAcceptGroupInvitation(false);
//        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
//        options.setDeleteMessagesAsExitGroup(false);
//        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
//        options.allowChatroomOwnerLeave(true);
//        // 设置google GCM推送id，国内可以不用设置
//        // options.setGCMNumber(MLConstants.ML_GCM_NUMBER);
//        // 设置集成小米推送的appid和appkey
//        // options.setMipushConfig(MLConstants.ML_MI_APP_ID, MLConstants.ML_MI_APP_KEY);
//
//        // 调用初始化方法初始化sdk
//        EMClient.getInstance().init(mContext, options);
//
//        // 设置开启debug模式
//        EMClient.getInstance().setDebugMode(true);
//
//        // 设置初始化已经完成
//        isInit = true;
//    }

    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName;
                    // 返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }

    public static synchronized MyApplication getInstance() {

        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;

    }


    public void addSmallActivity(Activity activity) {
        smallActivitys.add(activity);
    }

    public void addAllActivity(Activity activity) {
        allActivitys.add(activity);
    }

    public void addAgainActivity(Activity activity) {
        againActivitys.add(activity);
    }


    public void finishSmallActivitys() {
        for (Activity activity : smallActivitys) {
            if (activity != null) {
                activity.finish();

            }
        }

    }

    public void finishAllActivitys() {
        for (Activity activity : allActivitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }


    public void deleteOutSideActivitys() {
        if (againActivitys.size() > 5) {
            for (int i = 0; i < againActivitys.size() - 5; i++) {
                if (againActivitys.get(i) != null) {
                    againActivitys.get(i).finish();
                    againActivitys.remove(i);
                }
            }
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
