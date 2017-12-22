package cn.aiyangkeji.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.bean.AddressListBean;

/**
 * Created by gaoweiwei on 15/6/4.
 */
public class AddressListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<AddressListBean.Address> values;
    public AddressListAdapter(Context context)
    {
        this.context=context;
        layoutInflater= LayoutInflater.from(context);
        values=new ArrayList<AddressListBean.Address>();
    }

    public void addAddressBeans(List<AddressListBean.Address> values)
    {
        this.values.addAll(values);

        notifyDataSetChanged();
    }




    public void setDefaultAddress(int position)
    {
       for(int i=0;i<values.size();i++)
       {
           if(i==position) {
               values.get(i).disable =1;
           }else{
               values.get(i).disable = 0;
           }
       }
       notifyDataSetChanged();
    }

    public  void addAddressBean(AddressListBean.Address value)
    {
        if(value.disable==1){
            for(AddressListBean.Address value1:values)
            {
                value1.disable=0;
            }
        }
        values.add(value);
        notifyDataSetChanged();
    }

    public void editAddressBean(int position,AddressListBean.Address value)
    {
         if(value.disable==1) {
             for (AddressListBean.Address value1 : values) {
                 value1.disable=0;
             }
         }
        values.set(position,value);
        notifyDataSetChanged();
    }

    public void deleteAddress(int position){
          values.remove(position);
          notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public AddressListBean.Address getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return values.get(position).contactId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.adapter_address_item,null);
            viewHolder=new ViewHolder();
            viewHolder.ivStatus=(ImageView)convertView.findViewById(R.id.iv_status);
            viewHolder.tvReceiveName=(TextView)convertView.findViewById(R.id.tv_consignee_name);
            viewHolder.tvReceivePhone=(TextView)convertView.findViewById(R.id.tv_consignee_phone);
            viewHolder.tvReceiveAddress=(TextView)convertView.findViewById(R.id.tv_address);
            viewHolder.tvDefaultAddress=(TextView)convertView.findViewById(R.id.tv_default_address);
            viewHolder.tvEdit=(TextView)convertView.findViewById(R.id.tv_edit);
            viewHolder.tvDelete=(TextView)convertView.findViewById(R.id.tv_delete);
            viewHolder.tvPosition=(TextView)convertView.findViewById(R.id.tv_position);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        AddressListBean.Address value=getItem(position);
        if(value.disable==1)
        {
            Glide.with(context).load(R.mipmap.quanzhong_icon_normal_).into(viewHolder.ivStatus);
            viewHolder.tvDefaultAddress.setText("默认地址");
        }else{
            Glide.with(context).load(R.mipmap.weixianzhong_icon_normal_).into(viewHolder.ivStatus);
            viewHolder.tvDefaultAddress.setText("设置默认地址");
        }



        viewHolder.tvReceiveName.setText("收货人    "+value.name);
        viewHolder.tvReceivePhone.setText("联系方式："+value.mobile);
        viewHolder.tvReceiveAddress.setText("收货地址："+value.province+value.city+value.district+value.address);
        viewHolder.tvDefaultAddress.setOnClickListener((View.OnClickListener) context);
        viewHolder.tvEdit.setOnClickListener((View.OnClickListener)context);
        viewHolder.tvDelete.setOnClickListener((View.OnClickListener) context);
        viewHolder.tvPosition.setText(position+"");
        return convertView;
    }

    private class ViewHolder{
        private ImageView ivStatus;
        private TextView tvReceiveName;
        private TextView tvReceivePhone;
        private TextView tvReceiveAddress;
        private TextView tvDefaultAddress;
        private TextView tvEdit;
        private TextView tvDelete;
        private TextView tvPosition;
    }
}
