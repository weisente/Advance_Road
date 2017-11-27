package example.weisente.top.ParallaxDemo;

/**
 * Created by san on 2017/11/27.
 *
 *
 * 一些 位移的属性
 */

public class ParallaxTag {
    public float translationXIn;
    public float translationXOut;
    public float translationYIn;
    public float translationYOut;
    @Override
    public String toString() {
        return "translationXIn->"+translationXIn+" translationXOut->"+translationXOut
                +" translationYIn->"+translationYIn+" translationYOut->"+translationYOut;
    }
}
