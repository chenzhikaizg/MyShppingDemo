package cn.aiyangkeji.activities.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.app.AppConfig;

/**
 * Created by chenzhikai on 2017/11/4.
 * 营养师入驻
 */

public class YingYangShiRuZhuActivity extends BaseActivity {

    private ImageView ivback;
    private ImageView btnTiJiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yys_rz);

    }

    @Override
    public void initView() {
        TextView textView= (TextView)findViewById(R.id.tv_title);
        textView.setText("营养师入驻");
        ivback = (ImageView) findViewById(R.id.iv_back);
        btnTiJiao = (ImageView)findViewById(R.id.btn_tijiao);

    }

    @Override
    public void initListener() {
        ivback.setOnClickListener(this);
        btnTiJiao.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;

            case R.id.btn_tijiao:
                Intent intent = new Intent(this,YysRegisterActivity.class);
                startActivityForResult(intent,0);
                break;
            default:
                super.onClick(v);
                break;


        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== AppConfig.LOGIN_RESULT_SUCESS_CODE){
            this.finish();
        }

    }
}
