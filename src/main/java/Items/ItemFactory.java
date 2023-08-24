package Items;

public class ItemFactory {
    public static Item createItem(ItemType itemType){
        try {
            return itemType.getItemClass()
                    .getDeclaredConstructor(String.class, int.class, int.class)
                    .newInstance(itemType.getName(),itemType.getValue(),itemType.getUseTime());
        } catch (Exception e){
            throw new RuntimeException("Unknown Item Type!");
        }
    }
}
