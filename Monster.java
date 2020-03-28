
public abstract class Monster extends LivingEntity{

    private int damage, defense, dodge;
    private static final double DIFFICULTY = 0.02;
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
