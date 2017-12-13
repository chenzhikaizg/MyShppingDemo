package cn.aiyangkeji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.SelectType2BuyOrCarBean;


/**
 * Created by chenzhikai on 2017/11/4.
 */

public class AddCarAdapter extends RecyclerView.Adapter<AddCarAdapter.SyTjViewHolder> {
    private List<SelectType2BuyOrCarBean.SpecValues> values;
    private Context mContext;
    private int pos = 100; //标识选中那个
    private ViewGroup par;

    public AddCarAdapter(Context mContext) {
        this.mContext = mContext;
        values = new ArrayList<SelectType2BuyOrCarBean.SpecValues>();

    }

    public void addData(List<SelectType2BuyOrCarBean.SpecValues> values) {
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
    public long getItemId(int position) {

        return position;
    }

    public int getSelectItem() {
        return pos;
    }

    @Override
    public SyTjViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        par = parent;
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_add_car, parent, false);
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
//
//        Glide.with(mContext).load(R.mipmap.timg)
//                .into(holder.iv)
//                .onLoadStarted( mContext.getResources().getDrawable(R.mipmap.timg) );
        holder.tv.setBackgroundResource(R.drawable.bg_shape_grey_no_border);
        if (values.get(position).statusCode == 1) {
            holder.tv.setBackgroundResource(R.drawable.bg_shape_pink_shixin_corners_small);
            pos = position;
        }

        holder.tv.setText("   " + values.get(position).specValue + "   ");
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public SelectType2BuyOrCarBean.SpecValues getItem(int position) {


        return values.get(position);
    }

    class SyTjViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        //        private final ImageView iv;
        private final TextView tv;


        public SyTjViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //    iv = (ImageView)itemView.findViewById(R.id.iv_tjfw_item);
            tv = (TextView) itemView.findViewById(R.id.tv_month);


        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.onClick(itemView, getAdapterPosition());
            }


        }
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    private int selectItem = -1;
}
