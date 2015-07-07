package ua.artcode.week2;

import java.io.File;

public class ConsoleHelper {

    private ConsoleHelper(){}

    static boolean find(String path){
        File file = new File(path);
        return file.exists();
    }
}
