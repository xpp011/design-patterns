package StrategyDesignPattern.demo;

import java.util.Map;

/**
 * sort工厂类
 *
 * @author: xpp011 2022-10-03 22:13
 **/

public class SortAlgFactory {

    /**
     * SI标准   1kB=10^3B
     * IEC标准  1KiB=1024Bit
     * https://zh.m.wikipedia.org/zh-hans/%E5%8D%83%E5%AD%97%E8%8A%82
     */
    private static long GB = 1000 * 1000 * 1000;

    public static ISortAlg getSortAlg(long size) {
        if (size < 6 * GB) {
            return new QuickSort();
        }
        if (size < 10 * GB) {
            return new ExternalSort();
        }
        if (size < 100 * GB) {
            return new ConcurrentExternalSort();
        }
        return new MapreduceSort();
    }

}
