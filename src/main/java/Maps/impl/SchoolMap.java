package Maps.impl;
import Actors.Monster;
import Actors.MonsterFactory;
import Actors.MonsterTypes;
import Items.*;
import Maps.Directions;
import Maps.Map;
import Maps.MapType;
import Maps.Room;

import java.util.Arrays;
import java.util.HashMap;

public class SchoolMap extends Map {
    String first =
            "       SCHOOL                                                         First Floor         \n" +
            "       -::::::::::::::::::=::::::::::::::=::::::::::::::::::+:::::::::::::::::::+         \n" +
            "       +       -::-       =                                __                   =         \n" +
            "       =     -:    :.     =                                         Locker      =         \n" +
            "       =    =       =     =            Bathroom            __        room       =         \n" +
            "       =   =         =    =                                 +                   =         \n" +
            "       =   =         =    +__....|   |..=.-+::::::::::::::::::::::::|    |::::::+         \n" +
            "       =  ::         -.   =  |_         :-                                      =         \n" +
            "       +  ::         -.   =    |_       :-    *+::::::::::::::=::::::::::::=+=  =         \n" +
            "       =  .-         =    =      |_     :-    = :-           :=          .-  =  =         \n" +
            "       =   +         =    +________|    :-    =   =        -::=:-:      -:   =  =         \n" +
            "       +   ::       =.    __            __    =   ::      =  .-  -.    ::    =  =         \n" +
            "       =    -:     -.                         =   ::  Basketball Court =     =  =         \n" +
            "       =      -:::-       __ Down stair __    =   =        ::-+:-      .-    =  =         \n" +
            "       +  Swimming Pool   =   HallWay   :-    = .-           .-         .-   =  =         \n" +
            "       *::::::::::::::::::=             :-    +-:            .-           :-:=  =         \n" +
            "       =                  =             :-    -:::::::::::::::::::::::::::::-.  =         \n" +
            "       =                  =             :-                                      =         \n" +
            "       +    Teacher's     +:  :      :  :==:::::::::::::::::::*::::::::::::::::::+        \n" +
            "       =    Break room    =             ::                   =                  =         \n" +
            "       =                  =             ::                   =                  =         \n" +
            "       =                  =             ::                  __                  =         \n" +
            "       +::::::|     |:::::=             ::                          Kitchen     =         \n" +
            "       =                  =             __                  __                  =         \n" +
            "       =                  =   HallWay                        =                  =         \n" +
            "       =                  --     1      __      Cafeteria    +:::::::|    |:::::+         \n" +
            "       =                                ::                   =                  =         \n" +
            "       =       Dean       __            ::                   =                  -         \n" +
            "       =      Office      =             ::                   =                  -.        \n" +
            "       =                  =             ::                   =      Storage     -.        \n" +
            "       =              ....+...|     |...-=...                =                  :.        \n" +
            "       =              =                     =                =                  :.        \n" +
            "       =              =                     =                =                  ::        \n" +
            "       =              =       School        =::::::::::::::::-:::::::::::::::::::.        \n" +
            "       +.............:=      Entrance       =                                             \n" +
            "                     .=                     =                                             \n" +
            "                      =                     =                                             \n" +
            "                      =                     =                                             \n" +
            "                      -::::=+|      |=+:::::-                                             \n" +
            "                           .-+-=====-==                                                   \n" +
            "                            -:::::::::-                                                   \n";

