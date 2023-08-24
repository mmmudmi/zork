package Commands.impl;
import Commands.Command;
import Zork.Game;

public class QuitCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument){
        if(argument == null & game.isPlaying()){
            game.printMenu();
            isArgValid = true;
        }
    }
    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }
}
