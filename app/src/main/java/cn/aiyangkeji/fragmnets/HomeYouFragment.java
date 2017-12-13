package cn.aiyangkeji.fragmnets;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.ErTongYyActivity;
import cn.aiyangkeji.activities.MuYIingYyActivity;
import cn.aiyangkeji.activities.XiaoXiActivity;
import cn.aiyangkeji.activities.YingYangDaoJiaActivity;
import cn.aiyangkeji.activities.store.ProductDetailActivity;
import cn.aiyangkeji.activities.store.ShoppingCartActivity;
import cn.aiyangkeji.activities.store.YzcYouXuanActivity;
import cn.aiyangkeji.activities.zhaoyys.FindDieticianActivity;
import cn.aiyangkeji.adapter.SyTjFwAdapter;
import cn.aiyangkeji.adapter.SyTjYysAdapter;
import cn.aiyangkeji.adapter.SyTjYzcAdapter;
import cn.aiyangkeji.adapter.YouXuanBottonAdapter;
import cn.aiyangkeji.bean.BannerBean;
import cn.aiyangkeji.bean.HomeSortBean;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.util.UIHelper;
import cn.aiyangkeji.view.AutoListView;
import cn.aiyangkeji.view.BannerView;

/**
 * Created by chenzhikai on 2017/10/31.
 * 优选页面
 */

public class HomeYouFragment extends BaseFragment implements AutoListView.OnRefreshListener,
        AutoListView.OnLoadListener, AutoListView.MyScrollListener{
    private View viewParent;
    private AutoListView lvAuto;
    private View headerView;
    private ArrayAdapter<String> listArrayAdapter;
    private BannerView bannerView;
    private ImageView ivMmTuijian;
    private ImageView ivMyYongpin;
    private ImageView ivNfNiaoBu;
    private ImageView ivShRiYong;
    private Intent intent;
    private RecyclerView rvTuiJian;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView rvTjYys;
    private RecyclerView rvTjYzc;
    private RelativeLayout rlTtile;
    private LinearLayout llXiaoXi;
    private ImageView ivActivityThree;
    private ImageView ivActivityTwo;
    private ImageView ivActivityOne;
    private RelativeLayout rlHotMore;
    private ImageView ivImgOne;
    private ImageView ivImgTwo;
    private ImageView ivImgThree;
    private ImageView ivImgFour;
    private ImageView ivCheOne;
    private ImageView ivCheTwo;
    private ImageView ivCheThree;
    private ImageView ivCheFour;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private MyAPI myAPI;
    private Novate novate;
    private List<HomeSortBean.Sort> data;
    private TextView tvPositionOne;
    private TextView tvPositonTwo;
    private TextView tvPositionFour;
    private TextView tvPositionThree;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewParent = inflater.inflate(R.layout.fragment_youxuan, container,false);
        initView();
        initListener();
        initData();
        return viewParent;
    }
    @Override
    public void initView() {
        lvAuto  =  (AutoListView)viewParent.findViewById(R.id.lv_find) ;
        rlTtile = (RelativeLayout)viewParent.findViewById(R.id.tv_ss);
        //头布局
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.banner_layout_youxuan, null);
        lvAuto.initView(getActivity(), headerView);
        bannerView = (BannerView)headerView.findViewById(R.id.bv_banner);
        //辣妈推荐
        ivMmTuijian = (ImageView)headerView.findViewById(R.id.iv_mm_tuijan);
        tvPositionOne = (TextView)headerView.findViewById(R.id.tv_position_one);
        //母婴用品
        ivMyYongpin = (ImageView)headerView.findViewById(R.id.iv_muying_yongpin);
        tvPositonTwo = (TextView)headerView.findViewById(R.id.tv_position_two);
        //奶粉尿布
        ivNfNiaoBu = (ImageView)headerView.findViewById(R.id.iv_naifen_niaobu);
        tvPositionThree = (TextView)headerView.findViewById(R.id.tv_position_three);
        //生活日用
        ivShRiYong = (ImageView)headerView.findViewById(R.id.iv_shenghuo_riyong);
        tvPositionFour = (TextView) headerView.findViewById(R.id.tv_position_four);
        ivActivityOne = (ImageView)headerView.findViewById(R.id.iv_activity_one);
        ivActivityTwo = (ImageView)headerView.findViewById(R.id.iv_activity_two);
        ivActivityThree = (ImageView)headerView.findViewById(R.id.iv_activity_three);
        rlHotMore = (RelativeLayout)headerView.findViewById(R.id.rl_hot_more);
        ivImgOne = (ImageView) headerView.findViewById(R.id.iv_img_one);
        ivImgTwo = (ImageView)headerView.findViewById(R.id.iv_img_two);
        ivImgThree = (ImageView)headerView.findViewById(R.id.iv_img_three);
        ivImgFour = (ImageView)headerView.findViewById(R.id.iv_img_four);
