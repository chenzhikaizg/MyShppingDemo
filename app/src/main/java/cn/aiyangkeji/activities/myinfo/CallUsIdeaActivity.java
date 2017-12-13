package cn.aiyangkeji.activities.myinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.util.HashMap;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.zhaoyys.FindDieticianActivity;
import cn.aiyangkeji.bean.BaseBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.util.JsontoRequestBody;
import cn.aiyangkeji.util.UserInfoUtil;

/**
 * Created by chenzhikai on 2017/11/23.
 * 意见反馈
 */


public class CallUsIdeaActivity extends BaseActivity {

    private TextView tvTitle;
    private ImageView ivBack;
    private EditText etIdea;
    private Button btnConfirm;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private String strSuggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_us_idea);
    }

    @Override
    public void initView() {
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("意见反馈");
        ivBack = (ImageView)findViewById(R.id.iv_back);
        etIdea = (EditText)findViewById(R.id.et_idea);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);


    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch(v.getId()){
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.btn_confirm:
                strSuggestion = etIdea.getText().toString();
                if (strSuggestion.length()==0){
                    showShortMsg("意见不能为空");
                    return;
                }else {
                    commitSuggestion();
                }

                break;




        }

    }

    private void commitSuggestion() {
        Novate novate = new MyRetrofit(this).getMyApiService();
        MyAPI myAPI = novate.create(MyAPI.class);
        parameters.put("customerId", UserInfoUtil.getUserId(this));
        parameters.put("comment",strSuggestion);
        novate.call(myAPI.commitSuggestion(JsontoRequestBody.parameters2json(parameters)), new MyBaseSubscriber<BaseBean>(this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                if (baseBean.resultCode==200){
                    showShortMsg("提交成功");
                }else {
                    showShortMsg(baseBean.message);
                }
            }
        });


    }
}
