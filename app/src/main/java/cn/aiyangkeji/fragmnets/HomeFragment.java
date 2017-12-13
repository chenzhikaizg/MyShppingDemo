package cn.aiyangkeji.fragmnets;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.FindHomeActivity;
import cn.aiyangkeji.activities.HomeRvYyActivity;
import cn.aiyangkeji.activities.XiaoXiActivity;
import cn.aiyangkeji.activities.YingTangShiDetailActivity;
import cn.aiyangkeji.activities.YingYangDaoJiaActivity;
import cn.aiyangkeji.activities.zhaoyys.FindDieticianActivity;
import cn.aiyangkeji.activities.zhaoyys.ZhiShiListActivity;
import cn.aiyangkeji.adapter.ErTongZsAdapter;
import cn.aiyangkeji.adapter.SyTjFwAdapter;
import cn.aiyangkeji.adapter.SyTjYysAdapter;
import cn.aiyangkeji.adapter.SyTjYzcAdapter;
import cn.aiyangkeji.bean.BannerBean;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.util.UIHelper;
import cn.aiyangkeji.view.AutoListView;
import cn.aiyangkeji.view.BannerView;

/**
 * Created by gaoweiwei on 15/5/25.
 * 首页发现页面
 */
public class HomeFragment extends BaseFragment implements AutoListView.OnRefreshListener,
        AutoListView.OnLoadListener, AutoListView.MyScrollListener {

    private View viewParent;
    private AutoListView lvAuto;
    private View headerView;
    private ArrayAdapter<String> listArrayAdapter;
    private BannerView bannerView;
    private ImageView ivZyys;
    private ImageView ivYydj;
    private ImageView ivMyyy;
    private ImageView ivEtyy;
    private Intent intent;
    private RecyclerView rvTuiJian;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rvTjYys;
    private RecyclerView rvTjYzc;
    private RelativeLayout rlTtile;
    private LinearLayout llXiaoXi;
    private int top;
    private int h;
    private int headHeight;
    //滑动的距离
    private int mDistanceY = 0;
    private int height;
    private RelativeLayout rlFindDietician;
    private RelativeLayout rlNutritionHome;
    private ImageView ivHomeFind;

    private int pageNo = 0;
    private int i = 0;
    private List<MyWenDaBean.ArtImage> list;
    private ErTongZsAdapter erTongZsAdapter;
    private SyTjYysAdapter syTjYysAdapter;
    private SyTjFwAdapter adapter;
    private SyTjYzcAdapter syTjYzcAdapter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<MyWenDaBean.ArtImage> values = (List<MyWenDaBean.ArtImage>) msg.obj;

            switch (msg.what) {
                case AutoListView.REFRESH:
                    lvAuto.onRefreshComplete();
                    erTongZsAdapter.clear();
                    erTongZsAdapter.addData(values);
                    if (erTongZsAdapter.getCount() != 0) {
                        lvAuto.footViewShow();
                    }
                    break;
                case AutoListView.LOAD:
                    lvAuto.onLoadComplete();
                    erTongZsAdapter.addData(values);
                    break;
            }
            lvAuto.setResultSize(values.size());
            erTongZsAdapter.notifyDataSetChanged();
        }


    };


    public HomeFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewParent = inflater.inflate(R.layout.home_fragment, container, false);
        initView();
        initListener();
        initData();
        return viewParent;
    }

    @Override
    public void initView() {
        lvAuto = (AutoListView) viewParent.findViewById(R.id.lv_find);
        rlTtile = (RelativeLayout) viewParent.findViewById(R.id.tv_ss);
        //头布局
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.banner_layout, null);
        lvAuto.initView(getActivity(), headerView);
        bannerView = (BannerView) headerView.findViewById(R.id.bv_banner);
        rlFindDietician = (RelativeLayout) headerView.findViewById(R.id.rl_find_dietician);
        rlNutritionHome = (RelativeLayout) headerView.findViewById(R.id.rl_nutrition_home);
        //找营养师
        ivZyys = (ImageView) headerView.findViewById(R.id.iv_find_dietician);
        //营养到家
        ivYydj = (ImageView) headerView.findViewById(R.id.iv_nutrition_home);
        //母婴营养
        //   ivMyyy = (ImageView)headerView.findViewById(R.id.iv_infantmom_nutrition);
        //儿童营养
        //  ivEtyy = (ImageView)headerView.findViewById(R.id.iv_child_nutrition);
        //推荐服务rv
        rvTuiJian = (RecyclerView) headerView.findViewById(R.id.rv_tuijian_fuwu);
        rvTuiJian.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTuiJian.setLayoutManager(linearLayoutManager);
        //推荐营养师
        rvTjYys = (RecyclerView) headerView.findViewById(R.id.rv_tujian_yys);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTjYys.setLayoutManager(linearLayoutManager1);
        //推荐月子餐
        rvTjYzc = (RecyclerView) headerView.findViewById(R.id.rv_tujian_yzc);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvTjYzc.setLayoutManager(linearLayoutManager2);
        //消息
        llXiaoXi = (LinearLayout) viewParent.findViewById(R.id.ll_xiaoxi);

        ivHomeFind = (ImageView) viewParent.findViewById(R.id.iv_home_find);

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 90; i++) {
            list.add(i + 1 + "");
        }
        height = (int) (UIHelper.getScreenWidth(getActivity()) * 0.5);
        int width = UIHelper.getScreenWidth(getActivity());
        bannerView.initView(width, height);
        bannerView.setItemClickListener(bannerClickListener);


