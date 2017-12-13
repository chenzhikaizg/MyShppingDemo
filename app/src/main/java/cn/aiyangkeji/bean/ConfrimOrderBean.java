package cn.aiyangkeji.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 18810 on 2017/11/14.
 */

public class ConfrimOrderBean extends BaseBean {
    public ConfrimOrderBean.Address data;
    public static  class Address implements Serializable {
        public String name;
        public int num;
        public String price;
        public List<Type> typeList;

    }
    public static class Type implements Serializable {
        public String type;
        public String typeValue;
        public int status;

    }
}
