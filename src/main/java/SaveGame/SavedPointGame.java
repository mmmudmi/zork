package SaveGame;
import Actors.Monster;
import Actors.MonsterFactory;
import Actors.MonsterTypes;
import Actors.Player;
import Items.Inventory;
import Items.InventoryType;
import Items.Item;
import Maps.Map;
import Maps.MapFactory;
import Maps.Room;
import Zork.Game;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/

public class SavedPointGame {
    private static HashMap<String,String> saveNames = new HashMap<>();
    private static List<String> order = new ArrayList<>(List.of("1","2","3","4"));
    static {
        saveNames.put("1","/Users/mudmi/Desktop/SSC/ssc-y22t3-zork-mmmudmi/src/main/java/SaveGame/1.json");
        saveNames.put("2","/Users/mudmi/Desktop/SSC/ssc-y22t3-zork-mmmudmi/src/main/java/SaveGame/2.json");
        saveNames.put("3","/Users/mudmi/Desktop/SSC/ssc-y22t3-zork-mmmudmi/src/main/java/SaveGame/3.json");
        saveNames.put("4","/Users/mudmi/Desktop/SSC/ssc-y22t3-zork-mmmudmi/src/main/java/SaveGame/4.json");
    }
    public SavedPointGame(Maps.Map map, Player player, String path){
        JSONObject info = new JSONObject();

        for (Room room: map.getAllRooms()){
            JSONObject roomDetails = new JSONObject();

            if (room.isLock()){roomDetails.put("IS LOCK","yes");}
            else {roomDetails.put("IS LOCK","no");}

            Inventory droppedInventory = room.getDropInventory();
            if (droppedInventory==null){roomDetails.put("DROPPED INVENTORY","no");}
            else { roomDetails.put("DROPPED INVENTORY",droppedInventory.getInventoryName());}

            //room inventory
            JSONObject roomInventory = new JSONObject();
            for (Item item : room.getRoomInventory().getItems().values()){
                roomInventory.put(item.getName(),item.getTimeLeft());
            }
            if (!room.getRoomInventory().isEmpty()){
                roomDetails.put("ROOM INVENTORY",roomInventory);
            }

            //monster
            Monster currentMonster = room.getMonster();
            if (currentMonster != null){
                JSONObject monster = new JSONObject();
                monster.put("NAME", currentMonster.getName());
                monster.put("HP",String.valueOf(currentMonster.getHP()));
                roomDetails.put("MONSTER",monster);
            }

            if (map.getCurrentRoom().equals(room)){
                roomDetails.put("IS CURRENT ROOM", "yes");
            } else {
                roomDetails.put("IS CURRENT ROOM", "no");
            }
            info.put(room.getName(),roomDetails);
        }

        JSONObject playerDetails = new JSONObject();
        playerDetails.put("LEVEL",player.getLEV());
        playerDetails.put("EXP",player.getEXP());
        playerDetails.put("HP",player.getHP());
        playerDetails.put("INVENTORY NAME",player.getInventory().getInventoryName());
        //player inventory
        JSONObject playerInventory = new JSONObject();
        for (Item item : player.getInventory().getItems().values()){
            playerInventory.put(item.getName(),item.getTimeLeft());
        }
        if (!player.getInventory().isEmpty()){
            playerDetails.put("INVENTORY",playerInventory);
        }
        info.put("PLAYER",playerDetails);

        info.put("MAP",map.getMapName());
        info.put("FLOOR",map.getCurrentFloor());

        FileHandler.writeOver(path,"["+info.toJSONString()+"]");
    }

    public static void loadGame(Game game, String path){
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(path)){
            //read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray infoList = (JSONArray) obj;

            infoList.forEach(info -> parseObject(game,(JSONObject) info));

        } catch (IOException | ParseException e){
            System.out.println("Can not read JSON file!");
        }
    }
    //load game helper
    private static void parseObject(Game game,JSONObject info ){
        Map map = MapFactory.createMap((String) info.get("MAP"));
        Player player = new Player();

        for (Room room: map.getAllRooms()){
            JSONObject infoObj = (JSONObject) info.get(room.getName());
            if (infoObj.get("IS LOCK").equals("yes")){ room.setLockStatus(true); }

            String droppedInventory = infoObj.get("DROPPED INVENTORY").toString();
            if (!droppedInventory.equals("no")){ room.setDroppedInventory(droppedInventory);}
            else {room.setDroppedInventory(null);}

            JSONObject roomInventory = (JSONObject) infoObj.get("ROOM INVENTORY"); //map-like Map<name,usage>
            room.getRoomInventory().emptyInventory();
            if (roomInventory!=null){
                for (Object itemName: roomInventory.keySet()){
                    Item item = Item.getItemFromStr((String) itemName);
                    item.setTimeLeft(Integer.parseInt(roomInventory.get(itemName).toString()));
                    room.getRoomInventory().addItem(item);
                }
            }

            JSONObject monsterDetail = (JSONObject) infoObj.get("MONSTER"); //map-like
            room.removeMonster();
            if (monsterDetail!=null){
                Monster monster = MonsterFactory.createMonster(MonsterTypes.getMonsterType(monsterDetail.get("NAME").toString()));
                monster.setHP(Integer.parseInt(monsterDetail.get("HP").toString()));
                room.replaceMonster(monster);
            }

            if (infoObj.get("IS CURRENT ROOM").equals("yes")){ map.setCurrentRoom(room); }
        }

        JSONObject playerInfoObj = (JSONObject) info.get("PLAYER");
        player.setLEV(Integer.parseInt(playerInfoObj.get("LEVEL").toString()));
        player.setEXP(Integer.parseInt(playerInfoObj.get("EXP").toString()));
        player.setHP(Integer.parseInt(playerInfoObj.get("HP").toString()));
        Inventory inventory = InventoryType.getInventory(playerInfoObj.get("INVENTORY NAME").toString());
        if (inventory!=null){
            player.getInventory().changeInventory(inventory);
        }

        JSONObject playerInventory = (JSONObject) playerInfoObj.get("INVENTORY"); //map-like Map<name,usage>
        player.getInventory().emptyInventory();
        if (playerInventory!=null){
            for (Object itemName: playerInventory.keySet()){
                Item item = Item.getItemFromStr((String) itemName);
                item.setTimeLeft(Integer.parseInt(playerInventory.get(itemName).toString()));
                player.getInventory().addItem(item);
            }
        }

        String floor = (String) info.get("FLOOR");
        if (floor!=null){
            map.setMapArt(floor.toString());
        }


        game.mapSetUp(map);
        game.setPlayer(player);
    }

    public static List<String> getOrder(){
        return order;
    }
    public static String filePath(String saveName){
        return saveNames.get(saveName);
    }
    public static boolean isEmpty(String saveName){
        return FileHandler.isEmptyFile(saveNames.get(saveName));
    }
    public static void emptyFile(String saveName){
        FileHandler.emptyFile(saveNames.get(saveName));
    }
    public static HashMap<String,String> getAllSaveSlots(){
        return saveNames;
    }
    public static String getStatus(String saveName){
        if (isEmpty(saveName)){return " empty~ ";}
        return "occupied";
    }


}
