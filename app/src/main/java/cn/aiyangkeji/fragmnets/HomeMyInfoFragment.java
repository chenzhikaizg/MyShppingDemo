package cn.aiyangkeji.fragmnets;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tamic.novate.Novate;
import com.tamic.novate.callback.RxStringCallback;

import cn.aiyangkeji.FenXiangActivity;
import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.loginandregist.LoginActivity;
import cn.aiyangkeji.activities.myinfo.CallUsActivity;
import cn.aiyangkeji.activities.myinfo.CallUsIdeaActivity;
import cn.aiyangkeji.activities.myinfo.FaBuShouFeiFwActivity;
import cn.aiyangkeji.activities.myinfo.MyGuanZhuActivity;
import cn.aiyangkeji.activities.myinfo.MyHealthActivity;
import cn.aiyangkeji.activities.myinfo.MyOrderActivity;
import cn.aiyangkeji.activities.myinfo.MyShAdressActivity;
import cn.aiyangkeji.activities.myinfo.MyTouTingActivity;
import cn.aiyangkeji.activities.myinfo.MyWenDaActivity;
import cn.aiyangkeji.activities.myinfo.MyYhqActivity;

import cn.aiyangkeji.activities.myinfo.MyYySActivity;
import cn.aiyangkeji.activities.myinfo.MyYysOrderActivity;
import cn.aiyangkeji.activities.myinfo.MyZiXunActivity;
import cn.aiyangkeji.activities.myinfo.SettingActivity;
import cn.aiyangkeji.activities.myinfo.YingYangShiRuZhuActivity;

import cn.aiyangkeji.activities.myinfo.YysCollectionActivity;
import cn.aiyangkeji.activities.myinfo.YysCustomerActivity;
import cn.aiyangkeji.app.AppConfig;
import cn.aiyangkeji.huanxin.LoginHuanXinActivity;
import cn.aiyangkeji.util.UserInfoUtil;

/**
 * Created by chenzhikai on 2017/10/31.
 */

public class HomeMyInfoFragment extends BaseFragment {

