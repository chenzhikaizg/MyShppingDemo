package cn.aiyangkeji.activities.zhaoyys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.base.BaseFragmentActivity;
import cn.aiyangkeji.fragmnets.WdYunqYyFragment;
import cn.aiyangkeji.fragmnets.YysHaoPingFragment;
import cn.aiyangkeji.fragmnets.YysPriceFragment;
import cn.aiyangkeji.fragmnets.YysZongHeFragment;
import cn.aiyangkeji.interfaces.ScrollTabHolder;
import cn.aiyangkeji.view.PagerSlidingTabStrip;

/**
 * Created by chenzhikai on 2017/11/3.
 */

public class WenDaTtingActivity extends BaseFragmentActivity {

    private ImageView ivBack;
    private PagerSlidingTabStrip psts;
    private ViewPager viewPager;
    private DisplayMetrics dm;

    private YysZongHeFragment yysZongHeFragment;
    private YysHaoPingFragment yysHaoPingFragment;
    private YysPriceFragment yysPriceFragment;
    private WdYunqYyFragment wdYunqYyFragment;
    private WdYunqYyFragment wdYunqYyFragment1;
    private WdYunqYyFragment wdYunqYyFragment2;
    private WdYunqYyFragment wdYunqYyFragment3;
    private WdYunqYyFragment wdYunqYyFragment4;
    private WdYunqYyFragment wdYunqYyFragment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wenda_touting);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("问答偷听");
        ivBack = (ImageView)findViewById(R.id.iv_back);
        psts = (PagerSlidingTabStrip)findViewById(R.id.rl_tab);
        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        dm = getResources().getDisplayMetrics();
        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        psts.setViewPager(viewPager);


        setTabsValue();
    }
    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        psts.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        psts.setDividerColor(getResources().getColor(R.color.text_color_grey));
        // 设置Tab底部线的高度
        psts.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 0.5f, dm));

        psts.setUnderlineColor(getResources().getColor(R.color.text_color_grey));
        // 设置Tab Indicator的高度
        psts.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2.5f, dm));
        // 设置Tab标题文字的大小
        psts.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        psts.setIndicatorColor(getResources().getColor(R.color.pink_word_home));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        psts.setSelectedTextColor(getResources().getColor(R.color.pink_word_home));
        // 取消点击Tab时的背景色
        psts.setTabBackground(0);
    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

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

    public class PagerAdapter extends FragmentPagerAdapter {


        private final String[] TITLES = {"孕期营养", "哺乳营养", "婴幼儿营养","儿童营养","运动营养","临床营养"};
        private ScrollTabHolder mListener;


        public PagerAdapter(FragmentManager fm) {
            super(fm);

        }



        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (wdYunqYyFragment == null) {
                        wdYunqYyFragment = new WdYunqYyFragment();
                        Bundle args = new Bundle();
                        args.putInt("type", 0);
                        args.putInt("position", position);
                        wdYunqYyFragment.setArguments(args);

                    }
                    return wdYunqYyFragment;
                case 1:
                    if (wdYunqYyFragment1 == null) {
                        wdYunqYyFragment1 = new WdYunqYyFragment();
                        Bundle args = new Bundle();
                        args.putInt("type", 1);
                        args.putInt("position", position);
                        wdYunqYyFragment1.setArguments(args);



                    }
                    return wdYunqYyFragment1;
                case 2:
                    if (wdYunqYyFragment2 == null) {
                        wdYunqYyFragment2 = new WdYunqYyFragment();
                    }
                    return wdYunqYyFragment2;
                case 3:
                    if (wdYunqYyFragment3 == null) {
                        wdYunqYyFragment3 = new WdYunqYyFragment();
                    }
                    return wdYunqYyFragment3;
                case 4:
                    if (wdYunqYyFragment4 == null) {
                        wdYunqYyFragment4 = new WdYunqYyFragment();
                    }
                    return wdYunqYyFragment4;
                case 5:
                    if (wdYunqYyFragment5 == null) {
                        wdYunqYyFragment5 = new WdYunqYyFragment();
                    }
                    return wdYunqYyFragment5;

            }

            return null;
        }



    }

}
