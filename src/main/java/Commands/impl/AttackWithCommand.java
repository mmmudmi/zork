package Commands.impl;

import Actors.Monster;
import Actors.Player;
import Commands.Command;
import Items.Inventory;
import Items.Weapon;
import Maps.Room;
import Zork.Game;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class AttackWithCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument) {
        if (game.isPlaying() && isNumeric(argument)){
            isArgValid = true;
            Player player = game.getPlayer();
            Maps.Map currentMap = game.getMap();
            Room currentRoom = currentMap.getCurrentRoom();
            Monster monster = currentRoom.getMonster();
            Inventory currentInventory = player.getInventory();
            int inputSlot = Integer.parseInt(argument);
            try{ // in case we can't cast item to weapon
                Weapon usingWeapon = (Weapon) player.getInventory().getItems().get(inputSlot);
                if (usingWeapon != null){
                    if (usingWeapon.getValue() < 0) { //make sure that it's weapon (it has - value --> damage)
                        player.attack(monster,usingWeapon); //update inventory
                        if (usingWeapon.isExpired()){
                            player.getInventory().removeItem(inputSlot);
                        }
                        Monster finalBoss = currentMap.getFinalBoss();

                        if(!finalBoss.isAlive()) {
                            System.out.printf("%s (Final boss) has been killed! You win! \t※\\(^o^)/※\n",finalBoss.getName());
                            System.out.println("________________________________________________________________________");
                            game.printMenu(); //back to menu
                        } else if (monster.isAlive()){ //time for monster to fight back
                            monster.attack(player);
                            if (!player.isAlive()){
                                System.out.println("YOU DIED ༼ ༎ຶ ෴ ༎ຶ༽ GAME OVER\n");
                                System.out.println("________________________________________________________________________");
                                game.printMenu(); //back to menu
                            }
                        } else { // if dead, it won't fight back
                            currentRoom.removeMonster();
                        }

                    }

                } else if (inputSlot > currentInventory.getMaxSlot() || inputSlot <=0 ){
                    System.out.printf("SLOT %d is out of bond\n",inputSlot);
                } else {
                    System.out.printf("SLOT %d is empty\n",inputSlot);
                }

            } catch (Exception e) {
                System.out.printf("Can not use %s with \"attack with\" command\n",player.getInventory().getItems().get(inputSlot).getName());
            }
        } else {
            isArgValid = false;
        }
    }
    @Override
    public Boolean isArgValid() { return isArgValid; }
}
