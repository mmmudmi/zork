package Commands.impl;

import Commands.Command;
import Zork.Game;

public class MapCommand implements Command {
    private Boolean isArgValid = false;

    @Override
    public void execute(Game game, String argument) {
        if(argument == null && game.isPlaying()){
            isArgValid = true;
            game.getMap().printMap();
        }
    }

    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }

}
