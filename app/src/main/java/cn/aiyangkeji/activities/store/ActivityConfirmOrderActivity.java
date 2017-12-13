package cn.aiyangkeji.activities.store;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.activities.myinfo.MyShAdressActivity;
import cn.aiyangkeji.adapter.ConfrimOrderAdapter;
import cn.aiyangkeji.bean.SelectType2BuyOrCarBean;
import cn.aiyangkeji.util.ListViewUtil;

/**
 * Created by 18810 on 2017/11/16.
 */

public class ActivityConfirmOrderActivity extends BaseActivity {

    private TextView tvTitle;
    private ImageView ivBack;
    private RelativeLayout rlNoHave;
    private RelativeLayout rlAddressHave;
    private TextView tvReceivePeople;
    private TextView tvAddress;
    private ImageView ivProduct;
    private TextView tvProductName;
    private TextView tvPrice;
    private TextView tvSize;
    private TextView tvNum;
    private TextView tvColor;
    private EditText etPeopleMessage;
    private TextView tvOneMoney;
    private TextView tvFreight;
    private TextView tvDiscounts;
    private TextView tvAllMoney;
    private TextView tvTotalPrice;
    private RelativeLayout rlBuyImmediately;
    private String color;
    private String size;
    private String buynum;
    private String onePrice;
    private int anInt;
    private Intent intent;
    private TextView tvReceivePhone;
    private ListView lvType;
    private ListView lvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
    }

    @Override
    public void initView() {
        tvTitle = (TextView)findViewById(R.id.tv_title);
        tvTitle.setText("确认订单");
        ivBack = (ImageView)findViewById(R.id.iv_back);
        rlNoHave = (RelativeLayout)findViewById(R.id.rl_no_have);
        rlAddressHave = (RelativeLayout)findViewById(R.id.rl_address_have);
        tvReceivePeople = (TextView)findViewById(R.id.tv_receive_people);
        tvAddress = (TextView)findViewById(R.id.tv_address);

        lvOrder = (ListView)findViewById(R.id.lv_order_list);

        tvTitle.setFocusable(true);
        tvTitle.setFocusableInTouchMode(true);
        tvTitle.requestFocus();


    }

    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
        rlNoHave.setOnClickListener(this);
        rlAddressHave.setOnClickListener(this);
    }

    @Override
    public void initData() {


        List<SelectType2BuyOrCarBean.Type> listOrder = (List<SelectType2BuyOrCarBean.Type>) getIntent().getSerializableExtra("listOrder");
        List<String> listSelect = (List<String>) getIntent().getSerializableExtra("listSelect");
        ConfrimOrderAdapter adapter = new ConfrimOrderAdapter(this,listSelect);
        adapter.addData(listOrder);
        lvOrder.setAdapter(adapter);

        ListViewUtil.adaptiveHight(this,lvOrder,1f);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.rl_no_have:
                intent = new Intent(this,MyShAdressActivity.class);
                intent.putExtra("fromPage", 1);
                startActivityForResult(intent,1);

                break;
            case R.id.rl_address_have:
                intent = new Intent(this,MyShAdressActivity.class);
                intent.putExtra("fromPage", 1);
                startActivityForResult(intent,1);


                break;

            default :
                super.onClick(v);
                break;



        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == 1) {
                rlNoHave.setVisibility(View.GONE);
                rlAddressHave.setVisibility(View.VISIBLE);
                tvReceivePeople.setText(data.getStringExtra("name"));
                tvReceivePhone.setText(data.getStringExtra("phone"));
                tvAddress.setText(data.getStringExtra("address"));


            } else if (resultCode == 2) {
                if (data.getIntExtra("addressCount", -1) == 0) {
                    rlNoHave.setVisibility(View.VISIBLE);
                    rlAddressHave.setVisibility(View.GONE);

                }

            }
        }
    }
}
