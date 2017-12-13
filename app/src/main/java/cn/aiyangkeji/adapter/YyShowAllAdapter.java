package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.MyWenDaBean;

/**
 * Created by 18810 on 2017/11/24.
 */

public class YyShowAllAdapter extends BaseAdapter {
    private List<MyWenDaBean.ArtImage> values;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ImageView ivImg;
    private TextView tvWhatYy;

    public YyShowAllAdapter(Context mContext){
        this.mContext= mContext;
        values = new ArrayList<MyWenDaBean.ArtImage>();
        layoutInflater= LayoutInflater.from(mContext);

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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.adapter_yys_show_all, null);
        ivImg = (ImageView)convertView.findViewById(R.id.iv_img);
        tvWhatYy = (TextView)convertView.findViewById(R.id.tv_what_yy);
       tvWhatYy.setText(values.get(position).name);
        Glide.with(mContext).load( values.get(position).id ).into(ivImg);
        return convertView;
    }
}
