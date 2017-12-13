package cn.aiyangkeji.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 18810 on 2017/11/14.
 */

public class SelectType2BuyOrCarBean extends BaseBean {
    public SelectType2BuyOrCarBean.Type data;

    public static  class Type implements Serializable {
        public String name;
        public int num;
        public String price;
        public String type;

        public List<SelectType2BuyOrCarBean.TypeAddAll> stores;
        public List<SelectType2BuyOrCarBean.TypeList> specs;

    }
    public static  class TypeAddAll implements Serializable {


        public String  specKey;
        public String  store;

    }
    public static  class TypeList implements Serializable {


        public String spec;
        public List<SelectType2BuyOrCarBean.SpecValues> specValues;

    }
    public static  class SpecValues implements Serializable {

        public int statusCode;
        public String specValue;
        public int  specValueId;
        public int specId;
        public String specImage;
    }

}
