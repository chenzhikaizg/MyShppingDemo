package cn.aiyangkeji.activities.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tamic.novate.Novate;
import com.tamic.novate.Throwable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.aiyangkeji.R;
import cn.aiyangkeji.activities.base.BaseActivity;
import cn.aiyangkeji.adapter.CarAllTypeAdapter;
import cn.aiyangkeji.bean.AddCarReturnBean;
import cn.aiyangkeji.bean.ConfrimOrderBean;
import cn.aiyangkeji.bean.SelectType2BuyOrCarBean;
import cn.aiyangkeji.bean.YydjOrderItemBean;
import cn.aiyangkeji.newwork.MyAPI;
import cn.aiyangkeji.newwork.MyBaseSubscriber;
import cn.aiyangkeji.newwork.MyRetrofit;
import cn.aiyangkeji.util.JsontoRequestBody;
import cn.aiyangkeji.util.UserInfoUtil;

/**
 * Created by chenzhikai on 2017/11/20.
 * 加入购物车和立即购买
 */

public class AddtoCartActivity extends BaseActivity {

    private Button btnOver;
    private ImageView ivJia;
    private ImageView ivJian;
    private TextView tvBuyNum;
    private String from;
    private CarAllTypeAdapter ljYyueAdapter;


    private TextView tvProductPrice;
    private TextView tvHaveNum;
    private ListView lvAll;
    private List<SelectType2BuyOrCarBean.SpecValues> listBean;
    private List<SelectType2BuyOrCarBean.TypeList> listAll;
    private SelectType2BuyOrCarBean ss;
    //用来封装提交购物车到订单的数据
    private List<SelectType2BuyOrCarBean.Type> types;
    private String goodsId;
    private String specIds;
    private SelectType2BuyOrCarBean.Type stTypeBean;
    private Novate novate;
    private MyAPI myAPI;
    private String goodsName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtocart);

    }

    @Override
    public void initView() {


        lvAll = (ListView) findViewById(R.id.lv_all);
        btnOver = (Button) findViewById(R.id.btn_over);

        ivJia = (ImageView) findViewById(R.id.iv_jia);
        ivJian = (ImageView) findViewById(R.id.iv_jian);
        tvBuyNum = (TextView) findViewById(R.id.tv_buy_num);
        tvProductPrice = (TextView) findViewById(R.id.tv_product_price);
        tvHaveNum = (TextView) findViewById(R.id.tv_have_num);

    }

    @Override
    public void initListener() {
        btnOver.setOnClickListener(this);
        ivJia.setOnClickListener(this);
        ivJian.setOnClickListener(this);
    }

    @Override
    public void initData() {
        from = getIntent().getStringExtra("from");
        goodsId = getIntent().getStringExtra("goodsId");
        specIds = getIntent().getStringExtra("specIds");
        goodsName = getIntent().getStringExtra("goodsName");
        getAllSpecs();
        types = new ArrayList<SelectType2BuyOrCarBean.Type>();
        stTypeBean = new SelectType2BuyOrCarBean.Type();
        listAll = new ArrayList<SelectType2BuyOrCarBean.TypeList>();

//        for (int i = 0; i < 2; i++) {
//            SelectType2BuyOrCarBean.TypeList  address = new   SelectType2BuyOrCarBean.TypeList ();
//            listBean = new ArrayList<SelectType2BuyOrCarBean.SpecValues>();
//            for (int a = 0; a < 8; a++) {
//                SelectType2BuyOrCarBean.SpecValues typebean = new SelectType2BuyOrCarBean.SpecValues();
//
//
//                typebean.specValue = "黄色" + i;
//                listBean.add(typebean);
//            }
//            address.spec = "颜色"+i;
//            address.specValues = listBean;
//
//            listAll.add(address);
//        }




//        ljYyueAdapter.setClickListener(new AddCarAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                TextView rlRoot = (TextView) view.findViewById(R.id.tv_month);
//
//                for (int i=0;i<size;i++){
//                    YydjOrderItemBean.ArtImage bean = list1.get(i);
//                    if (bean.status==1){
//                        bean.status=0;
//                    }
//                    if (position==i){
//                        bean.status=1;
//                    }
//                }
//                    ljYyueAdapter.notifyDataSetChanged();
//            }
//        });


    }



    private void getAllSpecs() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("specIds",specIds);
        novate = new MyRetrofit(this).getMyApiService();
        myAPI =  novate.create(MyAPI.class);
        novate.call(myAPI.getAllSpecs(goodsId, map), new MyBaseSubscriber<SelectType2BuyOrCarBean>(this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(SelectType2BuyOrCarBean selectType2BuyOrCarBean) {
                if (selectType2BuyOrCarBean.resultCode==200){
                    SelectType2BuyOrCarBean.Type data = selectType2BuyOrCarBean.data;
                    List<SelectType2BuyOrCarBean.TypeList> specs = data.specs;
                    ljYyueAdapter = new CarAllTypeAdapter(AddtoCartActivity.this, specs,data.stores,tvHaveNum);
                    ljYyueAdapter.addData(specs);
                    lvAll.setAdapter(ljYyueAdapter);
                    stTypeBean.specs = specs;
                    stTypeBean.name= goodsName;
                    stTypeBean.price= "123";
                    stTypeBean.num=1;
                    types.add(stTypeBean);

                }else {
                    showShortMsg(selectType2BuyOrCarBean.message);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.btn_over:
                String strNum = tvBuyNum.getText().toString();
                List<String> listSelect = new ArrayList<String>();
                for (int i = 0; i < listAll.size(); i++) {


                    String item = ljYyueAdapter.getSelectItem(i);
                    if (item.equals("")) {


                        showShortMsg("请选择属性" + i);
                        return;

                    }

                    String selectItem = ljYyueAdapter.getSelectItem(i);
                    listSelect.add(selectItem);
                }
                List<SelectType2BuyOrCarBean> listOrder = new ArrayList<SelectType2BuyOrCarBean>();
                ConfrimOrderBean.Address bean = new ConfrimOrderBean.Address();


                if (from.equals("add2car")) {

                    addCar();


                } else if (from.equals("buynow")) {
                    Intent intent = new Intent(this, ActivityConfirmOrderActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("price", tvProductPrice.getText().toString());
                    intent.putExtra("buynum", strNum);
                    bundle.putSerializable("listOrder", (Serializable) types);
                    bundle.putSerializable("listSelect", (Serializable) listSelect);
                    intent.putExtras(bundle);
                    startActivity(intent);


                }

                break;
            case R.id.iv_jia:
                String strJia = tvBuyNum.getText().toString();
                int intJia = Integer.valueOf(strJia);
                int max = Integer.valueOf(12 + "");
                if (intJia >= max) {
                    showShortMsg("库存只有这么多了");
                    return;

                } else {
                    tvBuyNum.setText(intJia + 1 + "");
                }

                break;
            case R.id.iv_jian:
                String strJian = tvBuyNum.getText().toString();
                int intJian = Integer.valueOf(strJian);
                if (intJian <= 1) {
                    return;
                } else {
                    tvBuyNum.setText(intJian - 1 + "");
                }

                break;
            default:
                super.onClick(v);
                break;


        }

    }
    //加入购物车
    private void addCar() {
        Map<String,String> map = new HashMap<String,String>();
        map.put("userId", UserInfoUtil.getUserId(AddtoCartActivity.this)+"");

        map.put("goodsName",goodsName);
        if (ljYyueAdapter.getGoodsNum().equals("")||ljYyueAdapter.getGoodsNum().equals("0")){
            showShortMsg("您选的物品没有库存了");
            return;
        }else {

            map.put("number",tvBuyNum.getText().toString());
        }

        map.put("specs",ljYyueAdapter.getGoodsSpecs());

        novate.call(myAPI.addCar(JsontoRequestBody.parameters2json(map) ), new MyBaseSubscriber<AddCarReturnBean>(this) {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AddCarReturnBean addCarReturnBean) {
                if (addCarReturnBean.resultCode==200){
                    showShortMsg("成功加入购物车");
                   AddtoCartActivity.this.finish();
                }else {
                    showShortMsg(addCarReturnBean.message);
                }
            }
        });

    }


    //提交信息
    private void submit() {

        this.finish();
    }

}
