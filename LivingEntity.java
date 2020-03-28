public abstract class LivingEntity extends Entity {
    //an entity is something which fights, such as a monster or hero.

    private int level, totalHealth, currentHealth;

    public LivingEntity(String n, int l){
        super(n);
        setLevel(l);
        resetHealth();
    }


    //getters and setters

    public int getTotalHealth(){return totalHealth;}

    public int getCurrentHealth(){return currentHealth;}

    public int getLevel(){return level;}

    public void setTotalHealth(int hp){totalHealth = hp;}

    public void setCurrentHealth(int hp){currentHealth = hp;}

    public void setLevel(int l){level = l;}


    //change the current life total, damage is inputted as a negative number and healing is represented as a positive
    public void offsetLife(int change){
        setCurrentHealth(getCurrentHealth()+change);
    }

    //reset the total and current health to 100 * the current level
    public void resetHealth(){
        setTotalHealth(100*getLevel());
        setCurrentHealth(getTotalHealth());
    }

    public abstract boolean isDodged();

    public abstract int regularDamage();

    public abstract void receiveDamage(int dam);
}
