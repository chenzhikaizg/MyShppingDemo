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
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by chenzhikai on 2017/11/4.
 * 我的问答页面
 */

public class MyYySActivity extends BaseActivity {

    private ImageView ivback;
    private AutoListView lvYys;
    private View headview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_yys);

    }

    @Override
    public void initView() {
        TextView textView= (TextView)findViewById(R.id.tv_title);
        textView.setText("我的营养师");
        ivback = (ImageView) findViewById(R.id.iv_back);

        lvYys = (AutoListView)findViewById(R.id.lv_yys);
        headview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_view_head_null, null);
        lvYys.initView(getApplicationContext(),headview);
    }

    @Override
    public void initListener() {
        ivback.setOnClickListener(this);
    }

    @Override
    public void initData() {
        YingYangShiAdapter yingYangShiAdapter = new YingYangShiAdapter(getApplicationContext(),clickListener);
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
        lvYys.setAdapter(yingYangShiAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            default:
                super.onClick(v);
                break;


        }


    }

    View.OnClickListener clickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(getApplicationContext(), YingTangShiDetailActivity.class);
            startActivity(intent);
        }
    };
}
