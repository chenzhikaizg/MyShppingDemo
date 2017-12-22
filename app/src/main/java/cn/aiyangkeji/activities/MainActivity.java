package cn.aiyangkeji.activities;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.commonsdk.UMConfigure;

import org.greenrobot.eventbus.EventBus;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseFragmentActivity;
import cn.aiyangkeji.activities.loginandregist.LoginActivity;
import cn.aiyangkeji.app.AppConfig;
import cn.aiyangkeji.fragmnets.HomeFragment;
import cn.aiyangkeji.fragmnets.HomeZhiShiFragment;
import cn.aiyangkeji.fragmnets.HomeFindFragment;
import cn.aiyangkeji.fragmnets.HomeMyInfoFragment;
import cn.aiyangkeji.fragmnets.HomeYouFragment;
import cn.aiyangkeji.interfaces.CallBackParentMethod;
import cn.aiyangkeji.util.FragmentManagerHelper;
import cn.aiyangkeji.util.UserInfoUtil;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
//import tyrantgit.explosionfield.ExplosionField;

public class MainActivity extends BaseFragmentActivity implements CallBackParentMethod {

    private LinearLayout llTab;
    private ImageButton ibZhiShi;
    private ImageButton ibFind;
    private ImageButton ibMyInfo;
    private HomeZhiShiFragment homeArtShowFragment;
    private HomeFragment homeFindFragment;
    private HomeMyInfoFragment homeMyInfoFragment;
    private FragmentManager fragmentManager;
    private HomeYouFragment homeYouFragment;
    private ImageButton ibYouXuan;
    private TextView tvHomeYy;
    private TextView tvHomeYx;
    private TextView tvHomeZs;
    private TextView tvHomeWd;
    private EventHandler eventHandler;
    private FragmentManagerHelper mFragmentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //集成测试
        UMConfigure.setLogEnabled(true);

    }


    //初始化view
    @Override
    public void initView() {

        llTab = (LinearLayout) findViewById(R.id.ll_tab);
        ibZhiShi = (ImageButton) findViewById(R.id.ib_waterfall);
        ibFind = (ImageButton) findViewById(R.id.ib_find);
        ibMyInfo = (ImageButton) findViewById(R.id.ib_myinfo);
        ibYouXuan = (ImageButton) findViewById(R.id.ib_youxuan);

        //底部栏文字
        tvHomeYy = (TextView) findViewById(R.id.tv_home_yy);
        tvHomeYx = (TextView) findViewById(R.id.tv_home_yx);
        tvHomeZs = (TextView) findViewById(R.id.tv_home_zs);
        tvHomeWd = (TextView) findViewById(R.id.tv_home_wd);
    }

    @Override
    public void initListener() {
        llTab.setOnClickListener(this);
        ibZhiShi.setOnClickListener(this);
        ibFind.setOnClickListener(this);
        ibMyInfo.setOnClickListener(this);
        ibYouXuan.setOnClickListener(this);
        tvHomeZs.setOnClickListener(this);
        tvHomeWd.setOnClickListener(this);
        tvHomeYx.setOnClickListener(this);
        tvHomeYy.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mFragmentHelper = new FragmentManagerHelper(getSupportFragmentManager(), R.id.fl_fragment_container);

//        fragmentManager = this.getSupportFragmentManager();
        //主页的几个fragment
//        homeArtShowFragment = new HomeZhiShiFragment();
        homeFindFragment = new HomeFragment();
//        homeMyInfoFragment = new HomeMyInfoFragment();
//        homeYouFragment = new HomeYouFragment();
        mFragmentHelper.add(homeFindFragment);
//        fragmentManager.beginTransaction().add(R.id.fl_fragment_container, homeFindFragment).commit();
//        fragmentManager.beginTransaction().add(R.id.fl_fragment_container,homeArtShowFragment).commit();
//        fragmentManager.beginTransaction().add(R.id.fl_fragment_container, homeYouFragment).commit();
//        fragmentManager.beginTransaction().add(R.id.fl_fragment_container,homeMyInfoFragment).commit();


        //  replaceFragment(homeFindFragment);
    }


    private void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction().hide(homeArtShowFragment).commit();
        fragmentManager.beginTransaction().hide(homeFindFragment).commit();
        fragmentManager.beginTransaction().hide(homeMyInfoFragment).commit();
        fragmentManager.beginTransaction().hide(homeYouFragment).commit();
