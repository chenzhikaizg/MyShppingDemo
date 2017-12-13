package cn.aiyangkeji.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.BannerBean;
import cn.aiyangkeji.util.UIHelper;

/**
 * Created by gaoweiwei on 15/5/28.
 */
public class BannerView extends ViewPager {

    private Context context;

    private ImageView imageView;
    private LinearLayout llBannerIndex;
    private TextView tvBannerIndex;
    private List<BannerBean.Typ> vaules;

    private int imgCount;
    private int currentItem = 0;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentItem++;
            BannerView.this.setCurrentItem(currentItem);
            handler.postDelayed(runnable, 1000*10);
        }
    };
    private ArrayList<ImageView> mImageViews1;
    private ArrayList<ImageView> mImageViews;

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void initView(int width,int height){

        llBannerIndex=(LinearLayout)(((ViewGroup)this.getParent()).findViewById(R.id.ll_banner_index));
        tvBannerIndex=(TextView)(((ViewGroup)this.getParent()).findViewById(R.id.tv_banner_index));
        mImageViews = new ArrayList<ImageView>();
        for (int i = 0; i < 3; i++) {
            imageView = new ImageView(context);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setId(i);
            //imageView.setOnClickListener(context);
            mImageViews.add(imageView);
        }
        ViewGroup.LayoutParams bannerPartams = this.getLayoutParams();
        bannerPartams.width = width;
        bannerPartams.height = height;
        this.setLayoutParams(bannerPartams);
      //  RelativeLayout.LayoutParams tvIndexParams = (RelativeLayout.LayoutParams) tvBannerIndex.getLayoutParams();
      //  tvIndexParams.setMargins(UIHelper.dip2px(context, 60), height - UIHelper.dip2px(context, 28), 0, 0);
     //   tvBannerIndex.setLayoutParams(tvIndexParams);
        RelativeLayout.LayoutParams llIndexParams = (RelativeLayout.LayoutParams) llBannerIndex.getLayoutParams();
        llIndexParams.setMargins(0, height - UIHelper.dip2px(context, 15), UIHelper.dip2px(context, 5), 0);
        llIndexParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        llBannerIndex.setLayoutParams(llIndexParams);
        setOnPageChangeListener(onPageChangeListener);

           /*this.setOnTouchListener(new OnTouchListener() {
           public boolean onTouch(View v, MotionEvent event) {
               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   handler.removeCallbacks(runnable);
               } else if (event.getAction() == MotionEvent.ACTION_UP) {
                   handler.postDelayed(runnable,4000);
               }
               return false;
           }
       });*/

    }

    public void initData(List<BannerBean.Typ> vaules) {
        this.vaules = vaules;
        if (vaules.size() == 1) {
            imgCount = 1;
        } else if (vaules.size() > 1) {
            imgCount = Integer.MAX_VALUE;
        }
        currentItem = 0;
        handler.removeCallbacks(runnable);
        this.removeAllViews();
        setAdapter(new MyAdapter(imgCount));
        initDotIndex(0);

    }

    public void setItemClickListener(OnClickListener onClickListener){
        for (int i=0;i<3;i++){
            mImageViews.get(i).setOnClickListener(onClickListener);
        }

    }


    public void startScroll() {
        handler.postDelayed(runnable, 4000);
    }

    public void stopScroll() {
        handler.removeCallbacks(runnable);
    }

    /**
     * @author xiaanming
     */
    public class MyAdapter extends PagerAdapter {

         private int count;
        private ImageView view;

        public MyAdapter(int count) {
            this.count = count;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);
            //container.removeView((View)object);        // 删除页卡

        }

        private BannerBean.Typ vaule;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            view = mImageViews.get(position%3);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.removeView(view);
            container.addView(view, 0);

            vaule = vaules.get(position % vaules.size());
            Glide.with(getContext()).load(R.mipmap.timg).into(view);
            view.setTag(vaule);
            return view;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(ev);
    }

    OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentItem = position;
            for (int i = 0; i < llBannerIndex.getChildCount(); i++) {
                ((GradientDrawable) llBannerIndex.getChildAt(i).getBackground()).setColor(Color.parseColor("#ededed"));
            }
            ((GradientDrawable) llBannerIndex.getChildAt(position % vaules.size()).getBackground()).setColor(Color.parseColor("#333333"));
        //    tvBannerIndex.setText((position % vaules.size() + 1) + "/" + vaules.size());


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void initDotIndex(int index) {
        llBannerIndex.removeAllViews();
        View view;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < vaules.size(); i++) {
            view = new View(context);
            if (index == i) {
                view.setBackgroundResource(R.drawable.dot_focused);
            } else {
                view.setBackgroundResource(R.drawable.dot_normal);

            }
            layoutParams = new LinearLayout.LayoutParams(UIHelper.dip2px(context, 5), UIHelper.dip2px(context, 5));
            layoutParams.gravity = Gravity.CENTER;
            if (i != imgCount - 1) {
                layoutParams.setMargins(0, 0, UIHelper.dip2px(context, 5), 0);

            }
            llBannerIndex.addView(view, layoutParams);
        }
      //  tvBannerIndex.setText("1/" + vaules.size());
    }


}
