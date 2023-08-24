package Actors;
import java.util.*;

public class Monster extends Actor{
    private String name;
    private int droppedEXP;
    private double attackRate;
    public Monster(String name, int HP, double attackRate, int droppedEXP){
        this.name = name;
        this.droppedEXP = droppedEXP;
        this.attackRate = attackRate;
        setHP(HP);
        setMaxHP(HP);
    }

    /**Getter*/
    public String getName(){return this.name;}
    public int getDroppedEXP(){ return this.droppedEXP; }
    @Override
    public void printStat() { System.out.printf("%s [%d/%d]HP\n",getName(),getHP(),getMaxHP()); }


    /**Setter*/
    public void attack(Actor player) {
        HashMap<String,Integer> attackMoves = MonsterTypes.getAttackMoves();
        List<String> keys = attackMoves.keySet().stream().toList();
        int attackCount = attackMoves.size();
        Random random = new Random();
        int randomNumber = random.nextInt(attackCount);
        String attackMove = keys.get(randomNumber);
        int damage = (int) (attackMoves.get(attackMove) * attackRate);
        System.out.printf("%s attacked you %s which causes %d damage\n",getName(),attackMove,damage);
        player.updateHP(damage);
    }
}
