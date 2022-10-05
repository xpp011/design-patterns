package ChainOfResponsibilityDesignPattern.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器链
 *
 * @author: xpp011 2022-10-05 23:36
 **/

public class SensitiveWordFilterChain {

    private List<IWordFilter> wordFilters = new ArrayList<>();

    public void addWordFilter(IWordFilter wordFilter) {
        for (IWordFilter filter : wordFilters) {
            if (filter.equals(wordFilter)) return;
        }
        wordFilters.add(wordFilter);
    }

    public boolean filter(Context context) {
        for (IWordFilter wordFilter : wordFilters) {
            if (!wordFilter.doFilter(context)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
