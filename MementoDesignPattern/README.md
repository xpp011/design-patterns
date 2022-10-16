# 备忘录模式（Memento Design Pattern）



## 定义

> Captures and externalizes an object’s internal state so that it can be restored later, all without violating encapsulation.
>
> 在不违背封装原则的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便之后恢复对象为先前的状态。





## 经典实现

### redis

redis数据恢复RDB全量备份，AOF增量备份



### git

版本管理



## 实际应用

希望你编写一个小程序，可以接收命令行的输入。用户输入文本时，程序将其追加存储在内存文本中；用户输入“:list”，程序在命令行中输出内存文本的内容；用户输入“:undo”，程序会撤销上一次输入的文本，也就是从内存文本中将上次输入的文本删除掉。

```yaml
>hello
>:list
hello
>world
>:list
helloworld
>:undo
>:list
hello
```



代码

```java

public class InputText {
  private StringBuilder text = new StringBuilder();

  public String getText() {
    return text.toString();
  }

  public void append(String input) {
    text.append(input);
  }

  public Snapshot createSnapshot() {
    return new Snapshot(text.toString());
  }

  public void restoreSnapshot(Snapshot snapshot) {
    this.text.replace(0, this.text.length(), snapshot.getText());
  }
}

public class Snapshot {
  private String text;

  public Snapshot(String text) {
    this.text = text;
  }

  public String getText() {
    return this.text;
  }
}

public class SnapshotHolder {
  private Stack<Snapshot> snapshots = new Stack<>();

  public Snapshot popSnapshot() {
    return snapshots.pop();
  }

  public void pushSnapshot(Snapshot snapshot) {
    snapshots.push(snapshot);
  }
}

public class ApplicationMain {
  public static void main(String[] args) {
    InputText inputText = new InputText();
    SnapshotHolder snapshotsHolder = new SnapshotHolder();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String input = scanner.next();
      if (input.equals(":list")) {
        System.out.println(inputText.toString());
      } else if (input.equals(":undo")) {
        Snapshot snapshot = snapshotsHolder.popSnapshot();
        inputText.restoreSnapshot(snapshot);
      } else {
        snapshotsHolder.pushSnapshot(inputText.createSnapshot());
        inputText.append(input);
      }
    }
  }
}
```

对于字符串输入这种只会顺序撤销，并且是增量添加就可以节省空间每次不必全量备份。

扩展一下，假设我们需要备份的数据非常大，并且很频繁。全量备份不管是对存储还是性能都有极大的消耗，想要解决这个问题一般会采用**低频全量备份**和**高频增量备份**。

如果需要恢复某个时间节点数据，只需恢复到最近的一次时间节点的全量备份，在执行增量备份的数据慢慢恢复到恢复时间节点，减少对时间和空间的消耗