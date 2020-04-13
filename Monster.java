
public abstract class Monster extends LivingEntity{

    private int damage;
    private int defense;
    private int dodge;



    private int lane;
    private static final double DIFFICULTY = 0.02;

    //private int monstInt = -1;

    public Monster(String n, int l, int da, int de, int dod) {
        super(n, l);
        setDamage(da);
        setDefense(de);
        setDodge(dod);
    }
    public String toString(){
        return getName()+ ":"+
                "\n\t Level = \t" + getLevel() +
                "\n\t Total Health =\t" + getTotalHealth() +
                "\n\t Current Health =\t" + getCurrentHealth() +
                "\n\t Damage =\t" + (int)(getDamage()*DIFFICULTY) +
                "\n\t Defense =\t" + (int)(getDefense()*DIFFICULTY) +
                "\n\t Dodge\t= " + getDodge();
    }
    public int getDamage() { return damage;}

    public int getDefense(){return defense;}

    public int getDodge(){return dodge;}

    public void setDamage(int d){damage = d;}

    public void setDefense(int d){defense = d;}

    public void setDodge(int d){dodge = d;}

    public String getName(){
        return ANSI_RED+super.getName()+ANSI_RESET;
    }
    public boolean isDodged(){
        return Math.random()*100<dodge;
    }

    public int getLane() {
        return lane;
    }

    public void setLane(int lane) {
        this.lane = lane;
    }

    //public int getMonstInt() {
     //   return monstInt;
    //}


    //public void setMonstInt(int monstInt) {
     //   this.monstInt = monstInt;
    //}

    //public boolean hasMonstInt(){
     //   return monstInt != -1;
    //}
    @Override
    public int regularDamage() {
        return (int) (damage*DIFFICULTY);
    }

    @Override
    public void receiveDamage(int dam) {
        int damage;
        if(getDefense()*DIFFICULTY<=dam){
            damage = (int) (dam - getDefense()*DIFFICULTY);
        }else{
            damage=0;
        }
        System.out.println(getCurrentHealth());
        setCurrentHealth(getCurrentHealth()-damage);
        System.out.println(getCurrentHealth());
    }

}
