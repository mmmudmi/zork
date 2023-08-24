/*
https://www.geeksforgeeks.org/ways-to-read-input-from-console-in-java/ --> scanner
*/

package Zork;
import Actors.Player;
import Commands.*;
import Commands.impl.AllSavesCommand;
import Maps.Directions;
import Maps.Map;
import Maps.MapType;
import Maps.Room;

import java.util.Scanner;

public class Game {
    private Map currentMap;
    private Player player;
    private boolean exit = false;
    private boolean isPlaying = false;
    private Scanner scanner = new Scanner(System.in);

    /** Getter */
    public Map getMap(){ return currentMap; }
    public Player getPlayer(){ return player;}
    public boolean isPlaying(){ return isPlaying; }
    public boolean isExit() { return exit; }
    public void printMenu(){
        isPlaying = false;
        System.out.println( "  ____  __  ____  __ _ \n" +
                " (__  )/  \\(  _ \\(  / )\n" +
                "  / _/(  O ))   / )  ( \n" +
                " (____)\\__/(__\\_)(__\\_)");
        System.out.println("Kill the final boss to win");
        System.out.println("\n   Choose Map to Play");
        MapType.printMapNameWithArt();
        AllSavesCommand.printAllSavedGames();
        System.out.println(" ");
    }


    /** Setter */
    public void setIsPlaying(Boolean bool){this.isPlaying = bool;}
    public void mapSetUp(Map mapName){
        currentMap = mapName;
        isPlaying = true; //choose map then play
        System.out.println("Your starting point is " + mapName.getCurrentRoom().getName());
    }
    public void setPlayer(Player player){ this.player = player;}
    public void setPlayer(){ this.player = new Player(); }


    /** Command Helper */
    public void start(){
        printMenu();
        System.out.printf("> ");
        while (!isExit() && scanner.hasNext() ){ //quit when ctrlC or Exit is true
            String rawInput = scanner.nextLine();
            CommandLine commandLine = CommandParser.parseCommand(rawInput);
            if (commandLine == null) {
                System.out.println("\u001B[31mInvalid command, try again!\u001B[0m");}
            else {
                Command command = CommandFactory.get(commandLine.getCommandType());
                command.execute(this, commandLine.getArgument());
                if (!command.isArgValid()){
                    System.out.println("\u001B[31mInvalid argument, try again!\u001B[0m");
                }
            }
            if(!isExit()) {System.out.printf("> ");};
        }
    }

    public void playerMove(Directions direction){
        /**current room change*/
        this.currentMap.move(direction);
        Room currentRoom = currentMap.getCurrentRoom();
        /**print where are you in pink*/
        String pink = "\u001B[38;5;206m";
        String end = "\u001B[0m";
        System.out.println("You are in "+ pink + currentRoom.getName() + end);
        /** +20HP each time player walk to a new room*/
        player.updateHP(20);
    }
    public void exit() {
        this.exit = true;
        System.out.println("~ EXIT THE GAME ~");
        scanner.close();
    }
}
