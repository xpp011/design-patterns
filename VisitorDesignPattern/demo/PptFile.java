package VisitorDesignPattern.demo;

/**
 * ppt
 *
 * @author: xpp011 2022-10-12 22:38
 **/

public class PptFile extends ResourceFile {

    public PptFile(String filePath) {
        super(filePath);
    }

    @Override
    public void visitor(Visitor visitor) {
        visitor.visit(this);
    }
}
