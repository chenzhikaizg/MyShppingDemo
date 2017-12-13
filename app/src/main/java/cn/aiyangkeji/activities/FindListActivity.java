package cn.aiyangkeji.activities;

import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;

/**
 * Created by ${chenzhikai} on 2017/11/30.
 */

public class FindListActivity extends BaseActivity {

    private EditText etFind;
    private LinearLayout llFind;
    private RelativeLayout rlSs;
    private String content;
    private TextView tvFind;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_list);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Slide().setDuration(2000));
            getWindow().setExitTransition(new Slide().setDuration(2000));

        }

    }

    @Override
    public void initView() {
        etFind = (EditText)findViewById(R.id.et_find);
        llFind = (LinearLayout)findViewById(R.id.ll_find);
        rlSs = (RelativeLayout)findViewById(R.id.rl_ss);
        tvFind = (TextView)findViewById(R.id.tv_find_text);
        ivBack = (ImageView)findViewById(R.id.iv_back);

    }

    @Override
    public void initListener() {
        llFind.setOnClickListener(this);
        rlSs.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
        content = getIntent().getStringExtra("content");
        tvFind.setText("  "+content);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_find:




                break;
            case R.id.rl_ss:
                this.finish();

              //  overridePendingTransition(R.anim.from_left_to_right,0);
                break;
            case R.id.iv_back:
                this.finish();
                break;
            default:
            super.onClick(v);
            break;



        }

    }
}
