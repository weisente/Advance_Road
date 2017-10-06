package example.weisente.top.baselibrary.rxbus;

/**
 * Created by san on 2017/10/6.
 */

public class Message {
    private int code = -1;
    protected Object object;

    /**
     * 构造函数
     *
     * @param code 自定义code
     * @param o    事件类型
     */
    public Message(int code, Object o) {
        this.code = code;
        this.object = o;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", object=" + object +
                '}';
    }
}
