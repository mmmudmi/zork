package Items;

import java.util.HashMap;
import java.util.List;

public class Inventory {
    private int maxSlot;
    private int itemCount;
    private String inventoryName;
    private HashMap<Integer,Item> items;

    public Inventory(String name, int maxSlot){
        this.inventoryName = name;
        this.itemCount = 0;
        this.maxSlot = maxSlot;
        this.items = new HashMap<>();
    }

    /**Getter*/
    public boolean isFull(){
        return itemCount == maxSlot;
    }
    public HashMap<Integer,Item> getItems(){
        return this.items;
    }
    public boolean isSlotNotEmpty(Integer slot){return this.items.containsKey(slot);}
    public String getInventoryName() {
        return this.inventoryName;
    }
    public int getMaxSlot() {
        return maxSlot;
    }
    public int getItemCount() {
        return itemCount;
    }
    public boolean isEmpty(){ return itemCount == 0;}
    public void printInventoryStat(){
        System.out.println(this.inventoryName + " (" + itemCount + "/" + maxSlot + " slots)");
        for (Integer i = 1; i<=maxSlot;i++){
            if (items.containsKey(i) && items.get(i)!=null) {
                System.out.printf("|SLOT %d| %s   \n",i,items.get(i).getItemStat());
            } else {
                System.out.printf("|SLOT %d|  -  \n",i);
            }
        }
    }


    /**Setter*/
    public void setMaxSlot(int maxSlot) {
        this.maxSlot = maxSlot;
    }
    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }
    public void addItem(Item item){
        //add to empty slot only
        if (isFull()){this.maxSlot++;}
        for (Integer slotNum = 1; slotNum<= maxSlot ; slotNum++){
            if (!items.containsKey(slotNum)){
                items.put(slotNum,item);
                this.itemCount++;
                break;
            }
        }
    }
    public void addItems(List<Item> itemList){
        if (itemList!=null){
            for (Item item: itemList){
                this.addItem(item);
            }
        }
    }
    public void useItem(int slot){
        Item usingItem = items.get(slot);
        usingItem.used();
        if (usingItem.isExpired()) {
            removeItem(slot);
        }
    }

    public void removeItem(Integer slot){
        this.items.remove(slot);
        itemCount--;
    }
    public void emptyInventory(){
        this.items = new HashMap<>();
        itemCount = 0;
    }
    public void changeInventory(Inventory newInventory) { //current size must be <= new inventory else item drop in the room
        this.inventoryName = newInventory.getInventoryName();
        this.maxSlot = newInventory.maxSlot;
        for (Item item : this.items.values()){
            newInventory.addItem(item);
        }
        this.items = newInventory.items;
    }
}
