package Actors;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MonsterFactory {
    public static Monster createMonster(MonsterTypes monsterTypes) {
        try {
            return Monster.class.getDeclaredConstructor(String.class,int.class,double.class,int.class)
                    .newInstance(monsterTypes.getName(),monsterTypes.getHP(),monsterTypes.getAttackRate(),monsterTypes.getDroppedEXP());
        } catch (Exception e){
            throw new RuntimeException("Unknown Monster Type!");
        }
    }
}
