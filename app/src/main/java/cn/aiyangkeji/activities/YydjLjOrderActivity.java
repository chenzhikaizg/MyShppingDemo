package cn.aiyangkeji.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.adapter.LjYyueAdapter;
import cn.aiyangkeji.bean.BannerBean;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.bean.YydjOrderItemBean;

/**
 * Created by chenzhikai on 2017/11/20.
 * 营养到家立即预约页面
 */

public class YydjLjOrderActivity extends BaseActivity {

    private RecyclerView rvMonth;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private int size;
    private Button btnOver;
    private EditText etPhone;
    private EditText etName;
    private ImageView ivClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yydji_mmediately_order);
    }

    @Override
    public void initView() {
        rvMonth = (RecyclerView) findViewById(R.id.rv_month);
        rvMonth.setItemAnimator(new DefaultItemAnimator());
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvMonth.setLayoutManager(staggeredGridLayoutManager);//设置RecyclerView布局管理器为2列垂直排布
        rvMonth.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                staggeredGridLayoutManager.invalidateSpanAssignments();
            }
        });

        btnOver = (Button) findViewById(R.id.btn_over);
        etName = (EditText) findViewById(R.id.et_name);
        etPhone = (EditText) findViewById(R.id.et_phone);

        ivClose = (ImageView) findViewById(R.id.iv_close);


    }


    @Override
    public void initListener() {
        btnOver.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }

    @Override
    public void initData() {
        final LjYyueAdapter ljYyueAdapter = new LjYyueAdapter(this);

        final List<YydjOrderItemBean.ArtImage> list = new ArrayList<YydjOrderItemBean.ArtImage>();

        for (int i = 1; i < 11; i++) {
            YydjOrderItemBean.ArtImage bean = new YydjOrderItemBean.ArtImage();
            bean.month = "2017/" + i;
            list.add(bean);
        }
        final List<Integer> listOne = new ArrayList<Integer>();
        ljYyueAdapter.setClickListener(new LjYyueAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                RelativeLayout rlRoot = (RelativeLayout) view.findViewById(R.id.rl_root);

                for (int i = 0; i < size; i++) {
                    YydjOrderItemBean.ArtImage bean = list.get(i);
                    if (bean.status == 1) {
                        bean.status = 0;
                    }
                    if (position == i) {
                        bean.status = 1;
                    }
                }
                ljYyueAdapter.notifyDataSetChanged();
            }
        });
        ljYyueAdapter.addData(list);
        size = list.size();
        rvMonth.setAdapter(ljYyueAdapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_over:
                submit();
                break;
            case R.id.iv_close:
                this.finish();
                break;
            default:
                super.onClick(v);
                break;


        }

    }

    //提交信息
    private void submit() {
        String strName = etName.getText().toString();
        String strPhone = etPhone.getText().toString();
        this.finish();
    }
}
