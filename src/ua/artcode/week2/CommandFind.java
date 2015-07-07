package ua.artcode.week2;

import java.io.File;

public class CommandFind implements ICommand {

    @Override
    public void getHelp() {
        System.out.println("'find' searches the directory tree rooted at each file name FILE by\n" +
                "evaluating the EXPRESSION on each file it finds in the tree.");
    }

    @Override
    public Object run(String currentPath, String text) {
        if(ConsoleHelper.find(currentPath)){
            if(text.equals("")){
                int index = currentPath.lastIndexOf("/");
                text = currentPath.substring(index);
                System.out.println(text);
                showFile(currentPath, text);
            }else {
                System.out.println(text);
                showFile(currentPath + "/" + text, text);
            }
        }
        return currentPath;
    }


    private void showFile(String filePath, String text) {
        File file = new File(filePath);
        File[] files = file.listFiles();
        for (File f : files) {
            if (files == null) {
                return;
            }
            int index = f.getPath().lastIndexOf(text);
            System.out.println(f.getPath().substring(index));
            if (f.isDirectory()) {
                showFile(f.getPath(), text);
            }
        }
    }
}