//        //   bannerView.initData(bannerBean.value.typzero);
//        lvAuto.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int i) {
//
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
////                    if (i==0){
////                        rlTtile.setBackgroundResource(R.color.pink_line);
////                        rlTtile.setAlpha(1f);
////                    }else {
////                        rlTtile.setBackgroundResource(R.color.bg_white);
////                        rlTtile.setAlpha(0.5f);
////                    }
//
//                h = getScrollY();
//
//                if (h==0){
//                    mDistanceY=0;
//                }else {
//                    mDistanceY += h;
//                }
//
//                //上方图片的高度
//                headHeight = height;
//                if (mDistanceY < headHeight) {
//                    if (mDistanceY==0){
//                        rlTtile.setBackgroundResource(R.mipmap.yinying);
//                    }else {
//                        //滑动距离小于上方图片的1/2时，设置白色搜索按钮，透明度从0-255
//                        if (mDistanceY < headHeight ) {
//                            //    imgSearch.setImageResource(R.mipmap.search_white);
//                            float scale = (float) mDistanceY / (headHeight );
//                            float alpha = scale * 255;
//                            rlTtile.getBackground().setAlpha((int) alpha);
//                        } else {//滑动距离大于上方图片的1/2并小于上方图片时，设置黑色搜索按钮，透明度从0-255
//                            // imgSearch.setImageResource(R.mipmap.search_black);
//                            float scale = (float) (mDistanceY - headHeight ) / (headHeight );
//                            float alpha = scale * 255;
//                            rlTtile.getBackground().setAlpha((int) alpha);
//                        }
//                    }
//
//                } else {
//                    //当快速往下滑时，llSearch最后设置的alpha不约等于255，测试的为132,所以要再设置
//                    // rlTtile.getBackground().setAlpha((int) 255);
//                    rlTtile.setBackgroundResource(R.color.pink_word_home);
//                }
//            }
//        });


    }


    @Override
    public void initListener() {
        ivZyys.setOnClickListener(this);
        ivYydj.setOnClickListener(this);
        lvAuto.setOnRefreshListener(this);
        lvAuto.setOnLoadListener(this);
        lvAuto.setMyScrollListener(this);

        llXiaoXi.setOnClickListener(this);
        rlFindDietician.setOnClickListener(this);
        rlNutritionHome.setOnClickListener(this);
        ivHomeFind.setOnClickListener(this);


    }

    @Override
    public void initData() {
        erTongZsAdapter = new ErTongZsAdapter(getContext());
        syTjYysAdapter = new SyTjYysAdapter(getContext());
        adapter = new SyTjFwAdapter(getContext());
        syTjYzcAdapter = new SyTjYzcAdapter(getContext());
        list = new ArrayList<MyWenDaBean.ArtImage>();
        for (int i = 0; i < 29; i++) {
            MyWenDaBean.ArtImage s = new MyWenDaBean.ArtImage();
            BannerBean.Typ typ = new BannerBean.Typ();
            typ.img = "";
            typ.count = 1;

            s.img = "";
            s.kind = "我的tian";
            s.title = "我的没人";
            s.money = "2";
            list.add(s);
        }
        List<BannerBean.Typ> listTyp = new ArrayList<BannerBean.Typ>();
        for (int i = 0; i < 9; i++) {

            BannerBean.Typ typ = new BannerBean.Typ();
            typ.img = "";
            typ.count = 1;
            listTyp.add(typ);


        }
        bannerView.initData(listTyp);
        if (listTyp.size() > 1) {
            bannerView.startScroll();
        }


        lvAuto.setAdapter(erTongZsAdapter);

        onRefresh();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_find_dietician:
            case R.id.iv_find_dietician:
                intent = new Intent(getActivity(), FindDieticianActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_nutrition_home:
            case R.id.iv_nutrition_home:
                intent = new Intent(getActivity(), YingYangDaoJiaActivity.class);
                startActivity(intent);
                break;
//                case R.id.iv_infantmom_nutrition:
//                    intent = new Intent(getActivity(), MuYIingYyActivity.class);
//                    startActivity(intent);
//                    break;
//                case R.id.iv_child_nutrition:
//                    intent = new Intent(getActivity(), ErTongYyActivity.class);
//                    startActivity(intent);
//                    break;
            case R.id.ll_xiaoxi:
                intent = new Intent(getActivity(), XiaoXiActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_home_find:
                intent = new Intent(getContext(), FindHomeActivity.class);
                startActivity(intent);

                break;

            default:
                super.onClick(v);
                break;
        }


    }


    public void onLoad() {
        i++;
        pageNo = 20 * i;

        loadData(AutoListView.LOAD);
    }

    @Override
    public void onRefresh() {

        syTjYysAdapter.addData(list);
        syTjYzcAdapter.addData(list);

        adapter.addData(list);
        rvTuiJian.setAdapter(adapter);
        adapter.setClickListener(new SyTjYysAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), HomeRvYyActivity.class);
                startActivity(intent);
            }
        });


        rvTjYys.setAdapter(syTjYysAdapter);
        rvTjYzc.setAdapter(syTjYzcAdapter);
        syTjYzcAdapter.setClickListener(new SyTjYysAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), ZhiShiListActivity.class);
                startActivity(intent);
            }
        });
        syTjYysAdapter.setClickListener(new SyTjYysAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), YingTangShiDetailActivity.class);
                startActivity(intent);
            }
        });

        pageNo = 0;
        loadData(AutoListView.REFRESH);
    }

    private void loadData(final int what) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getMessage(what);
            }
        }).start();
    }

    @Override
    public void onScrollY(int y) {

        h = getScrollY();

        if (h == 0) {
            mDistanceY = 0;
        } else {
            mDistanceY += h;
        }

        //上方图片的高度
        headHeight = height;
        if (mDistanceY < headHeight) {
            if (mDistanceY == 0) {
                rlTtile.setBackgroundResource(R.mipmap.yinying);
            } else {
                //滑动距离小于上方图片的1/2时，设置白色搜索按钮，透明度从0-255
                if (mDistanceY < headHeight) {
                    //    imgSearch.setImageResource(R.mipmap.search_white);
                    float scale = (float) mDistanceY / (headHeight);
                    float alpha = scale * 255;
                    rlTtile.getBackground().setAlpha((int) alpha);
                } else {//滑动距离大于上方图片的1/2并小于上方图片时，设置黑色搜索按钮，透明度从0-255
                    // imgSearch.setImageResource(R.mipmap.search_black);
                    float scale = (float) (mDistanceY - headHeight) / (headHeight);
                    float alpha = scale * 255;
                    rlTtile.getBackground().setAlpha((int) alpha);
                }
            }

        } else {
            //当快速往下滑时，llSearch最后设置的alpha不约等于255，测试的为132,所以要再设置
            // rlTtile.getBackground().setAlpha((int) 255);
            rlTtile.setBackgroundResource(R.color.pink_word_home);
        }
    }


    ArrayList listsssss = new ArrayList<MyWenDaBean.ArtImage>();

    private void getMessage(int what) {

        for (int i = 0; i < 10; i++) {
            MyWenDaBean.ArtImage s = new MyWenDaBean.ArtImage();
            BannerBean.Typ typ = new BannerBean.Typ();
            typ.img = "";
            typ.count = 1;

            s.img = "";
            s.kind = "我的tian";
            s.title = "我的没人";
            s.money = "2";
            listsssss.add(s);
        }
        Message msg = handler.obtainMessage();
        msg.what = what;
        msg.obj = listsssss;
        handler.sendMessage(msg);


    }

    /**
     * 轮播图
     */
    View.OnClickListener bannerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        }
    };


    public int getScrollY() {
        View c = lvAuto.getChildAt(0);
        if (c == null) {
            return 0;
        }
        int firstVisiblePosition = lvAuto.getFirstVisiblePosition();
        int top = c.getTop();
        return -top + firstVisiblePosition * c.getHeight();
    }


}
