package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;

/**
 * Created by chenzhikai on 15/11/11.
 * 艺术品
 */
public class OneYuanAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Integer> values;

    private ImageView ivSign;
    private ImageView ivImg;

    public OneYuanAdapter(Context context)
    {
        this.context=context;
        layoutInflater= LayoutInflater.from(context);
        values=new ArrayList<Integer>();

    }

    public void addData(List<Integer> vaules) {
        this.values.addAll(vaules);
    }

    public void clear() {
        values.clear();
    }


    @Override
    public int getCount() {
        if (values.size()>9){
            return 9;
        }else{
            return values.size();
        }

    }

    @Override
    public Integer  getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//    @Override
//    public long getItemId(int position) {
//        return getItem(position).artwork_id;
//    }


    private ProgressBar pb;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.grid_item,null);
        ivImg = (ImageView)convertView.findViewById(R.id.iv_img);
        Glide.with(context).load(values.get(position)).into(ivImg);

        return convertView;
    }

}

