package DecoratorDesignPattern;

import java.io.*;

/**
 * @program: design-patterns
 * @description: 装饰器模式
 * @author: xpp011
 * @create: 2022-08-09 23:15
 **/

public class DecoratorPattern {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream fileInputStream = new FileInputStream("/a.txt");
        //增强缓冲读
        InputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        //增强读取基本类型
        InputStream dataInputStream = new DataInputStream(bufferedInputStream);

    }

}
