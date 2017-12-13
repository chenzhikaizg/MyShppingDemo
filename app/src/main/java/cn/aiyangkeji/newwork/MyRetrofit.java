package cn.aiyangkeji.newwork;

import android.content.Context;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.tamic.novate.Novate;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.aiyangkeji.activities.MainActivity;

/**
 * Created by chenzhikai on 2017/11/11.
 */

public class MyRetrofit {
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private Map<String, String> headers = new HashMap<>();

    private static final String baseUrl = "http://aiyangkeji.cn:8080/iyang-o2o/";
    public   MyAPI myApiService;

    static Novate novatgeSingleton = null;
    protected static final Object monitor = new Object();
    private Novate novate;
    private Context context;
    public     MyRetrofit(Context context){

this.context = context;



    }

    public Novate getMyApiService() {
        headers.put("Accept", "application/json");
        novate = new Novate.Builder(context)
                .addHeader(headers) //添加公共请求头
                .addParameters(parameters)//公共参数
                .connectTimeout(10)  //连接时间 可以忽略
                .addHeader(headers)
                .addCookie(false)  //是否同步cooike 默认
                .addCache(false)
                .baseUrl(baseUrl) //base URL
                .addLog(true) //是否开启log
                .build();
        return  novate;
    }


}
