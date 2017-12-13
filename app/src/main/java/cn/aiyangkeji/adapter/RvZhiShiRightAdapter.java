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


/**
 * Created by chenzhikai on 2017/11/4.
 */

public class RvZhiShiRightAdapter extends RecyclerView.Adapter<RvZhiShiRightAdapter.SyTjViewHolder> {
    private List<String> values;
    private Context mContext;
    public RvZhiShiRightAdapter(Context mContext){
        this.mContext = mContext;
        values = new ArrayList<String>();

    }
    public void addData(List<String> values) {
        this.values.addAll(values);
    }
    public void clear() {
        values.clear();
    }

    private SyTjYysAdapter.OnItemClickListener clickListener;

    public void setClickListener(SyTjYysAdapter.OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public static interface OnItemClickListener {
        void onClick(View view, int position);
    }
    @Override
    public SyTjViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_rv_zhishi_right, parent, false);
        SyTjViewHolder holder = new SyTjViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SyTjViewHolder holder, int position) {



        holder.tv.setText(values.get(position));
//        Glide.with(mContext).load(R.mipmap.timg)
//                .into(holder.iv)
//                .onLoadStarted( mContext.getResources().getDrawable(R.mipmap.timg) );


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    class SyTjViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private final ImageView iv;
        private final TextView tv;

        public SyTjViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            iv = (ImageView)itemView.findViewById(R.id.iv_more);
            tv = (TextView) itemView.findViewById(R.id.tv_content_title);

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(itemView, getAdapterPosition());
            }
        }
    }

}
