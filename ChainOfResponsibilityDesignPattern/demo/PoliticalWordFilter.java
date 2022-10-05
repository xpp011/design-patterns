package ChainOfResponsibilityDesignPattern.demo;

/**
 * 政治词过滤
 *
 * @author: xpp011 2022-10-05 23:33
 **/

public class PoliticalWordFilter implements IWordFilter {

    @Override
    public boolean doFilter(Context context) {
        return isContains(context);
    }

    private boolean isContains(Context context) {
        return true;
    }
}
