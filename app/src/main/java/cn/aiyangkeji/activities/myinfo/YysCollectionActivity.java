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
import cn.aiyangkeji.adapter.AttentionProductAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by chenzhikai on 2017/11/25.
 */

public class YysCollectionActivity extends BaseActivity {

    private ImageView ivBack;
    private AutoListView mLv;
    private View headview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yys_collection);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("我的收藏");
        ivBack = (ImageView)findViewById(R.id.iv_back);
        mLv = (AutoListView)findViewById(R.id.lv_yys_collection);
        headview = LayoutInflater.from(this).inflate(R.layout.list_view_head_null, null);
        mLv.initView(this, headview);
    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void initData() {

        AttentionProductAdapter yingYangShiAdapter = new AttentionProductAdapter(this,clickListener);
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
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_back:
                this.finish();
                break;





        }
    }

    View.OnClickListener clickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(YysCollectionActivity.this, YingTangShiDetailActivity.class);
            startActivity(intent);
        }
    };
}