    private View view;
    private RelativeLayout rlMyConsult;
    private RelativeLayout rlMyAnswer;
    private RelativeLayout rlMyHealth;
    private RelativeLayout rlMyDietician;
    private RelativeLayout rlMyAttention;
    private RelativeLayout rlMyOrder;
    private RelativeLayout rlMyDiZhi;
    private RelativeLayout rlDieticianHome;
    private ImageView iv;
    private Intent intent;
    private LinearLayout llYys;
    private LinearLayout llMyFunction;
    private LinearLayout llMyAll;
    private RelativeLayout rlYys;
    private RelativeLayout rlMyYhq;
    private FrameLayout fl;
    private RelativeLayout rlYouhuiQuan;
    private RelativeLayout rlMyTouTing;
    private RelativeLayout rlFbsfFw;
    private RelativeLayout rlCallUs;
    private RelativeLayout rlCallUsIdea;
    private RelativeLayout rlYysCollecton;
    private RelativeLayout rlYysOrder;
    private RelativeLayout rlYysFreeWd;
    private RelativeLayout rlYysCustomer;
    private RelativeLayout rlYysQd;
    private RelativeLayout rlYysAddress;
    private TextView tvSetting;
    private TextView tvName;
    private TextView tvStatus;
    private ImageView ivBack;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home_myinfo, container, false);
        initView();
        initListener();
        initData();

        return view;
    }

    @Override
    public void initView() {

        TextView tvTitle = (TextView)view.findViewById(R.id.tv_title);
        tvTitle.setText("个人中心");
        ivBack = (ImageView)view.findViewById(R.id.iv_back);
        ivBack.setVisibility(View.GONE);
        tvSetting = (TextView)view.findViewById(R.id.tv_search);
        tvSetting.setText("设置");
        tvSetting.setVisibility(View.VISIBLE);

        tvName = (TextView)view.findViewById(R.id.tv_name);
        tvStatus = (TextView)view.findViewById(R.id.tv_status);

        //我的问答一栏的item
        rlMyYhq = (RelativeLayout)view.findViewById(R.id.rl_youhi_quan);
        rlMyConsult = (RelativeLayout)view.findViewById(R.id.rl_my_zixun);
        rlMyAnswer = (RelativeLayout)view.findViewById(R.id.rl_my_answer);
        rlMyHealth = (RelativeLayout)view.findViewById(R.id.rl_my_health);
        rlMyDietician = (RelativeLayout)view.findViewById(R.id.rl_my_dietician);
        rlYouhuiQuan = (RelativeLayout)view.findViewById(R.id.rl_youhi_quan);


        //我的关注一栏的item
        rlMyAttention = (RelativeLayout)view.findViewById(R.id.rl_my_attention);
        rlMyOrder = (RelativeLayout)view.findViewById(R.id.rl_my_order);
        rlMyDiZhi = (RelativeLayout)view.findViewById(R.id.rl_my_dizhi);
        rlDieticianHome = (RelativeLayout)view.findViewById(R.id.rl_dietician_home);
        rlMyTouTing = (RelativeLayout)view.findViewById(R.id.rl_my_touting);
        //营养师
        llYys = (LinearLayout) view.findViewById(R.id.ll_yys_ye);

        rlYys = (RelativeLayout)view.findViewById(R.id.rl_yys_xianmian_hengtianmu);
        rlFbsfFw = (RelativeLayout)view.findViewById(R.id.rl_yys_fb_sfxx);


        //    //我的问答的一栏
        llMyFunction = (LinearLayout)view.findViewById(R.id.ll_my_function);
        //我的关注一栏
        llMyAll = (LinearLayout)view.findViewById(R.id.ll_my_all);
        fl = (FrameLayout)view.findViewById(R.id.fl_below_wode_yys);

        iv = (ImageView)view.findViewById(R.id.iv_my_zixun);

        rlCallUs = (RelativeLayout)view.findViewById(R.id.ll_my_spokesperson);
        rlCallUsIdea = (RelativeLayout)view.findViewById(R.id.rl_call_us_idea);
        //yys我的收藏
        rlYysCollecton = (RelativeLayout)view.findViewById(R.id.rl_yys_attentiopn);
        //yys我的订单
        rlYysOrder = (RelativeLayout)view.findViewById(R.id.rl_yys_order);
        //yys免费问答
        rlYysFreeWd = (RelativeLayout)view.findViewById(R.id.rl_yys_free_wenda);
        //yys我的顾客
        rlYysCustomer = (RelativeLayout)view.findViewById(R.id.rl_yys_gk);
        //yys抢答
        rlYysQd = (RelativeLayout)view.findViewById(R.id.rl_yys_qingda);
        //yys管理收货地址
        rlYysAddress = (RelativeLayout)view.findViewById(R.id.rl_guanli_adress);

    }

    @Override
    public void initListener() {
        rlMyConsult.setOnClickListener(this);
        rlMyAnswer.setOnClickListener(this);
        rlMyHealth.setOnClickListener(this);
        rlMyDietician.setOnClickListener(this);
        rlMyYhq.setOnClickListener(this);
        rlMyAttention.setOnClickListener(this);
        rlMyOrder.setOnClickListener(this);
        rlMyDiZhi.setOnClickListener(this);
        rlDieticianHome.setOnClickListener(this);
        rlYouhuiQuan.setOnClickListener(this);
        rlFbsfFw.setOnClickListener(this);
        iv.setOnClickListener(this);
        rlCallUs.setOnClickListener(this);
        rlCallUsIdea.setOnClickListener(this);
        rlMyTouTing.setOnClickListener(this);
        rlYysCollecton.setOnClickListener(this);
        rlYysOrder.setOnClickListener(this);
        rlYysFreeWd.setOnClickListener(this);
        rlYysCustomer.setOnClickListener(this);
        rlYysQd.setOnClickListener(this);
        rlYysAddress.setOnClickListener(this);
        tvSetting.setOnClickListener(this);
        tvName.setOnClickListener(this);
    }

    @Override
    public void initData() {
//            if (UserInfoUtil.isLogin(getContext())){
//                llYys.setVisibility(View.VISIBLE);
//                rlYys.setVisibility(View.VISIBLE);
//                llMyFunction.setVisibility(View.GONE);
//                llMyAll.setVisibility(View.GONE);
//                fl.setVisibility(View.GONE);
//                rlMyConsult.setVisibility(View.GONE);
//                rlMyTouTing.setVisibility(View.GONE);
//            }else {
                rlMyTouTing.setVisibility(View.VISIBLE);
                rlMyConsult.setVisibility(View.VISIBLE);
                llYys.setVisibility(View.GONE);
                rlYys.setVisibility(View.GONE);
                llMyFunction.setVisibility(View.VISIBLE);
                llMyAll.setVisibility(View.VISIBLE);
                fl.setVisibility(View.VISIBLE);
         //   }
    }

    @Override
    public void onClick(View v) {
        if (UserInfoUtil.isLogin(getContext())){
            switch (v.getId()){

                case R.id.tv_name:
                    if (UserInfoUtil.isLogin(getContext())){

                    }else {
                        intent = new Intent(getContext(), LoginActivity.class);
                        startActivityForResult(intent,0);
                    }
                    break;
                case R.id.rl_youhi_quan:
//                    intent = new Intent(getContext(), LoginHuanXinActivity.class);
//
//
//                    startActivity(intent);
      //              http://p023ecb00.bkt.clouddn.com/594396-Uplifting-Corporate.mp3

//                    intent = new Intent(getContext(), MyYhqActivity.class);
//
//                    startActivity(intent);
//                    intent = new Intent(getContext(), FenXiangActivity.class);
//                    startActivity(intent);

                    break;
                case R.id.tv_search:

                    if (UserInfoUtil.isLogin(getContext())){
                        intent = new Intent(getContext(), SettingActivity.class);
                        startActivityForResult(intent,0);
                    }else {
                        intent = new Intent(getContext(), LoginActivity.class);
                        startActivityForResult(intent,0);
                    }
                    break;




                case R.id.iv_my_zixun:
//                intent = new Intent(getContext(), ECMainActivity.class);
//                startActivity(intent);
                    break;
                case R.id.rl_my_zixun:
//                Intent intent = new Intent(getContext(), ECMainActivity.class);
//                startActivity(intent);
                    intent = new Intent(getContext(), MyZiXunActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_my_answer:
                    intent = new Intent(getContext(), MyWenDaActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_my_health:
                    intent = new Intent(getContext(), MyHealthActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_my_dietician:
                    intent = new Intent(getContext(), MyYySActivity.class);
                    startActivity(intent);

                    break;

                case R.id.rl_my_attention:
                    intent = new Intent(getContext(), MyGuanZhuActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_my_order:
                    intent = new Intent(getContext(), MyOrderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_my_dizhi:
                    intent = new Intent(getContext(), MyShAdressActivity.class);
                    intent.putExtra("fromPage",2);

                    startActivity(intent);
                    break;
                case R.id.rl_dietician_home:
                    intent = new Intent(getContext(), YingYangShiRuZhuActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_yys_fb_sfxx:
                    intent = new Intent(getContext(), FaBuShouFeiFwActivity.class);
                    startActivity(intent);

                    break;
                case R.id.ll_my_spokesperson:
                    intent = new Intent(getContext(), CallUsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_call_us_idea:
                    intent = new Intent(getContext(), CallUsIdeaActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_my_touting:
                    intent = new Intent(getContext(), MyTouTingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_yys_attentiopn:
                    intent = new Intent(getContext(), YysCollectionActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_yys_order:
                    intent = new Intent(getContext(), MyYysOrderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_yys_free_wenda:
                    intent = new Intent(getContext(),MyWenDaActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_yys_gk:
                    intent = new Intent(getContext(),YysCustomerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_yys_qingda:
                    intent = new Intent(getContext(),YysCustomerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.rl_guanli_adress:
                    intent = new Intent(getContext(), MyShAdressActivity.class);
                    intent.putExtra("fromPage",2);

                    startActivity(intent);
                    break;

                default:
                    super.onClick(v);
                    break;
            }
        }else {
            intent = new Intent(getContext(), LoginActivity.class);
            startActivityForResult(intent,0);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== AppConfig.LOGIN_OUT){
            tvName.setText("请先登录");
            tvStatus.setVisibility(View.GONE);
            UserInfoUtil.setLogout(getContext());

        }else if (resultCode==AppConfig.LOGIN_RESULT_SUCESS_CODE){
            tvName.setText("卡布达");
            tvStatus.setText("铁甲小宝");
            tvStatus.setVisibility(View.VISIBLE);
        }
    }
}