//        ivCheOne = (ImageView)headerView.findViewById(R.id.iv_che_one);
//        ivCheTwo = (ImageView)headerView.findViewById(R.id.iv_che_two);
//        ivCheThree = (ImageView)headerView.findViewById(R.id.iv_che_three);
//        ivCheFour = (ImageView)headerView.findViewById(R.id.iv_che_four);
        //消息
        llXiaoXi = (LinearLayout)viewParent.findViewById(R.id.ll_xiaoxi);
        ArrayList<String> list = new ArrayList<String>();
        for (int i=0;i<90;i++){
            list.add(i+1+"");
        }
        int height = (int) (UIHelper.getScreenWidth(getActivity()) * 0.5);
        int width = UIHelper.getScreenWidth(getActivity());
        bannerView.initView(width, height);
        bannerView.setItemClickListener(bannerClickListener);
        lvAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),ProductDetailActivity.class);
                intent.putExtra("from","other");
                startActivity(intent);
            }
        });
        //   bannerView.initData(bannerBean.value.typzero);
        lvAuto.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i==0){
                    rlTtile.setBackgroundResource(R.color.pink_line);
                    rlTtile.setAlpha(1f);
                }else {
                    rlTtile.setBackgroundResource(R.color.bg_white);
                    rlTtile.setAlpha(0.5f);
                }
            }
        });
    }
    @Override
    public void initListener() {
        ivMmTuijian.setOnClickListener(this);
        ivMyYongpin.setOnClickListener(this);
        ivNfNiaoBu.setOnClickListener(this);
        ivShRiYong.setOnClickListener(this);
        llXiaoXi.setOnClickListener(this);
        ivActivityOne.setOnClickListener(this);
        ivActivityTwo.setOnClickListener(this);
        ivActivityThree.setOnClickListener(this);
        ivImgOne.setOnClickListener(this);
        ivImgTwo.setOnClickListener(this);
        ivImgThree.setOnClickListener(this);
        ivImgFour.setOnClickListener(this);
        rlHotMore.setOnClickListener(this);
//        ivCheOne.setOnClickListener(this);
//        ivCheTwo.setOnClickListener(this);
//        ivCheThree.setOnClickListener(this);
//        ivCheFour.setOnClickListener(this);
    }

    @Override
    public void initData() {
        getHomeSort();
        YouXuanBottonAdapter erTongZsAdapter = new YouXuanBottonAdapter(getContext());
        SyTjYysAdapter syTjYysAdapter = new SyTjYysAdapter(getContext());
        SyTjFwAdapter adapter = new SyTjFwAdapter(getContext());
        SyTjYzcAdapter syTjYzcAdapter = new SyTjYzcAdapter(getContext());
        List<MyWenDaBean.ArtImage> list =new ArrayList<MyWenDaBean.ArtImage>();
        for (int i=0;i<9;i++){
            MyWenDaBean.ArtImage s = new MyWenDaBean.ArtImage();
            BannerBean.Typ typ = new BannerBean.Typ();
            typ.img="";
            typ.count=1;

            s.img="";
            s.kind="我的tian";
            s.title="我的没人";
            s.money="2";
            list.add(s);
        }
        List<BannerBean.Typ > listTyp = new ArrayList<BannerBean.Typ>();
        for (int i=0;i<9;i++){

            BannerBean.Typ typ = new BannerBean.Typ();
            typ.img="";
            typ.count=1;
            listTyp.add(typ);


        }
        bannerView.initData(listTyp);
        if (listTyp.size() > 1) {
            bannerView.startScroll();
        }

        adapter.addData(list);
        syTjYysAdapter.addData(list);
        syTjYzcAdapter.addData(list);

        erTongZsAdapter.addData(list);
        lvAuto.setAdapter(erTongZsAdapter);


    }
    //获取首页分类item
    private void getHomeSort() {
        novate = new MyRetrofit(getActivity()).getMyApiService();
        myAPI = novate.create(MyAPI.class);
        novate.call(myAPI.getHomeSort(), new MyBaseSubscriber<HomeSortBean>(getActivity()) {
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(HomeSortBean homeSortBean) {
                if (homeSortBean.resultCode==200){
                    data = homeSortBean.data;
                    tvPositionOne.setText(data.get(0).sortName);
                    tvPositonTwo.setText(data.get(1).sortName);
                    tvPositionThree.setText(data.get(2).sortName);
                    tvPositionFour.setText(data.get(3).sortName);
                }else {
                    showShortMsg(homeSortBean.message);
                }
            }
        });


    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onScrollY(int y) {

    }

    /**
     * 轮播图
     */
    View.OnClickListener bannerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {



        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_mm_tuijan:
                intent = new Intent(getActivity(), YzcYouXuanActivity.class);
               intent.putExtra("sortId",data.get(0).sortId+"");
                startActivity(intent);
                break;
            case R.id.iv_muying_yongpin:
                intent = new Intent(getActivity(), YzcYouXuanActivity.class);
                  intent.putExtra("sortId",data.get(1).sortId+"");
                startActivity(intent);
                break;
            case R.id.iv_naifen_niaobu:
                intent = new Intent(getActivity(), YzcYouXuanActivity.class);
                 intent.putExtra("sortId",data.get(2).sortId+"");
                startActivity(intent);
                break;
            case R.id.iv_shenghuo_riyong:
                intent = new Intent(getActivity(), YzcYouXuanActivity.class);
                 intent.putExtra("sortId",data.get(3).sortId+"");
                startActivity(intent);
                break;
            case R.id.ll_xiaoxi:
                intent = new Intent(getActivity(), ShoppingCartActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_activity_one:
                intent = new Intent(getActivity(),ProductDetailActivity.class);
                intent.putExtra("from","other");
                startActivity(intent);
                break;
            case R.id.iv_activity_two:
                intent = new Intent(getActivity(),ProductDetailActivity.class);
                intent.putExtra("from","other");
                startActivity(intent);
                break;
            case R.id.iv_activity_three:
                intent = new Intent(getActivity(),ProductDetailActivity.class);
                intent.putExtra("from","other");
                startActivity(intent);
                break;
            case R.id.iv_img_one:
                intent = new Intent(getActivity(),ProductDetailActivity.class);
                intent.putExtra("from","other");
                startActivity(intent);
                break;
            case R.id.iv_img_two:
                intent = new Intent(getActivity(),ProductDetailActivity.class);
                intent.putExtra("from","other");
                startActivity(intent);
                break;
            case R.id.iv_img_three:
                intent = new Intent(getActivity(),ProductDetailActivity.class);
                intent.putExtra("from","other");
                startActivity(intent);
                break;
            case R.id.iv_img_four:
                intent = new Intent(getActivity(),ProductDetailActivity.class);
                intent.putExtra("from","other");
                startActivity(intent);
                break;
            case R.id.rl_hot_more:
                intent = new Intent(getActivity(), YzcYouXuanActivity.class);
                startActivity(intent);
                break;

            default:
                super.onClick(v);
                break;
        }



    }




    }
