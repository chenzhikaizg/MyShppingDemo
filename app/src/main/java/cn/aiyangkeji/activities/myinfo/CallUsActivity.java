package cn.aiyangkeji.activities.myinfo;

import android.os.Bundle;
import android.widget.TextView;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;

/**
 * Created by 18810 on 2017/11/23.
 */

public class CallUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_us);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("联系我们");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
