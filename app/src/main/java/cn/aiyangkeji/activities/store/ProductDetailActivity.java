package cn.aiyangkeji.activities.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.adapter.YysDetailImgAdapter;
import cn.aiyangkeji.bean.BannerBean;
import cn.aiyangkeji.bean.GouWuCheBean;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.bean.ProductDetailBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.util.ListViewUtil;
import cn.aiyangkeji.util.UIHelper;
import cn.aiyangkeji.view.BannerView;

/**
 * Created by chenzhikai on 2017/11/16.
 */

public class ProductDetailActivity extends BaseActivity {

    private BannerView bannerView;
    private TextView tvGouWuChe;
    private RelativeLayout rlKeFu;
    private RelativeLayout rlNowBuy;
    private Intent intent;
    private RelativeLayout rlMoreEcaluate;
    private ImageView ivCollectionStatus;
    private RelativeLayout rlCollection;
    private ImageView ivCar;
    private ListView mLv;
    private ImageView ivBack;
    private static final int TO_CAR = 1;
    private GouWuCheBean.Value value;
    private String from;
    private String goods_id;
    private TextView tvProductName;
    private TextView tvProductPrice;
    private TextView tvSoldNum;
    private ProductDetailBean.Product data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
    }

    @Override
    public void initView() {
        ivBack = (ImageView)findViewById(R.id.iv_back);

        bannerView = (BannerView)findViewById(R.id.bv_banner);
        int height = (int) (UIHelper.getScreenWidth(this) * 0.5);
        int width = UIHelper.getScreenWidth(this);
       bannerView.initView(width, height);
        bannerView.setItemClickListener(bannerClickListener);
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

        bannerView.setFocusable(true);
        bannerView.setFocusableInTouchMode(true);
        bannerView.requestFocus();
        tvGouWuChe = (TextView)findViewById(R.id.tv_gouwuche);
        rlKeFu = (RelativeLayout)findViewById(R.id.rl_kefu);
        rlNowBuy = (RelativeLayout)findViewById(R.id.rl_now_buy);
        rlMoreEcaluate = (RelativeLayout)findViewById(R.id.rl_tv_evaluate);
        ivCollectionStatus = (ImageView)findViewById(R.id.iv_collection_status);
        rlCollection = (RelativeLayout)findViewById(R.id.rl_collection_status);
        ivCar = (ImageView)findViewById(R.id.iv_title_gouwuche);
        mLv = (ListView)findViewById(R.id.lv_img);
        tvProductName = (TextView)findViewById(R.id.tv_product_name);
        tvProductPrice = (TextView)findViewById(R.id.tv_product_money);
        tvSoldNum = (TextView)findViewById(R.id.tv_sold_num);


    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
        tvGouWuChe.setOnClickListener(this);
        rlKeFu.setOnClickListener(this);
        rlNowBuy.setOnClickListener(this);
        rlMoreEcaluate.setOnClickListener(this);
        rlCollection.setOnClickListener(this);
        ivCar.setOnClickListener(this);
    }

    @Override
    public void initData() {
        from = getIntent().getStringExtra("from");
        goods_id = getIntent().getStringExtra("goods_id");

        getProductDetail();
        value = new GouWuCheBean.Value();


        YysDetailImgAdapter yingYangShiAdapter = new YysDetailImgAdapter(this);
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
        yingYangShiAdapter.addData(list);
        mLv.setAdapter(yingYangShiAdapter);
        ListViewUtil.adaptiveHight(this,mLv,1f);
    }

    private void getProductDetail() {
        Novate novate = new MyRetrofit(this).getMyApiService();
        MyAPI myAPI = novate.create(MyAPI.class);
        novate.call(myAPI.getProductDetail(goods_id), new MyBaseSubscriber<ProductDetailBean>(ProductDetailActivity.this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ProductDetailBean productDetailBean) {
                    if (productDetailBean.resultCode==200){
                        showShortMsg(productDetailBean.toString());
                        data = productDetailBean.data;
                        tvProductName.setText(productDetailBean.data.goods_name);
                                tvProductPrice.setText(productDetailBean.data.goods_price+"");
                                tvSoldNum.setText(productDetailBean.data.goods_sail+"");

                    }else {
                        showShortMsg(productDetailBean.message);

                    }

            }
        });


    }

    /**
     * 轮播图
     */
    View.OnClickListener bannerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {



        }
    };
    private int status= 0;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                if (value.number!=0 && from.equals("car")){
                    setResult(TO_CAR);
                    intent.putExtra("value",value);
                }
                this.finish();
                break;
            case R.id.tv_gouwuche:

                value.number = 2;
                value.price= 1445+"";
                intent = new Intent(this,AddtoCartActivity.class);
                intent.putExtra("from","add2car");
                intent.putExtra("goodsId",data.goods_id+"");
                intent.putExtra("goodsName",data.goods_name);

                StringBuilder sb = new StringBuilder();
                for (int a=0;a<data.specIds.size();a++){
                    if (a==data.specIds.size()-1){
                        sb.append(data.specIds.get(a));
                    }else {
                        sb.append(data.specIds.get(a)+",");
                    }

                }
                intent.putExtra("specIds",new String(sb));
                startActivity(intent);
                break;
            case R.id.rl_kefu:

                break;
            case R.id.rl_now_buy:
                StringBuilder sbs = new StringBuilder();
                intent = new Intent(this,AddtoCartActivity.class);
                for (int a=0;a<data.specIds.size();a++){
                    if (a==data.specIds.size()-1){
                        sbs.append(data.specIds.get(a));
                    }else {
                        sbs.append(data.specIds.get(a)+",");
                    }

                }
                intent.putExtra("from","buynow");
                intent.putExtra("goodsId",data.goods_id+"");
                intent.putExtra("goodsName",data.goods_name);

                intent.putExtra("specIds",new String(sbs));
                startActivity(intent);
                break;
            case R.id.rl_tv_evaluate:
                intent = new Intent(this,ProductEvaluateActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_collection_status:
                if (status==0){
                    ivCollectionStatus.setImageResource(R.mipmap.collection);
                    status=1;
                }else {
                    ivCollectionStatus.setImageResource(R.mipmap.collection_no);
                    status=0;
                }
                break;
            case R.id.iv_title_gouwuche:
                intent = new Intent(this,ShoppingCartActivity.class);
                startActivity(intent);
                break;
            default:
                super.onClick(v);
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){


            this.finish();
            return false;
        }else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
