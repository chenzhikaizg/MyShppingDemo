package cn.aiyangkeji.activities.myinfo;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.adapter.MyTouTingAdapter;
import cn.aiyangkeji.adapter.MyWenDaAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;

/**
 * Created by chenzhikai on 2017/11/4.
 * 我的问答
 */

public class MyWenDaActivity extends BaseActivity {

    private ImageView ivback;
    private RecyclerView rvMyWenDa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wenda);

    }

    @Override
    public void initView() {
        TextView textView= (TextView)findViewById(R.id.tv_title);
        textView.setText("我的问答");
        ivback = (ImageView) findViewById(R.id.iv_back);

        rvMyWenDa = (RecyclerView)findViewById(R.id.rv_my_wenda);
        rvMyWenDa.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL );
        rvMyWenDa.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initListener() {
        ivback.setOnClickListener(this);
    }

    @Override
    public void initData() {
        final MyWenDaAdapter adapter = new MyWenDaAdapter(this);
        List<MyWenDaBean.ArtImage> list =new ArrayList<MyWenDaBean.ArtImage>();
        for (int i=0;i<9;i++){
            MyWenDaBean.ArtImage s = new MyWenDaBean.ArtImage();
            s.img="";
            s.kind="我的tian";
            s.title="我的没人";
            s.money="2";
            s.name="奥特曼";
            list.add(s);
        }
        adapter.addData(list);
        rvMyWenDa.setAdapter(adapter);
        adapter.setClickListener(new MyWenDaAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            default:
                super.onClick(v);
                break;


        }


    }
}
