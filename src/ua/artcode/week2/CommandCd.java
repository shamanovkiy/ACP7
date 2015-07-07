package ua.artcode.week2;

import java.io.File;

public class CommandCd implements ICommand{
    @Override
    public void getHelp() {

    }

    @Override
    public Object run(String currentPath, String text) {
        return changePath(currentPath, text);
    }

    private boolean find(String path, String newPath){
        File file = new File(path);
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(files[i].getPath().equals(newPath)){
                return true;
            }
        }
        return false;
    }

    private String changePath(String currentPath, String text){
        String newPath = currentPath + "\\" + text;
        if(find(currentPath, newPath)){
            return newPath;
        }
        if (text.equals("..")){
            int index = currentPath.lastIndexOf("\\");
            return currentPath.substring(0, index);

        }
        return currentPath;
    }
}
