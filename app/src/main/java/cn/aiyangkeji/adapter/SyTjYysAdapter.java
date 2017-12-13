package cn.aiyangkeji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.MyWenDaBean;


/**
 * Created by chenzhikai on 2017/11/4.
 */

public class SyTjYysAdapter extends RecyclerView.Adapter<SyTjYysAdapter.SyTjViewHolder> {
    private List<MyWenDaBean.ArtImage> values;
    private Context mContext;
    public SyTjYysAdapter(Context mContext){
        this.mContext = mContext;
        values = new ArrayList<MyWenDaBean.ArtImage>();

    }
    public void addData(List<MyWenDaBean.ArtImage> values) {
        this.values.addAll(values);
    }
    public void clear() {
        values.clear();
    }

    private OnItemClickListener clickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public static interface OnItemClickListener {
        void onClick(View view, int position);
    }

    @Override
    public SyTjViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shou_ye_tjyys_item, parent, false);
        SyTjViewHolder holder = new SyTjViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SyTjViewHolder holder, int position) {


//        Glide.with(mContext)
//                .load(values.get(position).img)
//                .preload()
//                .onLoadCleared(R.drawable.dis_load_icon)
//                .i;

        Glide.with(mContext).load(R.mipmap.timg)
                .into(holder.iv)
                .onLoadStarted( mContext.getResources().getDrawable(R.mipmap.timg) );


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    class SyTjViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private final ImageView iv;
        private final TextView tvStatus;
        private final TextView tvNum;

        public SyTjViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            iv = (ImageView)itemView.findViewById(R.id.iv_sytj_yys);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_order_status);
            tvNum = (TextView)itemView.findViewById(R.id.tv_order_num);


        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onClick(itemView, getAdapterPosition());
            }
        }
    }

}