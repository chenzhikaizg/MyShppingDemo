package cn.aiyangkeji.util;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import cn.aiyangkeji.activities.loginandregist.ForgetActivity;

/**
 * Created by $chenzhikai on 2017/12/19.
 */

public class FragmentManagerHelper {
    private FragmentManager mFragmentManager;
    private int mContainerViewId;

    /**
     *
     * @param fragmentManager 管理类
     * @param containerViewId 容器布局的id
     */
    public FragmentManagerHelper(@Nullable FragmentManager fragmentManager, @IdRes int containerViewId){

        this.mFragmentManager= fragmentManager;
        this.mContainerViewId = containerViewId;
    }

    /**
     * 添加fragment
     * @param fragment
     */
    public void add(Fragment fragment){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        fragmentTransaction.add(mContainerViewId,fragment);
        fragmentTransaction.commit();
    }

    /**
     * 切换显示fragment
     * @param fragment
     */
    public void switchFragmnet(Fragment fragment){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        List<Fragment> childFragments = mFragmentManager.getFragments();
        for (Fragment childFragment: childFragments) {
            fragmentTransaction.hide(childFragment);
        }
        if (!childFragments.contains(fragment)){
            fragmentTransaction.add(mContainerViewId,fragment);
        }else {
            fragmentTransaction.show(fragment);
        }

        // 替换Fragment
        // fragmentTransaction.replace(R.id.main_tab_fl,mHomeFragment);
        // 一定要commit
        fragmentTransaction.commit();

    }
}
