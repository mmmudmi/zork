package Commands;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<CommandType, Command> commandMap = new HashMap<>();
    static {
        for (CommandType commandType: CommandType.values()){
            Command command = null;
            try {
                command = (Command) commandType.getCommandClass().getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            commandMap.put(commandType,command);
        }
    }
    public static Command get(CommandType commandType){
        return commandMap.get(commandType);
    }
}