package Actors;

public abstract class Actor {
    private boolean alive = true;
    private int maxHP; //need to be set
    private int HP; //need to be set


    /**Getter*/
    public boolean isAlive() { return this.alive; }
    protected int getMaxHP() { return this.maxHP; }
    public int getHP() { return this.HP; }


    /**Setter*/
    protected void setDead() { alive = false; setHP(0); }
    protected void setMaxHP(int maxHP) { this.maxHP = maxHP; }
    public void setHP(int HP) { this.HP = HP; }
    public void updateHP(int value){ //the value can be - (weapon) + (healer)
        int newHP = getHP() + value;
        if (newHP <= 0) { setDead(); }
        else if (newHP > maxHP) { setHP(maxHP); }
        else { setHP(newHP); }
    }


    /**Need to be Overridden*/
    public abstract void printStat();


}
