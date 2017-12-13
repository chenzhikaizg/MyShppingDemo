package cn.aiyangkeji.activities.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.zhaoyys.ZhiShiListActivity;
import cn.aiyangkeji.adapter.AddCarAdapter;
import cn.aiyangkeji.adapter.LjYyueAdapter;
import cn.aiyangkeji.adapter.SyTjYysAdapter;
import cn.aiyangkeji.bean.YydjOrderItemBean;

/**
 * Created by chenzhikai on 2017/11/20.
 * 加入购物车和立即购买
 */

public class Add2CartActivity extends BaseActivity {

    private RecyclerView rvMonth;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private int size;
    private Button btnOver;
    private RecyclerView rvSize;
    private StaggeredGridLayoutManager staggeredGridLayoutManager2;
    private ImageView ivJia;
    private ImageView ivJian;
    private TextView tvBuyNum;
    private String from;
    private AddCarAdapter ljYyueAdapterSize;
    private AddCarAdapter ljYyueAdapter;
    private List<YydjOrderItemBean.ArtImage> list;
    private List<YydjOrderItemBean.ArtImage> list1;

    private TextView tvProductPrice;
    private TextView tvHaveNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2cart);

    }

    @Override
    public void initView() {

        rvMonth = (RecyclerView)findViewById(R.id.rv_color);
        rvMonth.setItemAnimator(new DefaultItemAnimator());
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        rvMonth.setLayoutManager(staggeredGridLayoutManager);//设置RecyclerView布局管理器为2列垂直排布
        rvMonth.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                staggeredGridLayoutManager.invalidateSpanAssignments();
            }
        });
        rvSize = (RecyclerView)findViewById(R.id.rv_size);
        rvSize.setItemAnimator(new DefaultItemAnimator());
        staggeredGridLayoutManager2 = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        rvSize.setLayoutManager(staggeredGridLayoutManager2);//设置RecyclerView布局管理器为2列垂直排布
        rvSize.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                staggeredGridLayoutManager2.invalidateSpanAssignments();
            }
        });



        btnOver = (Button)findViewById(R.id.btn_over);

        ivJia = (ImageView)findViewById(R.id.iv_jia);
        ivJian = (ImageView)findViewById(R.id.iv_jian);
        tvBuyNum = (TextView)findViewById(R.id.tv_buy_num);
        tvProductPrice = (TextView)findViewById(R.id.tv_product_price);
        tvHaveNum = (TextView)findViewById(R.id.tv_have_num);

    }

    @Override
    public void initListener() {
        btnOver.setOnClickListener(this);
        ivJia.setOnClickListener(this);
        ivJian.setOnClickListener(this);
    }

    @Override
    public void initData() {
        from = getIntent().getStringExtra("from");


        ljYyueAdapter = new AddCarAdapter(this);
        ljYyueAdapterSize = new AddCarAdapter(this);
        list1 = new ArrayList<YydjOrderItemBean.ArtImage>();

        for (int i=1;i<8;i++){
            YydjOrderItemBean.ArtImage bean = new YydjOrderItemBean.ArtImage();
            bean.month= "褐色"+i;
            list1.add(bean);
        }

        ljYyueAdapter.setClickListener(new AddCarAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView rlRoot = (TextView) view.findViewById(R.id.tv_month);

                for (int i=0;i<size;i++){
                    YydjOrderItemBean.ArtImage bean = list1.get(i);
                    if (bean.status==1){
                        bean.status=0;
                    }
                    if (position==i){
                        bean.status=1;
                    }
                }
                    ljYyueAdapter.notifyDataSetChanged();
            }
        });

        ljYyueAdapterSize.setClickListener(new AddCarAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                TextView rlRoot = (TextView) view.findViewById(R.id.tv_month);

                for (int i=0;i<size;i++){
                    YydjOrderItemBean.ArtImage bean = list1.get(i);
                    if (bean.status==1){
                        bean.status=0;
                    }
                    if (position==i){
                        bean.status=1;
                    }
                }
                ljYyueAdapterSize.notifyDataSetChanged();
            }
        });

      // ljYyueAdapter.addData(list1);
     //   ljYyueAdapterSize.addData(list1);
        size = list1.size();
        rvMonth.setAdapter(ljYyueAdapter);
        rvSize.setAdapter(ljYyueAdapterSize);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_over:
                String strNum = tvBuyNum.getText().toString();
                if (from.equals("add2car")){
                    if (ljYyueAdapter.getSelectItem()==100 ||ljYyueAdapterSize.getItemCount()==100){
                        showShortMsg("请选择商品属性");
                        return;
                    }else {
                        submit();
                    }


                }else if (from.equals("buynow")){
                    Intent intent = new Intent(this,ActivityConfirmOrderActivity.class);

                    if (ljYyueAdapter.getSelectItem()==100 ||ljYyueAdapterSize.getItemCount()==100){
                        showShortMsg("请选择商品属性");
                        return;
                    }else {
                        intent.putExtra("price",tvProductPrice.getText().toString());
                        intent.putExtra("color",ljYyueAdapter.getSelectItem()+"");
                        intent.putExtra("size",ljYyueAdapterSize.getSelectItem()+"");
                        intent.putExtra("buynum",strNum);

                        startActivity(intent);
                    }

                }

                break;
            case R.id.iv_jia:
                String strJia = tvBuyNum.getText().toString();
                int  intJia = Integer.valueOf(strJia);
                int  max=Integer.valueOf(12+"");
                if (intJia>=max){
                    showShortMsg("库存只有这么多了");
                    return;

                }else {
                    tvBuyNum.setText(intJia+1+"");
                }

                break;
            case R.id.iv_jian:
                String strJian = tvBuyNum.getText().toString();
                int  intJian = Integer.valueOf(strJian);
                if (intJian<=1){
                    return;
                }else {
                    tvBuyNum.setText(intJian-1+"");
            }

                break;
            default:
                super.onClick(v);
                break;


        }

    }




    //提交信息
    private void submit() {

        this.finish();
    }

}
