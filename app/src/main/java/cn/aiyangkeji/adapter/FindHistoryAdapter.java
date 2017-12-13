package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.MyWenDaBean;
import cn.aiyangkeji.db.StudentInfo;

/**
 * Created by chenzhikai on 2017/11/6.
 */

public class FindHistoryAdapter extends BaseAdapter {
    private List<StudentInfo> values;
    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;

    public FindHistoryAdapter(Context mContext){
        this.mContext = mContext;
        values = new ArrayList<StudentInfo>();
        layoutInflater=LayoutInflater.from(mContext);
    }
    public void addData(List<StudentInfo> values) {
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
    public StudentInfo getItem(int i) {
        return values.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = layoutInflater.inflate(R.layout.adapter_find_history, null);
            viewHolder = new ViewHolder();

         //   viewHolder.ivHead = (ImageView)view.findViewById(R.id.iv_head);
            viewHolder.tvHistory = (TextView)view.findViewById(R.id.tv_history);

            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
     viewHolder.tvHistory.setText(values.get(i).getName());

        return view;
    }
    private class ViewHolder{

     //   private  ImageView ivHead;
    private TextView tvHistory;
    }
}
