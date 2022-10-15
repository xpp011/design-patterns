package VisitorDesignPattern.demo;

/**
 * pdf
 *
 * @author: xpp011 2022-10-12 22:36
 **/

public class PdfFile extends ResourceFile {

    public PdfFile(String filePath) {
        super(filePath);
    }

    @Override
    public void visitor(Visitor visitor) {
        visitor.visit(this);
    }
}
