package VisitorDesignPattern.demo;

public interface Visitor {

    void visit(PdfFile file);

    void visit(WordFile file);

    void visit(PptFile file);
}
