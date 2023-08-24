package Actors;

import java.util.HashMap;

public enum MonsterTypes {
    DOG("zombie doggie",4, 250, 200),
    BIRD("bird zombie",3.6 , 200, 160),
    STUDENT("student zombie",5.5 , 560, 300),
    GRANNY("granny zombie",5 , 1000, 300),
    MAMA("mommy zombie",6 , 2000, 450), //final boss
    KID("kiddo zombie",4.5 , 600, 260),
    BRO("big bro zombie",5.3 , 800, 300),
    TEACHER("teacher zombie",5, 700, 310),
    ATHLETE("athlete zombie",6 , 800, 350),
    PRINCIPLE("principle zombie",6 , 3000, 1000); //final boss

    private String name;
    private double attackRate;
    private int HP;
    private int droppedEXP;

    MonsterTypes(String name,double attackRate,int HP,int droppedEXP){
        this.name = name;
        this.attackRate = attackRate;
        this.HP = HP;
        this.droppedEXP = droppedEXP;
    }

    public String getName() { return name; }

    public double getAttackRate() { return attackRate; }

    public int getHP() { return HP; }

    public int getDroppedEXP() { return droppedEXP; }
    public static MonsterTypes getMonsterType(String name){
        for (MonsterTypes monsterTypes: MonsterTypes.values()){
            if (monsterTypes.getName().equals(name)){return monsterTypes;}
        }
        return null;
    }
    private static HashMap<String,Integer> attackMoves = new HashMap<>();
    static {
        attackMoves.put("by biting your neck",-60);
        attackMoves.put("by biting your leg",-50);
        attackMoves.put("by biting your arm",-40);
        attackMoves.put("by biting your toe",-30);
        attackMoves.put("by scratching you",-20);
        attackMoves.put("but missed",0);
//        attackMoves.put("by biting your neck",-50);
//        attackMoves.put("by biting your leg",-50);
//        attackMoves.put("by biting your arm",-50);
//        attackMoves.put("by biting your toe",-50);
//        attackMoves.put("by scratching you",-50);
    }
    public static HashMap<String,Integer> getAttackMoves(){
        return attackMoves;
    }

}
