package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.MyWenDaBean;

/**
 * Created by chenzhikai on 2017/11/6.
 */

public class YysNowBuyServiceAdapter extends BaseAdapter {
    private List<MyWenDaBean.ArtImage> values;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;

    public YysNowBuyServiceAdapter(Context mContext){
        this.mContext = mContext;
        values = new ArrayList<MyWenDaBean.ArtImage>();
        layoutInflater=LayoutInflater.from(mContext);
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
            view = layoutInflater.inflate(R.layout.adapter_yys_nowbuy_service, null);
            viewHolder = new ViewHolder();

            viewHolder.ivHead = (ImageView)view.findViewById(R.id.iv_head);
            viewHolder.ivSelect = (ImageView)view.findViewById(R.id.iv_select);


            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
      //  viewHolder.tvTitle.setText("啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");


        return view;
    }
    private class ViewHolder{

        private  ImageView ivHead;
        private ImageView ivSelect;

    }
}
