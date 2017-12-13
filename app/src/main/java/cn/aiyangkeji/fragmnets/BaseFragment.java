package cn.aiyangkeji.fragmnets;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;


import com.google.gson.Gson;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONObject;

import cn.aiyangkeji.app.MyApplication;
import cn.aiyangkeji.view.AutoListView;

/**
 * Created by chenzhikai on 17/10/31
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {
    protected AutoListView lv;
    protected MyApplication myApp;
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    protected Gson gson;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp= MyApplication.getInstance();


        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }

    }
   /**
    * 获取View对象
    */
    public abstract void initView();

    /**
     * 设置View事件
     */
    public abstract void initListener();

    /**
     * 初始化当前活动数据
     */
    public abstract void initData();



    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());

    }

    @Override
     public void onPause() {
        super.onPause();
       MobclickAgent.onPageEnd(this.getClass().getSimpleName() );
    }

    public void showLongMsg(String msg) {
        Toast toast= Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showShortMsg(String msg)
    {
        Toast toast= Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }



    @Override
    public void onClick(View v) {

    }





    @Override
    public void onDestroy() {
        super.onDestroy();

        gson=null;
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//
//        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
//    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }
}
