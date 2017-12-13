package cn.aiyangkeji.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.adapter.YyDjListviewAdapter;
import cn.aiyangkeji.adapter.YysDetailImgAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.huanxin.LoginHuanXinActivity;
import cn.aiyangkeji.util.ListViewUtil;
//import tyrantgit.explosionfield.ExplosionField;

/**
 * Created by chenzhikai on 2017/11/7.
 * 营养师详情页面
 */

public class YingTangShiDetailActivity extends BaseActivity {

    private ImageView ivBack;
    private ListView mlistView;
    private RelativeLayout rlAttention;
    private RelativeLayout rlFreeAsk;
    private RelativeLayout rlNowBuy;
    private Intent intent;
    private ImageView ivCollectionStatus;

    // private ExplosionField mExplosionField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yys_detail);
    }

    @Override
    public void initView() {
//        mExplosionField = ExplosionField.attach2Window(this);
//        addListener(findViewById(R.id.root));
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("营养师详情");
        ivBack = (ImageView)findViewById(R.id.iv_back);
        mlistView = (ListView)findViewById(R.id.lv_img);
        //关注
        rlAttention = (RelativeLayout)findViewById(R.id.rl_attention);
        //免费咨询
        rlFreeAsk = (RelativeLayout)findViewById(R.id.rl_zixun);
        //立即下单
        rlNowBuy = (RelativeLayout)findViewById(R.id.rl_now_buy);
        ivCollectionStatus = (ImageView)findViewById(R.id.iv_collection_status);


    }
    //爆炸效果
//    private void addListener(View root) {
//        if (root instanceof ViewGroup) {
//            ViewGroup parent = (ViewGroup) root;
//            for (int i = 0; i < parent.getChildCount(); i++) {
//                addListener(parent.getChildAt(i));
//            }
//        } else {
//            root.setClickable(true);
//            root.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mExplosionField.explode(v);
//                    v.setOnClickListener(null);
//                }
//            });
//        }
//    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
        rlAttention.setOnClickListener(this);
        rlFreeAsk.setOnClickListener(this);
        rlNowBuy.setOnClickListener(this);



    }

    @Override
    public void initData() {

        YysDetailImgAdapter yingYangShiAdapter = new YysDetailImgAdapter(this);
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
        mlistView.setAdapter(yingYangShiAdapter);
        ListViewUtil.adaptiveHight(this, mlistView, 0.3f);

    }
    private int status= 0;
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.rl_attention:
                if (status==0){
                    ivCollectionStatus.setImageResource(R.mipmap.collection);
                    status=1;
                    showShortMsg("关注成功");
                }else {
                    ivCollectionStatus.setImageResource(R.mipmap.collection_no);
                    status=0;
                    showShortMsg("关注已取消");
                }


                break;
            case R.id.rl_zixun:
                intent = new Intent(this,LoginHuanXinActivity.class);
                startActivity(intent);

                break;
            case R.id.rl_now_buy:
                intent = new Intent(this,YysNowBuyActivity.class);
                startActivity(intent);
                break;



            default:
                super.onClick(v);
                break;
        }

    }
}
