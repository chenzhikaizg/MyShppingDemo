package cn.aiyangkeji.activities.myinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.util.HashMap;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.bean.AddAddressBean;
import cn.aiyangkeji.bean.AddressListBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.util.JsontoRequestBody;
import cn.aiyangkeji.util.UserInfoUtil;
import cn.aiyangkeji.view.picker.CityPickWheelDialog;

/**
 * Created by 18810 on 2017/11/15.
 */

public class AddAddressActivity extends BaseActivity {

    private TextView tvSave;
    private EditText etReMan;
    private EditText etRePhone;
    private TextView tvCity;
    private EditText etStreet;
    private int provinceId = 1;
    private int cityId = 1;
    private int countryId = 1;
    private RelativeLayout rlReceiveCity;
    private TextView tvShowAddress;
    private AddressListBean.Address value;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private String province;
    private String city;
    private String district;
    private Novate novate;
    private MyAPI myAPI;
    private int contactId;
    private int disable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
    }

    @Override
    public void initView() {
        TextView tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("添加新地址");
        tvSave = (TextView)findViewById(R.id.tv_search);
        tvSave.setText("保存");
        etReMan = (EditText)findViewById(R.id.et_receive_man);
        etRePhone = (EditText)findViewById(R.id.et_receive_phone);
        tvShowAddress = (TextView)findViewById(R.id.et_receive_city);
        tvCity = (TextView)findViewById(R.id.tv_receivce_city);
        etStreet = (EditText)findViewById(R.id.et_receive_street);
        rlReceiveCity = (RelativeLayout)findViewById(R.id.rl_receive_city);
    }

    @Override
    public void initListener() {
        tvSave.setOnClickListener(this);
        rlReceiveCity.setOnClickListener(this);
    }

    @Override
    public void initData() {
        if (getIntent().getIntExtra("type",-1)==2){
            value = (AddressListBean.Address) getIntent().getSerializableExtra("addressBean");
            disable = value.disable;
            contactId = value.contactId;
            province = value.province;
            city = value.city;
            district= value.district;
                        //   defaultAddress = value.isdefault;
            etReMan.setText(value.name);
            etRePhone.setText(value.mobile);
            tvShowAddress.setText(province+city+district);
          etStreet.setText(value.address);
        }else {
            value = new AddressListBean.Address();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_search:
                if (getIntent().getIntExtra("type",-1)==2){
                    editAddress();
                }else {
                    saveAddress();
                }



                break;
            case R.id.rl_receive_city:
                showCityPick();
                break;
            default:
                super.onClick(v);
                break;


        }
    }

    private void editAddress() {
        final String strReceiveName = etReMan.getText().toString();
        final String strStreet = etStreet.getText().toString();
        final String strAddressDetail = tvShowAddress.getText().toString();
        final String strPhoneNum = etRePhone.getText().toString();
        parameters.put("customerId", UserInfoUtil.getUserId(this));
        if (strReceiveName.equals("")){
            showShortMsg("收货人不能为空");
            return;
        }else if (strAddressDetail.equals("")){
            showShortMsg("地址不能为空");
            return;
        }else if (etRePhone.getText().toString().equals("")){
            showShortMsg("电话不能为空");
            return;
        }
        parameters.put("disable",disable);
        parameters.put("contactId",contactId+"");
        parameters.put("name",strReceiveName);
        parameters.put("address",strStreet);
        parameters.put("province",province);
        parameters.put("city",city);
        parameters.put("mobile",etRePhone.getText().toString());
        parameters.put("district",district);

        novate = new MyRetrofit(this).getMyApiService();
        myAPI = novate.create(MyAPI.class);
        novate.call(myAPI.editAddress(JsontoRequestBody.parameters2json(parameters)), new MyBaseSubscriber<AddAddressBean>(this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AddAddressBean addAddressBean) {
                if (addAddressBean.resultCode==200){
                    showShortMsg("添加地址成功");
                    Intent intent = new Intent(AddAddressActivity.this, AddressListActivity.class);
                    value.name = strReceiveName;
                    value.mobile = strPhoneNum;
                    value.address = strStreet;
                    value.province = province;
                    value.city = city;
                    value.district = district;
                    intent.putExtra("addressBean", value);
                    setResult(RESULT_OK, intent);
                    AddAddressActivity.this.finish();
                }else {
                    showShortMsg(addAddressBean.message);

                }

            }
        });
    }

    private void saveAddress() {
        final String strReceiveName = etReMan.getText().toString();
        String strStreet = etStreet.getText().toString();
        final String strAddressDetail = tvShowAddress.getText().toString();
        final String strPhoneNum = etRePhone.getText().toString();
        parameters.put("customerId", UserInfoUtil.getUserId(this));

        if (strReceiveName.equals("")){
            showShortMsg("收货人不能为空");
            return;
        }else if (strAddressDetail.equals("")){
            showShortMsg("地址不能为空");
            return;
        }else if (etRePhone.getText().toString().equals("")){
            showShortMsg("电话不能为空");
            return;
        }
        parameters.put("name",strReceiveName);
        parameters.put("address",strStreet);
        parameters.put("province",province);
        parameters.put("city",city);
        parameters.put("mobile",etRePhone.getText().toString());
        parameters.put("district",district);

         novate = new MyRetrofit(this).getMyApiService();
         myAPI = novate.create(MyAPI.class);
        novate.call(myAPI.addAddress(JsontoRequestBody.parameters2json(parameters)), new MyBaseSubscriber<AddAddressBean>(this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AddAddressBean addAddressBean) {
                if (addAddressBean.resultCode==200){
                    showShortMsg("添加地址成功");
                    Intent intent = new Intent(AddAddressActivity.this, AddressListActivity.class);
                    value.name = strReceiveName;
                    value.mobile = strPhoneNum;
                    value.address = strAddressDetail;
                    value.province = province;
                    value.city = city;
                    value.district = district;
                    intent.putExtra("addressBean", value);
                    setResult(RESULT_OK, intent);
                    AddAddressActivity.this.finish();
                }else {
                    showShortMsg(addAddressBean.message);

                }

            }
        });




    }

    private void showCityPick() {
        CityPickWheelDialog dialog = new CityPickWheelDialog(this, R.style.MyDialogStyleBottom);
        dialog.setCanceledOnTouchOutside(true); // 点击外面可以关闭dialog

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.setCityButtomListener(new CityPickWheelDialog.CityButtonListener() {



            @Override
            public void onPositiveButton(CityPickWheelDialog dialog) {

                tvShowAddress.setText(dialog.getProvince() + dialog.getCity() + dialog.getArea());
                province = dialog.getProvince();
                city = dialog.getCity();
                district = dialog.getArea();
                dialog.dismiss();

            }

            @Override
            public void onNegativeButton(CityPickWheelDialog dialog) {
                dialog.dismiss();
            }
        });
    }
}
