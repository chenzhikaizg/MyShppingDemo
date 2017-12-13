package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.SelectType2BuyOrCarBean;
import cn.aiyangkeji.util.ListViewUtil;

/**
 * Created by gaoweiwei on 15/6/4.
 */
public class ConfrimOrderAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<SelectType2BuyOrCarBean.Type> values;
    private List<String> lisypos;
    private  ArrayAdapter<String> adapter;
    private final String[] typeAll;
    private final List<String> ss;

    public ConfrimOrderAdapter(Context context, List<String> listpos) {
        this.context = context;
        this.lisypos= listpos;
        layoutInflater = LayoutInflater.from(context);
        ss = new ArrayList<String>();

        typeAll = new String[listpos.size()];


        values = new ArrayList<SelectType2BuyOrCarBean.Type>();
    }
    public void addData(List<SelectType2BuyOrCarBean.Type> values) {
        this.values.addAll(values);
    }









    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public SelectType2BuyOrCarBean.Type getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.adapter_confrim_order,null);
            viewHolder=new ViewHolder();
            viewHolder.ivProduct = (ImageView)convertView.findViewById(R.id.iv_product);
            viewHolder. tvProductName = (TextView)convertView.findViewById(R.id.tv_product_name);
            viewHolder.  tvColor = (TextView)convertView.findViewById(R.id.tv_color);
            viewHolder. tvPrice = (TextView)convertView.findViewById(R.id.tv_price);
            viewHolder.  tvSize = (TextView)convertView.findViewById(R.id.tv_size);
            viewHolder.tvNum = (TextView)convertView.findViewById(R.id.tv_product_num);
            viewHolder.etPeopleMessage = (EditText)convertView.findViewById(R.id.et_maijialiuyan);
            viewHolder.  tvOneMoney = (TextView)convertView.findViewById(R.id.tv_one_money);
            viewHolder. tvFreight = (TextView)convertView.findViewById(R.id.tv_yunfei_money);
            viewHolder.  tvDiscounts = (TextView)convertView.findViewById(R.id.tv_youhui);
            viewHolder.  tvAllMoney = (TextView)convertView.findViewById(R.id.tv_all_money);
            viewHolder.  tvTotalPrice = (TextView)convertView.findViewById(R.id.tv_total_price);
            viewHolder.lvtype = (ListView)convertView.findViewById(R.id.lv_type_all) ;

            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        SelectType2BuyOrCarBean.Type value=getItem(position);
//        for (int s =0;s<typeAll.length;s++){
            String type = "";
           String typeValue = "";
//            typeAll[s] = type+":"+typeValue;
//        }
        ss.clear();


       for (int a = 0 ;a<values.get(position).specs.size() ;a++){
            type = values.get(position).specs.get(a).spec;
           for (int b=0;b<values.get(position).specs.get(a).specValues.size();b++){
               typeValue  = values.get(position).specs.get(a).specValues.get(b).specValue;
           }
           ss.add(type+typeValue);
       }





        ColorAndSizeAdapter adapter = new ColorAndSizeAdapter(context);
        adapter.addData(ss);
        viewHolder.tvAllMoney.setText(Double.valueOf(value.price)*value.num+"");
        viewHolder.tvProductName.setText(value.name);
        viewHolder.tvPrice.setText("￥"+value.price);
        viewHolder.tvNum.setText("x"+value.num+"");
     viewHolder.lvtype.setAdapter(adapter);
        ListViewUtil.adaptiveHight(context, viewHolder.lvtype,0);



        return convertView;
    }

    private class ViewHolder{
        private ImageView ivProduct;
        private TextView tvProductName;
        private TextView tvColor;
        private TextView tvPrice;
        private TextView tvSize;
        private TextView tvNum;
        private TextView tvOneMoney;
        private TextView tvFreight;

        private TextView tvDiscounts;
        private TextView tvAllMoney;
        private TextView tvTotalPrice;
        private EditText etPeopleMessage;
        private ListView lvtype;
    }
}
