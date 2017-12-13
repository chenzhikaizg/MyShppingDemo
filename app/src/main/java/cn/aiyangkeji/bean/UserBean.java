package cn.aiyangkeji.bean;

/**
 * Created by 18810 on 2017/11/11.
 */

public class UserBean extends BaseBean {
    public DataUser data;
    public static class DataUser{
        public Customer customer;
    }
    public static class Customer{
        public int customerId;
        public String openid;
        public String nickName;
        public String headimgurl;
        public String mobile;
        public String source;
        public String password;
        public String status;
        public String isDietitian;
        public String createTime;
    }
}
