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
 */

public class MyWenDaAdapter extends RecyclerView.Adapter<MyWenDaAdapter.MyWendViewHolder> {
    private List<MyWenDaBean.ArtImage> values;
    private Context mContext;
    public MyWenDaAdapter(Context mContext){
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_my_wenda, parent, false);
        MyWendViewHolder holder = new MyWendViewHolder(view);
        return holder;
    }

    public OnItemClickListener clickListener;
     public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public  static interface  OnItemClickListener{
        void onClick(View view, int position);
    }
    public void deleteAddress(int position){
        values.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyWendViewHolder holder, final int position) {


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

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAddress(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
    class MyWendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageView ivHead;

        private final TextView tvName;
        private final TextView tvTime;
        private final TextView tvQuestion;
        private final TextView tvDelete;

        public MyWendViewHolder(View itemView) {
            super(itemView);

            ivHead = (ImageView)itemView.findViewById(R.id.iv_head);

            tvTime = (TextView)itemView.findViewById(R.id.tv_time);
            tvQuestion = (TextView)itemView.findViewById(R.id.tv_question);
            tvDelete = (TextView)itemView.findViewById(R.id.tv_delete);
            tvName = (TextView)itemView.findViewById(R.id.tv_name);

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(itemView, getAdapterPosition());
            }
        }
    }

}
