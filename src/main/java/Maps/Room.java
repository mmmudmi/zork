package Maps;
import java.util.HashMap;
import java.util.List;

import Actors.Monster;
import Items.Inventory;
import Items.InventoryType;
import Items.Item;

public class Room {
    private String name;
    private HashMap<Directions, Room> exits = new HashMap<>();
    private Inventory roomInventory;
    private Inventory dropInventory; //bag
    private Monster monster;
    private boolean lock;


    //At most 1 monster/room
    public Room(String name, List<Item> itemList, Inventory dropInventory, Monster monster, Boolean lock) {
        this.name = name;
        this.dropInventory = dropInventory;
        this.monster = monster;
        this.lock = lock;
        roomInventory = new Inventory(name +" inventory",5); //starter slot
        roomInventory.addItems(itemList);
    }
    public Room(String name, List<Item> itemList, Inventory dropInventory, Monster monster) { //if not state lock means unlock
      this(name,itemList,dropInventory,monster,false);
    }


    /**Getter*/
    public boolean isLock() { return lock;}
    public Inventory getDropInventory() { return dropInventory;}
    public Inventory getRoomInventory() { return roomInventory; }
    public String getName() { return name;}
    public HashMap<Directions, Room> getExits(){ return exits;}
    public boolean canMoveTo(Directions direction){ return exits.containsKey(direction);}
    public Room getRoomAt(Directions direction){ return exits.get(direction);}
    public void printExitInfo(){
        for (Directions direction : Directions.values() ){
            if (exits.containsKey(direction)){
                System.out.printf("%s : %s\n",direction.toString(),exits.get(direction).name);
            }
        }
    }
    public Monster getMonster(){ return this.monster;}



    /**Setter*/
    public void setLockStatus(boolean status){ this.lock = status; }
    public void removeMonster() { this.monster = null; }
    public void removeDroppedInventory() { this.dropInventory = null;}
    public void setDroppedInventory(String inventory){ this.dropInventory = InventoryType.getInventory(inventory);}
    public void replaceMonster(Monster monster){this.monster = monster;}
    //all directions
    public void setExits(Room west,Room east, Room north, Room south, Room up, Room down){
        if (west!=null) { exits.put(Directions.WEST,west);}
        if (east!=null) { exits.put(Directions.EAST,east);}
        if (north!=null) { exits.put(Directions.NORTH,north);}
        if (south!=null) { exits.put(Directions.SOUTH,south);}
        if (up!=null) { exits.put(Directions.UP,up);}
        if (down!=null) { exits.put(Directions.DOWN,down);}
    }

}
