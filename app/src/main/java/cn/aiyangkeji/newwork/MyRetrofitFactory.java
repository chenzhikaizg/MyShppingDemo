package cn.aiyangkeji.newwork;

import android.content.Context;

import com.tamic.novate.Novate;

import cn.aiyangkeji.activities.MainActivity;
import cn.aiyangkeji.activities.loginandregist.LoginActivity;

/**
 * Created by 18810 on 2017/11/11.
 */

public class MyRetrofitFactory {
    private static Context context;
    static Novate novatgeSingleton = null;



    public void   MyRetrofitFactory(Context context){
        this.context = context;
    }

    protected static final Object monitor = new Object();

    public   Novate getGankIOSingleton() {
        synchronized (monitor) {
            if (novatgeSingleton == null) {
                novatgeSingleton = new MyRetrofit(context).getMyApiService();
            }
            return novatgeSingleton;
        }
    }
}
