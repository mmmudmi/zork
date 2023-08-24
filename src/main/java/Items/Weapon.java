package Items;

public class Weapon extends Item {
    public Weapon(String name, int value, int useTime) {
        super(name,value, useTime);
    }
    public String getItemStat(){
        String time = "times";
        if (getTimeLeft()==1) {time = "time";}
        String stat = "☆"+getName() + "☆ has a damage of ("+ getValue()
                + " x Your Level) and can be used " + getTimeLeft() + " " + time + " left";
        return stat;
    };

}
