package Commands.impl;

import Actors.Player;
import Commands.Command;
import Maps.Directions;
import Maps.Room;
import Zork.Game;
import static Maps.Directions.getDirectionFromString;


public class UnlockCommand implements Command {
    private Room newRoom;
    private Maps.Map currentMap;
    private Player player;
    private Boolean isArgValid = false;

    @Override
    public void execute(Game game, String argument) {
        Directions inputDir = getDirectionFromString(argument);
        if (inputDir!=null && game.isPlaying()) {
            player = game.getPlayer();
            Integer keySlot = player.keyPickIsAt();
            isArgValid = true;
            currentMap = game.getMap();
            newRoom = currentMap.getCurrentRoom().getExits().get(inputDir);
            if (keySlot == null) {
                System.out.println("You do not have a key!");
            } else if (!newRoom.isLock()) {
                System.out.printf("%s is already unlock\n",newRoom.getName());
            } else {
                System.out.printf("the %s's door is now unlock!\n",newRoom.getName());
                newRoom.setLockStatus(false);
                player.getInventory().removeItem(keySlot);
            }
        }



    }

    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }
}
