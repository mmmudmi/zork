package Commands.impl;

import Commands.Command;
import SaveGame.FileHandler;
import SaveGame.SavedPointGame;
import Zork.Game;

public class LoadCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument) {
        if (!game.isPlaying() && argument!=null){
            String filePath = SavedPointGame.filePath(argument);
            if (filePath!=null){
                isArgValid = true;
                if (SavedPointGame.isEmpty(argument)){
                    System.out.printf("save%s is empty, choose again\n",argument);
                } else {
                    System.out.printf("Successfully loaded!\n",argument);
                    game.setIsPlaying(true);
                    SavedPointGame.loadGame(game,filePath);
                    game.getMap().printMap();
                }
            } else {
                System.out.println("Can not find that Save Slot Name?!");
            }
        } else {
            isArgValid = false;
        }
    }
    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }
}
