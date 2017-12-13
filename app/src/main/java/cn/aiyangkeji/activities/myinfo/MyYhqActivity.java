package cn.aiyangkeji.activities.myinfo;

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
import cn.aiyangkeji.adapter.YingYangShiAdapter;
import cn.aiyangkeji.adapter.YouHuiQuanAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by chenzhikai on 2017/11/4.
 * 我的问答页面
 */

public class MyYhqActivity extends BaseActivity {

    private ImageView ivback;
    private AutoListView mLv;
    private View header;
    private TextView tvExchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_youhui_quan);

    }

    @Override
    public void initView() {
        TextView textView= (TextView)findViewById(R.id.tv_title);
        textView.setText("我的优惠券");
        tvExchange = (TextView)findViewById(R.id.tv_search);
        tvExchange.setText("兑换券");
        tvExchange.setVisibility(View.VISIBLE);
        ivback = (ImageView) findViewById(R.id.iv_back);
        mLv = (AutoListView)findViewById(R.id.lv_yhq);
        header = LayoutInflater.from(this).inflate(R.layout.list_view_head_null, null);
        mLv.initView(this,header);
    }

    @Override
    public void initListener() {
        ivback.setOnClickListener(this);
        tvExchange.setOnClickListener(this);
    }

    @Override
    public void initData() {
        YouHuiQuanAdapter yingYangShiAdapter = new YouHuiQuanAdapter(this,clickListener);
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
        mLv.setAdapter(yingYangShiAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                Intent intent = new Intent(this,ExchangeYhqActivity.class);
                startActivity(intent);
                break;
            default:
                super.onClick(v);
                break;


        }


    }

    View.OnClickListener clickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(MyYhqActivity.this, YingTangShiDetailActivity.class);
            startActivity(intent);
        }
    };
}
