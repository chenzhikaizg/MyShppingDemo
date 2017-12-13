package cn.aiyangkeji.bean;

import java.util.List;

/**
 * Created by 18810 on 2017/11/14.
 */

public class HomeSortBean extends BaseBean {
    public List<HomeSortBean.Sort> data;
    public static  class Sort{
        public String  sortId;
        public String sortName;
        public String image;
        public String  sequence;

    }
}
