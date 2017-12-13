package cn.aiyangkeji.activities.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.YingTangShiDetailActivity;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.myinfo.MyYhqActivity;
import cn.aiyangkeji.adapter.CustomerEvaluateProductAdapter;
import cn.aiyangkeji.adapter.YouHuiQuanAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by ${chenzhikai} on 2017/11/28.
 */

public class ProductEvaluateActivity extends BaseActivity {


    private TextView tvTitle;
    private ImageView ivBack;
    private AutoListView mlv;
    private View inflate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
    }

    @Override
    public void initView() {
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("用户评论");
        ivBack = (ImageView)findViewById(R.id.iv_back);
        mlv = (AutoListView)findViewById(R.id.lv_evaluate);
        inflate = LayoutInflater.from(this).inflate(R.layout.list_view_head_null, null);
        mlv.initView(this,inflate);
    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
        CustomerEvaluateProductAdapter yingYangShiAdapter = new CustomerEvaluateProductAdapter(this,clickListener);
        List<MyWenDaBean.ArtImage> list =new ArrayList<MyWenDaBean.ArtImage>();
        for (int i=0;i<9;i++){
            MyWenDaBean.ArtImage s = new MyWenDaBean.ArtImage();
            s.img="";
            s.kind="我的tian";
            s.title="我的没人";
            s.money="2";
            list.add(s);
        }
        yingYangShiAdapter.addData(list);
        mlv.setAdapter(yingYangShiAdapter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_back:
                this.finish();
                break;
            default:
                super.onClick(v);
                break;


        }
    }


    View.OnClickListener clickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(ProductEvaluateActivity.this, YingTangShiDetailActivity.class);
            startActivity(intent);
        }
    };
}
