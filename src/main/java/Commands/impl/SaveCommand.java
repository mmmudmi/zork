package Commands.impl;

import Commands.Command;
import SaveGame.SavedPointGame;
import Zork.Game;

public class SaveCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument) {
        if (game.isPlaying() && argument != null){
            isArgValid = true;
            String filePath = SavedPointGame.filePath(argument);
            if (filePath!=null ){
                if (!SavedPointGame.isEmpty(argument)){
                    System.out.printf("save%s already saved other game, use \"remove <save slot number>\" command to remove the existing save\n",argument);
                }else {
                    SavedPointGame savedPointGame = new SavedPointGame(game.getMap(),game.getPlayer(),filePath);
                    System.out.printf("Successfully saved your game in save%s\n",argument);
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
