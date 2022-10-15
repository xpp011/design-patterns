package VisitorDesignPattern.demo;

/**
 * 压缩
 *
 * @author: xpp011 2022-10-12 23:24
 **/

public class Compressor implements Visitor {

    @Override
    public void visit(PdfFile file) {
        System.out.println("Compress pdf");
    }

    @Override
    public void visit(WordFile file) {
        System.out.println("Compress word");
    }

    @Override
    public void visit(PptFile file) {
        System.out.println("Compress ppt");
    }
}
