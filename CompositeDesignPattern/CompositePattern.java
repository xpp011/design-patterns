package CompositeDesignPattern;

import java.util.*;

/**
 * @program: design-patterns
 * @description: 组合模式
 * @author: xpp011
 * @create: 2022-08-14 22:28
 **/

public class CompositePattern {
    public static void main(String[] args) {

    }
}

abstract class FileSystemNode {
    private String path;

    public FileSystemNode(String path) {
        if (path == null || "".equals(path)) throw new IllegalArgumentException();
        this.path = path;
    }

    public abstract int countNumOfFiles();

    public abstract long countSizeOfFiles();

    public String getPath() {
        return path;
    }

}

class File extends FileSystemNode {

    public File(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        return 1;
    }

    @Override
    public long countSizeOfFiles() {
        java.io.File file = new java.io.File(getPath());
        return file.exists() ? file.length() : 0;
    }
}

class Directory extends FileSystemNode {

    private Map<String, FileSystemNode> filesMap = new HashMap<>();

    public Directory(String path) {
        super(path);
    }

    public Map<String, FileSystemNode> getFilesMap() {
        return filesMap;
    }

    @Override
    public int countNumOfFiles() {
        int sum = filesMap.values().stream().mapToInt(v -> v.countNumOfFiles()).sum();
        return sum;
    }

    @Override
    public long countSizeOfFiles() {
        long sum = filesMap.values().stream().mapToLong(v -> v.countSizeOfFiles()).sum();
        return sum;
    }

    public void addNode(FileSystemNode node) {
        if (node == null || filesMap.containsKey(node.getPath())) throw new IllegalArgumentException();
        FileSystemNode fileSystemNode = node.getPath().indexOf("/") == -1 ? this : find(node.getPath().substring(0, node.getPath().lastIndexOf("/")));
        if (fileSystemNode == null || ! (fileSystemNode instanceof Directory)) throw new IllegalArgumentException("未找到路径");
        ((Directory)fileSystemNode).getFilesMap().put(node.getPath(), node);
    }

    public void removeNode(String path) {
        if (path == null || !filesMap.containsKey(path)) throw new IllegalArgumentException();
        if (path.indexOf("/") == -1) {
            filesMap.remove(path);
        }
        else {
            int last = path.lastIndexOf("/");
            Directory directory = (Directory) find(path.substring(0, last));
            directory.removeNode(path.substring(last + 1));
        }
    }

    public FileSystemNode find(String path) {
        if (path == null) throw new IllegalArgumentException();
        int ixd = path.indexOf("/");
        if (ixd == -1) return filesMap.get(path);
        String name = path.substring(0, ixd);
        if (filesMap.get(name) instanceof Directory) {
            return ((Directory) filesMap.get(name)).find(path.substring(ixd + 1));
        }
        return null;
    }
}

