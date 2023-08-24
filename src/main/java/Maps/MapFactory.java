package Maps;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class MapFactory {

    public static Maps.Map createMap(String mapString){
        MapType mapType = MapType.getMapType(mapString);
            try {
                if (mapType == null) {return null;}
                return (Maps.Map) mapType.getMapClass().getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
    }
}
