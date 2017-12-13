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

public class YyDJAdapter extends RecyclerView.Adapter<YyDJAdapter.YyDjViewHolder> {
    private List<MyWenDaBean.ArtImage> values;
    private Context mContext;
    public YyDJAdapter(Context mContext){
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
    public YyDjViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_yydj_item, parent, false);
        YyDjViewHolder holder = new YyDjViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final YyDjViewHolder holder, int position) {


//        Glide.with(mContext)
//                .load(values.get(position).img)
//                .preload()
//                .onLoadCleared(R.drawable.dis_load_icon)
//                .i;

        Glide.with(mContext).load(R.mipmap.timg)
                .into(holder.ivHead)
                .onLoadStarted( mContext.getResources().getDrawable(R.mipmap.timg) );


    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    class YyDjViewHolder extends RecyclerView.ViewHolder{


        private final ImageView ivHead;
        private final TextView tvZhiWei;
        private final TextView tvShangChang;
        private final TextView tvHaoPing;
        private final TextView tvOrderNum;
        private final TextView tvJieNum;

        public YyDjViewHolder(View itemView) {
            super(itemView);
            tvZhiWei = (TextView)itemView.findViewById(R.id.tv_zhiwei);
            ivHead = (ImageView)itemView.findViewById(R.id.iv_head);
            tvShangChang = (TextView)itemView.findViewById(R.id.tv_shanchang);
            tvHaoPing = (TextView)itemView.findViewById(R.id.tv_haoping);
            tvOrderNum = (TextView)itemView.findViewById(R.id.tv_order_num);
            tvJieNum = (TextView)itemView.findViewById(R.id.tv_jie_num);

        }
    }

}
