package Commands;

import Commands.impl.*;
import Maps.MapType;

public enum CommandType {
    HELP(HelpCommand.class,"help","\u001B[1mhelp\u001B[0m : to print description of all game commands\n" +
            "\u001B[1mhelp <command>\u001B[0m : to print description of a specific command"),
    PLAY(PlayCommand.class,"play","\u001B[1mplay <map>\u001B[0m: to pick a map to play. Here are map names you can choose " + MapType.getMapNames()),
    INFO(InfoCommand.class,"info","\u001B[1minfo\u001B[0m : to print information of player and current room"),
    MAP(MapCommand.class,"map","\u001B[1mmap\u001B[0m : to print the current game map"),
    GO(GoCommand.class,"go", "\u001B[1mgo <direction>\u001B[0m : to enter to another room at (north, south, east, west, up, down) direction " ),
    TAKE(TakeCommand.class,"take","\u001B[1mtake <number of slot>\u001B[0m : to pick an item in the certain slot number from a room\n" +
            "\u001B[1mtake inventory\u001B[0m : to switch your current inventory to the one that dropped in the current room" ),
    USE(UseCommand.class,"use", "\u001B[1muse <number of slot>\u001B[0m : to heal your HP by using an item in a certain slot in your inventory"),
    DROP(DropCommand.class,"drop","\u001B[1mdrop <number of slot>\u001B[0m : to empty a specific slot in your inventory"),
    ATTACK_WITH(AttackWithCommand.class,"attack with","\u001B[1mattack with <number of slot>\u001B[0m : to attack the monster in a room with a weapon in your inventorty at the desired nunber of slot"),
    UNLOCK(UnlockCommand.class,"unlock","\u001B[1munlock <direction>\u001B[0m : to unlock the door on that direction"),
    QUIT(QuitCommand.class,"quit", "\u001B[1mquit\u001B[0m : to quit the game and back to the main menu" ),
    EXIT(ExitCommand.class,"exit","\u001B[1mexit\u001B[0m : to exit the game available only at menu page"),
    AUTO(AutopilotCommand.class,"autopilot","\u001B[1mautopilot <file>\u001B[0m : to run the game from list of commands from the file"),
    SAVE(SaveCommand.class,"save","\u001B[1msave <save slot number>\u001B[0m : to save your current game to the save game slot number"),
    LOAD(LoadCommand.class,"load","\u001B[1mload <save slot number>\u001B[0m : to load the game save from the save game slot number"),
    REMOVE(RemoveCommand.class,"remove","\u001B[1mremove <save slot number>\u001B[0m : to remove the game save in the save game slot number"),
    ALL_SAVES(AllSavesCommand.class,"allSaves","\u001B[1mallSaves\u001B[0m : to print all the save game slots and its status");
    private Class<? extends Command> commandClass;
    private String commandWord;
    private String explanation;

    CommandType(Class<? extends Command> commandClass, String commandWord, String explanation) {
        this.commandClass = commandClass;
        this.commandWord = commandWord;
        this.explanation = explanation;
    }
    public Class getCommandClass(){
        return commandClass;
    }
    public String getCommandWord() { return commandWord; }
    public String getExplanation() {return explanation;}
    public boolean match(String rawInput){
        return rawInput.startsWith(commandWord);
    }
}
