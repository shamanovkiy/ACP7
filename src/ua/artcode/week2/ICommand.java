package ua.artcode.week2;

public interface ICommand {

    void getHelp();

    Object run(String currentPath, String text);
}
