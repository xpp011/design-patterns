package ChainOfResponsibilityDesignPattern.demo;

/**
 * 广告过滤
 *
 * @author: xpp011 2022-10-05 23:35
 **/

public class AdsWordFilter implements IWordFilter {

    @Override
    public boolean doFilter(Context context) {
        return false;
    }
}
