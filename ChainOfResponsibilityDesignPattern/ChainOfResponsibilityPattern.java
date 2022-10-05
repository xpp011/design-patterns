package ChainOfResponsibilityDesignPattern;

import ChainOfResponsibilityDesignPattern.demo.*;

/**
 * 责任链模式
 *
 * @author: xpp011 2022-10-05 22:49
 **/

public class ChainOfResponsibilityPattern {

    public static void main(String[] args) {
        SensitiveWordFilterChain filterChain = new SensitiveWordFilterChain();
        filterChain.addWordFilter(new SexyWordFilter());
        filterChain.addWordFilter(new AdsWordFilter());
        filterChain.addWordFilter(new PoliticalWordFilter());
        boolean isCanBePost = filterChain.filter(new Context());
    }
}
