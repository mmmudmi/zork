package Items;

public enum ItemType {
    PENCIL(Weapon.class,"pencil",-100, 2),
    SCISSOR(Weapon.class,"scissor",-220, 2),
    BOTTLE(Weapon.class,"stainless steel water bottle",-160, 2),
    EXTINGUISHER(Weapon.class,"fire extinguisher",-220, 2),
    BEAKER(Weapon.class,"broken beaker",-230, 1),
    DRAWINGCOMPASS(Weapon.class,"drawing compass",-150, 1),
    TOXIC(Weapon.class,"toxic beaker",-270, 1),
    KNIFE(Weapon.class,"small knife",-350, 2),
    BASKETBALL(Weapon.class,"basketball",-120, 1),
    FORK(Weapon.class,"fork",-200, 2),
    MILK(Healer.class,"strawberry milk",50,1),
    APPLE(Healer.class,"apple",50,1),
    POPCORN(Healer.class,"popcorn",130,1),
    CHOCOLATE(Healer.class,"chocolate bar",100,1),
    ENERGYDRINK(Healer.class,"energy drink",160,1),
    PIZZA(Healer.class,"pizza",200,1),
    CANDY(Healer.class,"candy",25,1),
    FIRSTAID(Healer.class,"first aid kit",500,1),
    COOKIE(Healer.class,"cookie",100,1),
    KEYPICK(KeyPick.class,"key pick",0,1);

    /** Characteristics shared by each Item type*/
    private Class<? extends Item> itemClass;
    private String name;
    private int value;
    private int useTime;
    ItemType(Class<? extends Item> itemClass,String name,int value, int useTime){
        this.itemClass = itemClass;
        this.name = name;
        this.value = value;
        this.useTime = useTime;
    }
    public int getUseTime() { return useTime;}
    public Class<? extends Item> getItemClass() {
        return itemClass;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
    public static ItemType getItemType(String name){
        for (ItemType itemType: ItemType.values()){
            if (itemType.getName().equals(name)){
                return itemType;
            }
        }
        return null;
    }
}
