package cn.aiyangkeji.bean;

import java.util.List;

/**
 * Created by chenzhikai on 2017/11/4.
 */

public class AddToCarAllTypeBean extends BaseBean {

    public List<ArtImage> data;

    public static class ArtImage {
        public String  title;
        public List<Detail> month;

    }
    public static class Detail{
        public String what;
    }
}
