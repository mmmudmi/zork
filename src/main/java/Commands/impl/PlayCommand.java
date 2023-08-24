package Commands.impl;
import Commands.Command;
import Maps.Map;
import Maps.MapFactory;
import Zork.Game;

public class PlayCommand implements Command {
    private Boolean isArgValid = false;; //check if arg is valid
    @Override
    public void execute(Game game, String argument) {
        Map inputMap = MapFactory.createMap(argument);
        if ( inputMap != null && !game.isPlaying()) {
            game.mapSetUp(inputMap);
            game.setPlayer();
            isArgValid = true;
            inputMap.printMap();
        }
    }

    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }


}
