package Items;

public class KeyPick extends Item{
    public KeyPick(String name, int value, int useTime) {
        super(name,value, useTime);
    }
    public String getItemStat(){
        return "☆key pick☆ used to unlock any door, can be used time only";
    };
}
