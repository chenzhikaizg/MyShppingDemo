package cn.aiyangkeji.activities.myinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;

/**
 * Created by ${chenzhikai} on 2017/11/28.
 */

public class ExchangeYhqActivity extends BaseActivity {

    private ImageView ivBAack;
    private Button btnConfirm;
    private EditText etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_yhq);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("兑换券");
        ivBAack = (ImageView)findViewById(R.id.iv_back);
        btnConfirm = (Button)findViewById(R.id.btn_confirm);
        etCode = (EditText)findViewById(R.id.et_number);
    }

    @Override
    public void initListener() {
        ivBAack.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
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
            case R.id.btn_confirm:
                etCode.setText("");
                showShortMsg("兑换成功");
                break;
            default:
                super.onClick(v);
                break;



        }
    }
}
