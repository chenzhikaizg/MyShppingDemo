package cn.aiyangkeji.activities.myinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;

/**
 * Created by 18810 on 2017/11/24.
 */

public class AiYangXieYiActivity  extends BaseActivity {
    private TextView tvTitle;
    private TextView tvContent;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiyang_xieyi);
    }

    @Override
    public void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvContent = (TextView) findViewById(R.id.tv_content);
        ivBack = (ImageView)findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AiYangXieYiActivity.this.finish();
            }
        });
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        tvTitle.setText("营养师入驻爱样营养师平台线上合作合同协议");
        tvContent.setText(getResources().getString(R.string.register_agreement));
    }

}
