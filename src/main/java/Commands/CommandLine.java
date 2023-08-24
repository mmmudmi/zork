package Commands;

public class CommandLine {
    private CommandType commandType;
    private String argument;
    public CommandLine(CommandType commandTyp, String argument){
        this.commandType = commandTyp;
        this.argument = argument;
    }
    public CommandType getCommandType() {
        return commandType;
    }
    public String getArgument() {
        return argument;
    }

}
