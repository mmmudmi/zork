package Commands;

import org.apache.commons.lang3.StringUtils;

//convert string to CommandType enum
public class CommandParser {
    public static CommandLine parseCommand(String rawInput){
        for ( CommandType commandType : CommandType.values()) {
            if (commandType.match(rawInput)) {
                String arg = rawInput.replace(commandType.getCommandWord(),"").trim();
                arg = StringUtils.isBlank(arg)? null : arg; //if arg is empty --> null
                return new CommandLine(commandType,arg); //arg = sub string from the rest
            }
        }
        return null;
    }
}
