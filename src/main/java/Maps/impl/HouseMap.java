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

public class HouseMap extends Map {
    String first =
            "                                                                        _-_               \n" +
            "                                                                     /~~   ~~\\           \n" +
            "     HOUSE                %::::::::::::::::::::::::::::%          /~~         ~~\\        \n" +
            "                          *                            % =* *=* *{   Treehouse   }        \n" +
            "     %::::::::::::::::::::#                            __         \\  _-     -_  /         \n" +
            "     %                    __                                           \\  /     =        \n" +
            "     %       Dining                  Kitchen                            | |      *        \n" +
            "     %                                                 __               | |      =        \n" +
            "     %                    __                           #               /   \\    *        \n" +
            "     %                    *                            #     Garden              *        \n" +
            "     %                   .*                           .%-----------------------=#=        \n" +
            "     %                   .*                           :*                        #         \n" +
            "     %                   :#---------|        |--------=*                        #         \n" +
            "     %                   :+                           :*                        #         \n" +
            "     %------|      |-----++                           :*                        #         \n" +
            "     %                   :+                           __         Bedroom        #         \n" +
            "     %                   __                                                     #         \n" +
            "     %      Bathroom                  Living          __                        #         \n" +
            "     #                   __            room           :*                        #         \n" +
            "     #                   :+                           :*                        #         \n" +
            "     #-------------------=+                           :*........................#         \n" +
            "                         :+                           :*                                  \n" +
            "                         :*.........|::::::::|........-*                                  \n";

    private Monster FinalBoss;
    private String mapName;
    private HashMap<String,String> floorMatching = new HashMap<>();
    private String currentFloor;
    //set up
        //Inventory setup
    protected final Inventory plasticBag = InventoryType.PLASTIC.getInventory();
    protected final Inventory garbageBag = InventoryType.GARBAGE.getInventory();

        //Items setup
    private final Item pencil = ItemFactory.createItem(ItemType.PENCIL);
    private final Item bottle = ItemFactory.createItem(ItemType.BOTTLE);
    private final Item extinguisher = ItemFactory.createItem(ItemType.EXTINGUISHER);
    private final Item scissor = ItemFactory.createItem(ItemType.SCISSOR);
    private final Item knife = ItemFactory.createItem(ItemType.KNIFE);
    private final Item fork = ItemFactory.createItem(ItemType.FORK);
    private final Item milk = ItemFactory.createItem(ItemType.MILK);
    private final Item apple = ItemFactory.createItem(ItemType.APPLE);
    private final Item pizza = ItemFactory.createItem(ItemType.PIZZA);
    private final Item candy = ItemFactory.createItem(ItemType.CANDY);
    private final Item chocolate = ItemFactory.createItem(ItemType.CHOCOLATE);
    private final Item keyPick = ItemFactory.createItem(ItemType.KEYPICK);
    //create first floor rooms

    private final Room living = new Room("living room", Arrays.asList(milk.duplicate(),candy.duplicate(),pencil.duplicate()) , plasticBag, MonsterFactory.createMonster(MonsterTypes.DOG));
    private final Room bedroom = new Room("Bedroom",Arrays.asList(milk.duplicate(),pencil.duplicate(),scissor.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.MAMA),true); //final boss
    private final Room kitchen = new Room("Kitchen",Arrays.asList(knife.duplicate(),pizza.duplicate(),bottle.duplicate()),garbageBag,MonsterFactory.createMonster(MonsterTypes.GRANNY));
    private final Room dining = new Room("Dining room",Arrays.asList(keyPick.duplicate(),fork.duplicate(),apple.duplicate(),extinguisher.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.BRO));
    private final Room bathroom = new Room("Bathroom",Arrays.asList(pencil.duplicate(),candy.duplicate(),keyPick.duplicate()),null,null);
    private final Room garden = new Room("Garden",Arrays.asList(scissor.duplicate(),chocolate.duplicate(),knife.duplicate(),pencil.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.KID));
    private final Room treehouse = new Room("Tree house",Arrays.asList(scissor.duplicate(),apple.duplicate(),pencil.duplicate()),null,MonsterFactory.createMonster(MonsterTypes.BIRD));

    public HouseMap(){
        floorMatching.put("first",first);
        map = first;
        currentFloor = floorMatching.get(map);
        currentRoom = living;
        FinalBoss = bedroom.getMonster();
        mapName = MapType.HOUSE.getMapName();

        //setup first floor rooms
        this.setRoomExits(living,bathroom,bedroom,kitchen,null,null,null);
        this.setRoomExits(bathroom,null,living,dining,null,null,null);
        this.setRoomExits(dining,null,kitchen,null,bathroom,null,null);
        this.setRoomExits(kitchen,dining,garden,null,living,null,null);
        this.setRoomExits(garden,kitchen,null,null,null,treehouse,null);
        this.setRoomExits(treehouse,null,null,null,null,null,garden);
        this.setRoomExits(bedroom,living,null,null,null,null,null);
    }

    public void move(Directions direction){
        currentRoom = currentRoom.getRoomAt(direction);
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
