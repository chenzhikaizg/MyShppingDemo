package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.MyWenDaBean;

/**
 * Created by chenzhikai on 2017/11/6.
 */

public class YouXuanBottonAdapter extends BaseAdapter {
    private List<MyWenDaBean.ArtImage> values;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;

    public YouXuanBottonAdapter(Context mContext){
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
            view = layoutInflater.inflate(R.layout.adapter_youxuan_bottm, null);
            viewHolder = new ViewHolder();
            viewHolder.ivZhis = (ImageView)view.findViewById(R.id.iv_zhishi);
            viewHolder.tvContent = (TextView)view.findViewById(R.id.tv_content);
            viewHolder.tvTitle = (TextView)view.findViewById(R.id.tv_title);
            viewHolder.tvProductIntroduce = (TextView)view.findViewById(R.id.tv_product_introduce);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvTitle.setText("啊啊啊啊啊啊啊");
        viewHolder.tvContent.setText("sssssssssss");

        return view;
    }
    private class ViewHolder{
        private ImageView ivZhis;
        private TextView tvTitle;
        private TextView tvContent;
        private TextView tvProductIntroduce;

    }
}
