package VisitorDesignPattern.demo;

/**
 * 资源文件
 *
 * @author: xpp011 2022-10-12 22:33
 **/

public abstract class ResourceFile {

    private final String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public abstract void visitor(Visitor visitor);
}
