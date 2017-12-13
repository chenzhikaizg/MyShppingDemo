package cn.aiyangkeji.fragmnets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.aiyangkeji.R;

/**
 * Created by chenzhikai on 2017/10/31.
 * 知识
 */

public class HomeZhiShiFragment extends BaseFragment {

    private View view;
    private ImageView ivBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zhishi, container, false);
        initView();
        initListener();
        initData();

        return view;
    }

    @Override
    public void initView() {

        TextView tvTitle = (TextView)view.findViewById(R.id.tv_title);
        tvTitle.setText("知识");
        ivBack = (ImageView)view.findViewById(R.id.iv_back);
        ivBack.setVisibility(View.GONE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }
}
