package VisitorDesignPattern.demo;

/**
 * word
 *
 * @author: xpp011 2022-10-12 22:37
 **/

public class WordFile extends ResourceFile {

    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public void visitor(Visitor visitor) {
        visitor.visit(this);
    }
}
