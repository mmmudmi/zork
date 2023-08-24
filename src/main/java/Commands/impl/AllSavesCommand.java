package Commands.impl;

import Commands.Command;
import SaveGame.SavedPointGame;
import Zork.Game;

public class AllSavesCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument) {
        if (argument==null){
            isArgValid = true;
            //print all game save slots
            printAllSavedGames();
        } else {
            isArgValid = false;
        }
    }
    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }
    public static void printAllSavedGames(){
        System.out.println("     \u001B[1mGAME SAVE SLOT\u001B[0m");
        System.out.println("________________________");
        System.out.println(" NAME            STATUS");
        System.out.println("________________________");
        for (String slotName: SavedPointGame.getOrder()){
            System.out.printf(" save%s         %s\n",slotName,SavedPointGame.getStatus(slotName));
        }
        System.out.println("________________________");
    }
}
