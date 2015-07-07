package ua.artcode.week2;

public class CommandCd implements ICommand {
    @Override
    public void getHelp() {
        System.out.println("Change the shell working directory.");
        System.out.println("`..' is processed by removing the immediately previous pathname component\n" +
                "    back to a slash or the beginning of DIR.");
    }

    @Override
    public Object run(String currentPath, String text) {
        if(text.equals("")){
            return currentPath.substring(0, currentPath.length());
        }
        String newPath = currentPath + "/" + text;
        if (ConsoleHelper.find(newPath)) {
            return newPath;
        }
        if (text.equals("..")) {
            int index = currentPath.lastIndexOf("/");
            return currentPath.substring(0, index);
        }
        return currentPath;
    }
}
