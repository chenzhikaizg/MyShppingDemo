package cn.aiyangkeji.activities.store;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;
import com.tamic.novate.callback.RxStringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(ProductDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ProductDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(ProductDetailActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(ProductDetailActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    private String s;

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
                new Novate.Builder(this)
                        .baseUrl("http://192.168.120.233:8080/iyang-o2o/")
                        .addCache(false)
                        .build()
                        .rxGet("alipay/sign",new HashMap(), new RxStringCallback() {

                            @Override
                            public void onStart(Object tag) {
                                super.onStart(tag);
                            }

                            @Override
                            public void onError(Object tag, Throwable e) {

                            }

                            @Override
                            public void onCancel(Object tag, Throwable e) {

                            }

                            @Override
                            public void onNext(Object tag, final String response) {
                                s = response;




                            }


                        });
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
//                if (status==0){
//                    ivCollectionStatus.setImageResource(R.mipmap.collection);
//                    status=1;
//                }else {
//                    ivCollectionStatus.setImageResource(R.mipmap.collection_no);
//                    status=0;
//                }
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(ProductDetailActivity.this);
                        Log.e("ailipay==", "run:== "+s );
                  //      s="app_id=2017121200643477&timestamp=2016-07-29+16%3A55%3A53&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220.01%22%2C%22subject%22%3A%221%22%2C%22body%22%3A%22%E6%88%91%E6%98%AF%E6%B5%8B%E8%AF%95%E6%95%B0%E6%8D%AE%22%2C%22out_trade_no%22%3A%221215192336-9385%22%7D&method=alipay.trade.app.pay&charset=utf-8&version=1.0&sign_type=RSA2&sign=FRl5ozOZAM%2FvDse9bPnGIbm1nGyKUfZA0%2Fe1qGzY4UASQMEPav2yN7khkY6voJ%2FFU%2BslYui8eZmPRtjSfopH8%2F7KMQSb4P64SqgsVBQbvJ4Fjz%2BuosAFPkIO3JShWq8p2%2B%2F%2FfdiYC7765eyILHa6NZXD1HrK3AyNgGZnh9no6FvZMTuiQazNdwdmf%2FfN48sQoA4xuzolUihQ0rf%2FlLLsnbcgMsDdPMBzIpdJTW2FCwb9gCELnSL1%2Fd1AypeT8lqDO77NkkbFd0cdBC4dXVmRgjxCWyfJHd6r69kCq6oFQB%2BMKXLIlOKbcHBM%2Fuj3tmdrBb9i6ENPzSrAPeyeZG%2Frtw%3D%3D";
                        s="alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017121200643477&biz_content=%7B%22body%22%3A%22app%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95%22%2C%22out_trade_no%22%3A%2220171218604%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22App%E6%94%AF%E4%BB%98%E6%B5%8B%E8%AF%95Java%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.baidu.com&sign=ZPkGmavxsRjzMRiWESsO6QsSMwK0ocHoC%2Fgsfj24mbTnCp%2BWOBBnnkb%2BJLOUbGnKiIPepLB6fkAn3q66CtWfMWZx4K6d7SVGeCydKiIKcuSt1h1LTcKLjIa8hCxiFf%2B8aJkkJmYRKQM3aFacFG2MM%2BSkRWEaTKNpeswRVrIreiZr%2FBK12l2sxpqoAK14LhA85F7rLJO3S1oW%2F6kyuBsDRQTYBiOHAaazretpNFBcd6Pu27%2F4ARpRaw9AGAZ0MFExHr7VIzX5xLZmgdjNFQjiDrzSslRmJcvQstd6N%2FR31FPsLgmHhzi5B%2FhFXxct%2FttMbArxqO7uncb%2Foov1pqY1Jw%3D%3D&sign_type=RSA2&timestamp=2017-12-18+15%3A50%3A43&version=1.0";
                        Map<String, String> result = alipay.payV2(s, true);
                        Log.i("msp", result.toString());

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();

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
