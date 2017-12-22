package cn.aiyangkeji.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaoweiwei on 15/6/4.
 */
public class AddressListBean extends BaseBean{
    public Value data;
    public static class Value{
        public List<AddressListBean.Address> address;
        public List<AddressListBean.ShipMoney> shipMoney;
    }
    public static class Address implements Serializable {
        public int contactId;
        public int customerId;
        public String name;
        public String  mobile;
        public String province;
        public String city;
        public String district;
        public String address;
        public String createTime;
        public String shipMoneyId;
        public int disable;
    }
    public static class ShipMoney implements Serializable {
        public int addrPayMoneyId;
        public String address;
        public double payMoney;
    }


}
