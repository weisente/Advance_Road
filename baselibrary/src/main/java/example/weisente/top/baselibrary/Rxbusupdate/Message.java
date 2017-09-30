package example.weisente.top.baselibrary.Rxbusupdate;

/**
 * Created by san on 2017/9/30.
 */

public class Message {
    private int code = -1;
    protected Object object;
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
