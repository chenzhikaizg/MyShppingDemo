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
import cn.aiyangkeji.activities.base.BaseFragmentActivity;
import cn.aiyangkeji.adapter.AttentionProductAdapter;
import cn.aiyangkeji.adapter.AttentionYysAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by chenzhikai on 2017/11/8.
 */

public class AttentionCommodityFragment extends BaseFragment {


    private View view;
    private AutoListView lvYys;
    private View headview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_attention_commodity, container, false);
        initView();
        initListener();
        initData();
        return view ;
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
        AttentionProductAdapter yingYangShiAdapter = new AttentionProductAdapter(getContext(),clickListener);
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
