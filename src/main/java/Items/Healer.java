package Items;

public class Healer extends Item {
    public Healer(String name, int value, int useTime) {
        super(name,value,useTime);
    }
    public String getItemStat(){
        String stat = "☆"+ getName() + "☆ gives you "+ getValue() + " HP";
        return stat;
    };
}
