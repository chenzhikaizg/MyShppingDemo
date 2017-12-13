package cn.aiyangkeji.activities.myinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.base.BaseFragmentActivity;
import cn.aiyangkeji.fragmnets.OrderYysFragment;
import cn.aiyangkeji.fragmnets.OrderproductFragment;

/**
 * Created by chenzhikai on 2017/11/4.
 * 我的订单页面
 */

public class MyOrderActivity extends BaseFragmentActivity {

    private ImageView ivback;
    private TextView tvCommodity;
    private TextView tvYys;
    private FragmentManager fragmentManager;
    private OrderYysFragment orderYysFragment;
    private OrderproductFragment orderproductFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

    }

    @Override
    public void initView() {
        TextView textView= (TextView)findViewById(R.id.tv_title);
        textView.setText("我的订单");
        ivback = (ImageView) findViewById(R.id.iv_back);
        tvYys   = (TextView)findViewById(R.id.tv_yys);
        tvCommodity = (TextView)findViewById(R.id.tv_commodity);


    }

    @Override
    public void initListener() {
        ivback.setOnClickListener(this);
        tvCommodity.setOnClickListener(this);
        tvYys.setOnClickListener(this);
    }

    @Override
    public void initData() {
        orderYysFragment = new OrderYysFragment();
        orderproductFragment = new OrderproductFragment();

        fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fl_fragment_container,orderYysFragment).commit();
        fragmentManager.beginTransaction().add(R.id.fl_fragment_container,orderproductFragment).commit();
        replaceFragment(orderYysFragment);
    }
    private void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction().hide(orderYysFragment).commit();
        fragmentManager.beginTransaction().hide(orderproductFragment).commit();


        fragmentManager.beginTransaction().show(fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_commodity:
                setTabStatus(tvCommodity);
                replaceFragment(orderproductFragment);

                break;
            case R.id.tv_yys:
                setTabStatus(tvYys);
                replaceFragment(orderYysFragment);
                break;
            default:
                super.onClick(v);
                break;


        }


    }
    private void setTabStatus(TextView btn) {
        tvYys.setTextColor(getResources().getColor(R.color.pink_word_home));
        tvYys.setBackgroundResource(R.drawable.bg_shape_pink_left_bottom_corner_shixian_kongxin);
        tvCommodity .setBackgroundResource(R.drawable.bg_shape_pink_right_bottom_corner_shixan_kongxin);
        tvCommodity .setTextColor(getResources().getColor(R.color.pink_word_home));
        if (btn==tvYys){
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setBackgroundResource(R.drawable.bg_shape_pink_left_bottom_corner);
        }else {
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setBackgroundResource(R.drawable.bg_shape_pink_right_bottom_corner);
        }


    }
}
