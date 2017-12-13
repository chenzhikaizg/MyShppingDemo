package cn.aiyangkeji.app;

/**
 * Created by gaoweiwei on 15/5/18.
 */
public class AppConfig {
    /**
     * 文本编码格式
     */
    public static final String CODE_TYPE = "utf-8";
    /**
     * deubg输出开关
     */
    public final static boolean DEBUG =true;
    /**
     * 记录当前应用版本
     */
    public final static int APP_VERSION = 1;
    /**
     * app 本地图片存储路径
     *
     */
    public final static String LOCATION_IMAGE_PATH="artpollo/images";
    /**
     * app 本地文件存储路径
     *
     */
    public final static String LOCATION_FILE_PATH="artpollo/files";

    /**
     * volley超时
     */
    public static final int VOLLEY_TIMEOUT = 40000;
    /**
     * volley 最大重试的次数
     */
    public static final int VOLLEY_MAX_NUM_RETRIES = 0;
    /**
     *获取验证码中间间隔时间
     */
    public static final int GET_CODE_TIME=60;
    /**
     * 线下测试地址 36.110.43.162
     */
  //  public final static String API_PREFIX="http://124.202.199.114:9000/";

    /**
     * 线上正式版本
     */
    public final static String API_PREFIX="http://service.artpollo.com/";
    /**
     * 线上测试地址（外网可以访问）
     */
  // public final static String API_PREFIX="http://36.110.43.162:9000/";
    /**
     *边亮电脑*/
   //public final static String API_PREFIX="http://192.168.0.105:8888/";
    /**
     * 自媒体分享url
     */
   // public  final static String SHARE_URL="http://www.artpollo.com/";

    public  final  static String ICON_URL="http://img7.artpollo.com/ic_launcher.png";

    /**
     * 友盟渠道  360; baidu; wandoujia; tencent; artpollo;
     * anzhi;jifeng;yingyonghui,mumayi;nduo;anji;mmshequ;flyme;douban;google
     */
    // public final static String UM_CHANNEL="yingyonghui";
    /**
     * 登录请求code
     */
    public final static int LOGIN_REQUEST_CODE=20;
    /**
     * 登录成功后结果code
     */
    public final static int LOGIN_RESULT_SUCESS_CODE=21;
    /**
     * 登录失败结果code
     */
    public final static int LOGIN_RESULT_CANCEL_CODE=22;
    /**
     * 退出登录
     */
    public  final static int LOGIN_OUT = 33;




}
