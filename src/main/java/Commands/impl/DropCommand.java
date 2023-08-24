package Commands.impl;

import Actors.Player;
import Commands.Command;
import Items.Inventory;
import Items.Item;
import Zork.Game;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class DropCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument){
        if (isNumeric(argument) && game.isPlaying()) {
            Player player = game.getPlayer();
            Inventory currentMapInventory = game.getMap().getCurrentRoom().getRoomInventory();
            int inputSlot = Integer.parseInt(argument);
            isArgValid = true;
            if (player.getInventory().isSlotNotEmpty(inputSlot)) { //no item to drop at an empty slot
                Item droppedItem = player.getInventory().getItems().get(inputSlot);
                System.out.printf("You have dropped %s. It's now in the current room inventory\n", droppedItem.getName());
                player.getInventory().removeItem(inputSlot);
                //put dropped item back to the room
                if (currentMapInventory.isFull()){
                    int newMaxSlot = currentMapInventory.getMaxSlot()+2;
                    currentMapInventory.setMaxSlot(newMaxSlot); //if full put 2 more spaces
                }
                currentMapInventory.addItem(droppedItem);
                //player.getInventory().printInventoryStat();
            } else {
                System.out.printf("There is nothing to drop in SLOT %d ¯\\_(ツ)_/¯\n",inputSlot);
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
