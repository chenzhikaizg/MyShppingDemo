package cn.aiyangkeji.bean;

/**
 * Created by 18810 on 2017/11/14.
 */

public class AddAddressBean extends BaseBean {
    public Address data;
    public static  class Address{
        public String mobile;
        public String address;
        public String city;
        public String name;
        public int customerId;


    }
}
