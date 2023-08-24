package Maps;

import Actors.Monster;
import java.util.ArrayList;
import java.util.List;

public abstract class Map {
    /**Declared Variables*/
    protected List<Room> allRooms = new ArrayList<>();
    protected Room currentRoom;

    protected String map;

    /**Getter*/
    public Room getCurrentRoom(){
        return currentRoom;
    };
    public void printMap(){ System.out.println(map);};
    public List<Room> getAllRooms() { return allRooms; }

    /**Setter*/
    public void setCurrentRoom(Room room){ this.currentRoom = room;}
    protected void setRoomExits(Room name,Room west, Room east, Room north, Room south, Room up, Room down){
        name.setExits(west,east,north,south,up,down); //set up room
        this.allRooms.add(name);  // store room
    }

    /**Must be overridden*/
    public abstract void move(Directions direction);
    public abstract void setMapArt(String floor);
    public abstract Monster getFinalBoss();
    public abstract String getMapName();
    public abstract String getCurrentFloor();
}
