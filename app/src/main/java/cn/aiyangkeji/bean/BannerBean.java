package cn.aiyangkeji.bean;

import java.util.List;

/**
 *Created by chenzhkai on 17/11/1.
 */
public class BannerBean extends BaseBean{
    public BannerBean.Vaule value;
    public static class Vaule{
        public List<Typ> typzero;//Banner图
        public List<Typ> typone;//专题
        public List<Typ> typtwo;//艺术机构
        public List<ProductBean> derivative_info;
        public List<NewProduct> derivatives;

    }
    public static class ProductBean{
            public List<Derivative> derivative;

            public Artist artist;
    }
    public static class Derivative {
        public String id;
        public String name;
        public String file;
    }
    public static class Artist{

        /**
         * 任职机构
         */
        public int id;
        public String name;
        public String icon;
    }
    public static class Typ{
        public String id;
        public int platform;
        public String ordseq;
        public String url_type;
        public String name;
        public String typeid;
        public String img;
        public String typ;
        public String url;
        public int is_follower;
        public int count;


    }
    public static class NewProduct{
        public String price ;
        public String artmate;
        public String file;
        public int id;
        public String name;
    }
}
