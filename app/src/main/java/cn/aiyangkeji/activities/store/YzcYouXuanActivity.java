package cn.aiyangkeji.activities.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.adapter.LjYyueAdapter;
import cn.aiyangkeji.adapter.YzcYouXuanAdapter;
import cn.aiyangkeji.bean.SortProductsBean;
import cn.aiyangkeji.bean.YydjOrderItemBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.util.JsontoRequestBody;

/**
 * Created by 陈志凯 on 2017/11/23.
 * 月子餐优选
 */

public class YzcYouXuanActivity extends BaseActivity {

    private RecyclerView rvYzc;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private ImageView ivBack;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private String sortId;
    private YzcYouXuanAdapter ljYyueAdapter;
    private List<SortProductsBean.Products> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yzc_youxuan);

    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("爱样优选");
        ivBack = (ImageView) findViewById(R.id.iv_back);

        rvYzc = (RecyclerView) findViewById(R.id.rv_yzc);
        rvYzc.setItemAnimator(new DefaultItemAnimator());
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        rvYzc.setLayoutManager(staggeredGridLayoutManager);
//        rvYzc.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                staggeredGridLayoutManager.invalidateSpanAssignments();
//            }
//        });


    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void initData() {
        sortId = getIntent().getStringExtra("sortId");
        getSortProducts();

        ljYyueAdapter = new YzcYouXuanAdapter(this);







        ljYyueAdapter.setClickListener(new YzcYouXuanAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(YzcYouXuanActivity.this, ProductDetailActivity.class);
                intent.putExtra("goods_id",data.get(position).goodsId+"");
                startActivity(intent);
            }
        });
    }
    //获取列表
    private void getSortProducts() {
        Novate novate = new MyRetrofit(this).getMyApiService();
        MyAPI myAPI = novate.create(MyAPI.class);
        novate.call(myAPI.getSortProducts(sortId), new MyBaseSubscriber<SortProductsBean>(this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SortProductsBean sortProductsBean) {
                if (sortProductsBean.resultCode == 200) {
                    data = sortProductsBean.data;
                    ljYyueAdapter.addData(data);
                    rvYzc.setAdapter(ljYyueAdapter);
                } else {
                    showShortMsg(sortProductsBean.message);

                }
                }
            });

        }

        @Override
        public void onClick (View v){

            switch (v.getId()) {
                case R.id.iv_back:
                    this.finish();
                    break;
                default:
                    super.onClick(v);
                    break;


            }
        }
    }