    String second =
                    "    SCHOOL                                                              Second Floor      \n" +
                    "    +:::::::::::::::::::+::::::::::::::::::::::::::::::::::::=-::::::::::::::::::::+      \n" +
                    "    +                   +                                    -:                    =      \n" +
                    "    +                   __                                    -:                    =      \n" +
                    "    +      Theater                     Restroom              -:                    =      \n" +
                    "    +                   __                                    -:                    =      \n" +
                    "    *:::::::|   |:::::::*__......|   |..*::::::::::::::::::::=:                    =      \n" +
                    "    +                   =  |_           +                    ::                    =      \n" +
                    "    +                   =    |_         __                   __       Science      =      \n" +
                    "    +                   =      |_               Science                 Lab        =      \n" +
                    "    =                   =________|      __       Class       __                    =      \n" +
                    "    =                   =               +                    ::                    =      \n" +
                    "   .=     Library       =               +::::::::::::::::::::=:                    =      \n" +
                    "   .-                  __    Up stair   +                    ::                    =      \n" +
                    "   .-                        HallWay    +                    ::                    =      \n" +
                    "   :-                  __               +                    :=::::::::::::::::::::*      \n" +
                    "   :-                   =               +                                                 \n" +
                    "   :-                   =               +                                                 \n" +
                    "   .-                   =               +                                                 \n" +
                    "   .+:::::::|   |::::::::+. .:      :. .:*:::::::::::::::::::::                           \n" +
                    "   .=                   =               +                    ::                           \n" +
                    "    =                   =               +                    ::                           \n" +
                    "    =                  __               __                   ::                           \n" +
                    "    +     Computer           HallWay            English      ::                           \n" +
                    "    +       Lab        __       2       __       Class       ::                           \n" +
                    "    +                   =               +                    ::                           \n" +
                    "    +                   =               +                    ::                           \n" +
                    "    *:::::::::::::::::::+-. .:     :. .+::::::::::::::::::::::                           \n" +
                    "    +                   =               +                     -.                          \n" +
                    "    +                  __    HallWay    __                    -.                          \n" +
                    "    +                           3               History       -.                          \n" +
                    "    +    Math Class    __               __       Class        -.                          \n" +
                    "    +                   =.              +                     -.                          \n" +
                    "    +               +:::-::::|    |:::::-:::+                 -.                          \n" +
                    "    +               +                       =                 -.                          \n" +
                    "    +               +                       =-::::::::::::::::=-                          \n" +
                    "    +               +                       =                                             \n" +
                    "    =:::::::::::::::+       Art Class       =                                             \n" +
                    "                    +                       =                                             \n" +
                    "                    +                       =                                             \n" +
                    "                    +                       =                                             \n" +
                    "                    :::::::::::::::::::::::::                                             \n";



    private Monster FinalBoss;
    private String mapName;
    private HashMap<String,String> floorMatching = new HashMap<>();
    private String currentFloor;
    //set up
        //Inventory setup
    protected final Inventory backpack = InventoryType.BACKPACK.getInventory();
    protected final Inventory toteBag = InventoryType.TOTE.getInventory();
    protected final Inventory plasticBag = InventoryType.PLASTIC.getInventory();
    protected final Inventory garbageBag = InventoryType.GARBAGE.getInventory();

        //Items setup
    private final Item pencil = ItemFactory.createItem(ItemType.PENCIL);
    private final Item bottle = ItemFactory.createItem(ItemType.BOTTLE);
    private final Item extinguisher = ItemFactory.createItem(ItemType.EXTINGUISHER);
    private final Item scissor = ItemFactory.createItem(ItemType.SCISSOR);
    private final Item drawingCompass = ItemFactory.createItem(ItemType.DRAWINGCOMPASS);
    private final Item basketball = ItemFactory.createItem(ItemType.BASKETBALL);
    private final Item brokenBeaker = ItemFactory.createItem(ItemType.BEAKER);
    private final Item toxicBeaker = ItemFactory.createItem(ItemType.TOXIC);
    private final Item knife = ItemFactory.createItem(ItemType.KNIFE);
    private final Item fork = ItemFactory.createItem(ItemType.FORK);
    private final Item milk = ItemFactory.createItem(ItemType.MILK);
    private final Item apple = ItemFactory.createItem(ItemType.APPLE);
    private final Item pizza = ItemFactory.createItem(ItemType.PIZZA);
    private final Item popcorn = ItemFactory.createItem(ItemType.POPCORN);
    private final Item firstAid = ItemFactory.createItem(ItemType.FIRSTAID);
    private final Item cookie = ItemFactory.createItem(ItemType.COOKIE);
    private final Item energyDrink = ItemFactory.createItem(ItemType.ENERGYDRINK);
    private final Item candy = ItemFactory.createItem(ItemType.CANDY);
    private final Item chocolate = ItemFactory.createItem(ItemType.CHOCOLATE);
    private final Item keyPick = ItemFactory.createItem(ItemType.KEYPICK);
    //create first floor rooms

