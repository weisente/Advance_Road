package example.weisente.top.baselibrary.http;

import android.content.Context;

import java.util.Map;

/**
 * Created by san on 2017/11/6.
 */

public interface IHttpEngine {
    // get请求
    void get(boolean cache, Context context, String url, Map<String,Object> params, EngineCallBack callBack);

    // post请求
    void post(boolean cache, Context context, String url, Map<String,Object> params, EngineCallBack callBack);
}
