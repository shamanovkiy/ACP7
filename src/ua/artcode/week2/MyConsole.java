package ua.artcode.week2;

import java.util.Scanner;

public class MyConsole {
    private String current = "/home/maks";
    private boolean working = true;
    private Scanner scan = new Scanner(System.in);

    public MyConsole(){
        run();
    }

    private void run(){
        while(working){
            System.out.print(current + "$ ");
            String doing = scan.nextLine();
            checkString(doing);
        }
    }

    private void checkString(String line){
        line = line.trim();
        String[] lines = line.split(" ");
        String command = lines[0];
        String text = lines[1];
        if(command.equalsIgnoreCase("cd")){
            commandRun(new CommandCd(), text);
        }else if(command.equalsIgnoreCase("dir") || command.equalsIgnoreCase("ls")){
            commandRun(new CommandDir(), text);
        }
    }

    private void commandRun(ICommand command, String text){
        current = (String) command.run(current, text);
    }





}
