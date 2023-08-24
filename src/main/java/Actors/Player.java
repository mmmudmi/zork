package Actors;

import Items.Inventory;
import Items.Item;
import Items.KeyPick;
import Items.Weapon;

public class Player extends Actor {
    private Inventory inventory ;
    private int EXP;
    private int MAX_EXP;
    private int LEV;
    public Player() {
        setHP(1000);
        setMaxHP(1000);
        this.EXP = 0;
        this.MAX_EXP = 300;
        this.LEV = 1; //+1LEV every 300 EXP reached, attack rate is also LEV/2
        inventory = new Inventory("jeans pocket",2);
    }

    /**Getter*/
    public int getEXP() {return this.EXP;}
    public Inventory getInventory(){
        return this.inventory;
    }
    public int getMAX_EXP() {return this.MAX_EXP;}
    public int getLEV() {return this.LEV;}

    //return null if can't find the keyPick, else return # slot that has a keyPick
    public Integer keyPickIsAt(){
        Integer toReturn = null;
        for (Integer slotNum: inventory.getItems().keySet()){
            if (inventory.getItems().get(slotNum).getClass() == KeyPick.class){
                return slotNum;
            }
        }
        return toReturn;
    }
    @Override
    public void printStat() {
        System.out.printf("LEVEL %d           ",getLEV());
        int barCount = (int) (getEXP()*10/getMAX_EXP());
        for (int i = 0 ; i<10 ; i++){
            if (i < barCount) { System.out.printf("█");}
            else { System.out.printf("_");}
        }
        System.out.printf(" %d/%d EXP          [%d/%d]HP\n", getEXP(),getMAX_EXP(),getHP(),getMaxHP());
    }


    /**Setter*/
    public void setEXP(int EXP) { this.EXP = EXP;}
    public void setLEV(int LEV) { this.LEV =  LEV;}
    public void attack(Monster monster, Weapon weapon) {
        int damage = (int) weapon.getValue() * this.LEV;
        weapon.used(); // reduce amount of time can use the weapon
        monster.updateHP(damage);
        if(monster.isAlive()) {
            System.out.printf("You have used %s to attack the %s with %d damage! but it still alive\n",weapon.getName(),monster.getName(),damage);
        } else {
            System.out.printf("You have used %s to kill the %s to death!\n",weapon.getName(),monster.getName(),damage);
            int droppedEXP = monster.getDroppedEXP();
            System.out.printf("You have gain +%d XP\n",droppedEXP);
            updateEXP_LEV(droppedEXP);

        }
    }
    public void updateEXP_LEV(int EXP){
        int difference = (this.EXP+EXP) - getMAX_EXP();
        if (difference >= 0){ // level up
            this.LEV += 1;
            this.EXP = difference;
            System.out.printf("✿LEVEL UP ─=≡Σ((( つ◠‿◠)つ level %s -> level %s !\n",this.LEV-1,this.LEV);
        } else {
            this.EXP += EXP;
        }
    }
}
