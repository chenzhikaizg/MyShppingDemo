package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.fragmnets.YysZongHeFragment;

/**
 * Created by chenzhikai on 2017/11/6.
 */

public class OrderProductAllAdapter extends BaseAdapter {
    private List<MyWenDaBean.ArtImage> values;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;
    private YysZongHeFragment fragment;
    private View.OnClickListener clickListener;
    public OrderProductAllAdapter(Context mContext, View.OnClickListener clickListener){
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
            view = layoutInflater.inflate(R.layout.adapter_product_all, null);

            viewHolder = new ViewHolder();
            viewHolder.ivHead = (ImageView)view.findViewById(R.id.iv_order_img);
            viewHolder.tvOrderNumber = (TextView)view.findViewById(R.id.tv_order_number);
            viewHolder.tvOrderStatus = (TextView)view.findViewById(R.id.tv_order_status);
            viewHolder.tvOrderName = (TextView)view.findViewById(R.id.tv_order_name);
            viewHolder.tvOrderPrice = (TextView)view.findViewById(R.id.tv_order_price);
            viewHolder.tvOrderCount = (TextView)view.findViewById(R.id.tv_order_count);
            viewHolder.tvTotalCount = (TextView)view.findViewById(R.id.tv_order_total_count);
            viewHolder.tvTotalPrice = (TextView)view.findViewById(R.id.tv_order_total_price);
            viewHolder.tvWarn = (TextView)view.findViewById(R.id.tv_warn);

            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
     //   viewHolder.ll.setOnClickListener(clickListener);

        return view;
    }



    private class ViewHolder{

        private ImageView ivHead;
        private TextView tvOrderNumber;
        private TextView tvOrderStatus;
        private TextView tvOrderName;
        private TextView tvOrderPrice;
        private TextView tvOrderCount;
        private TextView tvTotalCount;
        private TextView tvTotalPrice;
        private TextView tvWarn;



    }
}
