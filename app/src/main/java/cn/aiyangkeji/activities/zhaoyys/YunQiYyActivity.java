package cn.aiyangkeji.activities.zhaoyys;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.base.BaseFragmentActivity;
import cn.aiyangkeji.adapter.YyShowAllAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.fragmnets.YysHaoPingFragment;
import cn.aiyangkeji.fragmnets.YysPriceFragment;
import cn.aiyangkeji.fragmnets.YysZongHeFragment;
import cn.aiyangkeji.interfaces.ScrollTabHolder;
import cn.aiyangkeji.view.PagerSlidingTabStrip;

/**
 * Created by chenzhikai on 2017/11/7.
 * 孕期营养
 */

public class YunQiYyActivity extends BaseFragmentActivity implements  ViewPager.OnPageChangeListener {
    private  PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;
    private int mLastY;
    private DisplayMetrics dm;
    private YysZongHeFragment yysZongHeFragment;
    private YysHaoPingFragment yysHaoPingFragment;
    private YysPriceFragment yysPriceFragment;
    private ImageView ivback;
    private RelativeLayout rlAllShow;

    private String[] datas = {"孕期营养   ", "哺乳期营养   ", "婴幼儿营养   ", "儿童营养   ", "运动营养   ","临床营养   "};
    private TextView tvAllShow;
    private FrameLayout flAllShow;
    private     int[] imgs = {R.mipmap.yunqi_zyys_iocn_normal_,R.mipmap.buru_zyys_icon_normal_,R.mipmap.yingyou_zyys_icon_normal_,R.mipmap.ertong_zyys_icon_normal_,R.mipmap.yuedong_zyys_icon_normal_,R.mipmap.linchuang_zyys_icon_normal_};
    private TextView tvTitle;
    private PopupWindow window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yunqi_yy);
    }

    @Override
    public void initView() {
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("孕期营养");
        ivback = (ImageView)findViewById(R.id.iv_back);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(3);


        mLastY = 0;
        dm = getResources().getDisplayMetrics();


        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setOnPageChangeListener(this);

        setTabsValue();

        rlAllShow = (RelativeLayout)findViewById(R.id.rl_all_show);
        tvAllShow = (TextView)findViewById(R.id.tv_all_show);
        flAllShow = (FrameLayout)findViewById(R.id.fl_all_show);
    }

    @Override
    public void initListener() {
        ivback.setOnClickListener(this);
        rlAllShow.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }
    /**
     * 对PagerSlidingTabStrip的各项属性进行赋值。
     */
    private void setTabsValue() {
        // 设置Tab是自动填充满屏幕的
        mPagerSlidingTabStrip.setShouldExpand(true);
        // 设置Tab的分割线是透明的
        mPagerSlidingTabStrip.setDividerColor(getResources().getColor(R.color.text_color_grey));
        // 设置Tab底部线的高度
        mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 0.5f, dm));

        mPagerSlidingTabStrip.setUnderlineColor(getResources().getColor(R.color.text_color_grey));
        // 设置Tab Indicator的高度
        mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 2.5f, dm));
        // 设置Tab标题文字的大小
        mPagerSlidingTabStrip.setTextSize((int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, dm));
        // 设置Tab Indicator的颜色
        mPagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.text_color_back));
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)
        mPagerSlidingTabStrip.setSelectedTextColor(getResources().getColor(R.color.text_color_back));
        // 取消点击Tab时的背景色
        mPagerSlidingTabStrip.setTabBackground(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(position==3){

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public class PagerAdapter extends FragmentPagerAdapter {


        private final String[] TITLES = {"综合", "好评", "价格"};
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
                    if (yysZongHeFragment == null) {
                        yysZongHeFragment = new YysZongHeFragment();
                        Bundle args = new Bundle();
                        args.putInt("type", 0);
                        args.putInt("position", position);
                        yysZongHeFragment.setArguments(args);

                    }
                    return yysZongHeFragment;
                case 1:
                    if (yysHaoPingFragment == null) {
                        yysHaoPingFragment = new YysHaoPingFragment();
                        Bundle args = new Bundle();
                        args.putInt("type", 1);
                        args.putInt("position", position);
                        yysHaoPingFragment.setArguments(args);



                    }
                    return yysHaoPingFragment;
                case 2:
                    if (yysPriceFragment == null) {
                        yysPriceFragment = new YysPriceFragment();


                    }
                    return yysPriceFragment;
            }

            return null;
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_all_show:
                // TODO: 2016/5/17 构建一个popupwindow的布局
                View popupView = this.getLayoutInflater().inflate(R.layout.pp_all_show, null);

                // TODO: 2016/5/17 为了演示效果，简单的设置了一些数据，实际中大家自己设置数据即可，相信大家都会。
                ListView lsvMore = (ListView) popupView.findViewById(R.id.lsvMore);
                List<MyWenDaBean.ArtImage> list = new ArrayList<MyWenDaBean.ArtImage>();
                YyShowAllAdapter allAdapter = new YyShowAllAdapter(this);
                for (int i=0;i<imgs.length;i++){
                    MyWenDaBean.ArtImage artImage = new MyWenDaBean.ArtImage();
                    artImage.id = imgs[i];
                    artImage.name = datas[i];
                       list.add(artImage);
                }
                allAdapter.addData(list);
                lsvMore.setAdapter(allAdapter);
                lsvMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //发送数据
                    //    EventBus.getDefault().post(datas[position]);
                        tvTitle.setText(datas[position]);
                        if(window.isShowing()){
                            window.dismiss();
                        }
                    }
                });


                // TODO: 2016/5/17 创建PopupWindow对象，指定宽度和高度
                window = new PopupWindow(popupView, 450, 900);
                // TODO: 2016/5/17 设置动画
               window.setAnimationStyle(R.style.popup_window_anim);
                // TODO: 2016/5/17 设置背景颜色
            //    window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
                // TODO: 2016/5/17 设置可以获取焦点
                window.setFocusable(true);
                // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
                window.setOutsideTouchable(true);

                // TODO：更新popupwindow的状态
                window.update();
                // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
                window.showAsDropDown(flAllShow, -130, 0);
                break;

            default:
                super.onClick(v);
                break;


        }

    }
}
