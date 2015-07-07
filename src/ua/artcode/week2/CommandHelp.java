package ua.artcode.week2;

public class CommandHelp implements ICommand{


    @Override
    public void getHelp() {
        System.out.println("Display information about builtin commands.");
        System.out.println("cd [directory]");
        System.out.println("find [directory]");
        System.out.println("dir [directory]");
    }

    @Override
    public Object run(String currentPath, String text) {
        if(text.equalsIgnoreCase("cd")){
            help(new CommandCd());
        }else if(text.equalsIgnoreCase("dir") || text.equalsIgnoreCase("ls")) {
            help(new CommandDir());
        }else if(text.equalsIgnoreCase("find")) {
            help(new CommandFind());
        }else if(text.equalsIgnoreCase("help") || text.equalsIgnoreCase("")) {
            getHelp();
        }
        return currentPath;
    }

    private void help(ICommand command){
        command.getHelp();
    }


}
