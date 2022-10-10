package IteratorDesignPattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器模式
 *
 * @author: xpp011 2022-10-10 22:37
 **/

public class IteratorPattern {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        Iterator<String> iterator = names.iterator();
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

}
