package BuilderPattern;

import BuilderPattern.ding.B;
import BuilderPattern.ding.Message;

import java.util.Arrays;

/**
 * @program: design-patterns
 * @description: 建造者模式
 * @author: xpp011
 * @create: 2022-07-31 22:23
 **/

public class BuilderPattern {

    public static void main(String[] args) {
    }

    void test() {
        new Message.MarkDownBuilder()
                .setTitle("杭州天气")
                .setText(new StringBuilder()
                        .append("#### 杭州天气 @150XXXXXXXX")
                        .append("\n > 9度，西北风1级，空气良89，相对温度73%")
                        .append("\n > ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)")
                        .append("\n > ###### 10点20分发布 [天气](https://www.dingtalk.com) \n")
                        .toString())
                .at()
                .setAtAll(true)
                .setAtMobiles(Arrays.asList("150XXXXXXXX"))
                .setAtUserIds(Arrays.asList("user123"))


    }
}
