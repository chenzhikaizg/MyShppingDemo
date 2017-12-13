package cn.aiyangkeji.bean;

import java.util.List;

/**
 * Created by chenzhikai on 2017/11/4.
 */

public class MyWenDaBean extends BaseBean {

    public List<ArtImage> value;

    public static class ArtImage {
        public String status;
        public int love;
        public String img;
        public int id;


        public String name;
        public String money;
        public String kind;
        public String title;

        /**
         * 0
         */
        public int isinternalpayment;
        public boolean isSelected= false;

    }
}
