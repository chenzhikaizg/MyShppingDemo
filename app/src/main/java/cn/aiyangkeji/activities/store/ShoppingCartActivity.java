package cn.aiyangkeji.activities.store;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.adapter.AddressListAdapter;
import cn.aiyangkeji.adapter.GouWuCheAdapter;
import cn.aiyangkeji.bean.AddressListBean;
import cn.aiyangkeji.bean.DeleteCartBean;
import cn.aiyangkeji.bean.GouWuCheBean;
import cn.aiyangkeji.bean.SelectType2BuyOrCarBean;
import cn.aiyangkeji.bean.ShoppingCarBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.util.JsonUtil;
import cn.aiyangkeji.util.JsontoRequestBody;
import cn.aiyangkeji.util.ListViewUtil;
import cn.aiyangkeji.util.UserInfoUtil;

/**
 * Created by 18810 on 2017/11/15.
 */

public class ShoppingCartActivity extends BaseActivity {

    private ListView lvCart;
    private GouWuCheAdapter adapter;
    private int position;
    private TextView tvCheckNum;
    private int num;
    private TextView tvDelete;
    private ImageView ivBack;
    private TextView tvAllCheck;

    private RelativeLayout rlAllCheck;
    private ImageView ivAllCheck;
    private TextView tvTotalPrice;
    private RelativeLayout rl2Pay;

