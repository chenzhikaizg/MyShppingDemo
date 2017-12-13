package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.zhaoyys.FindDieticianActivity;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.fragmnets.YysZongHeFragment;

/**
 * Created by chenzhikai on 2017/11/6.
 */

public class ZhaoYingYangShiAdapter extends BaseAdapter {
    private List<MyWenDaBean.ArtImage> values;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;
    private FindDieticianActivity activity;
    public ZhaoYingYangShiAdapter( FindDieticianActivity activity){
        this.activity = activity;

        values = new ArrayList<MyWenDaBean.ArtImage>();
        layoutInflater=LayoutInflater.from(activity);
    }
    public void addData(List<MyWenDaBean.ArtImage> values) {
        this.values.addAll(values);
    }
    public void clear() {
        values.clear();
    }
    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public MyWenDaBean.ArtImage getItem(int i) {
        return values.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = layoutInflater.inflate(R.layout.zhao_yys_item, null);

            viewHolder = new ViewHolder();
            viewHolder.ivHead = (ImageView)view.findViewById(R.id.iv_head);
            viewHolder.tvYysName = (TextView)view.findViewById(R.id.tv_yys_name);
            viewHolder.tvZhiWei = (TextView)view.findViewById(R.id.tv_zhiwei);
            viewHolder.tvDanWei = (TextView)view.findViewById(R.id.tv_danwei);
            viewHolder.tvShangChang = (TextView)view.findViewById(R.id.tv_shanchang);
            viewHolder.tvHaoPing = (TextView)view.findViewById(R.id.tv_haoping);
            viewHolder.tvOrderNuM = (TextView)view.findViewById(R.id.tv_order_num);
            viewHolder.tvJieNum = (TextView)view.findViewById(R.id.tv_jie_num);
            viewHolder.ll = (LinearLayout)view.findViewById(R.id.ll_yys_item);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.ll.setOnClickListener(activity);

        return view;
    }



    private class ViewHolder{

        private ImageView ivHead;
        private TextView tvYysName;
        private TextView tvZhiWei;
        private TextView tvDanWei;
        private TextView tvShangChang;
        private TextView tvHaoPing;
        private TextView tvOrderNuM;
        private TextView tvJieNum;
        private LinearLayout ll;



    }
}
