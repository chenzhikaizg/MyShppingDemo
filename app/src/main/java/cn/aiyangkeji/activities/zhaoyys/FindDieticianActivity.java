package cn.aiyangkeji.activities.zhaoyys;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.YingTangShiDetailActivity;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.adapter.YingYangShiAdapter;
import cn.aiyangkeji.adapter.ZhaoYingYangShiAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by chenzhikai on 2017/11/3.
 * 找营养师
 */

public class FindDieticianActivity extends BaseActivity {

    private AutoListView lvYys;
    private View view;
    private RelativeLayout rlYunQiYy;
    private Intent intent;
    private ImageView ivBack;
    private RelativeLayout rlBuRuYy;
    private RelativeLayout rl_youYingYy;
    private RelativeLayout rlErTongYy;
    private RelativeLayout rlYunDongYy;
    private RelativeLayout rlLinChuangYy;
    private RelativeLayout rlWdTouTing;
    private RelativeLayout rlQuickWd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_dietician);
    }

    @Override
    public void initView() {
        lvYys = (AutoListView)findViewById(R.id.lv_yys);
        ivBack = (ImageView)findViewById(R.id.iv_back);

        view = LayoutInflater.from(this).inflate(R.layout.zhao_yys_header, null);
        lvYys.initView(this,view);
        rlYunQiYy = (RelativeLayout)view.findViewById(R.id.rl_yunqi_yy);
        rlBuRuYy = (RelativeLayout)view.findViewById(R.id.rl_buru_yy);
        rl_youYingYy = (RelativeLayout)view.findViewById(R.id.rl_yingyou_yy);
        rlErTongYy = (RelativeLayout)view.findViewById(R.id.rl_ertong_yy);
        rlYunDongYy = (RelativeLayout)view.findViewById(R.id.rl_yundong_yy);
        rlLinChuangYy = (RelativeLayout)view.findViewById(R.id.rl_linchuang_yy);
        rlWdTouTing = (RelativeLayout)view.findViewById(R.id.rl_wend_tting);
        rlQuickWd = (RelativeLayout)view.findViewById(R.id.rl_quick_wend);
    }

    @Override
    public void initListener() {
        rlYunQiYy.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        rlBuRuYy.setOnClickListener(this);
        rl_youYingYy.setOnClickListener(this);
        rlErTongYy.setOnClickListener(this);
        rlYunDongYy.setOnClickListener(this);
        rlLinChuangYy.setOnClickListener(this);
        rlWdTouTing.setOnClickListener(this);
        rlQuickWd.setOnClickListener(this);




    }

    @Override
    public void initData() {
        YingYangShiAdapter yingYangShiAdapter = new YingYangShiAdapter(this,clickListener);
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
            case R.id.rl_yunqi_yy:
                intent = new Intent(this,YunQiYyActivity.class );
                startActivity(intent);
                break;
            case R.id.rl_buru_yy:
                intent = new Intent(this,YunQiYyActivity.class );
                startActivity(intent);
                break;
            case R.id.rl_yingyou_yy:
                intent = new Intent(this,YunQiYyActivity.class );
                startActivity(intent);
                break;
            case R.id.rl_ertong_yy:
                intent = new Intent(this,YunQiYyActivity.class );
                startActivity(intent);
                break;
            case R.id.rl_yundong_yy:
                intent = new Intent(this,YunQiYyActivity.class );
                startActivity(intent);
                break;
            case R.id.rl_linchuang_yy:
                intent = new Intent(this,YunQiYyActivity.class );
                startActivity(intent);
                break;
            case R.id.rl_wend_tting:
                intent = new Intent(this,WenDaTtingActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_quick_wend:
                intent = new Intent(this,QuickWenDaActivity.class);
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
            Intent intent =new Intent(FindDieticianActivity.this, YingTangShiDetailActivity.class);
            startActivity(intent);
        }
    };
}
