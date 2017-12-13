package cn.aiyangkeji.fragmnets;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.YingTangShiDetailActivity;
import cn.aiyangkeji.activities.myinfo.AddAddressActivity;
import cn.aiyangkeji.adapter.MyWenDaTouTingAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by chenzhikai on 2017/11/7.
 */

public class WdYunqYyFragment extends  BaseFragment implements AutoListView.OnRefreshListener,
        AutoListView.OnLoadListener, AutoListView.MyScrollListener{

    private View view;
    private AutoListView lvYys;
    private View headview;
    private String path;
    private MediaPlayer mediaPlayer;

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
        MyWenDaTouTingAdapter adapter = new MyWenDaTouTingAdapter(getContext(),clickListener);
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
        lvYys.setAdapter(adapter);

        //这里给一个歌曲的网络地址就行了
        path = "http://p023ecb00.bkt.clouddn.com/594396-Uplifting-Corporate.mp3";


        mediaPlayer = new MediaPlayer();

        // 通过Uri解析一个网络地址

        Uri uri = Uri

                .parse(path);

        try {
            mediaPlayer.setDataSource(getContext(), uri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

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



            if (mediaPlayer.isPlaying()){
                mediaPlayer.pause();

            }else {
                mediaPlayer.start();
            }

//            Intent intent =new Intent(getContext(), YingTangShiDetailActivity.class);
//            startActivity(intent);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}
