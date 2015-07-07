package ua.artcode.week2;

import java.io.File;

public class CommandDir implements ICommand {
    @Override
    public void getHelp() {

    }

    @Override
    public Object run(String currentPath, String text) {
        File file = new File(currentPath);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getPath());
        }
        return null;
    }
}
