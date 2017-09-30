package example.weisente.top.baselibrary.rxbus;


/**
 * 自定义错误类型
 */

public class RxBusException extends RuntimeException {
    public RxBusException(String detailMessage) {
        super(detailMessage);
    }
}
