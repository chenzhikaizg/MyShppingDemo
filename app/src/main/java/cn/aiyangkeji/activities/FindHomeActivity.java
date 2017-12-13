package cn.aiyangkeji.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.adapter.FindHistoryAdapter;
import cn.aiyangkeji.adapter.YysDetailImgAdapter;
import cn.aiyangkeji.bean.BannerBean;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.db.StudentInfo;
import cn.aiyangkeji.db.dao.StudentDao;
import cn.aiyangkeji.util.ListViewUtil;

/**
 * Created by ${chenzhikai} on 2017/11/30.
 */

public class FindHomeActivity extends BaseActivity {

    private EditText etFind;
    private LinearLayout llFind;
    private ListView mLv;
    private StudentDao dao;
    private FindHistoryAdapter yingYangShiAdapter;
    private ImageView ivBack;
    private RelativeLayout rlClear;
    private FrameLayout flBelowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_home);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Slide().setDuration(2000));
            getWindow().setExitTransition(new Slide().setDuration(2000));

        }
    }

    @Override
    public void initView() {
        etFind = (EditText)findViewById(R.id.et_find);
        llFind = (LinearLayout)findViewById(R.id.ll_find);
        mLv = (ListView)findViewById(R.id.lv_history);
        ivBack = (ImageView)findViewById(R.id.iv_back);
        rlClear = (RelativeLayout)findViewById(R.id.rl_clear);
        flBelowList = (FrameLayout)findViewById(R.id.fl_below_list);
    }

    @Override
    public void initListener() {
        llFind.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        rlClear.setOnClickListener(this);
    }

    @Override
    public void initData() {
        yingYangShiAdapter = new FindHistoryAdapter(this);

        List<StudentInfo> list =new ArrayList<StudentInfo>();
        dao = new StudentDao(this);
        List<StudentInfo> all = dao.findAll();
        if (all.size()==0){
            rlClear.setVisibility(View.GONE);
            flBelowList.setVisibility(View.GONE);
        }else {
            yingYangShiAdapter.addData(all);
            mLv.setAdapter(yingYangShiAdapter);
            ListViewUtil.adaptiveHight(this,mLv,1f);
            rlClear.setVisibility(View.VISIBLE);
            flBelowList.setVisibility(View.VISIBLE);
        }
    mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(FindHomeActivity.this,FindListActivity.class);
            intent.putExtra("content",yingYangShiAdapter.getItem(position).getName());
            startActivityForResult(intent,2);
        }
    });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_find:
                String s = etFind.getText().toString();
                if(s.equals("")){
                    showShortMsg("请输入搜索内容");
                }else {
                    List<String> strList = new ArrayList<String>();
                 for (StudentInfo  student : dao.findAll()){
                    strList.add(student.getName());
                 }
                if (!strList.contains(s)){
                    dao.add(s);
                }
                    Intent intent = new Intent(this,FindListActivity.class);
                    intent.putExtra("content",s);
                    startActivityForResult(intent,1);
                }
              //  overridePendingTransition(R.anim.from_right_to_left,0);
                break;
            case R.id.iv_back:
                this.finish();

                break;
            case R.id.rl_clear:
                dao.deleteAll();
                yingYangShiAdapter.clear();
                yingYangShiAdapter.notifyDataSetChanged();
                rlClear.setVisibility(View.GONE);
                flBelowList.setVisibility(View.GONE);
                break;
            default:
            super.onClick(v);
            break;



        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            List<StudentInfo> all = dao.findAll();
            yingYangShiAdapter.clear();
            if (all.size()==0){
                rlClear.setVisibility(View.GONE);
                flBelowList.setVisibility(View.GONE);
            }else {

                yingYangShiAdapter.addData(all);
                yingYangShiAdapter.notifyDataSetChanged();
                mLv.setAdapter(yingYangShiAdapter);
                ListViewUtil.adaptiveHight(this,mLv,1f);
                rlClear.setVisibility(View.VISIBLE);
                flBelowList.setVisibility(View.VISIBLE);
            }

        }
    }
}
