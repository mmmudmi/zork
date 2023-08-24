package Commands.impl;

import Commands.Command;
import SaveGame.SavedPointGame;
import Zork.Game;

public class RemoveCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument) {
        if (argument != null){
            isArgValid = true;
            String filePath = SavedPointGame.filePath(argument);
            if (filePath!=null ){
                if (!SavedPointGame.isEmpty(argument)){
                    SavedPointGame.emptyFile(argument);
                    System.out.printf("Successfully remove save%s from your save\n", argument);
                }else {
                    System.out.printf("%s is empty, nothing to be deleted\n",argument);
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
