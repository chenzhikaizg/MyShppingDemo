package cn.aiyangkeji.activities.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.store.ActivityConfirmOrderActivity;
import cn.aiyangkeji.adapter.AddressListAdapter;
import cn.aiyangkeji.bean.AddressListBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.util.ListViewUtil;
import cn.aiyangkeji.util.UserInfoUtil;

/**
 * Created by chenzhikai on 2017/11/4.
 * 我的地址页面
 */

public class MyShAdressActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ImageView ivback;
    private ListView lvAddress;
    private RelativeLayout rlAddress;
    private Intent intent;
    private AddressListAdapter adapter;
    private int position;
    private int fromPage;//1 购买页面  2我的个人中
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shouhuo_adress);

    }

    @Override
    public void initView() {
        TextView textView= (TextView)findViewById(R.id.tv_title);
        textView.setText("管理收货地址");
        ivback = (ImageView) findViewById(R.id.iv_back);
        lvAddress = (ListView)findViewById(R.id.lv_address);
        rlAddress = (RelativeLayout)findViewById(R.id.rl_add_address);

    }

    @Override
    public void initListener() {
        ivback.setOnClickListener(this);
        rlAddress.setOnClickListener(this);
        lvAddress.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
//        List<AddressListBean.Value> list = new ArrayList<AddressListBean.Value>();
//        for(int i=0;i<3;i++){
//            AddressListBean.Value value = new AddressListBean.Value();
//            value.address="北京北京市房山区";
//            value.name = "cccc";
//           // value.detail="sssssssssssss";
//            list.add(value);
//        }
        fromPage=getIntent().getIntExtra("fromPage",-1);
        adapter = new AddressListAdapter(this);
       // adapter.addAddressBeans(list);


        getAddressList();

    }

    private void getAddressList() {
        Novate novate = new MyRetrofit(this).getMyApiService();
        MyAPI myAPI = novate.create(MyAPI.class);
        novate.call(myAPI.getAddress(UserInfoUtil.getUserId(this)), new MyBaseSubscriber<AddressListBean>(this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AddressListBean addressListBean) {
                if (addressListBean.resultCode==200){
                    adapter.addAddressBeans(addressListBean.data);
                    lvAddress.setAdapter(adapter);
                    ListViewUtil.adaptiveHight(MyShAdressActivity.this, lvAddress, 0);

                }else {
                    showShortMsg(addressListBean.message);
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_add_address:
                intent = new Intent(this,AddAddressActivity.class);

                startActivityForResult(intent,1);

                break;
            case R.id.tv_default_address:
            case R.id.tv_edit:
            case R.id.tv_delete:
                position = Integer.valueOf(((TextView)((ViewGroup)v.getParent()).findViewById(R.id.tv_position)).getText().toString());
                if (v.getId()==R.id.tv_delete){

                    deleteAddress(adapter.getItemId(position));
                }else if (v.getId()==R.id.tv_edit){
                    intent=new Intent(this,AddAddressActivity.class);
                    intent.putExtra("type",2);
                    intent.putExtra("addressBean",adapter.getItem(position));
                    startActivityForResult(intent, 2);
                }else if(v.getId()==R.id.tv_default_address){
                    setDefaultAddress(adapter.getItemId(position));
                }


                break;
            default:
                super.onClick(v);
                break;


        }


    }

    private void deleteAddress(long itemId) {
        adapter.deleteAddress(position);
    }

    /**
     * 设置默认地址
     * @param id
     */
    private void setDefaultAddress(long id)
    {

                        adapter.setDefaultAddress(position);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            if (requestCode==1){
                adapter.addAddressBean((AddressListBean.Value) data.getSerializableExtra("addressBean"));
                ListViewUtil.adaptiveHight(this, lvAddress, 1f);
            }else if (requestCode==2){

                adapter.editAddressBean(position, (AddressListBean.Value) data.getSerializableExtra("addressBean"));
            }else {

            }
        }



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,ActivityConfirmOrderActivity.class);
        intent.putExtra("addressId",adapter.getItem(position).customerId);
        intent.putExtra("name",adapter.getItem(position).name);
        intent.putExtra("phone",adapter.getItem(position).mobile);
        intent.putExtra("address",adapter.getItem(position).address);
        intent.putExtra("addressCount",adapter.getCount());
        if(fromPage==1) {
            setResult(1, intent);
            this.finish();
        }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent  intent=new Intent(this,ActivityConfirmOrderActivity.class);
            intent.putExtra("addressCount",adapter.getCount());
            setResult(2, intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);

    }
}
