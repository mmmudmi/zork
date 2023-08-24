package Maps;

import Maps.impl.HouseMap;
import Maps.impl.SchoolMap;

public enum MapType {
    SCHOOL(SchoolMap.class,2,"school",
            "    \\_/\n" +
            "  --(_)--  .\n" +
            "    / \\   /_\\\n" +
            "          |O|\n" +
            "    .-----' '-----.\n" +
            "   /____[school]___\\\n" +
            "    | [] .-.-. [] |\n" +
            "  ..|____|_|_|____|..\n\n"),
    HOUSE(HouseMap.class,1,"house",
                "       _\n" +
                    "     _|=|__________\n" +
                    "    /              \\\n" +
                    "   /     [house]    \\\n" +
                    "  /__________________\\\n" +
                    "   ||  || /--\\ ||  ||\n" +
                    "   ||[]|| | .| ||[]||\n" +
                    "   ||__||_|__|_||__||  \n" +
                    "   |-|-|-|====|-|-|-|   \n" +
                    "  ^^^^^^^^^====^^^^^^^^\n\n");

    private Class<? extends Map> mapClass;
    private String mapName;
    private int floor;
    private String art;
    MapType(Class<? extends Map> mapClass, int floor, String mapName, String art){
        this.mapClass = mapClass;
        this.mapName = mapName;
        this.floor = 2;
        this.art = art;
    }
    public Class getMapClass() { return mapClass;}
    public String getMapName() {return this.mapName;}
    public static String getMapNames(){
        String toReturn = "( ";
        int countMaps = MapType.values().length;
        int count = 1;
        for (MapType mapType : MapType.values()){
            toReturn += mapType.getMapName();
            if (count != countMaps){
                toReturn += ", ";
                count++;
            }
        }
        return toReturn + " )";
    }
    public int getFloor() {return this.floor;}
    public String getArt() {return this.art;}
    public static MapType getMapType(String mapString) {
        for (MapType mapType: MapType.values()){
            if (mapString.equals(mapType.getMapName())) {return mapType;}
        }
        return null;
    }
    public static void printMapNameWithArt(){
        for (MapType mapType : MapType.values()){
            System.out.println("-------> " + mapType.getMapName() + " <-------");
            System.out.println(mapType.getArt());
        }
    }

}