//        fragmentManager.beginTransaction().hide(loginFragment).commit();
//        fragmentManager.beginTransaction().hide(telephoneLoginFragment).commit();
        fragmentManager.beginTransaction().show(fragment).commit();
    }


    @Override
    public void changeFragment(int index) {
        switch (index) {
//            case 1:
//                replaceFragment(telephoneLoginFragment);
//                telephoneLoginFragment.setData();
//                setTabSelected(ibMyInfo, R.drawable.iconon_07);
//                break;
//            case 2:
//                replaceFragment(homeMyInfoFragment);
//                homeMyInfoFragment.setData();
//                setTabSelected(ibMyInfo, R.drawable.iconon_07);
//                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_home_yy:
            case R.id.ib_waterfall:
                setTabSelected(ibZhiShi, R.mipmap.zhishi_icon_elect_, tvHomeZs);
                //   replaceFragment(homeArtShowFragment);
                if (homeArtShowFragment == null) {
                    homeArtShowFragment = new HomeZhiShiFragment();
                }
                mFragmentHelper.switchFragmnet(homeArtShowFragment);
                break;
            case R.id.tv_home_zs:
            case R.id.ib_find:
                setTabSelected(ibFind, R.mipmap.yy_icon_elect_, tvHomeYy);
                //   replaceFragment(homeFindFragment);
                if (homeFindFragment == null) {
                    homeFindFragment = new HomeFragment();
                }
                mFragmentHelper.switchFragmnet(homeFindFragment);
                break;
            case R.id.tv_home_yx:
            case R.id.ib_youxuan:
                setTabSelected(ibYouXuan, R.mipmap.youxuan_icon_elect_, tvHomeYx);
                // replaceFragment(homeYouFragment);
                if (homeYouFragment == null) {
                    homeYouFragment = new HomeYouFragment();
                }
                mFragmentHelper.switchFragmnet(homeYouFragment);
                break;
            case R.id.tv_home_wd:
            case R.id.ib_myinfo:
                //  setTabSelected(ibMyInfo, R.mipmap.wode_icon_elect_,tvHomeWd);
                //   replaceFragment(homeMyInfoFragment);


                if (!UserInfoUtil.isLogin(this)) {

                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent, 0);

                } else {
                    setTabSelected(ibMyInfo, R.mipmap.wode_icon_elect_, tvHomeWd);
                    // replaceFragment(homeMyInfoFragment);
                    if (homeMyInfoFragment == null) {
                        homeMyInfoFragment = new HomeMyInfoFragment();
                    }
                    mFragmentHelper.switchFragmnet(homeMyInfoFragment);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 设置tab选中状态
     */
    private void setTabSelected(ImageButton ib, int drawableResId, TextView view) {
        ibZhiShi.setImageResource(R.mipmap.zhishi_icon_uncheck_);
        ibFind.setImageResource(R.mipmap.yy_icon_uncheck_);
        ibYouXuan.setImageResource(R.mipmap.youxuan_icon_uncheck_);
        ibMyInfo.setImageResource(R.mipmap.wode_icon_uncheck_);

        tvHomeYy.setTextColor(getResources().getColor(R.color.home_word));
        tvHomeZs.setTextColor(getResources().getColor(R.color.home_word));
        tvHomeYx.setTextColor(getResources().getColor(R.color.home_word));
        tvHomeWd.setTextColor(getResources().getColor(R.color.home_word));

        ib.setImageResource(drawableResId);
        view.setTextColor(getResources().getColor(R.color.pink_word_home));
    }

    //    protected void onSaveInstanceState(Bundle outState) {
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.remove(homeFindFragment);
//        transaction.remove(homeArtShowFragment);
//        transaction.remove(homeYouFragment);
//        transaction.remove(homeMyInfoFragment);
//        transaction.commitAllowingStateLoss();
//        super.onSaveInstanceState(outState);
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        homeArtShowFragment = null;
        homeFindFragment = null;
        homeYouFragment = null;
        homeMyInfoFragment = null;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == AppConfig.LOGIN_RESULT_SUCESS_CODE) {
            setTabSelected(ibMyInfo, R.mipmap.wode_icon_elect_, tvHomeWd);
            replaceFragment(homeMyInfoFragment);
        } else if (requestCode == AppConfig.LOGIN_RESULT_CANCEL_CODE) {
            replaceFragment(homeFindFragment);
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }


    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                showShortMsg("再按一次退出程序");
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
