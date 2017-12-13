package cn.aiyangkeji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.MyWenDaBean;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


/**
 * Created by chenzhikai on 2017/11/4.
 * 我的偷听
 */

public class MyTouTingAdapter extends RecyclerView.Adapter<MyTouTingAdapter.MyWendViewHolder> {
    private List<MyWenDaBean.ArtImage> values;
    private Context mContext;
    public MyTouTingAdapter(Context mContext){
        this.mContext = mContext;
        values = new ArrayList<MyWenDaBean.ArtImage>();

    }
    public void addData(List<MyWenDaBean.ArtImage> values) {
        this.values.addAll(values);
    }
    public void clear() {
        values.clear();
    }
    @Override
    public MyWendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_touting, parent, false);
        MyWendViewHolder holder = new MyWendViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyWendViewHolder holder, int position) {
        holder.tvWdTitle.setText(values.get(position).title);
        holder.tvKind.setText(values.get(position).kind);
        holder.tvMoney.setText(values.get(position).money);
        holder.tvName.setText(values.get(position).name);

//        Glide.with(mContext)
//                .load(values.get(position).img)
//                .preload()
//                .onLoadCleared(R.drawable.dis_load_icon)
//                .i;

//        Glide.with(mContext).load(R.mipmap.timg)
//                .into(holder.ivHead)
//                .onLoadStarted( mContext.getResources().getDrawable(R.mipmap.timg) );
        Glide.with(mContext)
                .load(R.mipmap.timg)
                .apply(bitmapTransform(new CropCircleTransformation()))
                .into(holder.ivHead);


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    class MyWendViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvWdTitle;
        private final TextView tvKind;
        private final ImageView ivHead;
        private final ImageView ivWdTouTing;
        private final TextView tvMoney;
        private final TextView tvName;

        public MyWendViewHolder(View itemView) {
            super(itemView);
            tvWdTitle = (TextView)itemView.findViewById(R.id.wd_rv_title);
            tvKind = (TextView)itemView.findViewById(R.id.tv_kind);
            ivHead = (ImageView)itemView.findViewById(R.id.wd_rv_headview);
            ivWdTouTing = (ImageView)itemView.findViewById(R.id.wd_rv_touting);
            tvMoney = (TextView) itemView.findViewById(R.id.wd_rv_money);
            tvName = (TextView)itemView.findViewById(R.id.wd_rv_tv_name);

        }
    }

}
