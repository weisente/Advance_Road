package example.weisente.top.baselibrary.http;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by san on 2017/11/6.
 */

public class HttpUtils {
    // 直接带参数 ，链式调用
    private String mUrl;

    // 请求方式
    private int mType = GET_TYPE;
    private static final int POST_TYPE = 0x0011;
    private static final int GET_TYPE = 0x0022;

    private Map<String,Object> mParams;

    // 上下文
    private Context mContext;

    private HttpUtils(Context context){
        mContext = context;
        mParams = new HashMap<>();
    }
    public static HttpUtils with(Context context){
        return new HttpUtils(context);
    }

    public HttpUtils url(String url){
        this.mUrl = url;
        return this;
    }
    // 请求的方式
    public HttpUtils post(){
        mType = POST_TYPE;
        return this;
    }
    public HttpUtils get(){
        mType = GET_TYPE;
        return this;
    }
    // 添加参数
    public HttpUtils addParam(String key,Object value){
        mParams.put(key, value);
        return this;
    }
    public HttpUtils addParams(Map<String,Object> params){
        mParams.putAll(params);
        return this;
    }

    // 添加回掉 执行
    public void execute(EngineCallBack callBack){
        // 每次执行都会进入这个方法：这里添加是行不通的
        // 1.baseLibrary里面这里面不包含业务逻辑
        // 2.如果有多条业务线，
        // 让callBack回调去
        callBack.onPreExecute(mContext,mParams);

        if(callBack == null){
            callBack = EngineCallBack.DEFUALT_CALL_BACK;
        }

        // 判断执行方法
        if(mType == POST_TYPE){
            post(mUrl,mParams,callBack);
        }

        if(mType == GET_TYPE){
            get(mUrl, mParams, callBack);
        }
    }
    public void execute(){
        execute(null);
    }

    // 默认OkHttpEngine
    private static IHttpEngine mHttpEngine = new OkHttpEngine();
    // 在Application初始化引擎
    public static void init(IHttpEngine httpEngine){
        mHttpEngine = httpEngine;
    }

    /**
     * 每次可以自带引擎
     */
    public HttpUtils exchangeEngine(IHttpEngine httpEngine){
        mHttpEngine = httpEngine;
        return this;
    }
}
