package cn.aiyangkeji.activities.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.loginandregist.ForgetActivity;
import cn.aiyangkeji.app.AppConfig;
import cn.aiyangkeji.util.UserInfoUtil;
import cn.aiyangkeji.view.MyDialog;

/**
 * Created by 18810 on 2017/11/27.
 */

public class SettingActivity extends BaseActivity {

    private TextView tvTitle;
    private ImageView ivBack;
    private RelativeLayout rlClearCache;
    private RelativeLayout rlAmendPassword;
    private RelativeLayout rlExitLogin;
    private Intent intent;
    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }

    @Override
    public void initView() {
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("设置");
        ivBack = (ImageView)findViewById(R.id.iv_back);
        rlClearCache = (RelativeLayout)findViewById(R.id.rl_clear_cache);
        rlAmendPassword = (RelativeLayout)findViewById(R.id.rl_amned_password);
        rlExitLogin = (RelativeLayout)findViewById(R.id.rl_exit_login);

    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
        rlClearCache.setOnClickListener(this);
        rlAmendPassword.setOnClickListener(this);
        rlExitLogin.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.rl_clear_cache:

                break;
            case R.id.rl_amned_password:
                intent = new Intent(this, ForgetActivity.class);
                startActivity(intent);

                break;
            case R.id.rl_exit_login:


                myDialog = new MyDialog(this,0.28f,0.75f);
                myDialog.show();
                View view= LayoutInflater.from(this).inflate(R.layout.dialog_view,null);
                myDialog.addView(view);
                ((TextView)view.findViewById(R.id.tv_content)).setText("您确定要退出登录吗？");
                view.findViewById(R.id.btn_sure).setOnClickListener(this);
                view.findViewById(R.id.btn_cancel).setOnClickListener(this);


                break;
            case R.id.btn_sure:
                myDialog.cancel();
                loginOut();
                break;


            default:
                super.onClick(v);
                break;




        }

    }

    private void loginOut() {
        UserInfoUtil.setLogout(SettingActivity.this);
        setResult(AppConfig.LOGIN_OUT);
        this.finish();
    }
}