    private final Room schoolEntrance = new Room("School Entrance", null , null, null);
    private final Room deanOffice = new Room("Dean Office",Arrays.asList(pencil.duplicate(),cookie.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.DOG));
    private final Room teacherBreakRoom = new Room("Teacher's break room", Arrays.asList(firstAid.duplicate(),candy.duplicate()), null,MonsterFactory.createMonster(MonsterTypes.PRINCIPLE),true);
    private final Room hallway1 = new Room("Hallway 1",Arrays.asList(pencil.duplicate()),null,null);
    private final Room cafeteria = new Room("Cafeteria",Arrays.asList(fork.duplicate(),apple.duplicate(),milk.duplicate(),bottle.duplicate()), plasticBag,MonsterFactory.createMonster(MonsterTypes.STUDENT));
    private final Room kitchen = new Room("Kitchen",Arrays.asList(knife.duplicate()), garbageBag,MonsterFactory.createMonster(MonsterTypes.BIRD));
    private final Room storage = new Room("Storage",Arrays.asList(pizza.duplicate(),milk.duplicate()), null,MonsterFactory.createMonster(MonsterTypes.DOG),true);
    private final Room swimmingPool = new Room("Swimming Pool",Arrays.asList(energyDrink.duplicate(),candy.duplicate(),keyPick.duplicate()), toteBag, MonsterFactory.createMonster(MonsterTypes.ATHLETE));
    private final Room downStairHallway= new Room("Down Stair Hallway",null,null,null);
    private final Room basketballCourt= new Room("Basketball Court",Arrays.asList(basketball.duplicate(),energyDrink.duplicate()), null, MonsterFactory.createMonster(MonsterTypes.ATHLETE));
    private final Room bathroom = new Room("Bathroom",Arrays.asList(keyPick.duplicate(),candy.duplicate()), null,MonsterFactory.createMonster(MonsterTypes.STUDENT));
    private final Room lockerRoom = new Room("Locker Room",Arrays.asList(pencil.duplicate()),toteBag,MonsterFactory.createMonster(MonsterTypes.BIRD));
    //create second floor rooms
    private final Room artClass = new Room("Art Class",Arrays.asList(pencil.duplicate(),candy.duplicate(),scissor.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.TEACHER));
    private final Room mathClass = new Room("Math Class",Arrays.asList(drawingCompass.duplicate(),pencil.duplicate(),apple.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.TEACHER));
    private final Room historyClass = new Room("History Class",Arrays.asList(pencil.duplicate(),bottle.duplicate(),chocolate.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.TEACHER));
    private final Room englishClass = new Room("English Class", Arrays.asList(pencil.duplicate(),keyPick.duplicate()),null,null);
    private final Room hallway2 = new Room("Hallway 2",null,null,null);
    private final Room hallway3 = new Room("Hallway 3",Arrays.asList(extinguisher.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.BIRD));
    private final Room upStairHallway = new Room("Up Stair Hallway",null,null,null);
    private final Room library = new Room("Library",Arrays.asList(drawingCompass.duplicate(),pencil.duplicate(),candy.duplicate(),cookie.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.STUDENT));
    private final Room theater = new Room("Theater",Arrays.asList(popcorn.duplicate(),milk.duplicate(),chocolate.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.TEACHER));
    private final Room computerLab = new Room("Computer Lab",Arrays.asList(pizza.duplicate(),keyPick.duplicate()),backpack,MonsterFactory.createMonster(MonsterTypes.STUDENT),true);
    private final Room restroom = new Room("Restroom",Arrays.asList(keyPick.duplicate(),pencil.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.BIRD));
    private final Room scienceClass = new Room("Science Class",Arrays.asList(scissor.duplicate(),brokenBeaker.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.DOG));
    private final Room scienceLab = new Room("Science Lab",Arrays.asList(toxicBeaker.duplicate(),pencil.duplicate(),chocolate.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.DOG),true);

