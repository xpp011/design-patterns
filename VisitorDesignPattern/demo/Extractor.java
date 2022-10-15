package VisitorDesignPattern.demo;

/**
 * 提取
 *
 * @author: xpp011 2022-10-12 22:40
 **/

public class Extractor implements Visitor {

    @Override
    public void visit(PdfFile file) {
        System.out.println("提取pdf");
    }

    @Override
    public void visit(WordFile file) {
        System.out.println("提取word");
    }

    @Override
    public void visit(PptFile file) {
        System.out.println("提取ppt");
    }
}
