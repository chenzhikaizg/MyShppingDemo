package cn.aiyangkeji.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.store.YzcYouXuanActivity;
import cn.aiyangkeji.adapter.YingYangShiAdapter;
import cn.aiyangkeji.adapter.YyDJAdapter;
import cn.aiyangkeji.adapter.YyDjListviewAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.util.ListViewUtil;
import cn.aiyangkeji.view.FullyLinearLayoutManager;

/**
 * Created by chenzhikai on 2017/11/3.
 * 营养到家
 */

public class YingYangDaoJiaActivity extends BaseActivity {

    private ImageView ivBack;
    private ListView rvYydj;
    private RelativeLayout rlLjYyue;
    private FrameLayout flYzc;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yy_daojia);
    }

    @Override
    public void initView() {
        TextView tvTtile = (TextView)findViewById(R.id.tv_title);
        tvTtile.setText("营养到家");
        ivBack = (ImageView) findViewById(R.id.iv_back);
        rvYydj = (ListView)findViewById(R.id.rv_yydj_fuwu);
        rlLjYyue= (RelativeLayout)findViewById(R.id.rl_liji_yuyue);

        rlLjYyue.setFocusable(true);
        rlLjYyue.setFocusableInTouchMode(true);
        rlLjYyue.requestFocus();
        flYzc = (FrameLayout)findViewById(R.id.fl_yzc);

      //  FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(this);



    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
        rlLjYyue.setOnClickListener(this);
        flYzc.setOnClickListener(this);
    }

    @Override
    public void initData() {
        YyDjListviewAdapter yingYangShiAdapter = new YyDjListviewAdapter(this);
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
        rvYydj.setAdapter(yingYangShiAdapter);
        ListViewUtil.adaptiveHight(this, rvYydj, 0.3f);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.rl_liji_yuyue:
                intent = new Intent(this,YydjLjOrderActivity.class);
                startActivity(intent);
            break;
            case R.id.fl_yzc:
                intent = new Intent(this, YzcYouXuanActivity.class);
                startActivity(intent);
                break;
            default:
                super.onClick(v);
                break;
        }
    }
}
