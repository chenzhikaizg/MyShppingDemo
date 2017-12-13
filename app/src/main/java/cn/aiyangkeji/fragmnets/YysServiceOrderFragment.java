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
import cn.aiyangkeji.adapter.MyWenDaAdapter;
import cn.aiyangkeji.adapter.OrderYysAllAdapter;
import cn.aiyangkeji.adapter.OrderYysServiceAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by 18810 on 2017/11/25.
 */

public class YysServiceOrderFragment extends BaseFragment {

    private View view;
    private AutoListView lvYys;
    private View headview;

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

    }

    @Override
    public void initData() {

        OrderYysServiceAdapter yingYangShiAdapter = new OrderYysServiceAdapter(getContext(),clickListener);
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

    View.OnClickListener clickListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(getContext(), YingTangShiDetailActivity.class);
            startActivity(intent);
        }
    };
}
