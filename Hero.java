import java.util.ArrayList;

public abstract class Hero extends LivingEntity{

    private int totalMana, currentMana, strength, agility, dexterity, money, currentExperience;
    private Weaponry weapon;
    private Armor armor;



    private final int BACKPACKSIZE = 30;
    private ArrayList<Item> backpack = new ArrayList<Item>();

    @Override
    public String toString() {
        return getName()+ ":"+
                "\n\t Level = \t" + getLevel() +
                "\n\t Total Health =\t" + getTotalHealth() +
                "\n\t Current Health =\t" + getCurrentHealth() +
                "\n\t Total Mana =\t"+ getTotalMana() +
                "\n\t Current Mana\t= " + getCurrentMana() +
                "\n\t Strength =\t" + getStrength() +
                "\n\t Agility =\t" + getAgility() +
                "\n\t Dexterity\t= " + getDexterity() +
                "\n\t Money =\t" + getDexterity() +
                "\n\t Experience =\t" + getCurrentExperience()+"/" +(10*getLevel())+
                "\n\t Backpack =\t" + getBackpack().toString();
    }

    public Hero(String n, int l, int tm, int s, int a, int dex, int mo, int ex){
        super(n,l);
        setTotalMana(tm);
        setCurrentMana(getTotalMana());
        setStrength(s);
        setAgility(a);
        setDexterity(dex);
        setMoney(mo);
        setCurrentExperience(ex);
    }

    //getters and setters

    public ArrayList<Item> getBackpack() {
        return backpack;
    }

    public void displayBackpack(){
        int i = 1;
        for(Item it : backpack){
            System.out.println(i++ +": "+it);
        }
    }

    public void setBackpack(ArrayList<Item> backpack) {
        this.backpack = backpack;
    }

    public Weaponry getWeapon() {
        return weapon;
    }

    public void setWeapon(Weaponry weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public int getCurrentMana(){return currentMana;}

    public int getTotalMana(){return totalMana;}

    public int getStrength(){return strength;}

    public int getAgility(){return agility;}

    public int getDexterity(){return dexterity;}

    public int getMoney(){return money;}

    public int getCurrentExperience(){return currentExperience;}

    public void setCurrentMana(int i){currentMana = i;}

    public void setTotalMana(int i){totalMana = i;}

    public void setStrength(int i){strength = i;}

    public void setAgility(int i){agility = i;}

    public void setDexterity(int i){dexterity = i;}

    public void setMoney(int i){money = i;}

    public void setCurrentExperience(int i){currentExperience = i;}

    // levels up the hero for non abstract desendents of this class,
    // this method should be overwritten to include the favored stats
    // increases.
    public void LevelUp(){
        setLevel(getLevel()+1);
        resetHealth();
        setTotalMana((int) Math.round(getTotalMana()*1.1));
        setCurrentMana(getTotalMana());
        setStrength((int) Math.round(getStrength()*1.05));
        setAgility((int) Math.round(getAgility()*1.05));
        setDexterity((int) Math.round(getDexterity()*1.05));
        setCurrentExperience(getCurrentExperience()%(10*getLevel()));
    }

    public boolean isReadyToLevelUp(){
        return getCurrentExperience() >= 10*getLevel();
    }

    public boolean isDodged(){
        return Math.random()*100 < getAgility()*0.02;
    }

    @Override
    public void receiveDamage(int dam) {
        int damage;
        if(armor!=null){
            if(armor.getDamageReduction()<dam){
                damage = dam - armor.getDamageReduction();
            }else{
                damage=0;
            }
        }else{
            damage = dam;
        }
        setCurrentHealth(getCurrentHealth()-damage);
    }

    @Override
    public int regularDamage() {
        int damage;
        if(weapon !=null){
            damage = (int) ((getStrength()+weapon.getDamage())*0.05);
        }else{
            damage = (int) (getStrength()*0.05);
        }
        return damage;
    }
}
