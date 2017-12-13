package cn.aiyangkeji.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;

/**
 * Created by $chenzhikai on 2017/12/7.
 */

public class SplashActivity extends BaseActivity {
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                SplashActivity.this.finish();
            }
        }, 2000);
    }
}
