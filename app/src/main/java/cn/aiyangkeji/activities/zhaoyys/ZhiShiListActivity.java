package cn.aiyangkeji.activities.zhaoyys;

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
import cn.aiyangkeji.adapter.MyWenDaTouTingAdapter;
import cn.aiyangkeji.adapter.ZhishiListRightAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by 18810 on 2017/11/21.
 */

public class ZhiShiListActivity extends BaseActivity implements AutoListView.OnRefreshListener,
        AutoListView.OnLoadListener, AutoListView.MyScrollListener{

    private AutoListView mLv;
    private View headview;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhishi_list);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("知识列表");
        ivBack = (ImageView)findViewById(R.id.iv_back);
        mLv = (AutoListView)findViewById(R.id.lv_yys);
        headview = LayoutInflater.from(this).inflate(R.layout.list_view_head_null, null);
        mLv.initView(this,headview);

    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
        ZhishiListRightAdapter adapter = new ZhishiListRightAdapter(this,clickListener);
        List<MyWenDaBean.ArtImage> list =new ArrayList<MyWenDaBean.ArtImage>();
        for (int i=0;i<9;i++){
            MyWenDaBean.ArtImage s = new MyWenDaBean.ArtImage();
            s.img="";
            s.kind="我的tian";
            s.title="我的没人";
            s.money="2";
            s.name="奥特曼";
            list.add(s);
        }
        adapter.addData(list);
        mLv.setAdapter(adapter);


    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onScrollY(int y) {

    }

    View.OnClickListener clickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(ZhiShiListActivity.this, YingTangShiDetailActivity.class);
            startActivity(intent);
        }
    };

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
}
