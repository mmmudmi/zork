package Commands.impl;

import Commands.Command;
import Commands.CommandLine;
import Commands.CommandParser;
import Commands.CommandType;
import Zork.Game;

public class HelpCommand implements Command  {
    private Boolean isArgValid = false;

    @Override
    public void execute(Game game, String argument) {

        if (argument == null){ //help
            //System.out.println("✧✧✧ All Commands ✧✧✧");
            System.out.println("\u001B[1mAll Commands\u001B[0m");
            for (CommandType commandType : CommandType.values()){
                System.out.println(commandType.getExplanation());
            }
            isArgValid = true;
        } else { // help go
            CommandLine commandLine = CommandParser.parseCommand(argument);
            if (commandLine != null) {
                System.out.printf("\u001B[1mHelp on Command%s\u001B[0m\n",argument);
                System.out.println(commandLine.getCommandType().getExplanation());
                isArgValid = true;
            }
        }
    }
    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }

}
