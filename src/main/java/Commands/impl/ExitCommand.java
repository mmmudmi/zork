package Commands.impl;
import Commands.Command;
import Zork.Game;

public class ExitCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument){
        if(argument == null & !game.isPlaying()){
            game.exit();
            isArgValid = true;
        }

    }
    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }
}
