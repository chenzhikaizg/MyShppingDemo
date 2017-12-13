package cn.aiyangkeji.activities.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cn.aiyangkeji.R;
import cn.aiyangkeji.app.MyApplication;
import cn.aiyangkeji.view.AutoListView;



/**
 * Created by chenzhikai on 17/11/3.
 */
public abstract class BaseActivity extends Activity implements OnClickListener{
    protected MyApplication myApp;
    protected AutoListView lv;
    protected Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp=MyApplication.getInstance();
        gson=new Gson();
         myApp.addAllActivity(this);
        getDeviceInfo(this);
//        MobclickAgent.setScenarioType(this, E_UM_NORMAL);
//       // MobclickAgent.setDebugMode( true );
//        MobclickAgent.enableEncrypt(false);

    }

    @Override
    public void setContentView(int layoutResID) {
        if(layoutResID==-1){
            super.setContentView(R.layout.view_null);
        }else {
            super.setContentView(layoutResID);
            initView();
            initListener();
            initData();

        }
    }

    /**
     * 获取View对象
     */
    public abstract void initView();

    /**
     * 设置View事件
     */
    public abstract void initListener();

    /**
     * 初始化当前活动数据
     */
    public abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
        MobclickAgent.onResume(this);


    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName() );
        MobclickAgent.onPause(this);
    }

    public void showLongMsg(String msg) {
        Toast toast=Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    public void showShortMsg(String msg)
    {
        Toast toast=Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    /**
     * 判断token是否失效
     */
//    public void tokeIsInvalid(JSONObject responseJson)
//    {
//        try{
//            if(responseJson.getString("error_reason")!=null&&"invalid_token".equals(responseJson.getString("error_reason")))
//            {
//                showShortMsg(responseJson.getString("message"));
//                Intent intent=new Intent(this, TelephoneLoginActivity.class);
//                startActivityForResult(intent, AppConfig.LOGIN_REQUEST_CODE);
//            }else{
//                showShortMsg(responseJson.getString("message"));
//
//            }
//
//        }catch (Exception ex){
//
//        }
//    }


    @Override
    public void onClick(View v) {

    }

//    @Override
//    public void onErrorResponse(VolleyError error) {
//        showLongMsg(VolleyErrorHelper.getMessage(error, this));
//        if(lv!=null)
//        {
//           lv.onRefreshComplete();
//        }
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lv=null;
        gson=null;
        setContentView(-1);
        System.gc();

    }
    public static String getDeviceInfo(Context context) {
        try {
            JSONObject json = new JSONObject();
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
}
