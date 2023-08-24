package Items;

public abstract class Item {
    private String name;
    private int value;
    private int timeLeft; //how many time item can be used
    public Item(String name, int value, int timeLeft){
        this.name = name;
        this.value = value;
        this.timeLeft = timeLeft;
    }

    /**Getter*/
    public boolean isExpired(){ return this.timeLeft <= 0;}
    public int getTimeLeft() { return timeLeft; }
    public String getName() { return name;}
    public int getValue() { return value; }
    public static Item getItemFromStr(String itemStr){
        for (ItemType itemType: ItemType.values()){
            if (itemType.getName().equals(itemStr)) {return ItemFactory.createItem(itemType);}
        }
        return  null;
    }

    /**Setter*/
    public void used(){ this.timeLeft -= 1;}
    public void setTimeLeft(int timeLeft) { this.timeLeft = timeLeft;}
    public String copyItem(){
        String toReturn = "";
        toReturn += getName() + "\n";
        toReturn += getTimeLeft();
        return toReturn;
    }
    public Item duplicate(){
        return ItemFactory.createItem(ItemType.getItemType(this.name));
    }

    /**Need to be Overridden*/
    public abstract String getItemStat();

}
