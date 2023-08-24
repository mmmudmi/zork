package Commands;

import Zork.Game;

public interface Command {
    public void execute(Game game, String argument);
    public Boolean isArgValid();;

}
