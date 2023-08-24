package Commands.impl;

import Actors.Player;
import Commands.Command;
import Items.Inventory;
import Items.Item;
import Maps.Room;
import Zork.Game;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class TakeCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument){
        if (game.isPlaying() && argument!=null){
            Player player = game.getPlayer();
            Maps.Map currentMap = game.getMap();
            Room currentRoom = currentMap.getCurrentRoom();
            Inventory currentRoomInventory = currentRoom.getRoomInventory();
            isArgValid = true;

            if (isNumeric(argument)) {
                int inputSlot = Integer.parseInt(argument);
                if (!player.getInventory().isFull()) { //not full yet
                    if (currentRoomInventory.getItems().containsKey(inputSlot)){
                        Item newItem = currentRoomInventory.getItems().get(inputSlot);
                        player.getInventory().addItem(newItem);
                        currentRoomInventory.removeItem(inputSlot);
                        System.out.printf("The %s has added to your inventory!\n", newItem.getName());
                    } else if (inputSlot > currentRoomInventory.getMaxSlot() || inputSlot <=0 ){
                        System.out.printf("SLOT %d is out of bond\n",inputSlot);
                    } else {
                        System.out.printf("SLOT %d is empty\n",inputSlot);
                    }
                } else {
                    System.out.println("Your inventory is full");
                }
            } else if (argument.equals("inventory")){
                Inventory newInventory = currentRoom.getDropInventory();
                Inventory currentInventory = player.getInventory();
                if (newInventory == null) {
                    System.out.printf("The %s does not have a dropped inventory\n",currentRoom.getName());
                } else {
                    //if the current inventory > than new one, drop some item in the current room inventory
                    if (currentInventory.getItemCount() > newInventory.getMaxSlot()) {
                        System.out.printf("You carry items more than %s's capacity (the dropped inventory). Some of your items will drop in the current room", newInventory.getInventoryName());
                        int theDifference = currentInventory.getItemCount() - newInventory.getMaxSlot() ;
                        //transfer the inventory first
                        for (int slot = theDifference ; slot == 0 ; slot ++) {
                            currentRoomInventory.addItem(currentInventory.getItems().get(slot));
                            currentInventory.removeItem(slot);
                        }
                    }
                    System.out.printf("You just swapped your inventory from %s to %s!\n",currentInventory.getInventoryName(),newInventory.getInventoryName());
                    //update swap inventory, but if the current capacity <= 2 just throw it away
                    Inventory temp = newInventory;
                    if (currentInventory.getMaxSlot() > 2){
                        newInventory.setInventoryName(currentInventory.getInventoryName());
                        newInventory.setMaxSlot(currentInventory.getMaxSlot());
                    } else {
                        currentRoom.removeDroppedInventory();
                    }
                    player.getInventory().changeInventory(temp);
                }
            } else {
                isArgValid = false;
            }

        }
    }

    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }

}