    //表示合计的价格
    private double totalPrice;
    //表示是不是全选
    private int allCheckStatus;
    private List<SelectType2BuyOrCarBean.Type> listOrder;
    private Map<String, SelectType2BuyOrCarBean.Type> map;
    private List<String> listSelect;
    private SelectType2BuyOrCarBean selectType2BuyOrCarBean;
    private List<ShoppingCarBean.Product> data;
    private MyAPI myAPI;
    private Novate novate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
    }
    @Override
    public void initView() {
        TextView tvTtile = (TextView) findViewById(R.id.tv_title);
        tvTtile.setText("购物车");
        lvCart = (ListView) findViewById(R.id.lv_shopping_cart);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        tvAllCheck = (TextView) findViewById(R.id.tv_quanxuan);
        rlAllCheck = (RelativeLayout) findViewById(R.id.rl_all_check);
        ivAllCheck = (ImageView) findViewById(R.id.iv_quanxuan);
        tvTotalPrice = (TextView) findViewById(R.id.tv_total_price);
        rl2Pay = (RelativeLayout) findViewById(R.id.rl_to_pay);
    }
    @Override
    public void initListener() {
        ivBack.setOnClickListener(this);
        tvAllCheck.setOnClickListener(this);
        rlAllCheck.setOnClickListener(this);
        rl2Pay.setOnClickListener(this);
    }
    @Override
    public void initData() {
        getShopplingcarList();
        listSelect = new ArrayList<String>();
        map = new HashMap<String, SelectType2BuyOrCarBean.Type>();
        listOrder = new ArrayList<SelectType2BuyOrCarBean.Type>();
//        list = new ArrayList<GouWuCheBean.Value>();
//        for (int i = 0; i < 3; i++) {
//            GouWuCheBean.Value value = new GouWuCheBean.Value();
//            value.address = "北京北京市房山区";
//            value.name = "cccc";
//            value.price = "123";
//            value.number = 1;
//            value.types = "颜色";
//            List<GouWuCheBean.Type> listType = new ArrayList<GouWuCheBean.Type>();
//            for (int a = 0; a < 2; a++) {
//                GouWuCheBean.Type bean = new GouWuCheBean.Type();
//                bean.typeValue = "红色";
//                bean.statusCode = 1;
//                listType.add(bean);
//            }
////            value.detail="sssssssssssss";
////            value.num = 1;
//            value.type = listType;
//            list.add(value);
//        }

    }

    private void getShopplingcarList() {
        novate = new MyRetrofit(this).getMyApiService();
        myAPI = novate.create(MyAPI.class);
        novate.call(myAPI.getCarList(UserInfoUtil.getUserId(this)+""), new MyBaseSubscriber<ShoppingCarBean>(this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShoppingCarBean shoppingCarBean) {
                if (shoppingCarBean.resultCode==200){
                    data = shoppingCarBean.data;
                    adapter = new GouWuCheAdapter(ShoppingCartActivity.this, clickListener, shoppingCarBean.data);
                    adapter.addAddressBeans(shoppingCarBean.data);
                    lvCart.setAdapter(adapter);
                    ListViewUtil.adaptiveHight(ShoppingCartActivity.this, lvCart, 1f);
                    lvCart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(ShoppingCartActivity.this, ProductDetailActivity.class);
                            intent.putExtra("from", "car");
                           // intent.putExtra("goodsId",data.get(position).cartItem.productId);
                            startActivityForResult(intent, 0);
                        }
                    });
                }else {
                    showShortMsg(shoppingCarBean.message);
                }
            }
        });




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.rl_all_check:
            case R.id.tv_quanxuan:
                if (adapter.getCount() == 0) {
                    showShortMsg("请先添加商品到购物车");
                } else {
                    if (allCheckStatus == 0) {
                        for (ShoppingCarBean.Product value : data) {
                            value.status = 1;
                        }
                        allCheckStatus = 1;
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.quanzhong_icon_normal_).into(ivAllCheck);
                        computePrice();
                    } else {
                        for (ShoppingCarBean.Product value : data) {
                            value.status = 0;
                        }
                        allCheckStatus = 0;
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.weixianzhong_icon_normal_).into(ivAllCheck);
                        computePrice();
                    }
                    adapter.notifyDataSetChanged();
                }
                map.clear();
                for (int i = 0; i < data.size(); i++) {
                    listSelect.add(i + "");
                    if (allCheckStatus == 1) {
                        SelectType2BuyOrCarBean.Type selectType2BuyOrCarBean = new SelectType2BuyOrCarBean.Type();

                        selectType2BuyOrCarBean.specs = new ArrayList<SelectType2BuyOrCarBean.TypeList>();
                        selectType2BuyOrCarBean.name = adapter.getItem(i).cartItem.productName;
                        selectType2BuyOrCarBean.num = adapter.getItem(i).cartItem.number;
                        selectType2BuyOrCarBean.price = adapter.getItem(i).cartItem.productPrice+"";
                   //     selectType2BuyOrCarBean.type = adapter.getItem(position).types;
                        for (int a = 0; a < adapter.getItem(i).specs.size(); a++) {
                            SelectType2BuyOrCarBean.TypeList address = new SelectType2BuyOrCarBean.TypeList();
                            address.spec = adapter.getItem(i).specs.get(a).specKey;
                            address.specValues = new ArrayList<SelectType2BuyOrCarBean.SpecValues>();
                            for (int b = 0;b<1;b++){
                                SelectType2BuyOrCarBean.SpecValues specValues = new SelectType2BuyOrCarBean.SpecValues();
                                specValues. specValue = adapter.getItem(i).specs.get(a).specValue;
                                specValues.statusCode = 1;
                                address.specValues.add(specValues);
                            }
                            selectType2BuyOrCarBean.specs.add(address);
                        }
                        map.put(i + "", selectType2BuyOrCarBean);
                    } else {
                        map.remove(i + "");
//             }
                    }
                }
                break;
            case R.id.rl_to_pay:
                if (adapter.getCount() == 0) {
                    showShortMsg("请先添加商品");
                    return;
                } else {
                    listOrder.clear();
                    if (map.size() == 0) {
                        showShortMsg("请先添加商品");
                        return;
                    } else {
                        for (SelectType2BuyOrCarBean.Type value : map.values()) {
                            listOrder.add(value);
                        }
                        Intent intent = new Intent(this, ActivityConfirmOrderActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("listOrder", (Serializable) listOrder);
                        bundle.putSerializable("listSelect", (Serializable) listSelect);
                        intent.putExtras(bundle);


                        startActivity(intent);
                    }
                }
                break;
            default:
                super.onClick(v);
                break;
        }
    }

    /**
     * 计算总价
     */
    private void computePrice() {
        totalPrice = 0;
        for (ShoppingCarBean.Product value : data) {
            if (value.status == 1) {
                totalPrice += value.cartItem.productPrice * value.cartItem.number;
            }

        }

        tvTotalPrice.setText("￥" + totalPrice);


    }
    //存储0/1来判断是是不是全选
    List mallStatus = new ArrayList();


    public View.OnClickListener clickListener = new View.OnClickListener() {

        private ImageView ivCheck;

        @Override
        public void onClick(View v) {
            position = Integer.valueOf(((TextView) ((ViewGroup) v.getParent()).findViewById(R.id.tv_position)).getText().toString());
            switch (v.getId()) {
                case R.id.iv_product_jian:

                    tvCheckNum = (TextView) ((ViewGroup) v.getParent()).findViewById(R.id.tv_check_num);
                    num = Integer.valueOf(tvCheckNum.getText().toString());
                    if (num == 1) {
                        showShortMsg("数量不能小于1");
                        return;
                    } else {
                        tvCheckNum.setText(num - 1 + "");

                    }
                    adapter.getItem(position).cartItem.number = num - 1;
                    computePrice();

                    break;
                case R.id.iv_product_jia:
                    tvCheckNum = (TextView) ((ViewGroup) v.getParent()).findViewById(R.id.tv_check_num);
                    num = Integer.valueOf(tvCheckNum.getText().toString());
                    tvCheckNum.setText(num + 1 + "");
                    adapter.getItem(position).cartItem.number = num + 1;
                    computePrice();
                    break;
                case R.id.tv_delete:

                    deleteAddress(adapter.getItemId(position));
                    break;
                case R.id.iv_check:
                    mallStatus.clear();
                    SelectType2BuyOrCarBean.Type selectType2BuyOrCarBean = new SelectType2BuyOrCarBean.Type();
                    selectType2BuyOrCarBean.specs = new ArrayList<SelectType2BuyOrCarBean.TypeList>();
                    ivCheck = (ImageView) ((ViewGroup) v.getParent()).findViewById(R.id.iv_check);

                    if (adapter.getItem(position).status == 0) {
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.quanzhong_icon_normal_).into(ivCheck);
                        adapter.getItem(position).status = 1;
                        selectType2BuyOrCarBean.name = adapter.getItem(position).cartItem.productName;
                        selectType2BuyOrCarBean.num = adapter.getItem(position).cartItem.number;
                        selectType2BuyOrCarBean.price = adapter.getItem(position).cartItem.productPrice+"";
                   //     selectType2BuyOrCarBean.type = adapter.getItem(position).types;
                        for (int a = 0; a < adapter.getItem(position).specs.size(); a++) {
                            SelectType2BuyOrCarBean.TypeList address = new SelectType2BuyOrCarBean.TypeList();
                            address.spec = adapter.getItem(position).specs.get(a).specKey;
                            address.specValues = new ArrayList<SelectType2BuyOrCarBean.SpecValues>();
                            for (int b = 0;b<1;b++){
                                SelectType2BuyOrCarBean.SpecValues specValues = new SelectType2BuyOrCarBean.SpecValues();
                                specValues. specValue = adapter.getItem(position).specs.get(a).specValue;
                                specValues.statusCode = 1;
                                address.specValues.add(specValues);
                            }
                            selectType2BuyOrCarBean.specs.add(address);
                        }
                        map.put(position + "", selectType2BuyOrCarBean);
                        Log.e("map_add", "onClick: " + map.size());
                    } else {
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.weixianzhong_icon_normal_).into(ivCheck);
                        adapter.getItem(position).status = 0;

                        map.remove(position + "");
                        Log.e("map_remove", "onClick: " + map.size());
                    }
                    //点击每次就去遍历，存储0/1
                    for (ShoppingCarBean.Product value : data) {
                        mallStatus.add(value.status);

                    }
                    //判断有0/1为，来判断是否全选
                    if (mallStatus.contains(0)) {
                        allCheckStatus = 0;
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.weixianzhong_icon_normal_).into(ivAllCheck);
                    } else {
                        allCheckStatus = 1;
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.quanzhong_icon_normal_).into(ivAllCheck);
                    }
                    computePrice();
                    break;
            }
        }
    };
    /**
     * 删除条目
     *
     * @param itemId
     */
    private void deleteAddress(long itemId) {
        HashMap<String ,String> maps = new HashMap<String,String>();
        maps.put("userId",UserInfoUtil.getUserId(this)+"");
        maps.put("productId",data.get(position).cartItem.productId+"");
        novate.call(myAPI.deleteCartItem(maps), new MyBaseSubscriber<DeleteCartBean>(this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DeleteCartBean deleteCartBean) {
                if (deleteCartBean.resultCode==200){
                    showShortMsg(deleteCartBean.data);
                    map.remove(position + "");
                    adapter.deleteAddress(position);
                    data.remove(position);
                    if (data.size() == 0) {
                        allCheckStatus = 0;
                        Glide.with(ShoppingCartActivity.this).load(R.mipmap.weixianzhong_icon_normal_).into(ivAllCheck);
                        rl2Pay.setBackgroundResource(R.color.text_color_grey);
                    }
                    computePrice();
                }else {
                    showShortMsg(deleteCartBean.message);
                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (resultCode == 1) {
//                adapter.clear();
//                GouWuCheBean.Value bean = (GouWuCheBean.Value) data.getSerializableExtra("value");
//
//                list.add(bean);
//                adapter.addAddressBeans(list);
//                adapter.notifyDataSetChanged();
//                ListViewUtil.adaptiveHight(this, lvCart, 0.5f);
            }
        }


    }
}
