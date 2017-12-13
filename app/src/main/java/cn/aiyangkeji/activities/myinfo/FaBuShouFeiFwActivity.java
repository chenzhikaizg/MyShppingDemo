package cn.aiyangkeji.activities.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.adapter.OneYuanAdapter;
import cn.aiyangkeji.util.GridViewTool;

import static android.R.attr.x;

/**
 * Created by 18810 on 2017/11/17.
 */

public class FaBuShouFeiFwActivity extends BaseActivity {

    private GridView gdView;
    private int[] ids = {R.mipmap.aiyang_fabu_icon_normal,R.mipmap.yundong_fabu_iocn_normal,R.mipmap.zuoyuezi_fabu_icon_normal,R.mipmap.yye_fabu_iocn_normal,R.mipmap.ertyy_fabu_iocn_normal,R.mipmap.jianfss_fabu_icon_normal
    ,R.mipmap.yundong_fabu_iocn_normal,R.mipmap.tnb_fabu_iocn_normal,R.mipmap.sangao_fabu_iocn_normal};
    private OneYuanAdapter adapter;
    private ImageView ivRlImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fbsffw);
    }

    @Override
    public void initView() {
        TextView textView = (TextView)findViewById(R.id.tv_title);
        textView.setText("发布服务");
        gdView = (GridView)findViewById(R.id.gv_img);
        ivRlImg = (ImageView)findViewById(R.id.iv_rl_img);
        int aiyang_fabu_icon_normal = R.mipmap.aiyang_fabu_icon_normal;



    }

    @Override
    public void initListener() {
        gdView.setOnItemClickListener(clickListener);
    }

    @Override
    public void initData() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i=0;i<ids.length;i++){
            list.add(ids[i]);
        }
        adapter = new OneYuanAdapter(this);
        adapter.addData(list);
        gdView.setAdapter(adapter);
        GridViewTool.adaptiveHight(gdView, this, 5, 3);
    }

    /**
     * 一元多宝item
     */
    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Glide.with(FaBuShouFeiFwActivity.this).load(adapter.getItem(position)).into(ivRlImg);



        }
    };
}
