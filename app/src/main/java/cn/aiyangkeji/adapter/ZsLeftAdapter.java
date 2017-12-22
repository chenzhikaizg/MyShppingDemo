package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.bean.YydjOrderItemBean;

/**
 * Created by chenzhikai on 2017/11/6.
 */

public class ZsLeftAdapter extends BaseAdapter {
    private List<YydjOrderItemBean.ArtImage> values;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;
    private  List<String> mList;
    private int FIRST = 0;

    public ZsLeftAdapter(Context mContext,List<String> mList){
        this.mContext = mContext;
        values = new ArrayList<YydjOrderItemBean.ArtImage>();
        layoutInflater=LayoutInflater.from(mContext);
        this.mList = mList;
    }
    public void addData(List<YydjOrderItemBean.ArtImage> values) {
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
    public YydjOrderItemBean.ArtImage getItem(int i) {
        return values.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = layoutInflater.inflate(R.layout.adapter_zhishi_left_item, null);
            viewHolder = new ViewHolder();
            viewHolder.ivZhis = (ImageView)view.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = (TextView)view.findViewById(R.id.tv_zhishi_title);
            viewHolder.flBlue = (FrameLayout)view.findViewById(R.id.fl_blue);
            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
      viewHolder.tvTitle.setText(values.get(i).month);
        Glide.with(mContext).load(values.get(i).img).into(viewHolder.ivZhis);
        viewHolder.flBlue.setVisibility(View.GONE);

        if (values.get(i).status==1){
            viewHolder.flBlue.setVisibility(View.VISIBLE);
            if (i==0){
                //发送数据
                if (FIRST==0){
                    EventBus.getDefault().post(mList);
                    FIRST=1;
                }

            }
        }



        return view;
    }
    private class ViewHolder{
        private ImageView ivZhis;
        private TextView tvTitle;
        private FrameLayout flBlue;

    }
}
