package cn.aiyangkeji.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.zhaoyys.YysServiceCheckActivity;
import cn.aiyangkeji.adapter.YysDetailImgAdapter;
import cn.aiyangkeji.adapter.YysNowBuyServiceAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.util.ListViewUtil;

/**
 * Created by 18810 on 2017/11/22.
 */

public class YysNowBuyActivity extends BaseActivity {

    private ImageView ivBack;

    private ListView mList;
    private List<MyWenDaBean.ArtImage> list;
    private RelativeLayout rlNowBuy;

    private RelativeLayout rlMoney_one;
    private TextView tvMoneyOne;
    private TextView tvOrderOne;
    private ImageView ivMoneyOne;
    private RelativeLayout rlMoneyTwo;
    private TextView tvMoneyTwo;
    private TextView tvOrderTwo;
    private ImageView ivMoneyTwo;
    private RelativeLayout rlMoneyThree;
    private TextView tvMoneyThree;
    private TextView tvOrderThree;
    private ImageView ivMoneyThree;
    private RelativeLayout rlMoneyFour;
    private TextView tvMoneyFour;
    private TextView tvOrderFour;
    private ImageView ivMoneyFour;
    private List<RelativeLayout> mlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_buy);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("立即下单");
        ivBack = (ImageView)findViewById(R.id.iv_back);


        //立即购买
        rlNowBuy = (RelativeLayout)findViewById(R.id.rl_now_buy);


        mList = (ListView)findViewById(R.id.lv_fuwu);
        ivBack.setFocusable(true);
        ivBack.setFocusableInTouchMode(true);
        ivBack.requestFocus();


        rlMoney_one = (RelativeLayout)findViewById(R.id.rl_money_one);
        tvMoneyOne = (TextView)findViewById(R.id.rl_money_1);
        tvOrderOne = (TextView)findViewById(R.id.tv_order);
        ivMoneyOne = (ImageView)findViewById(R.id.iv_money_one);

        rlMoneyTwo = (RelativeLayout)findViewById(R.id.rl_money_two);
        tvMoneyTwo = (TextView)findViewById(R.id.rl_money_2);
        tvOrderTwo = (TextView)findViewById(R.id.tv_order2);
        ivMoneyTwo = (ImageView)findViewById(R.id.iv_money_two);

        rlMoneyThree = (RelativeLayout)findViewById(R.id.rl_money_three);
        tvMoneyThree = (TextView)findViewById(R.id.rl_money_3);
        tvOrderThree = (TextView)findViewById(R.id.tv_order3);
        ivMoneyThree = (ImageView)findViewById(R.id.iv_money_three);

        rlMoneyFour = (RelativeLayout)findViewById(R.id.rl_money_four);
        tvMoneyFour = (TextView)findViewById(R.id.rl_money_4);
        tvOrderFour = (TextView)findViewById(R.id.tv_order4);
        ivMoneyFour = (ImageView)findViewById(R.id.iv_money_four);

    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);

        rlNowBuy.setOnClickListener(this);

        rlMoney_one.setOnClickListener(this);
        rlMoneyTwo.setOnClickListener(this);
        rlMoneyThree.setOnClickListener(this);
        rlMoneyFour.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mlist = new ArrayList<RelativeLayout>();
        final YysNowBuyServiceAdapter yingYangShiAdapter = new YysNowBuyServiceAdapter(this);
        list = new ArrayList<MyWenDaBean.ArtImage>();
        for (int i=0;i<4;i++){
            MyWenDaBean.ArtImage s = new MyWenDaBean.ArtImage();
            s.img="";
            s.kind="我的tian";
            s.title="我的没人";
            s.money="2";
            list.add(s);
        }
        yingYangShiAdapter.addData(list);


        mList.setAdapter(yingYangShiAdapter);
        ListViewUtil.adaptiveHight(this, mList, 0.3f);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }


        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_back:
                this.finish();
                break;

            case R.id.rl_now_buy:
//                Intent intent = new Intent(this, YysServiceCheckActivity.class);
//                startActivity(intent);
                break;

            case R.id.rl_money_one:
                setYysClass(rlMoney_one);

                break;
            case R.id.rl_money_two:
                setYysClass(rlMoneyTwo);

                break;
            case R.id.rl_money_three:
                setYysClass(rlMoneyThree);

                break;
            case R.id.rl_money_four:
                setYysClass(rlMoneyFour);

                break;

            default:
                super.onClick(v);
                break;



        }


    }


    public void setYysClass(RelativeLayout btn){

        rlMoney_one.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        rlMoneyTwo.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        rlMoneyThree.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);
        rlMoneyFour.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);



        if (mlist.size()==0){
            btn .setBackgroundResource(R.drawable.btn_new_bg_shape_normal_pink);


            mlist.add(btn);
        }else {
            if (mlist.get(0)==btn){
                btn.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);

                mlist.clear();
            }else {
                mlist.clear();
                btn .setBackgroundResource(R.drawable.btn_new_bg_shape_normal_pink);

                mlist.add(btn);
            }
        }
    }

    }

