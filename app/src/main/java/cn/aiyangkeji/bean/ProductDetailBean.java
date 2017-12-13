package cn.aiyangkeji.bean;

import java.util.List;

/**
 * Created by 18810 on 2017/11/14.
 */

public class ProductDetailBean extends BaseBean {
    public ProductDetailBean.Product data;

    public static  class Product{
        public String  goods_sn;
        public List<String> specIds;
        public float price;
        public String image;
        public int   market_enable;
        public int goods_type_id;
        public int goods_story;
        public String goods_name;
        public String goods_image;
        public float goods_weight;
        public String goods_putAway;
        public String goods_lastSoldOut;
        public float goods_price;
        public int  goods_sail;
        public int goods_id;
        public int  goods_costPrice;

    }
}
