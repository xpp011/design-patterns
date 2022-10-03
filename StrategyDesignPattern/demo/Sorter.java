package StrategyDesignPattern.demo;

import java.io.File;

/**
 * 排序
 *
 * @author: xpp011 2022-10-03 22:09
 **/

public class Sorter {

    public void sorterFile(File file) {
        long size = file.length();
        ISortAlg sortAlg = SortAlgFactory.getSortAlg(size);
        sortAlg.sort(file);
    }

}
