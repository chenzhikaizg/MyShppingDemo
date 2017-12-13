package cn.aiyangkeji.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.SelectType2BuyOrCarBean;
import cn.aiyangkeji.bean.SelectWhatBean;
import cn.aiyangkeji.bean.SouguBean;
import cn.aiyangkeji.bean.YydjOrderItemBean;

/**
 * Created by chenzhikai on 2017/11/6.
 */

public class CarAllTypeAdapter extends BaseAdapter {
    private List<SelectType2BuyOrCarBean.TypeList> values;

    private Context mContext;
    private LayoutInflater layoutInflater;
    private ViewHolder viewHolder;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private List<YydjOrderItemBean.ArtImage> list1;
    private AddCarAdapter ljYyueAdapter;

    private final List<AddCarAdapter> adapterList;
    private String goodsSpecs;
    private  Map<String, String> map;
    private  StringBuilder sb;
    private final SelectWhatBean selectWhatBean;
    private String count;
    private  List<String> listSpecs;
    private StringBuilder sss;

    public CarAllTypeAdapter(Context mContext, final List<SelectType2BuyOrCarBean.TypeList> listvalues, final List<SelectType2BuyOrCarBean.TypeAddAll> valueStores, final TextView textView){
        this.mContext = mContext;
        values = new ArrayList<SelectType2BuyOrCarBean.TypeList>();

        layoutInflater=LayoutInflater.from(mContext);

        count = new String();
        listSpecs = new ArrayList<String>();
        for (int x=0;x<listvalues.size();x++){
            listSpecs.add("");
        }
        adapterList = new ArrayList<AddCarAdapter>();
        map = new HashMap<String,String>();
        selectWhatBean = new SelectWhatBean();
        for (int a=0;a<listvalues.size();a++){
            ljYyueAdapter = new AddCarAdapter(mContext);
            adapterList.add(ljYyueAdapter);

            map.put(a+"","");
            list1 = new ArrayList<YydjOrderItemBean.ArtImage>();


            final List<SelectType2BuyOrCarBean.TypeAddAll> finalValueStores = valueStores;

            adapterList.get(a).setClickListener(new AddCarAdapter.OnItemClickListener() {



                @Override
                public void onClick(View view, int position) {
                int     p = Integer.valueOf(((TextView) ((ViewGroup) view.getParent().getParent()).findViewById(R.id.tv_position)).getText().toString());

                    for (int i=0;i<values.get(p).specValues.size();i++){

                        SelectType2BuyOrCarBean.SpecValues specValues = values.get(p).specValues.get(i);
                        if (specValues.statusCode==1){
                            specValues.statusCode=0;
                            map.put(p+"","");
                        }
                        sb = new StringBuilder();
                        sss = new StringBuilder();
                        if (position==i){
                            specValues.statusCode=1;
                            map.put(p+"",position+"");
                            listSpecs.set(p,values.get(p).specValues.get(position).specValue);
                            if (p==0){
                                selectWhatBean.color=values.get(p).specValues.get(position).specValue;
                            }else if (p==1){
                                selectWhatBean.size = values.get(p).specValues.get(position).specValue;
                            }


                           for (int s=0;s<listSpecs.size();s++){
                               if (s!=listSpecs.size()-1){
                                   sss.append(listSpecs.get(s)+",");
                               }else {
                                   sss.append(listSpecs.get(s));
                               }
                           }

                            sb.append(selectWhatBean.color+","+selectWhatBean.size);
                            goodsSpecs = new String(sss);
                            for (int c=0;c<valueStores.size();c++){
                                if (valueStores.get(c).specKey.equals(new String(sss))){

                                    count = valueStores.get(c).store;
                                    goodsSpecs = new String(sss);
                                    textView.setText("库存："+count);
                                }
                            }

                        }

                    }



                    adapterList.get(p).notifyDataSetChanged();
                    CarAllTypeAdapter.this.notifyDataSetChanged();
                }
            });
        }



    }
    public void addData(List<SelectType2BuyOrCarBean.TypeList> values) {
        this.values.addAll(values);
    }
    public String getGoodsNum(){
        return  count;
    }
    public String getGoodsSpecs(){
        return goodsSpecs;
    }

    public void clear() {
        values.clear();
    }
    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public SelectType2BuyOrCarBean.TypeList getItem(int i) {
        return values.get(i);
    }

    public String  getSelectItem(int i) {
        return map.get(i+"");
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view = layoutInflater.inflate(R.layout.adapter_add_tocar_all_type, null);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView)view.findViewById(R.id.tv_title);
            viewHolder.rvType = (RecyclerView)view.findViewById(R.id.rv_type);
            viewHolder.tvPos = (TextView)view.findViewById(R.id.tv_position);

            view.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvPos.setText(i+"");

        viewHolder.rvType.setItemAnimator(new DefaultItemAnimator());

            adapterList.get(i).clear();
            viewHolder.tvTitle.setText(values.get(i).spec);



            staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };

        viewHolder.rvType.setLayoutManager(staggeredGridLayoutManager);//设置RecyclerView布局管理器为2列垂直排布
        viewHolder.rvType.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                staggeredGridLayoutManager.invalidateSpanAssignments();


            }
        });
        adapterList.get(i).addData(values.get(i).specValues);
        viewHolder.rvType.setAdapter(adapterList.get(i));




        return view;
    }
    private class ViewHolder{
        TextView tvTitle ;
        RecyclerView rvType;
        private TextView tvPos;
    }
}
