package cn.aiyangkeji.fragmnets;

/**
 * Created by chenzhikai on 2017/11/3.
 */


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.adapter.ZsLeftAdapter;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.bean.YydjOrderItemBean;


/**
 * 作者：程序员小冰 on 2015/11/30 19:55
 * <p/>
 * 网站：http://blog.csdn.net/qq_21376985
 * <p/>
 * 微博：http://weibo.com/mcxiaobing
 */

/**
 * 发布者 eventBus
 */
public class LeftFragment extends Fragment {

    private ListView lv;
    private String[] strs = new String[]{"JAVA", "ADNROID", "IOS", "PHP", "HTML5", "Python","程序员","SSSSs","sssss","QQQQQ","SSSSSS","XXXXX","ZZZZZZz","FFSAASASFASDASf","JAVA", "ADNROID", "IOS", "PHP", "HTML5", "Python","程序员","SSSSs","sssss","QQQQQ","SSSSSS","XXXXX","ZZZZZZz","FFSAASASFASDASf"};
    private FrameLayout viewById;
    private List<YydjOrderItemBean.ArtImage> list;
    private ZsLeftAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.left_fragment,container,false);
        adapter = new ZsLeftAdapter(getContext());
        lv = (ListView) rootView.findViewById(R.id.lv);
        list = new ArrayList<YydjOrderItemBean.ArtImage>();
        for (int i=0;i<10;i++){
            YydjOrderItemBean.ArtImage artImage = new YydjOrderItemBean.ArtImage();
            list.add(artImage);
        }
        adapter.addData(list);
        final List<String> mlist = new ArrayList<String>();

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int size = adapter.getCount();


                for (int i=0;i<size;i++){
                    YydjOrderItemBean.ArtImage bean = list.get(i);
                    if (bean.status==1){
                        bean.status=0;
                    }
                    if (position==i){
                        bean.status=1;
                    }
                }
                adapter.notifyDataSetChanged();
                mlist.clear();
                for (int i=0;i<strs.length;i++){
                    mlist.add(strs[i]+position);


                }
                //发送数据
                EventBus.getDefault().post(mlist);
                viewById = (FrameLayout) view.findViewById(R.id.fl_blue);


            }
        });

        return rootView;
    }
}
