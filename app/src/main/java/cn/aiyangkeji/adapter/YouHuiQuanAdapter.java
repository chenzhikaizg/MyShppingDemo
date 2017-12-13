package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.fragmnets.YysZongHeFragment;

/**
 * Created by chenzhikai on 2017/11/6.
 */

public class YouHuiQuanAdapter extends BaseAdapter {
    private List<MyWenDaBean.ArtImage> values;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;
    private YysZongHeFragment fragment;
    private View.OnClickListener clickListener;
    public YouHuiQuanAdapter(Context mContext, View.OnClickListener clickListener){
        this.clickListener = clickListener;

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
            view = layoutInflater.inflate(R.layout.adapter_youhuiquan, null);

            viewHolder = new ViewHolder();
            viewHolder.rlRoot = (RelativeLayout) view.findViewById(R.id.rl_root);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i%2==0){
            viewHolder.rlRoot.setBackgroundResource(R.mipmap.youhuiquan_bg_grey);
        }else {
            viewHolder.rlRoot.setBackgroundResource(R.mipmap.youhuiuqan_bg_red);
        }

        return view;
    }



    private class ViewHolder{
        private RelativeLayout rlRoot;




    }
}
