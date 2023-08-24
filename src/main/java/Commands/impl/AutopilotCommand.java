package Commands.impl;

import Actors.Player;
import Commands.Command;
import Commands.CommandFactory;
import Commands.CommandLine;
import Commands.CommandParser;
import Items.Inventory;
import Items.Item;
import SaveGame.FileHandler;
import Zork.Game;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class AutopilotCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument){
        if (argument!=null && game.isPlaying()){
            isArgValid = true;
            File file = new File(argument);
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()){
                    String rawInput = sc.nextLine();
                    System.out.printf("> \u001B[34m%s\u001B[0m\n",rawInput);
                    CommandLine commandLine = CommandParser.parseCommand(rawInput);
                    if (commandLine == null) {
                        System.out.printf("\u001B[31m%s is an invalid command\u001B[0m\n",rawInput);
                    }
                    else {
                        Command command = CommandFactory.get(commandLine.getCommandType());
                        command.execute(game, commandLine.getArgument());
                        if (!command.isArgValid()){
                            System.out.println("\u001B[31m"+"Invalid argument"+"\u001B[0m");
                        }
                    }
                    sleep(2000);
                }
            } catch (IOException e){
                System.out.println("Can not read file!");
            }
        } else {
            isArgValid = false;
        }
    }

    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }

    private void sleep(int millisecond){
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            System.out.println("Sleep error");
        }
    }

}
