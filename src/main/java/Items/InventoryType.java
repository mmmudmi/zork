package Items;

import java.lang.reflect.InvocationTargetException;

public enum InventoryType {
    BACKPACK("backpack", 7),
    TOTE("tote bag", 4),
    PLASTIC("plastic bag", 3),
    GARBAGE("garbage bag", 5);

    private String name;
    private int maxSlot;
    InventoryType (String name,int maxSlot){
        this.name = name;
        this.maxSlot = maxSlot;
    }

    public String getName() {
        return name;
    }
    public int getMaxSlot() { return maxSlot; }
    public static Inventory getInventory(String inventoryName){
        for (InventoryType inventoryType : InventoryType.values()){
            if(inventoryType.getName().equals(inventoryName)){
                return new Inventory(inventoryType.getName(), inventoryType.getMaxSlot());
            }
        }
        return null;
    }
    public Inventory getInventory(){ //Inventory Factory
//        return new Inventory(this.name,this.maxSlot);
        try {
            return Inventory.class.getDeclaredConstructor(String.class,int.class)
                    .newInstance(this.getName(),this.getMaxSlot());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println("Can't create an inventory");
        }
        return null;
    }

}
