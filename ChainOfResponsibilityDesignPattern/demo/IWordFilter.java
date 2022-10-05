package ChainOfResponsibilityDesignPattern.demo;

public interface IWordFilter {

    /**
     *
     * @param context
     * @return 是否能够发表
     */
    boolean doFilter(Context context);

}
