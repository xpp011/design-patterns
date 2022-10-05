package ChainOfResponsibilityDesignPattern.demo;

/**
 * 敏感词过滤
 *
 * @author: xpp011 2022-10-05 23:31
 **/

public class SexyWordFilter implements IWordFilter {
    @Override
    public boolean doFilter(Context context) {
        context.setContext(context.getContext().replaceAll("敲你大爷", "****"));
        return true;
    }
}
