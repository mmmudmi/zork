package Commands.impl;

import Actors.Monster;
import Actors.Player;
import Commands.Command;
import Items.Inventory;
import Maps.Room;
import Zork.Game;

import java.util.Map;

public class InfoCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument) {
        if (argument == null && game.isPlaying() ){
            Player player = game.getPlayer();
            Maps.Map currentMap = game.getMap();
            Room currentRoom = currentMap.getCurrentRoom();
            Inventory droppedInventory = currentRoom.getDropInventory();
            //COLOR ADDED TO FONTS
            String end = "\u001B[0m";
            String yellow = "\u001b[33m";
            String blue = "\u001b[35m";
            String cyan = "\u001b[36m";
            String red = "\u001b[31m";
            String green = "\u001b[32m";
            isArgValid = true;

            /**PLAYER INFO*/
            System.out.println(cyan+"PLAYER INFO"+end);
            player.printStat();
            System.out.println(cyan+"PLAYER INVENTORY"+end);
            player.getInventory().printInventoryStat();

            /**ROOM INFO*/
            System.out.println(yellow+"CURRENT ROOM INVENTORY"+end);
            currentRoom.getRoomInventory().printInventoryStat();
            if(droppedInventory != null){
                System.out.printf("%s with capacity of %d is dropped in the room. Want it? use \"take inventory\" command!\n",droppedInventory.getInventoryName(),droppedInventory.getMaxSlot());
            }
            System.out.println(yellow+"AVAILABLE MOVE"+end);
            currentRoom.printExitInfo();
            System.out.println(yellow+"MONSTER"+end);
            Monster monster = currentRoom.getMonster();
            if (monster!=null) {
                monster.printStat();
                if (currentMap.getFinalBoss().equals(monster)) {
                    System.out.println("This is Final Boss ヽ(｀Д´)ﾉ Kill it to win the game!");
                }
            } else {
                System.out.println("no monster 三三ᕕ( ᐛ )ᕗ");
            }

        }
    }

    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }

}
