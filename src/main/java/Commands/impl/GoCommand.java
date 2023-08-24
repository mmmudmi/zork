package Commands.impl;

import Actors.Player;
import Commands.Command;
import Maps.Directions;
import Zork.Game;
import Maps.Room;
import static Maps.Directions.getDirectionFromString;

public class GoCommand implements Command{
    private Room newRoom;
    private Maps.Map currentMap;
    private Player player;
    private Boolean isArgValid = false;



    @Override
    public void execute(Game game, String argument) {
        Directions inputDir = getDirectionFromString(argument);
        if (inputDir!=null && game.isPlaying()){
            currentMap = game.getMap();
            player = game.getPlayer();
            if (currentMap.getCurrentRoom().canMoveTo(inputDir)){
                isArgValid = true;
                newRoom = currentMap.getCurrentRoom().getExits().get(inputDir);
                if (newRoom.isLock()) {//if the door is lock
                    System.out.printf("Opps! the %s's door is lock. Use a keyPick to unlock the door\n",newRoom.getName());
                } else {
                    game.playerMove(inputDir);
                }
            } else {
                System.out.println("There is no door! Can't go that way!");
            }
        } else {
            isArgValid = false;
        }
    }

    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }
//get dir return Directions else nul


}
