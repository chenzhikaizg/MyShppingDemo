package cn.aiyangkeji.activities.loginandregist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;



import java.util.HashMap;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.app.AppConfig;
import cn.aiyangkeji.bean.SouguBean;
import cn.aiyangkeji.bean.UserBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.util.JsontoRequestBody;
import cn.aiyangkeji.util.UserInfoUtil;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static android.R.attr.data;

/**
 * Created by chenzhikai on 2017/11/9.
 */

public class RegisterActivity extends BaseActivity {

    private EditText etname;
    private EditText etPhoneNum;
    private Button btnPhoneCode;
    private EditText etPhoneCode;
    private EditText etPassword;
    private EditText etAgainPassword;
    private Button btnRegister;
    private boolean timeStop = true;
    private int time;
    private Intent intent;
    private int i=0;


    private Novate novate;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private Map<String, String> headers = new HashMap<>();

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -1) {
                //修改控件文本进行倒计时  i 以60秒倒计时为例
              //  btnSendMsg.setText( i+" s");
            } else if (msg.what == -2) {
                //修改控件文本，进行重新发送验证码
//                btnSendMsg.setText("重新发送");
//                btnSendMsg.setClickable(true);
               i = 60;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;

                // 短信注册成功后，返回MainActivity,然后提示
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    // 提交验证码成功,调用注册接口，之后直接登录
                    //当号码来自短信注册页面时调用登录注册接口
                    //当号码来自绑定页面时调用绑定手机号码接口
            //    showShortMsg("验证码正确");
                    register();

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    Toast.makeText(getApplicationContext(), "验证码已经发送",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ((Throwable) data).printStackTrace();
                }
              if (result == SMSSDK.RESULT_ERROR) {
                try {
                    Throwable throwable = (Throwable) data;
                    throwable.printStackTrace();
                 //   JSONObject object = new JSONObject(throwable.getMessage());
                //    String des = object.optString("detail");//错误描述
                //    int status = object.optInt("status");//错误代码
//                    if (status > 0 && !TextUtils.isEmpty(des)) {
//                        Toast.makeText(RegisterActivity.this, des, Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                } catch (Exception e) {
                    //do something
                }
            }
        }
    }
};
    private String strPhone;


    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            --time;
            if(time==0)
            {
                btnPhoneCode.setText("验证码");

                btnPhoneCode.setEnabled(true);
                btnPhoneCode.setBackgroundResource(R.drawable.bg_shape_pink_shixin_corners_small);

            }else {
                if(timeStop)
                {
                    btnPhoneCode.setText(time+"S");
                    mHandler.postDelayed(runnable,1000);
                }
            }


        }
    };
    private String strCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;

                System.out.println("result-----" + result);
                System.out.println("data-----" + data);

                mHandler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("注册");
        etname = (EditText)findViewById(R.id.et_name);
        etPhoneNum = (EditText)findViewById(R.id.et_phone_num);
        btnPhoneCode = (Button) findViewById(R.id.btn_sign_code);
        etPhoneCode = (EditText)findViewById(R.id.et_phone_code);
        etPassword = (EditText)findViewById(R.id.et_password);
        etAgainPassword = (EditText)findViewById(R.id.et_again_password);
        btnRegister = (Button)findViewById(R.id.btn_register);


    }

    @Override
    public void initListener() {
        btnPhoneCode.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sign_code:
                strPhone = etPhoneNum.getText().toString().trim();
                    getPhoneCode(strPhone);
                break;
            case  R.id.btn_register:

                strCode = etPhoneCode.getText().toString().trim();
                SMSSDK.submitVerificationCode("86", strPhone, strCode);
                break;
            default:
                super.onClick(v);
                break;


        }




    }

    private void register() {
        String strPhone=etPhoneNum.getText().toString().trim();
        strCode = etPhoneCode.getText().toString().trim();
        String strRecommedCode="";
        String strNick=etname.getText().toString().trim();
        String strPass=etPassword.getText().toString().trim();
        String strPassConfirm = etAgainPassword.getText().toString().trim();

        if(strPhone.length()!=11)
        {
            showShortMsg("请输入11位手机号码！");
            return;
        }else if(etPhoneCode.length()!=4){
            showShortMsg("请输入4位验证码！");
            return;
        }else if("".equals(strNick)){
            showShortMsg("昵称不能为空！");
            return;
        }else if("".equals(strPass)){
            showShortMsg("密码不能为空！");
            return;
        }else if (!strPass.equals(strPassConfirm)){
            showShortMsg("两次密码不一致");
            return;
        }

        parameters.clear();
        parameters.put("mobile", strPhone);
        parameters.put("password", strPass);
        parameters.put("nickName", strNick);
      //  parameters.put("headimagurl", "souguapp");
        parameters.put("source", "android");
        Novate novate = new MyRetrofit(this).getMyApiService();
        MyAPI myAPI = novate.create(MyAPI.class);
        novate.call(myAPI.register(JsontoRequestBody.parameters2json(parameters)),
                new MyBaseSubscriber<UserBean>(RegisterActivity.this) {


                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(UserBean souguBean) {
                        if (souguBean.resultCode==200){
                            UserBean.Customer userInfo=new UserBean.Customer();
                            userInfo.customerId = souguBean.data.customer.customerId;
                            userInfo.nickName = souguBean.data .customer.nickName;
                            UserInfoUtil.saveUserInfo(RegisterActivity.this,userInfo);

                            Toast.makeText(RegisterActivity.this, souguBean.toString(), Toast.LENGTH_SHORT).show();
                            showShortMsg("恭喜您注册成功");
                            setResult(AppConfig.LOGIN_RESULT_SUCESS_CODE);
                            RegisterActivity.this.finish();
                        }else {
                            showShortMsg(souguBean.message);
                        }

                    }
                });


    }

    private void getPhoneCode(String s) {

        String strCode=etPhoneCode.getText().toString().trim();
        if(s.length()!=11)
        {
            showShortMsg("请输入11位手机号码！");
            return;
        }
        time=AppConfig.GET_CODE_TIME;
        btnPhoneCode.setText(time+"S");
        btnPhoneCode.setEnabled(false);
        btnPhoneCode.setBackgroundResource(R.drawable.bg_shape_grey_no_border);
      mHandler.postDelayed(runnable,1000);
        btnPhoneCode.setVisibility(View.VISIBLE);


        SMSSDK.getVerificationCode("86", strPhone);
       // btnSendMsg.setClickable(false);
        //开始倒计时
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; i > 0; i--) {
                    mHandler.sendEmptyMessage(-1);
                    if (i <= 0) {
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //倒计时结束执行
                mHandler.sendEmptyMessage(-2);
            }
        }).start();






       // mHandler.removeCallbacks(runnable);

    }
}
