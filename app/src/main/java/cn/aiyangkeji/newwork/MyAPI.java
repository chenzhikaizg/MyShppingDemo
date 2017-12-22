package cn.aiyangkeji.newwork;



import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cn.aiyangkeji.bean.AddAddressBean;
import cn.aiyangkeji.bean.AddCarReturnBean;
import cn.aiyangkeji.bean.AddressListBean;
import cn.aiyangkeji.bean.BannerBean;
import cn.aiyangkeji.bean.BaseBean;
import cn.aiyangkeji.bean.DeleteCartBean;
import cn.aiyangkeji.bean.HomeSortBean;
import cn.aiyangkeji.bean.ProductDetailBean;
import cn.aiyangkeji.bean.ResetPasswordBean;
import cn.aiyangkeji.bean.SelectType2BuyOrCarBean;
import cn.aiyangkeji.bean.ShoppingCarBean;
import cn.aiyangkeji.bean.SortProductsBean;
import cn.aiyangkeji.bean.SouguBean;
import cn.aiyangkeji.bean.UserBean;
import cn.aiyangkeji.bean.YysRegisterBean;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by chenzhikai on 2017-11-11.
 */
public interface MyAPI {

    @GET("app.php")
    Observable<BannerBean> getBanner(@QueryMap Map<String, Object> maps);

    @GET("{url}")
    Observable<ResponseBody>getWeatherStr(@Path("url") String url,
                                          @QueryMap Map<String, String> maps);

    @GET("app.php")
    Observable<SouguBean> getSougu(@QueryMap Map<String, Object> maps);

//    @GET("combo/{dietId}")
//    Observable<UserBean> register(@Path("dietId") String dietId);
    @POST("customer")
    Observable<UserBean> register(@Body RequestBody body);

    @POST("customer/login")
    Observable<UserBean> login(@Body RequestBody body);
    //营养师注册
    @POST("diet")
    Observable<YysRegisterBean> yYsRegister(@Body RequestBody body);
    //修改密码
    @POST("customer/password")
    Observable<ResetPasswordBean> resetPassword(@Body RequestBody body);
    //保存收货地址
    @POST("contact")
    Observable<AddAddressBean> addAddress(@Body RequestBody body);
    @GET("contact/{customerId}")
    Observable<AddressListBean> getAddress(@Path("customerId") int customerId);
    //意见反馈提交
    @POST("suggestion")
    Observable<BaseBean> commitSuggestion(@Body RequestBody body);
    //商城首页分类列表获取
    @GET("sorts/homeSort")
    Observable<HomeSortBean> getHomeSort();
    //28分类商品获取
    @GET("sorts/{sortId}/products")
    Observable<SortProductsBean> getSortProducts(@Path("sortId") String sortId);
    //商品详情获取
    @GET("goods/{goodsId}")
    Observable<ProductDetailBean> getProductDetail(@Path("goodsId") String  goodsId);
    //商品所有规格获取
    @GET("goods/{goodsId}/specs")
    Observable<SelectType2BuyOrCarBean> getAllSpecs(@Path("goodsId") String goodsId,  @QueryMap Map<String, String> maps );
    //加入购物车
    @POST("cart/addCart")
    Observable<AddCarReturnBean> addCar(@Body RequestBody body);
    //根据当前登录对象获取购物车
    @GET("cart/user/{userId}")
    Observable<ShoppingCarBean> getCarList(@Path("userId") String userId);
    //删除购物车item
//    @DELETE("cart/deleteCart")
//    Observable<DeleteCartBean> deleteCartItem(@Body RequestBody body);
    //删除地址
    @HTTP(method = "DELETE",path = "cart/deleteCart",hasBody = true)
    Observable<DeleteCartBean> deleteCartItem (@Body HashMap<String,String> content);
    //编辑联系人地址
    @PUT("contact")
    Observable<AddAddressBean> editAddress(@Body RequestBody body);
    //请求支付



}
