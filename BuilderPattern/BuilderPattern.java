package BuilderPattern;

import BuilderPattern.ding.At;
import BuilderPattern.ding.MarkDownMessage;
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
        At at = new At.Builder()
                .setAtMobiles(Arrays.asList("150XXXXXXXX"))
                .setAtUserIds(Arrays.asList("user123"))
                .setAtAll(true)
                .builder();
        Message message = new MarkDownMessage.Builder()
                .setTitle("杭州天气")
                .setText(new StringBuilder()
                        .append("#### 杭州天气 @150XXXXXXXX \n")
                        .append("> 9度，西北风1级，空气良89，相对温度73%\n")
                        .append("> ![screenshot](https://img.alicdn.com/tfs/TB1NwmBEL9TBuNjy1zbXXXpepXa-2400-1218.png)\n")
                        .append("> ###### 10点20分发布 [天气](https://www.dingtalk.com) \n")
                        .toString())
                .setAt(at)
                .builder();

        System.out.println(message.toString());
    }

}
