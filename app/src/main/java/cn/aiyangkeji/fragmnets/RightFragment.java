package cn.aiyangkeji.fragmnets;

/**
 * Created by chenzhikai on 2017/11/3.
 */



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.zhaoyys.ZhiShiListActivity;
import cn.aiyangkeji.adapter.RvZhiShiRightAdapter;
import cn.aiyangkeji.adapter.SyTjYysAdapter;


/**
 * 作者：程序员小冰 on 2015/11/30 19:56
 * <p/>
 * 网站：http://blog.csdn.net/qq_21376985
 * <p/>
 * 微博：http://weibo.com/mcxiaobing
 */
public class RightFragment extends BaseFragment {
    private TextView tv;
    private View rootView;
    private RecyclerView rvZhiShiRight;
    private LinearLayoutManager linearLayoutManager;
    private RvZhiShiRightAdapter rvZhiShiRightAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.right_fragment,container,false);

        //首先要注册eventbus
        EventBus.getDefault().register(this);
        initView();
        initListener();
        initData();
        return rootView;
    }

    /**
     *
     * 从发布者那里得到eventbus传送过来的数据
     *
     * 加上@Subscribe以防报错：its super classes have no public methods with the @Subscribe annotation
     *
     * @param event
     */
    @Subscribe
    public void onEvent(List<String> event){
        rvZhiShiRightAdapter.clear();
        rvZhiShiRightAdapter.addData(event);
        rvZhiShiRight.setAdapter(rvZhiShiRightAdapter);
        rvZhiShiRightAdapter.setClickListener(new SyTjYysAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getContext(), ZhiShiListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initView() {
        rvZhiShiRight = (RecyclerView)rootView.findViewById(R.id.rv_zhishi_right);
        rvZhiShiRight.setItemAnimator(new DefaultItemAnimator());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL );
        rvZhiShiRight.setLayoutManager(linearLayoutManager);


    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        rvZhiShiRightAdapter = new RvZhiShiRightAdapter(getContext());

    }

    /**
     * 最后一定要在销毁的时候，解绑
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
