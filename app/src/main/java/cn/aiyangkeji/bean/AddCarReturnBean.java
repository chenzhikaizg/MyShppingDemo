package cn.aiyangkeji.bean;

import java.util.List;

/**
 * Created by 陈志凯 on 2017/12/12.
 */

public class AddCarReturnBean extends BaseBean {
    public AddCarReturnBean.Product data;
    public static  class Product{
        public String  specValue;

        public String specKey;

    }
    public static class Specs{
        public String specValue;
        public String specKey;
    }
    public static class CartItem{
        public int productId;
        public String productName;
        public float productPrice;
        public int number;
        public String image;
    }

}
