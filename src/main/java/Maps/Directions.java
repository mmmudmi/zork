package Maps;

public enum Directions {
    NORTH("north"), WEST("west"), EAST("east"), SOUTH("south"), UP("up"), DOWN("down");
    private String directionString;
    Directions(String directionString){
        this.directionString = directionString;
    }
    public String getDirectionString() { return this.directionString; }
    public static Directions getDirectionFromString(String directionStr) {
        Directions toReturn = null;
        if (directionStr != null) {
            for (Directions directions: Directions.values()){
                if (directionStr.equals(directions.getDirectionString())){
                    toReturn = directions;
                }
            }
        }
        return toReturn;
    }
}
