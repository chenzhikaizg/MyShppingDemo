package cn.aiyangkeji.util;

import com.google.gson.Gson;
import com.tamic.novate.util.Utils;



import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 18810 on 2017/11/13.
 */

public class JsontoRequestBody {
        public static RequestBody parameters2json(Map parameters){
            Gson gson = new Gson();
            String s = gson.toJson(parameters);
         //   String s = StringUtil.setUrlJoin(parameters);


            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),s);

            return requestBody;
        }






}
