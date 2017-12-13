package cn.aiyangkeji.bean;

import java.util.List;

/**
 * Created by 18810 on 2017/11/14.
 */

public class SortProductsBean extends BaseBean {
    public List<SortProductsBean.Products> data;
    public static  class Products{
        public String  goodsName;
        public float price;
        public String image;
        public int   market_enable;
        public int goodsId;

    }
}
