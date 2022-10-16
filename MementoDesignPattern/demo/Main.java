package MementoDesignPattern.demo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author: xpp011 2022-10-16 22:45
 **/

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command;
        Context context = new Context();
        while (!"break".equals(command = reader.readLine())) {
            if (command.length() > 0 && command.charAt(0) == ':') {
                switch (command) {
                    case ":list":
                        System.out.println(context.getText());
                        break;
                    case ":undo":
                        context.undo();
                        break;
                    default:
                        System.out.println("command not found");
                }
            } else {
                context.append(command);
            }
        }
    }

}
