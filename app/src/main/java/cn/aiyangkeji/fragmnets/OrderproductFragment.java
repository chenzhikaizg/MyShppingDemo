package cn.aiyangkeji.fragmnets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.aiyangkeji.R;
import cn.aiyangkeji.interfaces.ScrollTabHolder;
import cn.aiyangkeji.view.PagerSlidingTabStrip;

/**
 * Created by chenzhikai on 2017/11/8.
 */

public class OrderproductFragment extends BaseFragment {
    private View view;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;
    private DisplayMetrics dm;


    private AttentionCommodityFragment attentionCommodityFragment;
    private AttentionYysFragment myFootprintFragment;
    private OrderProductAllFragment orderProductAllFragment;
    private OrderProductAllFragment myFootprintFragment1;
    private OrderProductAllFragment orderProductAllFragment2;
    private OrderProductAllFragment orderProductAllFragment1;
    private OrderProductAllFragment orderProductAllFragment3;
    private OrderProductAllFragment orderProductAllFragment4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_order_product, container, false);
        initView();
        initListener();
        initData();
        return view;
    }

    @Override
    public void initView() {
        pagerSlidingTabStrip = (PagerSlidingTabStrip)view.findViewById(R.id.tabs);
        viewPager = (ViewPager)view.findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        dm = getResources().getDisplayMetrics();

        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager()));
        pagerSlidingTabStrip.setViewPager(viewPager);
        // pagerSlidingTabStrip.setOnPageChangeListener(this);
        setTabsValue();
    }
    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        pagerSlidingTabStrip.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        pagerSlidingTabStrip.setDividerColor(getResources().getColor(R.color.text_color_grey));
        // 设置Tab底部线的高度
        pagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 0.5f, dm));

        pagerSlidingTabStrip.setUnderlineColor(getResources().getColor(R.color.text_color_grey));
        // 设置Tab Indicator的高度
        pagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2.5f, dm));
        // 设置Tab标题文字的大小
        pagerSlidingTabStrip.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        pagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.pink_word_home));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        pagerSlidingTabStrip.setSelectedTextColor(getResources().getColor(R.color.pink_word_home));
        // 取消点击Tab时的背景色
        pagerSlidingTabStrip.setTabBackground(0);
        pagerSlidingTabStrip.setDividerColor(R.color.pink_word_home);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"全部", "待收货","已完成","待评价"};


        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (orderProductAllFragment == null) {
                        orderProductAllFragment = new OrderProductAllFragment();
                    }
                    return orderProductAllFragment;

                case 1:
                    if (orderProductAllFragment1 == null) {
                        orderProductAllFragment1 = new OrderProductAllFragment();
                    }
                    return orderProductAllFragment1;
                case 2:
                    if (orderProductAllFragment2 == null) {
                        orderProductAllFragment2 = new OrderProductAllFragment();
                    }
                    return orderProductAllFragment2;
                case 3:
                    if (orderProductAllFragment3 == null) {
                        orderProductAllFragment3 = new OrderProductAllFragment();
                    }
                    return orderProductAllFragment3;



            }
            return null;
        }

    }
}
