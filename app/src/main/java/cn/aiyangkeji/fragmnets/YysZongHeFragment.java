package cn.aiyangkeji.fragmnets;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.YingTangShiDetailActivity;
import cn.aiyangkeji.adapter.YingYangShiAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by chenzhikai on 2017/11/7.
 */

public class YysZongHeFragment extends  BaseFragment implements AutoListView.OnRefreshListener,
        AutoListView.OnLoadListener, AutoListView.MyScrollListener{

    private View view;
    private AutoListView lvYys;
    private View headview;
    private int pageNo = 0;
    private YingYangShiAdapter yingYangShiAdapter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<MyWenDaBean.ArtImage> values = (List<MyWenDaBean.ArtImage>) msg.obj;

            switch (msg.what) {
                case AutoListView.REFRESH:
                    lvYys.onRefreshComplete();
                    yingYangShiAdapter.clear();
                    yingYangShiAdapter.addData(values);
                    if (yingYangShiAdapter.getCount() != 0) {
                        lvYys.footViewShow();
                        //rlNoData.setVisibility(View.GONE);
                    } else {
                        // rlNoData.setVisibility(View.VISIBLE);
                    }

                    break;
                case AutoListView.LOAD:
                    lvYys.onLoadComplete();
                    yingYangShiAdapter.addData(values);
                    break;
            }
            lvYys.setResultSize(values.size());
            yingYangShiAdapter.notifyDataSetChanged();
        }


    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zonghe_yys, container, false);
        initView();
        initListener();
        initData();

        return view;
    }

    @Override
    public void initView() {
        lvYys = (AutoListView) view.findViewById(R.id.lv_yys);
        headview = LayoutInflater.from(getContext()).inflate(R.layout.list_view_head_null, null);
        lvYys.initView(getContext(),headview);

    }

    @Override
    public void initListener() {
        lvYys.setOnRefreshListener(this);
        lvYys.setOnLoadListener(this);
    }

    @Override
    public void initData() {

        yingYangShiAdapter = new YingYangShiAdapter(getContext(),clickListener);
        lvYys.setAdapter(yingYangShiAdapter);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        pageNo = 0;
        loadData(AutoListView.REFRESH);
    }

    @Override
    public void onLoad() {

        pageNo = yingYangShiAdapter.getCount();
        loadData(AutoListView.LOAD);
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
                getArtistDetails(what);
            }
        }).start();
    }

    private void getArtistDetails(int what) {

        List<MyWenDaBean.ArtImage> list =new ArrayList<MyWenDaBean.ArtImage>();
        for (int i=0;i<11;i++){
            MyWenDaBean.ArtImage s = new MyWenDaBean.ArtImage();
            s.img="";
            s.kind="我的tian";
            s.title="我的没人";
            s.money="2";
            list.add(s);
        }

        Message msg = handler.obtainMessage();
        msg.what = what;
        msg.obj = list;
        handler.sendMessage(msg);


    }



    @Override
    public void onScrollY(int y) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_yys_item:
                Intent intent = new Intent(getContext(), YingTangShiDetailActivity.class);
                startActivity(intent);
                break;


        }


        super.onClick(v);

    }
    View.OnClickListener clickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(getContext(), YingTangShiDetailActivity.class);
            startActivity(intent);
        }
    };
}
