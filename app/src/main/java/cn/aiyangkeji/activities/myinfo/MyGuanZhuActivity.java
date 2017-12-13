package cn.aiyangkeji.activities.myinfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.base.BaseFragmentActivity;
import cn.aiyangkeji.fragmnets.AttentionCommodityFragment;
import cn.aiyangkeji.fragmnets.AttentionYysFragment;

/**
 * Created by chenzhikai on 2017/11/4.
 * 我的问答页面
 */

public class MyGuanZhuActivity extends BaseFragmentActivity {

    private ImageView ivback;
    private ViewPager attVieapager;
    private TextView tvCommodity;
    private TextView tvYys;
    private AttentionCommodityFragment attentionCommodityFragment;
    private AttentionYysFragment myFootprintFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_guanzhu);

    }

    @Override
    public void initView() {
        TextView textView= (TextView)findViewById(R.id.tv_title);
        textView.setText("我的关注");
        ivback = (ImageView) findViewById(R.id.iv_back);
        attVieapager = (ViewPager)findViewById(R.id.jazzy_pager);
        tvCommodity = (TextView)findViewById(R.id.tv_commodity);

        tvYys = (TextView)findViewById(R.id.tv_yys);

    }

    @Override
    public void initListener() {
        ivback.setOnClickListener(this);
        tvCommodity.setOnClickListener(this);
        tvYys.setOnClickListener(this);
        // attVieapager.setOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void initData() {
        attVieapager.setAdapter(new ViewPagerAdapter(this.getSupportFragmentManager()));
        attVieapager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             //   tvCommodity.getBackground().mutate().setAlpha((int) (255*(1-positionOffset)));
             //   Log.e("2222222222222222222222222222222","onPageScrolled: "+positionOffset);


            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                   setTabStatus(tvCommodity);
                } else if (position == 1) {
                    setTabStatus(tvYys);
                     //  myFootprintFragment.setData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_commodity:
                setTabStatus(tvCommodity);
                attVieapager.setCurrentItem(0, false);
                break;
            case R.id.tv_yys:
                setTabStatus(tvYys);
                attVieapager.setCurrentItem(1, false);
                break;
            default:
                super.onClick(v);
                break;


        }


    }
    private class ViewPagerAdapter extends FragmentPagerAdapter {



        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (attentionCommodityFragment == null) {
                        attentionCommodityFragment = new AttentionCommodityFragment();
                    }
                    return attentionCommodityFragment;

                case 1:
                    if (myFootprintFragment == null) {
                        myFootprintFragment = new AttentionYysFragment();
                    }
                    return myFootprintFragment;

                default:
                    return null;
            }
        }

    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                setTabStatus(tvCommodity);
            } else if (position == 1) {
                setTabStatus(tvYys);
                //   myFootprintFragment.setData();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void setTabStatus(TextView btn) {
        tvCommodity.setTextColor(getResources().getColor(R.color.pink_word_home));
        tvCommodity.setBackgroundResource(R.drawable.bg_shape_pink_left_bottom_corner_shixian_kongxin);
        tvYys.setBackgroundResource(R.drawable.bg_shape_pink_right_bottom_corner_shixan_kongxin);
        tvYys.setTextColor(getResources().getColor(R.color.pink_word_home));
        if (btn==tvCommodity){
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setBackgroundResource(R.drawable.bg_shape_pink_left_bottom_corner);
        }else {
            btn.setTextColor(getResources().getColor(R.color.white));
            btn.setBackgroundResource(R.drawable.bg_shape_pink_right_bottom_corner);
        }


    }


}
