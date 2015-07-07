package ua.artcode.week2;

import java.io.File;

public class CommandDir implements ICommand {
    @Override
    public void getHelp() {
        System.out.println("Display the list of currently remembered directories.");
    }

    @Override
    public Object run(String currentPath, String text) {
        if(!text.equals("")){
            currentPath = currentPath + "/" + text;
        }
        File file = new File(currentPath);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(!files[i].getName().startsWith(".")) {
                System.out.println(files[i].getName());
            }
        }
        return currentPath;
    }
}
