package cn.aiyangkeji.fragmnets;

import android.content.Intent;
import android.os.Bundle;
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

public class YysHaoPingFragment extends  BaseFragment implements AutoListView.OnRefreshListener,
        AutoListView.OnLoadListener, AutoListView.MyScrollListener{

    private AutoListView lvYys;
    private View view;

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
        View   headview = LayoutInflater.from(getContext()).inflate(R.layout.list_view_head_null, null);
        lvYys.initView(getContext(),headview);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        YingYangShiAdapter yingYangShiAdapter = new YingYangShiAdapter(getActivity(),clickListener);
        List<MyWenDaBean.ArtImage> list =new ArrayList<MyWenDaBean.ArtImage>();
        for (int i=0;i<9;i++){
            MyWenDaBean.ArtImage s = new MyWenDaBean.ArtImage();
            s.img="";
            s.kind="我的tian";
            s.title="我的没人";
            s.money="2";
            list.add(s);
        }
        yingYangShiAdapter.addData(list);
        lvYys.setAdapter(yingYangShiAdapter);
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
    View.OnClickListener clickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(getContext(), YingTangShiDetailActivity.class);
            startActivity(intent);
        }
    };
}
