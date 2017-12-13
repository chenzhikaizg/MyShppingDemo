package cn.aiyangkeji.activities.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;


import org.json.JSONObject;

import cn.aiyangkeji.R;
import cn.aiyangkeji.app.MyApplication;


/**
 * Created by chenzhikai on 17/10/31.
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements OnClickListener {

    protected MyApplication myApp;


    /**
     * 判断是否跳转过登录页面  1跳转过 0没有
     */
    protected int loginFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp = MyApplication.getInstance();
        myApp.addAllActivity(this);

    }

    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID == -1) {
            super.setContentView(R.layout.view_null);
        } else {
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
      MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
       MobclickAgent.onPause(this);
    }


    public void showLongMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showShortMsg(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     * 判断token是否失效
     */



    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


        setContentView(-1);
        System.gc();
    }
}
