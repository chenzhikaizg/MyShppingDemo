package cn.aiyangkeji.activities.zhaoyys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;

/**
 * Created by 18810 on 2017/11/22.
 * 营养师服务选择月份页面
 */

public class YysServiceCheckActivity extends BaseActivity{
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
    private List<RelativeLayout> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_check);
    }

    @Override
    public void initView() {
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
        rlMoney_one.setOnClickListener(this);
        rlMoneyTwo.setOnClickListener(this);
        rlMoneyThree.setOnClickListener(this);
        rlMoneyFour.setOnClickListener(this);
    }

    @Override
    public void initData() {
        list = new ArrayList<RelativeLayout>();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
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



        if (list.size()==0){
            btn .setBackgroundResource(R.drawable.btn_new_bg_shape_normal_pink);


            list.add(btn);
        }else {
            if (list.get(0)==btn){
                btn.setBackgroundResource(R.drawable.bg_shape_grey_login_border_no_solid_xiao);

                list.clear();
            }else {
                list.clear();
                btn .setBackgroundResource(R.drawable.btn_new_bg_shape_normal_pink);

                list.add(btn);
            }
        }
    }
}
