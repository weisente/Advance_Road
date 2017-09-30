package example.weisente.top.baselibrary.Rxbusupdate;

/**
 * Created by san on 2017/9/30.
 */

public class StickyMessage extends Message {
    private int canExecuteTimes = 1;


    /**
     * canExecuteTimes 是记录执行了多少次
     * @param canExecuteTimes
     * @param o
     */
    public StickyMessage(int canExecuteTimes, Object o) {
        super(-1, o);
        this.canExecuteTimes = (canExecuteTimes == 0 ? 1 : canExecuteTimes);
        this.object = o;
    }
    public int getCanExecuteTimes() {
        return canExecuteTimes;
    }

    public void setCanExecuteTimes(int canExecuteTimes) {
        this.canExecuteTimes = canExecuteTimes;
    }

    @Override
    public String toString() {
        return "StickyMessage{" +
                "canExecuteTimes=" + canExecuteTimes +
                '}';
    }
}
