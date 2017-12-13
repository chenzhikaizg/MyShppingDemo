package cn.aiyangkeji.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.zhaoyys.FindDieticianActivity;
import cn.aiyangkeji.adapter.ErTongZsAdapter;
import cn.aiyangkeji.adapter.YingYangShiAdapter;
import cn.aiyangkeji.adapter.YysDetailImgAdapter;
import cn.aiyangkeji.bean.BannerBean;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.util.ListViewUtil;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by 18810 on 2017/11/23.
 * 推荐服务页面
 */

public class HomeRvYyActivity extends BaseActivity {

    private AutoListView lv;
    private View headerView;
    private ListView lvHead;
    private TextView tvTitle;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye_yy);
    }

    @Override
    public void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("推荐服务");
        ivBack = (ImageView) findViewById(R.id.iv_back);

        lv = (AutoListView) findViewById(R.id.lv_yys);
        //头布局
        headerView = LayoutInflater.from(this).inflate(R.layout.home_rv_yy_banner_layout, null);
        lv.initView(this, headerView);
        lvHead = (ListView) headerView.findViewById(R.id.lv_head);

    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void initData() {

        YysDetailImgAdapter yingYangShiAdapter = new YysDetailImgAdapter(this);
        YingYangShiAdapter erTongZsAdapter = new YingYangShiAdapter(this, clickListener);
        List<MyWenDaBean.ArtImage> list = new ArrayList<MyWenDaBean.ArtImage>();
        for (int i = 0; i < 9; i++) {
            MyWenDaBean.ArtImage s = new MyWenDaBean.ArtImage();
            BannerBean.Typ typ = new BannerBean.Typ();
            typ.img = "";
            typ.count = 1;

            s.img = "";
            s.kind = "我的tian";
            s.title = "我的没人";
            s.money = "2";
            list.add(s);
        }
        erTongZsAdapter.addData(list);
        lv.setAdapter(erTongZsAdapter);

        yingYangShiAdapter.addData(list);
        lvHead.setAdapter(yingYangShiAdapter);
        ListViewUtil.adaptiveHight(this, lvHead, 0.3f);
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(HomeRvYyActivity.this, YingTangShiDetailActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            default:
                super.onClick(v);
                break;

        }


    }
}
