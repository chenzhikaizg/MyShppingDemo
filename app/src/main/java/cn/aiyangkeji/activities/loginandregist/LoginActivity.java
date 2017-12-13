package cn.aiyangkeji.activities.loginandregist;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.util.HashMap;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.MainActivity;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.app.AppConfig;
import cn.aiyangkeji.bean.SouguBean;
import cn.aiyangkeji.bean.UserBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.newwork.MyRetrofitFactory;
import cn.aiyangkeji.util.JsontoRequestBody;
import cn.aiyangkeji.util.UserInfoUtil;

/**
 * Created by chenzhikai on 2017/11/9.
 */

public class LoginActivity  extends BaseActivity{

    private TextView tvRegister;
    private EditText etTel;
    private EditText etPassWord;
    private TextView tvForget;
    private Button btnLogin;
    private ImageView ivWeiXin;
    private ImageView ivQq;
    private Intent intent;

    String baseUrl = "http://ip.taobao.com/";
    private Novate novate;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private Map<String, String> headers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("登录");
        tvRegister = (TextView)findViewById(R.id.tv_register);
        etTel = (EditText)findViewById(R.id.et_phone);
        etPassWord = (EditText)findViewById(R.id.et_password);
        tvForget = (TextView)findViewById(R.id.tv_forget);
        btnLogin = (Button)findViewById(R.id.btn_login);
        ivWeiXin = (ImageView)findViewById(R.id.iv_weixin);
        ivQq = (ImageView)findViewById(R.id.iv_qq);
    }

    @Override
    public void initListener() {
        tvRegister.setOnClickListener(this);
        tvForget.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        ivWeiXin.setOnClickListener(this);
        ivQq.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_register:
                intent = new Intent(this, RegisterActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_forget:
                intent = new Intent(this,ForgetActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                    login();
                break;
            case R.id.iv_weixin:

                break;
            case R.id.iv_qq:

                break;
            default:
                super.onClick(v);
                break;


        }
    }

    private void login() {
         String strPhone = etTel.getText().toString().trim();
        String strPass = etPassWord.getText().toString().trim();

//        if (strPhone.length() != 11) {
//            showShortMsg("请输入11位手机号码！");
//            return;
//        } else if ("".equals(strPass)) {
//            showShortMsg("验证码不能为空！");
//            return;
//        }


        parameters.clear();
        parameters.put("mobile", strPhone);
        parameters.put("password", strPass);
        Novate novate = new MyRetrofit(this).getMyApiService();
        MyAPI myAPI = novate.create(MyAPI.class);
        novate.call(myAPI.login(JsontoRequestBody.parameters2json(parameters)), new MyBaseSubscriber<UserBean>(LoginActivity.this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UserBean souguBean) {
                if (souguBean.resultCode == 200){
                    UserBean.Customer customer = new UserBean.Customer();
                    customer.customerId = souguBean.data.customer.customerId;
                    customer.nickName = souguBean.data.customer.nickName;
                    customer.mobile = souguBean.data.customer.mobile;

                    UserInfoUtil.saveUserInfo(LoginActivity.this,customer);

                   // Toast.makeText(LoginActivity.this, souguBean.toString(), Toast.LENGTH_SHORT).show();
                    setResult(AppConfig.LOGIN_RESULT_SUCESS_CODE);
                    LoginActivity.this.finish();
                }else {
                    showShortMsg(souguBean.message);
                }


            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == AppConfig.LOGIN_RESULT_SUCESS_CODE) {
            etTel.setText("");
            etPassWord.setText("");
            this.finish();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            setResult(AppConfig.LOGIN_RESULT_CANCEL_CODE);
            this.finish();

            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