    public SchoolMap(){
        floorMatching.put("first",first);
        floorMatching.put("second",second);
        FinalBoss = teacherBreakRoom.getMonster();
        map = first;
        currentFloor = floorMatching.get(map);
        currentRoom = schoolEntrance;
        mapName = MapType.SCHOOL.getMapName();

        //setup first floor rooms
        this.setRoomExits(schoolEntrance,null,null,hallway1,null,null,null);
        this.setRoomExits(hallway1,deanOffice,cafeteria,downStairHallway,schoolEntrance,null,null);
        this.setRoomExits(deanOffice,null,hallway1,teacherBreakRoom,null,null,null);
        this.setRoomExits(teacherBreakRoom,null,null,null,deanOffice,null,null);
        this.setRoomExits(cafeteria,hallway1,kitchen,null,null,null,null);
        this.setRoomExits(kitchen,cafeteria,null,null,storage,null,null);
        this.setRoomExits(storage,null,null,kitchen,null,null,null);
        this.setRoomExits(downStairHallway,swimmingPool,basketballCourt,bathroom,hallway1,upStairHallway,null);
        this.setRoomExits(swimmingPool,null,downStairHallway,null,null,null,null);
        this.setRoomExits(bathroom,null,lockerRoom,null,downStairHallway,null,null);
        this.setRoomExits(lockerRoom,bathroom,null,null,basketballCourt,null,null);
        this.setRoomExits(basketballCourt,downStairHallway,null,lockerRoom,null,null,null);
        //setup second floor rooms
        this.setRoomExits(upStairHallway,library,scienceLab,restroom,hallway2,null,downStairHallway);
        this.setRoomExits(library,null,upStairHallway,theater,computerLab,null,null);
        this.setRoomExits(theater,null,restroom,null,library,null,null);
        this.setRoomExits(restroom,theater,null,null,upStairHallway,null,null);
        this.setRoomExits(computerLab,null,hallway2,library,null,null,null);
        this.setRoomExits(scienceClass,upStairHallway,scienceLab,null,null,null,null);
        this.setRoomExits(scienceLab,scienceClass,null,null,null,null,null);
        this.setRoomExits(hallway2,computerLab,englishClass,upStairHallway,hallway3,null,null);
        this.setRoomExits(englishClass,hallway2,null,null,null,null,null);
        this.setRoomExits(hallway3,mathClass,historyClass,hallway2,artClass,null,null);
        this.setRoomExits(artClass,null,null,hallway3,null,null,null);
        this.setRoomExits(historyClass,hallway3,null,null,null,null,null);
        this.setRoomExits(mathClass,null,hallway3,null,null,null,null);
    }


    public void move(Directions direction){
        currentRoom = currentRoom.getRoomAt(direction);
        if (currentRoom.equals(downStairHallway)){map = first; currentFloor = "first";}
        else if (currentRoom.equals(upStairHallway)) {map = second; currentFloor = "second";}
    }
    public String getCurrentFloor(){ return this.currentFloor; }
    public Monster getFinalBoss(){return this.FinalBoss;}
    public String getMapName(){return this.mapName;}
    public void setMapArt(String floor){
        String mapArt = floorMatching.get(floor);
        if (mapArt!=null){
            this.map = mapArt;
        }
    }


}
