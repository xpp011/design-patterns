package VisitorDesignPattern.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: xpp011 2022-10-12 22:41
 **/

public class Main {

    public static void main(String[] args) {
        Extractor extractor = new Extractor();
        Compressor compressor = new Compressor();
        List<ResourceFile> resourceFiles = listAllResourceFiles(args[0]);
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.visitor(extractor);
            resourceFile.visitor(compressor);
        }
    }

    private static List listAllResourceFiles(String resourceDirectory) {
        List resourceFiles = new ArrayList<>();
        //...根据后缀(pdf/ppt/word)由工厂方法创建不同的类对象(PdfFile/PPTFile/WordFile)
        resourceFiles.add(new PdfFile("a.pdf"));
        resourceFiles.add(new WordFile("b.word"));
        resourceFiles.add(new PptFile("c.ppt"));
        return resourceFiles;
    }
}
