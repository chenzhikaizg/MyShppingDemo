package cn.aiyangkeji.activities.myinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;

/**
 * Created by chenzhikai on 2017/11/4.
 * 我的问答页面
 */

public class MyHealthActivity extends BaseActivity {

    private ImageView ivback;
    private RelativeLayout rlYuLingOne;
    private ImageView ivOne;
    private RelativeLayout rlYunLingTwo;
    private ImageView ivTwo;
    private RelativeLayout rlYuLingThree;
    private ImageView ivThree;
    private RelativeLayout rlYuLingFour;
    private ImageView ivFour;
    private RelativeLayout rlHanZu;
    private ImageView ivHanZu;
    private RelativeLayout rlLittleNation;
    private ImageView ivTittleNation;
    private RelativeLayout rlStudent;
    private ImageView ivStudent;
    private RelativeLayout rlMm;
    private ImageView ivMm;
    private ImageView ivWokder;
    private RelativeLayout rlWorker;
    private RelativeLayout rlBaiLing;
    private ImageView ivBaiLing;
    private RelativeLayout rlFreeDom;
    private ImageView ivFreeDom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_health);

    }

    @Override
    public void initView() {
        TextView textView= (TextView)findViewById(R.id.tv_title);
        textView.setText("我的健康");
        ivback = (ImageView) findViewById(R.id.iv_back);
        rlYuLingOne = (RelativeLayout)findViewById(R.id.rl_yuling_one);
        ivOne = (ImageView)findViewById(R.id.iv_one);
        rlYunLingTwo = (RelativeLayout)findViewById(R.id.rl_yuling_two);
        ivTwo = (ImageView)findViewById(R.id.iv_two);
        rlYuLingThree = (RelativeLayout)findViewById(R.id.rl_yuling_three);
        ivThree = (ImageView)findViewById(R.id.iv_three);
        rlYuLingFour = (RelativeLayout)findViewById(R.id.rl_yuling_four);
        ivFour = (ImageView)findViewById(R.id.iv_four);
        rlHanZu = (RelativeLayout)findViewById(R.id.rl_hanzu);
        ivHanZu = (ImageView)findViewById(R.id.iv_haunzu);
        rlLittleNation = (RelativeLayout)findViewById(R.id.rl_little_nation);
        ivTittleNation = (ImageView)findViewById(R.id.iv_little_nation);
        rlStudent = (RelativeLayout)findViewById(R.id.rl_student);
        ivStudent = (ImageView)findViewById(R.id.iv_student);
        rlMm = (RelativeLayout)findViewById(R.id.rl_mm);
        ivMm = (ImageView)findViewById(R.id.iv_mm);
        rlWorker = (RelativeLayout)findViewById(R.id.rl_worker);
        ivWokder = (ImageView)findViewById(R.id.iv_worker);
        rlBaiLing = (RelativeLayout)findViewById(R.id.rl_bailing);
        ivBaiLing = (ImageView)findViewById(R.id.iv_bailing);
        rlFreeDom = (RelativeLayout)findViewById(R.id.rl_freedom);
        ivFreeDom = (ImageView)findViewById(R.id.iv_freedom);

    }

    @Override
    public void initListener() {
        ivback.setOnClickListener(this);
        rlYuLingOne.setOnClickListener(this);
        rlYunLingTwo.setOnClickListener(this);
        rlYuLingThree.setOnClickListener(this);
        rlYuLingFour.setOnClickListener(this);
        rlStudent.setOnClickListener(this);
        rlHanZu.setOnClickListener(this);
        rlLittleNation.setOnClickListener(this);
        rlMm.setOnClickListener(this);
        rlWorker.setOnClickListener(this);
        rlBaiLing.setOnClickListener(this);
        rlFreeDom.setOnClickListener(this);





    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_yuling_one:



                 setYysClass(listOne,ivOne);

                break;
            case R.id.rl_yuling_two:
                setYysClass(listOne,ivTwo);
                break;
            case R.id.rl_yuling_three:
                setYysClass(listOne,ivThree);
                break;
            case R.id.rl_yuling_four:
                setYysClass(listOne,ivFour);
                break;
            case R.id.rl_hanzu:
                setYysClass(listNation,ivHanZu);
                break;
            case R.id.rl_little_nation:
                setYysClass(listNation,ivTittleNation);
                break;
            case R.id.rl_student:
                setYysClass(listProfession,ivStudent);
                break;
            case R.id.rl_mm:
                setYysClass(listProfession,ivMm);
                break;
            case R.id.rl_worker:
                setYysClass(listProfession,ivWokder);
                break;
            case R.id.rl_bailing:
                setYysClass(listProfession,ivBaiLing);
                break;
            case R.id.rl_freedom:
                setYysClass(listProfession,ivFreeDom);
                break;

            default:
                super.onClick(v);
                break;


        }


    }
        Map<String,String> maps = new HashMap<String ,String>();
        List<ImageView> listOne = new ArrayList<ImageView>();
    List<ImageView> listNation = new ArrayList<ImageView>();
    List<ImageView> listProfession = new ArrayList<ImageView>();
    //改变Imageview的选中状态
    public void setYysClass( List<ImageView> list ,ImageView iv){
        if (list.equals(listOne)){
            ivOne.setImageResource(R.mipmap.weixianzhong_icon_normal_);
            ivTwo.setImageResource(R.mipmap.weixianzhong_icon_normal_);
            ivThree.setImageResource(R.mipmap.weixianzhong_icon_normal_);
            ivFour.setImageResource(R.mipmap.weixianzhong_icon_normal_);
        }else if (list.equals(listNation)){
            ivHanZu.setImageResource(R.mipmap.weixianzhong_icon_normal_);
            ivTittleNation.setImageResource(R.mipmap.weixianzhong_icon_normal_);
        }else {
            ivStudent.setImageResource(R.mipmap.weixianzhong_icon_normal_);
            ivMm.setImageResource(R.mipmap.weixianzhong_icon_normal_);
            ivWokder.setImageResource(R.mipmap.weixianzhong_icon_normal_);
            ivBaiLing.setImageResource(R.mipmap.weixianzhong_icon_normal_);
            ivFreeDom.setImageResource(R.mipmap.weixianzhong_icon_normal_);
        }






        if (list.size()==0){
            iv.setImageResource(R.mipmap.quanzhong_icon_normal_);

            list.add(iv);
        }else {
            if (list.get(0)==iv){
                ivFour.setImageResource(R.mipmap.weixianzhong_icon_normal_);
                list.clear();
            }else {
                list.clear();
                iv.setImageResource(R.mipmap.quanzhong_icon_normal_);
                list.add(iv);
            }
        }
    }

}
