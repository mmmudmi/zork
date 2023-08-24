package Commands.impl;

import Actors.Player;
import Commands.Command;
import Items.Inventory;
import Items.Item;
import Maps.Room;
import Zork.Game;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class UseCommand implements Command {
    private Boolean isArgValid = false;
    @Override
    public void execute(Game game, String argument){
        if (isNumeric(argument) && game.isPlaying()){
            isArgValid = true;
            Player player = game.getPlayer();
            Inventory currentInventory = player.getInventory();
            int inputSlot = Integer.parseInt(argument);
            Item usingItem = player.getInventory().getItems().get(inputSlot);
            if (usingItem != null){
                if (usingItem.getValue() > 0) {
                    player.updateHP(usingItem.getValue());
                    currentInventory.useItem(inputSlot); //update inventory
                    String sign = "+";
                    if (usingItem.getValue()<=0){sign = "";}
                    System.out.printf("%s gives %s %d HP\n", usingItem.getName(),sign,usingItem.getValue());
                } else {
                    System.out.printf("Can not use %s with \"use\" command\n",usingItem.getName());
                }
            } else if (inputSlot > currentInventory.getMaxSlot() || inputSlot <=0 ){
                System.out.printf("SLOT %d is out of bond\n",inputSlot);
            } else {
                System.out.printf("SLOT %d is empty\n",inputSlot);
            }
        }
    }

    @Override
    public Boolean isArgValid() {
        return isArgValid;
    }

}
