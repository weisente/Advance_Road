package example.weisente.top.baselibrary.http;

import android.content.Context;

import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by san on 2017/11/6.
 */

public class OkHttpEngine implements IHttpEngine {


    private static OkHttpClient mOkHttpClient = new OkHttpClient();


    @Override
    public void get(Context context, String url, Map<String, Object> params, EngineCallBack callBack) {

    }

    @Override
    public void post(Context context, String url, Map<String, Object> params, EngineCallBack callBack) {

    }

    /**
     * 拼接参数
     */
    public static String jointParams(String url, Map<String, Object> params) {
        if (params == null || params.size() <= 0) {
            return url;
        }

        StringBuffer stringBuffer = new StringBuffer(url);
        if (!url.contains("?")) {
            stringBuffer.append("?");
        } else {
            if (!url.endsWith("?")) {
                stringBuffer.append("&");
            }
        }
        //遍历params里面的每一个entry
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        //The last one  neets to be removed
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);

        return stringBuffer.toString();
    }
}
